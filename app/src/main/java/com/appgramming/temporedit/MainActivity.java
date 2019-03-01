package com.appgramming.temporedit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Console;

public class MainActivity extends Activity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);

//        mEditText.setTypeface(Typeface.MONOSPACE);

//        setActionBar((Toolbar) findViewById(R.id.toolbar));
//        getActionBar().setDisplayShowTitleEnabled(false);

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
        final String typefaceName = pref.getString(getString(R.string.pref_typeface_key), getString(R.string.pref_typeface_default));

//        mEditText.setTypeface(Typeface.DEFAULT);
        try {
            Typeface typeface = (Typeface) Typeface.class.getField(typefaceName).get(null);
            mEditText.setTypeface(typeface);

//            Log.e("typeface", Typeface.class.getField(typefaceName).get(null).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
