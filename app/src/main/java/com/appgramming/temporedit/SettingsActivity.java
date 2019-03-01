package com.appgramming.temporedit;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add standard preferences
        addPreferencesFromResource(R.xml.preferences);

//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.settings_container, new PrefsFragment())
//                .commit();
    }



//    public static class PrefsFragment extends PreferenceFragment {
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            // Load the preferences from an XML resource
//            addPreferencesFromResource(R.xml.preferences);
//        }
//    }
}
