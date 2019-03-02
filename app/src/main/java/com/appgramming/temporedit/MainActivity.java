package com.appgramming.temporedit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toolbar;

public class MainActivity extends Activity {

    private EditText mEditText;

    private int mSystemUiVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);

        // Set the default preferences, and load first preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    /**
     * Loads preferences when the activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }

    /**
     * Inflate the menu items for use in the action bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);

//        MenuItem item = menu.findItem(R.id.action_copy);
//        item.setIconTintList(ColorStateList.valueOf(Color.RED));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_copy:
                SystemUtils.copyToClipboard(this, getString(R.string.app_name), String.valueOf(mEditText.getText()));
                return true;
            case R.id.action_cut:
                SystemUtils.copyToClipboard(this, getString(R.string.app_name), String.valueOf(mEditText.getText()));
                mEditText.getText().clear();
                return true;
            case R.id.action_share:
                SystemUtils.shareText(this, String.valueOf(mEditText.getText()), getString(R.string.share_title));
                return true;
            case R.id.action_clear:
                mEditText.getText().clear();
                return true;
            case R.id.action_paste:
                String clipText = SystemUtils.pasteTextFromClipboard(this);
                if (clipText != null) mEditText.setText(clipText);
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadPreferences() {
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        // Apply typeface and font style settings
        final String typefaceName = pref.getString(getString(R.string.pref_typeface_key), getString(R.string.pref_typeface_default));
        final String styleName = pref.getString(getString(R.string.pref_font_style_key), getString(R.string.pref_font_style_default));
        mEditText.setTypeface(SettingsHelper.parseFontTypeface(typefaceName), SettingsHelper.parseFontStyle(styleName));

        // Apply font size setting
        final String fontSizeString = pref.getString(getString(R.string.pref_font_size_key), getString(R.string.pref_font_size_default));
        mEditText.setTextSize(Integer.parseInt(fontSizeString));

        // Apply elegant text height setting
        final boolean elegantTextHeight = pref.getBoolean(getString(R.string.pref_elegant_text_height_key), Boolean.parseBoolean(getString(R.string.pref_elegant_text_height_default)));
//        mEditText.setElegantTextHeight(elegantTextHeight);
//        mEditText.setLineSpacing(20f, 1f);
//        Log.e("elegant", String.valueOf(mEditText.isElegantTextHeight()));

        // Apply editor background color setting
        final int editorBackgroundColor = pref.getInt(getString(R.string.pref_editor_background_color_key), getColor(R.color.editor_background_color));
        mEditText.setBackgroundColor(editorBackgroundColor);
//        getActionBar().setBackgroundDrawable(new ColorDrawable(editorBackgroundColor));

        // Apply editor text color setting
        final int editorTextColor = pref.getInt(getString(R.string.pref_editor_text_color_key), getColor(R.color.editor_text_color));
        mEditText.setTextColor(editorTextColor);

        final String fullscreenString = pref.getString(getString(R.string.pref_fullscreen_key), getString(R.string.pref_fullscreen_value_none));
        mSystemUiVisibility = SettingsHelper.parseFullscreen(this, fullscreenString);
//        if (fullscreen > 0) getWindow().getDecorView().setSystemUiVisibility(fullscreen);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && mSystemUiVisibility != 0) {
//            getWindow().getDecorView().setSystemUiVisibility(mSystemUiVisibility);
//        }
//    }
}
