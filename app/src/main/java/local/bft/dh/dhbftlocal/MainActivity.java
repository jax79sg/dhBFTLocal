package local.bft.dh.dhbftlocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;

import dh.gov.sg.mq.rabbitmq.MQRabbit;
import sg.gov.dh.trackers.Coords;
import sg.gov.dh.trackers.Event;
import sg.gov.dh.trackers.NavisensLocalTracker;
import sg.gov.dh.trackers.TrackerListener;

public class MainActivity extends AppCompatActivity {



    String TAG = "BFTLOCAL";
    private void startTracker()
    {
        DHBFTLocalApplication.tracker = new NavisensLocalTracker(this);
        DHBFTLocalApplication.tracker.setTrackerListener(new TrackerListener() {
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
        String message = coords.getX()+","+coords.getY()+","+coords.getAltitude()+","+coords.getBearing()+","+DHBFTLocalApplication.pref.getName()+","+ coords.getAction();
        Log.d(TAG,"Calling JAVASCRIPT with " + message);
        DHBFTLocalApplication.myWebView.evaluateJavascript("javascript: " +"androidToJSupdateLocation(\""+message+"\")", null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DHBFTLocalApplication.myWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = DHBFTLocalApplication.myWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        DHBFTLocalApplication.myWebView.setWebChromeClient(new WebChromeClient());
        DHBFTLocalApplication.myWebView.loadUrl("file:///android_asset/mobile/" + DHBFTLocalApplication.pref.getOverview());
        DHBFTLocalApplication.myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");


        final ImageButton butUpdeck = findViewById(R.id.upButton);
        butUpdeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DHBFTLocalApplication.myWebView.loadUrl("file:///android_asset/mobile/"+ DHBFTLocalApplication.pref.getNextFloorUp());
            }
        });

        final ImageButton butDowndeck = findViewById(R.id.downButton);
        butDowndeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DHBFTLocalApplication.myWebView.loadUrl("file:///android_asset/mobile/"+ DHBFTLocalApplication.pref.getNextFloorDown());
            }
        });

        final ImageButton zeroButtonDeck = findViewById(R.id.zeroButton);
        zeroButtonDeck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DHBFTLocalApplication.myWebView.loadUrl("file:///android_asset/mobile/"+ DHBFTLocalApplication.pref.getOverview());
            }
        });


        startTracker();
        setupMessageQueue();
    }

    private void setupMessageQueue() {

        String host=DHBFTLocalApplication.pref.getBfthost();
        if (DHBFTLocalApplication.mqRabbit != null) {
            Log.w(TAG, "You already have a Rabbit running, killing previous queue and restarting another");
            DHBFTLocalApplication.mqRabbit.close();
        }
        else
        {
            DHBFTLocalApplication.mqRabbit = new MQRabbit();
            Log.d(TAG, "Connecting to MQ on " +host);
            boolean isSuccess = DHBFTLocalApplication.mqRabbit.connect(host, DHBFTLocalApplication.pref.getMqUsername(), DHBFTLocalApplication.pref.getMqPassword());
            Log.d(TAG, "Connection to MQ is successful: " + isSuccess);
        }
    }

    private void sendCoords(Coords _coords)
    {

        try {
            DHBFTLocalApplication.mqRabbit.sendMessage(_coords.getX()+","+_coords.getY()+","+_coords.getAltitude()+","+_coords.getBearing()+","+DHBFTLocalApplication.pref.getName()+","+_coords.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
