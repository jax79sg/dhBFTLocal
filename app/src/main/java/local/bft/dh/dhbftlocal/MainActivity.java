package local.bft.dh.dhbftlocal;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sg.gov.dh.mq.MQListener;
import sg.gov.dh.mq.rabbitmq.MQRabbit;
import sg.gov.dh.beacons.BeaconObject;
import sg.gov.dh.beacons.BeaconListener;
import sg.gov.dh.beacons.BeaconManagerInterface;
import sg.gov.dh.beacons.DroppedBeacon;
import sg.gov.dh.beacons.estimote.EstimoteBeaconManager;
import sg.gov.dh.utils.Coords;
import sg.gov.dh.trackers.Event;
import sg.gov.dh.trackers.NavisensLocalTracker;
import sg.gov.dh.trackers.TrackerListener;
import sg.gov.dh.utils.FileSaver;

public class MainActivity extends AppCompatActivity {

    //Somehow need to think of a way to make these 2 variables eternal
    double currentHeight=0.0;
    double mapHeight=0.0;
    double newHeight=0.0;
    double currentOffset=0.0;
    String SOUND_BEACON_DETECT="to-the-point.mp3";
    String SOUND_BEACON_DROP="drop.mp3";
    MQRabbit mqRabbit;
    FileSaver fs;
    NavisensLocalTracker tracker;
    WebView myWebView;
    BFTLocalPreferences prefs ;
    BeaconManagerInterface beaconManager;
    BeaconZeroing beaconZeroing ;
    String TAG = "BFTLOCAL";
    private void initTracker()
    {
        this.tracker = new NavisensLocalTracker(this);
        this.tracker.setTrackerListener(new TrackerListener() {
            @Override
            public void onNewCoords(Coords coords) {

//                Log.d(TAG,"X:"+coords.getX());
//                Log.d(TAG,"Y:"+coords.getY());
//                Log.d(TAG,"Z:"+coords.getLocalAltitude());
//                Log.d(TAG,"bearing:"+coords.getLocalBearing());
//                Log.d(TAG,"Action:"+coords.getAction());
//                Log.d(TAG,"RealAlt:" + coords.getLatitude());

                updateMap(coords);
//                sendCoords(coords);
//                saveCoords(coords);
                showCoords(coords);
            }

            @Override
            public void onNewEvent(Event event) {

            }
        });
        Toast.makeText(this.getApplicationContext(),"Tracker setup complete",Toast.LENGTH_LONG).show();
    }

    private void saveCoords(Coords _coords) {
        if (fs!=null) {
            String pattern = "yyyyMMddHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            try {
                fs.write(_coords.getX() + "," + _coords.getY() + "," + _coords.getLocalAltitude() + "," + _coords.getLocalBearing() + "," + prefs.getName() + "," + _coords.getAction() + "," + _coords.getLatitude() + "," + date);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,"FileSaver failed to save coords");
            }
        }
    }


    private void showCoords(Coords coords) {
        final EditText textXYZ = findViewById(R.id.textXYZ);
        final EditText textBearing = findViewById(R.id.textBearing);
        final EditText textAction = findViewById(R.id.textAction);
        DecimalFormat df2dec = new DecimalFormat("###.##");
        DecimalFormat df6dec = new DecimalFormat("###.########");
        textXYZ.setText("XYZ: " + df2dec.format(coords.getX())+"  ,  "+df2dec.format(coords.getY())+"  ,  "+df2dec.format(coords.getLocalAltitude()) + "  ,  "+ df6dec.format(coords.getLatitude())+"  ,  "+df6dec.format(coords.getLongitude())+"  ,  "+df2dec.format(coords.getGlobalAltitude()));
        textBearing.setText("Bearing: "+df2dec.format(coords.getLocalBearing())+ "  ,  " +df2dec.format(coords.getGlobalBearing()));
        textAction.setText("Action: " + coords.getAction()+","+coords.getVerticalAction());

    }

    private void updateMap(Coords coords) {
        // Android to Javascript
        String message = coords.getX()+","+coords.getY()+","+coords.getLocalAltitude()+","+coords.getLocalBearing()+","+this.prefs.getName()+","+ coords.getAction();
        Log.d(TAG,"Calling JAVASCRIPT with " + message);
        myWebView.evaluateJavascript("javascript: " +"androidToJSupdateLocation(\""+message+"\")", null);

    }

    private void updateMapOfBeacon(Coords coords, String beaconId) {
        // Android to Javascript
        String message = coords.getX()+","+coords.getY()+","+coords.getLocalAltitude()+","+coords.getLocalBearing()+","+beaconId+","+ BeaconZeroing.BEACONOBJ;
        Log.d(TAG,"Calling JAVASCRIPT with " + message);
        myWebView.evaluateJavascript("javascript: " +"androidToJSupdateLocation(\""+message+"\")", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        prefs= new BFTLocalPreferences(this);
        initBeacon();
        initTracker();


        myWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl("file:///android_asset/mobile/" + prefs.getOverview());
        myWebView.addJavascriptInterface(new WebAppInterface(this, prefs, tracker), "Android");


        final ImageButton butUpdeck = findViewById(R.id.upButton);
        butUpdeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myWebView.loadUrl("file:///android_asset/mobile/"+ prefs.getNextFloorUp());
            }
        });

        final ImageButton butDowndeck = findViewById(R.id.downButton);
        butDowndeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myWebView.loadUrl("file:///android_asset/mobile/"+ prefs.getNextFloorDown());
            }
        });

        final ImageButton zeroButtonDeck = findViewById(R.id.zeroButton);
        zeroButtonDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myWebView.loadUrl("file:///android_asset/mobile/"+ prefs.getOverview());
            }
        });

        final ImageButton beaconButton = findViewById(R.id.beaconButton);
        beaconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                killmyself();
            }
        });

