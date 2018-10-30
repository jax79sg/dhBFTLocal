package local.bft.dh.dhbftlocal;

import android.content.Context;
import android.net.DhcpInfo;
import android.util.Log;
import android.webkit.JavascriptInterface;

import sg.gov.dh.trackers.Coords;


/**
 * Javascript to Android interaction
 */
public class WebAppInterface {
    Context mContext;
    String TAG = "JStoANDROIDinterface";

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void manualLocationUpdateinPixels(String xyInPixels) {
        String[] xy=xyInPixels.split(",");
        Log.d(TAG,"1.manualLocationUpdateinPixels: " + xy[0] + ","+ xy[1]);
        Log.d(TAG,"2.manualLocationUpdateinPixels: " + Double.parseDouble(xy[0]) + ","+ Double.parseDouble(xy[1]));
        Log.d(TAG,"3.manualLocationUpdateinPixels: " + DHBFTLocalApplication.pref.getMetresFromPixels(Double.parseDouble(xy[0])) + ","+ DHBFTLocalApplication.pref.getMetresFromPixels(Double.parseDouble(xy[1])));


        DHBFTLocalApplication.tracker.setManualLocation(new Coords(0, 0, 0, 0, 0,0, 0,DHBFTLocalApplication.pref.getMetresFromPixels(Double.parseDouble(xy[0])) , DHBFTLocalApplication.pref.getMetresFromPixels(Double.parseDouble(xy[1])), null));
    }

    @JavascriptInterface
    public String getMQSettings()
    {

        String message=DHBFTLocalApplication.pref.getMqHost()+","+DHBFTLocalApplication.pref.getMqUsername()+","+ DHBFTLocalApplication.pref.getMqPassword()+","+ DHBFTLocalApplication.pref.getTopic();
        Log.d(TAG,"getMQSettings: " + message);
        return message;
    }

}
