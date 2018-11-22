package sg.gov.dh.beacons.estimote;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory;
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.api.ProximityObserver;
import com.estimote.proximity_sdk.api.ProximityObserverBuilder;
import com.estimote.proximity_sdk.api.ProximityZone;
import com.estimote.proximity_sdk.api.ProximityZoneBuilder;
import com.estimote.proximity_sdk.api.ProximityZoneContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import sg.gov.dh.beacons.BeaconObject;
import sg.gov.dh.beacons.BeaconListener;
import sg.gov.dh.beacons.BeaconManagerInterface;

public class EstimoteBeaconManager implements BeaconManagerInterface {

    private ProximityObserver.Handler proximityObserverHandler;
    EstimoteCloudCredentials cloudCredentials =null;
    String APPID="ong-xin-cai-s-proximity-fo-hm6";
    String APPTOKEN="35cd4baf21cdcc9feee14d0fb028c36d";
    private BeaconListener listener;
    double minDist = 3.0;
    Activity context = null;
    String TAG = "EstimoteBeaconManager";


    public EstimoteBeaconManager(){
        this.context=context;
    }

    @Override
    public void setParentContext(Activity context) {
        this.context=context;
    }

    @Override
    public void setAppId(String id) {
        APPID=id;
    }

    @Override
    public void setAppToken(String token) {
        APPTOKEN=token;
    }

    @Override
    public void setDistActivate(double dist) {
        this.minDist=dist;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setBeaconListener(BeaconListener listener) {
        this.listener = listener;

    }

    @Override
    public void deactivate() {
        proximityObserverHandler.stop();
    }

    @Override
    public void setup() {
        cloudCredentials = new EstimoteCloudCredentials(APPID, APPTOKEN);

        RequirementsWizardFactory
                .createEstimoteRequirementsWizard()
                .fulfillRequirements(context,
                        new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                Log.d("app", "requirements fulfilled");
                                Toast.makeText(context.getApplicationContext(), "Estimote requirements satisfied",Toast.LENGTH_LONG);
                                return null;
                            }
                        },
                        new Function1<List<? extends Requirement>, Unit>() {
                            @Override
                            public Unit invoke(List<? extends Requirement> requirements) {
                                Log.e("app", "requirements missing: " + requirements);
                                return null;
                            }
                        },
                        new Function1<Throwable, Unit>() {
                            @Override
                            public Unit invoke(Throwable throwable) {
                                Log.e("app", "requirements error: " + throwable);
                                return null;
                            }
                        });

        ProximityObserver proximityObserver = new ProximityObserverBuilder(context.getApplicationContext(), cloudCredentials)
                .onError(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        Log.e("app", "proximity observer error: " + throwable);
                        return null;
                    }
                })
                .withBalancedPowerMode()
                .build();

        Log.d(TAG, "Setting up proximity zone for a range of " + minDist + " metres");
        ProximityZone zone = new ProximityZoneBuilder()
                .forTag(APPID)
                .inCustomRange(minDist)
                .onContextChange(new Function1<Set<? extends ProximityZoneContext>, Unit>() {
                    @Override
                    public Unit invoke(Set<? extends ProximityZoneContext> contexts) {
                        Log.d(TAG,"Detected beacon(s)!");

                        List<EstimoteProximityContent> nearbyContent = new ArrayList<>(contexts.size());
                        Log.d(TAG,"Detected number of beacons == " + contexts.size());
                        for (ProximityZoneContext proximityContext : contexts) {
                            String title = proximityContext.getAttachments().get(APPID+"/title");
                            if (title == null) {
                                title = "unknown";
                            }
                            String subtitle = EstimoteUtils.getShortIdentifier(proximityContext.getDeviceId());

                            nearbyContent.add(new EstimoteProximityContent(title, subtitle));
                            listener.onNewUpdate(new BeaconObject(title));
                        }


                        return null;
                    }
                })
                .build();

        proximityObserverHandler = proximityObserver.startObserving(zone);
    }
}
