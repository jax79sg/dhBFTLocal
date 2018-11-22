package sg.gov.dh.beacons.estimote;

import android.app.Activity;
import android.content.Context;
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
import sg.gov.dh.beacons.Beacon;
import sg.gov.dh.beacons.BeaconListener;
import sg.gov.dh.beacons.Beacons;

public class EstimoteBeacon implements Beacons {

    private ProximityObserver.Handler proximityObserverHandler;
    EstimoteCloudCredentials cloudCredentials =null;
    String APPID="ong-xin-cai-s-proximity-fo-hm6";
    String APPTOKEN="35cd4baf21cdcc9feee14d0fb028c36d";
    private BeaconListener listener;
    double minDist = 3.0;
    Activity context = null;


    public EstimoteBeacon(){
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

        ProximityZone zone = new ProximityZoneBuilder()
                .forTag(APPID)
                .inCustomRange(minDist)
                .onContextChange(new Function1<Set<? extends ProximityZoneContext>, Unit>() {
                    @Override
                    public Unit invoke(Set<? extends ProximityZoneContext> contexts) {

                        List<ProximityContent> nearbyContent = new ArrayList<>(contexts.size());

                        for (ProximityZoneContext proximityContext : contexts) {
                            String title = proximityContext.getAttachments().get(APPID+"/title");
                            if (title == null) {
                                title = "unknown";
                            }
                            String subtitle = Utils.getShortIdentifier(proximityContext.getDeviceId());

                            nearbyContent.add(new ProximityContent(title, subtitle));
                            listener.onNewUpdate(new Beacon(title));
                        }


                        return null;
                    }
                })
                .build();

        proximityObserverHandler = proximityObserver.startObserving(zone);
    }
}
