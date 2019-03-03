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
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private EditText mEditText;
    private LinearLayout mRootLayout;

    private int mSystemUiVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootLayout = findViewById(R.id.root_layout);
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
        final String typefaceName = pref.getString(getString(R.string.pref_typeface_key), getString(R.string.pref_typeface_evalue_default));
        final String styleName = pref.getString(getString(R.string.pref_font_style_key), getString(R.string.pref_font_style_evalue_normal));
        mEditText.setTypeface(SettingsHelper.parseFontTypeface(this, typefaceName), SettingsHelper.parseFontStyle(this, styleName));

        // Apply font size setting
        final String fontSizeString = pref.getString(getString(R.string.pref_font_size_key), getString(R.string.pref_font_size_evalue_18));
        mEditText.setTextSize(Integer.parseInt(fontSizeString));

        // Apply line spacing setting
        final String lineSpacingString = pref.getString(getString(R.string.pref_line_spacing_key), getString(R.string.pref_line_spacing_value_1_0));
//        Log.e("getLineSpacingExtra", String.valueOf(mEditText.getLineSpacingExtra()));
//        Log.e("getLineSpacingMultiplier", String.valueOf(mEditText.getLineSpacingMultiplier()));
        mEditText.setLineSpacing(0, Float.parseFloat(lineSpacingString));
//        Log.e("getLineSpacingExtra", String.valueOf(mEditText.getLineSpacingExtra()));
//        Log.e("getLineSpacingMultiplier", String.valueOf(mEditText.getLineSpacingMultiplier()));

        // Apply editor background color setting
        final int editorBackgroundColor = pref.getInt(getString(R.string.pref_editor_background_color_key), getColor(R.color.editor_background_color));
        mRootLayout.setBackgroundColor(editorBackgroundColor);

        // Apply editor text color setting
        final int editorTextColor = pref.getInt(getString(R.string.pref_editor_text_color_key), getColor(R.color.editor_text_color));
        mEditText.setTextColor(editorTextColor);

        // Restore text
        final String textString = pref.getString(getString(R.string.pref_text_key), "");
        mEditText.setText(textString);
    }

    /**
     * Save preferences.
     */
    private void savePreferences() {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(getString(R.string.pref_text_key), String.valueOf(mEditText.getText()))
                .apply();
    }
}
