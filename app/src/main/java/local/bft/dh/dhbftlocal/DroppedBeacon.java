package local.bft.dh.dhbftlocal;

import sg.gov.dh.trackers.Coords;

public class DroppedBeacon {
    String beaconId = null;
    Coords coords = null;

    public DroppedBeacon(Coords coords, String beaconId)
    {
        setBeaconId(beaconId);
        setCoords(coords);
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

}