//        setupMessageQueue();
//        setupFileSaver();


    }

    private void killmyself() {
        this.finishAndRemoveTask();
    }

    private void setupFileSaver() {
        try {
            fs = new FileSaver(this.getApplicationContext(),prefs.getLogLocation());
            if (fs!=null) {
                Toast.makeText(this.getApplicationContext(), "Logging to "+prefs.getLogLocation(), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"FileSaver cannot initialise");
            Toast.makeText(this.getApplicationContext(),"FileSaver cannot initialise",Toast.LENGTH_LONG).show();
        }
    }

    private void placeBeacon() {
        Coords coords = this.tracker.getCurrentXYZLocation();
        this.beaconZeroing.dropBeacon(coords,"ice");
        this.beaconZeroing.dropBeacon(coords,"mint");
        this.beaconZeroing.dropBeacon(coords,"coconut");
        this.beaconZeroing.dropBeacon(coords,"blueberry");
    }

    private void placeBeacon(String beaconId) {
        Coords coords = this.tracker.getCurrentXYZLocation();
        this.beaconZeroing.dropBeacon(coords, beaconId);
        updateMapOfBeacon(coords,beaconId);
        sendBeacon(coords,beaconId);
        Log.d(TAG,"Placed Beacon ID " + beaconId + " on " + coords.getX() + ","+coords.getY()+","+coords.getLocalAltitude());
        Toast.makeText(this.getApplicationContext(),"Placed Beacon ID " + beaconId + " on " + coords.getX() + ","+coords.getY()+","+coords.getLocalAltitude(),Toast.LENGTH_LONG).show();
        try {
            playAudio(SOUND_BEACON_DROP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBeacon() {
        beaconZeroing = new BeaconZeroing();
        beaconManager = new EstimoteBeaconManager(this);
        beaconManager.setBeaconListener(new BeaconListener() {
            @Override
            public void onNewUpdate(BeaconObject beacon) {
                Log.d(TAG,"Detected beacon with ID: " + beacon.getId());
                DroppedBeacon droppedBeacon = beaconZeroing.getBeacon(beacon.getId());
                if (droppedBeacon!=null)
                {
                    Log.d(TAG,"Beacon " + beacon.getId() +  " is recognized, zeroing location");
                    Coords coord = droppedBeacon.getCoords();
//                    coord.setAltitude(tracker.getCurrentXYZLocation().getAltitude()); //Effectively ignoring the alt info from beacon
                    coord.setBearing(tracker.getCurrentXYZLocation().getLocalBearing());
                    tracker.setManualLocation(coord);

                    try {
                        playAudio(SOUND_BEACON_DETECT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.d(TAG,"Beacon " + beacon.getId() +  " is NOT recognized, skipping");
                }

            }
        });
        beaconManager.setAppId(prefs.getBeaconAppId());
        beaconManager.setAppToken(prefs.getBeaconToken());
        beaconManager.setDistActivate(prefs.getBeaconActivateDistance());
        beaconManager.setup();
        Toast.makeText(this.getApplicationContext(),"Beacon setup complete",Toast.LENGTH_LONG).show();
    }

    private void playAudio(String audioName) throws IOException {
        AssetFileDescriptor afd = getAssets().openFd(audioName);
        MediaPlayer player = new MediaPlayer();
        player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
        player.prepare();
        player.start();
    }

    private void setupMessageQueue() {

        String host=prefs.getBfthost();
        if (mqRabbit != null) {
            Log.w(TAG, "You already have a Rabbit running, killing previous queue and restarting another");
            mqRabbit.close();
        }
        else
        {
            mqRabbit = new MQRabbit();

            Log.d(TAG, "Connecting to MQ on " +host);
            boolean isSuccess = mqRabbit.connect(host, prefs.getMqUsername(), prefs.getMqPassword());
            if (isSuccess)
            {
                Toast.makeText(this.getApplicationContext(),"RabbitMQ setup complete",Toast.LENGTH_SHORT).show();
                setupMQListener();
            }else
            {
                Toast.makeText(this.getApplicationContext(),"RabbitMQ failed. C2 capabilities disabled",Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, "Connection to MQ is successful: " + isSuccess);
        }
    }

    /**
     * This will setup a MQ listener for requests to mq send all beacons known to this device.
     */
    private void setupMQListener()
    {
        mqRabbit.setListener(new MQListener() {
            @Override
            public void onNewMessage(String message) {
                String[] messageArray = message.split(",");
                String action = messageArray[5];
                if (action.equals(BeaconZeroing.BEACONREQ))
                {
                    Log.d(TAG,"Loading Beacons to send");
                    ArrayList<DroppedBeacon> droppedBeaconsList = beaconZeroing.getAllDroppedbeacons();
                    for (int i=0;i<droppedBeaconsList.size();i++)
                    {
                        Log.d(TAG,"BEACON SEND");
                        sendBeacon(droppedBeaconsList.get(i).getCoords(),droppedBeaconsList.get(i).getId());
                    }

                }
                else  if (action.equals(BeaconZeroing.BEACONOBJ))
                {
                    Log.d(TAG,"Add this beacon in if its not here");
                    String x = messageArray[0];
                    String y = messageArray[1];
                    String z = messageArray[2];
                    String bearing = messageArray[3];
                    String beaconId = messageArray[4];
                    if (beaconZeroing.getBeacon(beaconId)==null)
                    {
                        Log.d(TAG,"Beacon not here, adding it");
                        beaconZeroing.dropBeacon(new Coords(0.0, 0.0, Double.valueOf(z), Double.valueOf(bearing), 0,0, 0, Double.valueOf(x), Double.valueOf(y), ""), beaconId);
                    }
                }
            }
        });
    }

    private void sendCoords(Coords _coords)
    {
        try {
            String pattern = "yyyyMMddHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            mqRabbit.sendMessage(_coords.getX()+","+_coords.getY()+","+_coords.getLocalAltitude()+","+_coords.getLocalBearing()+","+prefs.getName()+","+_coords.getAction()+","+_coords.getLatitude()+","+date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendBeacon(Coords _coords, String beaconId)
    {
        try {
            mqRabbit.sendMessage(_coords.getX()+","+_coords.getY()+","+_coords.getLocalAltitude()+","+_coords.getLocalBearing()+","+beaconId+","+BeaconZeroing.BEACONOBJ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        if (fs!=null){
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settingsMenu) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onPause() {
        super.onPause();
        beaconManager.disableForegroundDispatch();
    }

    public void onResume() {
        super.onResume();
        beaconManager.enableForegroundDispatch();
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d(TAG,"NFC: New Intent") ;
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            Log.d(TAG, "NFC: RawMsg is " + rawMsgs);
            if (rawMsgs != null) {
                for (int i = 0; i < rawMsgs.length; i++) {
                    NdefMessage msg = (NdefMessage) rawMsgs[i];
                    String beaconId = beaconManager.getBeaconIdbByNFC(msg);
                    Log.d(TAG,"NFC: Received Beacon ID " + beaconId);
                    placeBeacon(beaconId);
                }
            }
            return;
//        }
    }


}
