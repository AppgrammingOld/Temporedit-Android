package com.appgramming.temporedit;

import android.content.Context;
import android.graphics.Typeface;
import android.util.ArrayMap;
import android.view.View;

import java.util.HashMap;

class SettingsHelper {

    static Typeface parseFontTypeface(String styleString) {
        switch (styleString) {
            case "DEFAULT":
                return Typeface.DEFAULT;
            case "DEFAULT_BOLD":
                return Typeface.DEFAULT_BOLD;
            case "MONOSPACE":
                return Typeface.MONOSPACE;
            case "SANS_SERIF":
                return Typeface.SANS_SERIF;
            case "SERIF":
                return Typeface.SERIF;
            default:
                return Typeface.DEFAULT;
        }
    }

    static int parseFontStyle(String styleString) {
        switch (styleString) {
            case "NORMAL":
                return Typeface.NORMAL;
            case "BOLD":
                return Typeface.BOLD;
            case "ITALIC":
                return Typeface.ITALIC;
            case "BOLD_ITALIC":
                return Typeface.BOLD_ITALIC;
            default:
                return Typeface.NORMAL;
        }
    }

    static int parseFullscreen(final Context context, String fullscreenString) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put(context.getString(R.string.pref_fullscreen_value_none), 0);
        map.put(context.getString(R.string.pref_fullscreen_value_normal), View.SYSTEM_UI_FLAG_FULLSCREEN);
        map.put(context.getString(R.string.pref_fullscreen_value_full),
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        return map.get(fullscreenString);
    }


}
