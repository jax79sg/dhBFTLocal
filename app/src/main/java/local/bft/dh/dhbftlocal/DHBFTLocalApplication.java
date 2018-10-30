package local.bft.dh.dhbftlocal;

import android.app.Application;
import android.webkit.WebView;

import dh.gov.sg.mq.rabbitmq.MQRabbit;
import sg.gov.dh.trackers.NavisensLocalTracker;

public class DHBFTLocalApplication extends Application {

    public static WebView myWebView;
    public static NavisensLocalTracker tracker;
    public static BFTLocalPreferences pref = new BFTLocalPreferences();
    public static MQRabbit mqRabbit=null;

}

