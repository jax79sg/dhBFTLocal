package local.bft.dh.dhbftlocal;
import java.util.ArrayList;

public class BFTLocalPreferences {
    private ArrayList<String> floors = new ArrayList<String>();
    private int currentFloor=0;
    private double onePixelToMetres=(11.82/1400*250/100);
    private double mapScale=250; //1cm to 250cm

    public String getMqHost() {
        return mqHost;
    }

    public void setMqHost(String mqHost) {
        this.mqHost = mqHost;
    }

    private String mqHost="ws://jax79sg.hopto.org:15674/ws";

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String topic="/exchange/bfttracksExchange/bfttracks";
    public String getMqUsername() {
        return mqUsername;
    }

    public void setMqUsername(String mqUsername) {
        this.mqUsername = mqUsername;
    }

    public String getMqPassword() {
        return mqPassword;
    }

    public void setMqPassword(String mqPassword) {
        this.mqPassword = mqPassword;
    }

    private String mqUsername="jax";
    private String mqPassword="password";

    public String getBfthost() {
        return bfthost;
    }

    public void setBfthost(String bfthost) {
        this.bfthost = bfthost;
    }

    private String bfthost="jax79sg.hopto.org";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name="Xperia";

    public BFTLocalPreferences()
    {
        //Ground floor up
        floors.add(0,"lealfet-avatar-deck3rd.html");
        floors.add(1,"lealfet-avatar-deck2nd.html");
        floors.add(2,"lealfet-avatar-decktween.html");
        floors.add(3,"lealfet-avatar-maindeck.html");
        floors.add(4,"lealfet-avatar-deck2.html");
        floors.add(5,"lealfet-avatar-deck3.html");
        floors.add(6,"lealfet-avatar-deck4.html");
        floors.add(7,"lealfet-avatar-deck6.html");
        floors.add(8,"lealfet-avatar-deck7.html");
        floors.add(9,"lealfet-avatar-deckplatform.html");
        floors.add(10,"lealfet-avatar-lateral.html");
    }

    private int getOneFloorUp()
    {
        if ((currentFloor+1)<floors.size())
        {
            currentFloor=currentFloor+1;
        }
        return currentFloor;
    }

    private int getOneFloorDown()
    {
        if (currentFloor>0)
        {
            currentFloor=currentFloor-1;
        }
        return currentFloor;
    }

    public String getNextFloorUp()
    {
        return floors.get(getOneFloorUp());
    }

    public String getNextFloorDown()
    {
        return floors.get(getOneFloorDown());
    }

    public String getOverview()
    {
        return floors.get(floors.size()-1);
    }

    public double getMetresFromPixels(double pixels)
    {
        double metres=pixels*onePixelToMetres;
        return metres;
    }
}
