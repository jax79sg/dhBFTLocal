package local.bft.dh.dhbftlocal;

import java.util.ArrayList;
import java.util.HashMap;
import bft.utils.Coords;
import sg.gov.dh.beacons.DroppedBeacon;

public class BeaconZeroing {

    static String BEACONOBJ="BEACONDROP";
    static String BEACONREQ="BEACONREQUEST";
    HashMap<String, DroppedBeacon> droppedBeacons = new HashMap();

    public void dropBeacon(Coords coords, String beaconId)
    {
        droppedBeacons.put(beaconId,new DroppedBeacon(coords,beaconId));
    }

    public DroppedBeacon getBeacon(String beaconId)
    {
        return droppedBeacons.get(beaconId);
    }

    public ArrayList<DroppedBeacon> getAllDroppedbeacons()
    {
        return new ArrayList<DroppedBeacon>(droppedBeacons.values());
    }

}
