package com.example.todoapp;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragmentCompat {
    public static final String TAG = "list";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences sharedPref;
//    private ListPreference language;
//    public static final String KEY_PREF_LANGUAGE = "list";
//    public String languagePref_ID;
//
//    final String English = getString(R.string.English);
//    final String Deutch = getString(R.string.Deutch);
//    final String Indonesia = getString(R.string.Indonesia);

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();

        int count = prefScreen.getPreferenceCount();

// Go through all of the preferences, and set up their preference summary.
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            // You don't need to set up preference summaries for checkbox preferences because
            // they are already set up in xml using summaryOff and summary On
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }

//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
//        SharedPreferences.OnSharedPreferenceChangeListener listener =
//                new SharedPreferences.OnSharedPreferenceChangeListener() {
//                    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
//                        if (key.equals(KEY_PREF_LANGUAGE)) {
//                            languagePref_ID = prefs.getString(SettingAct.KEY_PREF_LANGUAGE, "3");
//                            switch (languagePref_ID) {
//                                case "1":
//                                    Locale localeEN = new Locale(English);
//                                    setLocale(localeEN);
//                                    break;
//                                case "2":
//                                    Locale localeDE = new Locale(Deutch);
//                                    setLocale(localeDE);
//                                    break;
//                                case "3":
//                                    Locale localeIN = new Locale(Indonesia);
//                                    setLocale(localeIN);
//                                    break;
//                                default:
//                                    throw new IllegalStateException("Unexpected value: " + languagePref_ID);
//                            }
//                        }
//                    }
//                };
//        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }
    // The below method sets the Preference Summary as per selected.
    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else if (preference instanceof EditTextPreference) {
            // For EditTextPreferences, set the summary to the value's simple string representation.
            preference.setSummary(value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) this.getContext());
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) getContext());
//    }

//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if (key.equals(KEY_PREF_LANGUAGE)) {
//            languagePref_ID = sharedPreferences.getString(SettingAct.KEY_PREF_LANGUAGE, "3");
//            switch (languagePref_ID) {
//                case "1":
//                    Locale localeEN = new Locale("EN");
//                    setLocale(localeEN);
//                    break;
//                case "2":
//                    Locale localeDE = new Locale("DE");
//                    setLocale(localeDE);
//                    break;
//                case "3":
//                    Locale localeIN = new Locale("IN");
//                    setLocale(localeIN);
//                    break;
//            }
//        }
//    }

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
//        recreate();
    }
}

//    @Override
//    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//        setPreferencesFromResource(R.xml.preferences, rootKey);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
//    }

//    private void loadLocale() {
//        SharedPreferences prefs = getPreferenceManager("Setting", MODE_PRIVATE);
//        String language = prefs.getString("My_Lang", "");
//        setLocale(language);
//    }
//
//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//
//        SharedPreferences.Editor editor = getPreferenceManager("Setting", MODE_PRIVATE).edit();
//        editor.putString("My_Lang", lang);
//        editor.apply();
//    }


