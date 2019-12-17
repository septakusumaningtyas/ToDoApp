package com.example.todoapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragmentCompat {
    public static final String TAG = "list";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences sharedPref;
    private ListPreference language;
    TextView titlepage,subtitlepage,endpage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        language = findPreference("list");

        language.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(language.getEntry().equals(0))
                {
                    System.out.println("English");
                }
                return true;
            }
        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

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
}
