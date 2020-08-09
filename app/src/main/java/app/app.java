package app;

import android.os.Build;
import android.os.VibrationEffect;
import android.util.Log;

import com.sdsmdg.tastytoast.TastyToast;

public class app {

    public static final String TAG = "Salavat";
    public static final String TAG_DB = "SalavatDB";
    public static final String DB_NAME = TAG_DB + ".db";
    public static final int DB_VERSION = 1;
    public static boolean VIBR;

    public static class main {
        private static final String TAg = "***** Error ===> ";
    }

    public static void l(String message) {
        Log.e(main.TAg, message);
    }

    public static void toastINFO(String message) {
        TastyToast.makeText(Application.getContext(), message, TastyToast.LENGTH_LONG, TastyToast.INFO);
    }

    public static void toastSUCCESS(String message) {
        TastyToast.makeText(Application.getContext(), message, TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    public static void VIB() {

        if (Application.vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Application.vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                Application.vibrator.vibrate(500);
            }
        }
    }




}