package local.bft.dh.dhbftlocal;

import android.content.Context;
import android.net.DhcpInfo;
import android.util.Log;
import android.webkit.JavascriptInterface;

import sg.gov.dh.trackers.Coords;
import sg.gov.dh.trackers.NavisensLocalTracker;


/**
 * Javascript to Android interaction
 */
public class WebAppInterface {
    Context mContext;
    BFTLocalPreferences pref = null;
    NavisensLocalTracker tracker = null;
    String TAG = "JStoANDROIDinterface";

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c, BFTLocalPreferences prefs, NavisensLocalTracker tracker) {
        mContext = c;
        pref=prefs;
        this.tracker=tracker;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void manualLocationUpdateinPixels(String xyInPixels) {
        String[] xy=xyInPixels.split(",");
        Log.d(TAG,"1.manualLocationUpdateinPixels: " + xy[0] + ","+ xy[1]);
        Log.d(TAG,"2.manualLocationUpdateinPixels: " + Double.parseDouble(xy[0]) + ","+ Double.parseDouble(xy[1]));
        Log.d(TAG,"3.manualLocationUpdateinPixels: " + pref.getMetresFromPixels(Double.parseDouble(xy[0])) + ","+ pref.getMetresFromPixels(Double.parseDouble(xy[1])));


        tracker.setManualLocation(new Coords(0, 0, 0, 0, 0,0, 0,pref.getMetresFromPixels(Double.parseDouble(xy[0])) , pref.getMetresFromPixels(Double.parseDouble(xy[1])), null));
    }

    @JavascriptInterface
    public String getMQSettings()
    {

        String message=pref.getMqHost()+","+pref.getMqUsername()+","+ pref.getMqPassword()+","+ pref.getTopic()+","+ pref.getPort();
        Log.d(TAG,"getMQSettings: " + message);
        return message;
    }

}
