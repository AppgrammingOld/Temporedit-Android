/*
 * Temporedit
 * Copyright (C) 2019 Appgramming. All rights reserved.
 * https://www.appgramming.com
 */
package com.appgramming.temporedit;

import android.content.Context;
import android.graphics.Typeface;
import android.util.ArrayMap;

class SettingsHelper {

    /**
     * Returns a default Typeface object from a preference value string.
     */
    static Typeface parseFontTypeface(final Context context, String typefaceString) {
        ArrayMap<String, Typeface> map = new ArrayMap<>();
        map.put(context.getString(R.string.pref_typeface_evalue_default), Typeface.DEFAULT);
        map.put(context.getString(R.string.pref_typeface_evalue_monospace), Typeface.MONOSPACE);
        map.put(context.getString(R.string.pref_typeface_evalue_sans_serif), Typeface.SANS_SERIF);
        map.put(context.getString(R.string.pref_typeface_evalue_serif), Typeface.SERIF);
        Typeface typeface = map.get(typefaceString);
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    /**
     * Returns a font style constant from a preference value string.
     */
    static int parseFontStyle(final Context context, String styleString) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put(context.getString(R.string.pref_font_style_evalue_normal), Typeface.NORMAL);
        map.put(context.getString(R.string.pref_font_style_evalue_bold), Typeface.BOLD);
        map.put(context.getString(R.string.pref_font_style_evalue_italic), Typeface.ITALIC);
        map.put(context.getString(R.string.pref_font_style_evalue_bold_italic), Typeface.BOLD_ITALIC);
        Integer style = map.get(styleString);
        return style != null ? style : Typeface.NORMAL;
    }
}
