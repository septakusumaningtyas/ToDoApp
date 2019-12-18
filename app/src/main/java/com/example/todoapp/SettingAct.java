package com.example.todoapp;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        openFragment(new SettingFragment());
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    private void openFragment(Fragment fragment) {
        openFragment(fragment, false);
    }
    private void openFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackstack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static final String KEY_PREF_LANGUAGE = "pref_language";

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String languagePref_ID = sharedPref.getString(KEY_PREF_LANGUAGE, "3");
        switch (languagePref_ID) {
            case "1":
                Locale localeEN = new Locale("en");
                setLocale(localeEN);
                break;
            case "2":
                Locale localeDE = new Locale("de");
                setLocale(localeDE);
                break;
            case "3":
                Locale localeIN = new Locale("in");
                setLocale(localeIN);
                break;

        }
    }

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        //recreate();
        //finish();
        //startActivity(getIntent());
        //if these are not commented, main activity wont show at start at all
    }
}
