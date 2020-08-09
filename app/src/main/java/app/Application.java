package app;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Vibrator;

public class Application extends android.app.Application {

    private static Context context;
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;
    public static Vibrator vibrator;

    @Override
    public void onCreate() {

        super.onCreate();
        context = this;
        setFont();
        initTypeface();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


    }

    public static Context getContext() {
        return context;
    }

    private void setFont() {
        String SegoeUi = "fonts/segoe_ui.ttf";
        FontOverride.setDefaultFont(this, "MONOSPACE", SegoeUi);

    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);
    }
}