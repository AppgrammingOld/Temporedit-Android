package com.appgramming.temporedit;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add standard preferences
        addPreferencesFromResource(R.xml.preferences);
    }
}
