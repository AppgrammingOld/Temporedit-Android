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
    static Typeface parseTypeface(final Context context, String typefaceString) {
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

    /**
     * Returns a theme resource id from a theme value string.
     */
    static int parseTheme(final Context context, String themeString) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put(context.getString(R.string.pref_theme_evalue_dark), R.style.AppTheme_Dark);
        map.put(context.getString(R.string.pref_theme_evalue_light), R.style.AppTheme_Light);
        map.put(context.getString(R.string.pref_theme_evalue_black), R.style.AppTheme_Black);
        map.put(context.getString(R.string.pref_theme_evalue_white), R.style.AppTheme_White);
        map.put(context.getString(R.string.pref_theme_evalue_temporary), R.style.AppTheme_Temporary);
        map.put(context.getString(R.string.pref_theme_evalue_turbo_pascal), R.style.AppTheme_TurboPascal);

        Integer themeId = map.get(themeString);
        return themeId != null ? themeId : R.style.AppTheme_Light;
    }
}
