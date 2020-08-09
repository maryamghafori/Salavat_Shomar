package app;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontOverride {

    public static void setDefaultFont(Context context, String typeFaceName, String fontName) {

        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);

        try {
            final Field staticField = Typeface.class.getDeclaredField(typeFaceName);
            staticField.setAccessible(true);
            staticField.set(null, typeface);
        }
        catch (Exception e) { app.l(e.toString()); }

    }
}