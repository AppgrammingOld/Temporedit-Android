/*
 * Temporedit
 * Copyright (C) 2019 Appgramming. All rights reserved.
 * https://www.appgramming.com
 */
package com.appgramming.temporedit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Objects;

/**
 * The main Temporedit activity.
 */
public class MainActivity extends Activity {

    private static final int SETTINGS_REQUEST = 1;

    private EditText mEditText;

    /**
     * Init activity functionality.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Read and apply the theme setting
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String themeName = pref.getString(getString(R.string.pref_theme_key), getString(R.string.pref_theme_evalue_dark));
        if (!Objects.equals(themeName, getString(R.string.pref_theme_evalue_temporary)))
            setTheme(SettingsHelper.parseTheme(this, themeName));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find main views
        mEditText = findViewById(R.id.edit_text);

        // Set the default preferences, and load first preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    /**
     * Loads app preferences when the activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }

    /**
     * Save app preferences when the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    /**
     * Inflate the menu items for use in the action bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handle action bar items click events.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_copy:
                Utils.copyToClipboard(this, R.string.app_name, String.valueOf(mEditText.getText()));
                return true;
            case R.id.action_cut:
                Utils.copyToClipboard(this, R.string.app_name, String.valueOf(mEditText.getText()));
                mEditText.getText().clear();
                return true;
            case R.id.action_share:
                Utils.shareText(this, String.valueOf(mEditText.getText()), R.string.share_title);
                return true;
            case R.id.action_clear:
                mEditText.getText().clear();
                return true;
            case R.id.action_paste:
                String clipText = Utils.pasteTextFromClipboard(this);
                if (clipText != null) mEditText.setText(clipText);
                return true;
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), SETTINGS_REQUEST);
                return true;
            case R.id.action_rate:
                Utils.viewUrl(this, getString(R.string.action_rate_url));
                return true;
            case R.id.action_help:
                Utils.viewUrl(this, getString(R.string.action_help_url));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            recreate();
        }
    }

    /**
     * Load app preferences.
     */
    private void loadPreferences() {
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        // Apply typeface and font style settings
        final String typefaceStr = pref.getString(getString(R.string.pref_typeface_key),
                getString(R.string.pref_typeface_evalue_default));
        final String styleStr = pref.getString(getString(R.string.pref_font_style_key),
                getString(R.string.pref_font_style_evalue_normal));
        mEditText.setTypeface(SettingsHelper.parseTypeface(this, typefaceStr),
                SettingsHelper.parseFontStyle(this, styleStr));

        // Apply font size setting
        final String sizeStr = pref.getString(getString(R.string.pref_font_size_key),
                getString(R.string.pref_font_size_evalue_18));
        if (sizeStr != null) mEditText.setTextSize(Integer.parseInt(sizeStr));

        // Apply line spacing setting
        final String spacingStr = pref.getString(getString(R.string.pref_line_spacing_key),
                getString(R.string.pref_line_spacing_value_1_0));
        if (spacingStr != null) mEditText.setLineSpacing(0, Float.parseFloat(spacingStr));

        // Restore app state (current text)
        final String textString = pref.getString(getString(R.string.pref_text_key), "");
        mEditText.setText(textString);
    }

    /**
     * Save app state (current text) to preferences.
     */
    private void savePreferences() {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(getString(R.string.pref_text_key), String.valueOf(mEditText.getText()))
                .apply();
    }
}
