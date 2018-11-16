package sg.gov.dh.trackers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import com.navisens.motiondnaapi.MotionDna;
import com.navisens.motiondnaapi.MotionDnaApplication;
import com.navisens.motiondnaapi.MotionDnaInterface;
import java.util.Map;

/**
 * The NAVISENS implementation of the Tracker Interface and NAVISENS's own API interface<br>
 * Note that this requires 'com.navisens:motiondnaapi:1.4.2' as dependancy in Gradle script.
 * Note: This only support local coordinate system
 * @see Tracker
 */

public class NavisensLocalTracker implements MotionDnaInterface, Tracker {

    /**
     * Do not change this.
     */
    private static final String DEVELOPER_KEY = "r1NHUejpHZSyf2tXHaTLzcfxJ1nFpWh12QRvxV9XaDVHVs8l0BqzYHJjYPyYFIKh";

    /**
     * For logging purposes only
     */
    private static final String TAG = "NAVISENS_LOCAL_TRACKER";

    /**
     * Will not accept GPS updates if the radial distance error of the GPS update is more than this value in metres
     */
    private static float ACCEPTABLE_ERROR_RADIAL_DISTANCE_METRES=(float)1.0;

    /**
     * Update rate of the NAVISENS tracker in ms.
     */
    private static final double UPDATERATE_MS= 500.00;


    /**
     * Activity passed by the parent class (E.g. Map Provider's LocationDataSource)
     */
    private Activity context=null;



    public void setActive(boolean active) {
        isActive = active;
    }


    private boolean isActive = false;

    /**
     * Navisens core API that the Navisens Tracker depends on
     */
    MotionDnaApplication motionDnaApp;

    /**
     * The listener which would be assigned when setTrackerListener(TrackerListener listener) is called by parent class(E.g. Map Provider's LocationDataSource)
     */
    private TrackerListener listener;


    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * This listener is used to trigger parent class<br>
     * {@inheritDoc}
     * @param listener
     */
    public void setTrackerListener(TrackerListener listener) {
        Log.d(TAG,"Setting up listener");
        this.listener = listener;
    }

    /**
     * This will release the Navisens API<br>
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        try {
            motionDnaApp.stop();
        } catch (Exception e)
        {
            Log.e(TAG,e.getMessage());
        }
        setActive(false);

    }

    /**
     * Disable regular GPS updates, and manually set a set of given coordinates to the NAVISENS API.<br>
     * {@inheritDoc}
     * @param coords
     */
    @Override
    public void setManualLocation(Coords coords) {

        motionDnaApp.setCartesianPositionXY(coords.getX(),coords.getY());
        motionDnaApp.setLocalHeading(coords.getBearing());
    }

    /**
     * Enable regular GPS updates<br>
     * {@inheritDoc}
     */
    @Override
    public void setGPSLocation() {

    }

    /**
     * This feature is unnecessary in Navisens<br>
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        Log.i(TAG, "This feature is unnecessary in Navisens");
    }

    @Override
    public void stopGPSUpdate() {

    }

    @Override
    public void startGPSUpdate() {

    }


    /**
     * WARNING: NAVISENS uses the Right Hand Coordinate System. Meaning
     *                        X
     *                        ^
     *                        |
     *                        |
     *                        |
     *                        |
     *                        |
     * Y <---------------------
     * From X=0,Y=0,Deg=0, Walk straight: X increase
     * From X=0,Y=0,Deg=0, Turn right then walk straight: Degree=-90, Y decreases
     * From X=0,Y=0,Deg=0, Turn left then walk straight: Degree=90, Y increases
     * From X=0,Y=0,Deg=0, Turn around then walk straight: Degree=180/-180, X decreases
     * This constructor will be replaced with NavisensLocalTracker(Activity context, boolean useLastKnownLoc)
     * The constructor accepts a Activity class. This is needed to provide backdoor access to the UI. (E.g. Toast)<br>
     * Constructor will always get a initial location update via last known GPS, if any.<br>
     * It will then initialise the NAVISENS API core. After constructor completes, NAVISENS should be providing regular location updates.
     * @param context
     */
    public NavisensLocalTracker(Activity context){


        Log.d(TAG,"Initialising Navisens Integrator");
        this.listener = null;
        this.context=context;
        Log.d(TAG,"Checking for strict mode");
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            Log.d(TAG,"Enabling strict mode access");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Log.d(TAG,"Loading Navisens");
        motionDnaApp = new MotionDnaApplication(this);

        motionDnaApp.runMotionDna(DEVELOPER_KEY);
        Log.i(TAG,"Running Navisens SDK version " + MotionDnaApplication.checkSDKVersion() + " on " + motionDnaApp.getDeviceModel());
        Log.d(TAG,"Setting update rate to " + UPDATERATE_MS + " ms");
        motionDnaApp.setCallbackUpdateRateInMs(UPDATERATE_MS);
        Log.d(TAG,"Setting power consumption to performance");
        motionDnaApp.setPowerMode(MotionDna.PowerConsumptionMode.PERFORMANCE);
        motionDnaApp.resetLocalEstimation();
        motionDnaApp.resetLocalHeading();
        motionDnaApp.setBinaryFileLoggingEnabled(false);
    }


    /**
     * This is called whenever NAVISENS API core receives a new location update.<br>
     * NAVISENS provides 2 types of locations, a global lat long if its initialised with a lat long, and a local XY that starts from 0,0.<br>
     * Call listener.onNewCoords to pass the coordinates to the parent class
     * {@inheritDoc}
     * @param motionDna Not to worry about this, handled internally by NAVISENS
     * @see TrackerListener
     */
    @Override
    public void receiveMotionDna(MotionDna motionDna) {
        Log.d(TAG,"Received update from Navisens Tracker");
        MotionDna.Location loc = motionDna.getLocation();
        String motionType=motionDna.getMotion().motionType.name();
        String locStatus=loc.locationStatus.name();
        MotionDna.XYZ localLocation=loc.localLocation;
        double x = localLocation.x;
        double y = localLocation.y;
        double z = localLocation.z;
        double localHeading = loc.localHeading;
        String verticalMotion = loc.verticalMotionStatus.name();


        Log.i(TAG,"X:"+x + " Y:"+y + " Z:"+z + " Heading:" + localHeading + " locStatus:"+ locStatus + "VerticalMotion:" + verticalMotion + " EstimatedMotion:" + motionType);
        listener.onNewCoords(new Coords(0,0,z,localHeading,(float)loc.uncertainty.x,(float)loc.uncertainty.y, (float)loc.absoluteAltitudeUncertainty, x, y, motionType));

    }

    /**
     * Optional: Only if using NAVISENS network mode.
     */
    @Override
    public void receiveNetworkData(MotionDna motionDna) {

    }

    /**
     * Optional: Only if using NAVISENS network mode.
     */
    @Override
    public void receiveNetworkData(MotionDna.NetworkCode networkCode, Map<String, ?> map) {

    }

    /**
     * Optional: if implement, please add Events.
     */
    @Override
    public void reportError(MotionDna.ErrorCode errorCode, String s) {
        Log.d(TAG, "reportError: " + s);
    }

    /**
     * Required to pass Activity context to NAVISENS
     */
    @Override
    public Context getAppContext() {
        return this.context;
    }

    /**
     * Optional
     */
    @Override
    public PackageManager getPkgManager() {
        return this.getPkgManager();
    }

}
