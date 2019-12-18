package com.example.todoapp;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragmentCompat {
    public static final String TAG = "list";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences sharedPref;
    private ListPreference language;
    public static final String KEY_PREF_LANGUAGE = "pref_language";
    public String languagePref_ID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.OnSharedPreferenceChangeListener listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                        if (key.equals(KEY_PREF_LANGUAGE)) {
                            languagePref_ID = prefs.getString(SettingAct.KEY_PREF_LANGUAGE, "3");
                            switch (languagePref_ID) {
                                case "1":
                                    Locale localeEN = new Locale("EN");
                                    setLocale(localeEN);
                                    break;
                                case "2":
                                    Locale localeDE = new Locale("DE");
                                    setLocale(localeDE);
                                    break;
                                case "3":
                                    Locale localeIN = new Locale("IN");
                                    setLocale(localeIN);
                                    break;
                            }
                        }
                    }
                };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) this.getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) getContext());
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_LANGUAGE)) {
            languagePref_ID = sharedPreferences.getString(SettingAct.KEY_PREF_LANGUAGE, "3");
            switch (languagePref_ID) {
                case "1":
                    Locale localeEN = new Locale("EN");
                    setLocale(localeEN);
                    break;
                case "2":
                    Locale localeDE = new Locale("DE");
                    setLocale(localeDE);
                    break;
                case "3":
                    Locale localeIN = new Locale("IN");
                    setLocale(localeIN);
                    break;
            }
        }
    }

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


