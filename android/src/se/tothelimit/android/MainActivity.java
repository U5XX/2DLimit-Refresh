package se.tothelimit.android;

import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


;import se.tothelimit.Input.Buffer;
import se.tothelimit.ToTheLimit;

/**
 * Class that starts the libGDX project on Android.
 *
 * @author Simon Cedergren <simon@tuxflux.se> and the mighty Fredrik C. Andersson <cfredrikandersson@hotmail.com> - lead programmer and paint-master.
 */
public class MainActivity extends AndroidApplication {

//    private BluetoothHandler bt;
    private View decorView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(android.os.Build.VERSION.SDK_INT >= 19) {
//        	getWindow().getDecorView().setSystemUiVisibility(2);
//        	getActionBar().hide();
//        }
        decorView = getWindow().getDecorView();
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
//        cfg.useGL20 = true;

//        bt = new BluetoothHandler(this);
//        Buffer.init();
        initialize(new ToTheLimit(), cfg);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        bt.checkForBluetooth();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        bt.stop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && android.os.Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(2);
            if(getActionBar() != null) getActionBar().hide();
        }
    }
}