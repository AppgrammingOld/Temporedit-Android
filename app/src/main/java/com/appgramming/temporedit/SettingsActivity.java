/*
 * Temporedit
 * Copyright (C) 2019 Appgramming. All rights reserved.
 * https://www.appgramming.com
 */
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
