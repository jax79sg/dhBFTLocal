package local.bft.dh.dhbftlocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.text.DecimalFormat;

import dh.gov.sg.mq.rabbitmq.MQRabbit;
import sg.gov.dh.trackers.Coords;
import sg.gov.dh.trackers.Event;
import sg.gov.dh.trackers.NavisensLocalTracker;
import sg.gov.dh.trackers.TrackerListener;

public class MainActivity extends AppCompatActivity {

    //Somehow need to think of a way to make these 2 variables eternal
    double currentHeight=0.0;
    double mapHeight=0.0;
    double newHeight=0.0;
    double currentOffset=0.0;



    MQRabbit mqRabbit = null;
    NavisensLocalTracker tracker=null;
    WebView myWebView=null;
    BFTLocalPreferences prefs =null;
    String TAG = "BFTLOCAL";
    private void initTracker()
    {
        this.tracker = new NavisensLocalTracker(this);
        this.tracker.setTrackerListener(new TrackerListener() {
            @Override
            public void onNewCoords(Coords coords) {

                Log.d(TAG,"X:"+coords.getX());
                Log.d(TAG,"Y:"+coords.getY());
                Log.d(TAG,"Z:"+coords.getAltitude());
                Log.d(TAG,"bearing:"+coords.getBearing());
                Log.d(TAG,"Action:"+coords.getAction());

                updateMap(coords);
                sendCoords(coords);
                showCoords(coords);
            }

            @Override
            public void onNewEvent(Event event) {

            }
        });
    }


    private void runTracker()
    {

    }

    private void showCoords(Coords coords) {
        final EditText textXYZ = findViewById(R.id.textXYZ);
        final EditText textBearing = findViewById(R.id.textBearing);
        final EditText textAction = findViewById(R.id.textAction);
        DecimalFormat df2dec = new DecimalFormat("###.##");
        textXYZ.setText("XYZ: " + df2dec.format(coords.getX())+"  ,  "+df2dec.format(coords.getY())+"  ,  "+df2dec.format(coords.getAltitude()));
        textBearing.setText("Bearing: "+df2dec.format(coords.getBearing()));
        textAction.setText("Action: " + coords.getAction());

    }

    private void updateMap(Coords coords) {
        // Android to Javascript
        String message = coords.getX()+","+coords.getY()+","+coords.getAltitude()+","+coords.getBearing()+","+this.prefs.getName()+","+ coords.getAction();
        Log.d(TAG,"Calling JAVASCRIPT with " + message);
        myWebView.evaluateJavascript("javascript: " +"androidToJSupdateLocation(\""+message+"\")", null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        initTracker();
        prefs= new BFTLocalPreferences(this);
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

        setupMessageQueue();
        runTracker();



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
            Log.d(TAG, "Connection to MQ is successful: " + isSuccess);
        }
    }

    private void sendCoords(Coords _coords)
    {

        try {
            mqRabbit.sendMessage(_coords.getX()+","+_coords.getY()+","+_coords.getAltitude()+","+_coords.getBearing()+","+prefs.getName()+","+_coords.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        }


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
}
