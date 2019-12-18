package com.example.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingAct extends AppCompatActivity {
    public static final String KEY_PREF_LANGUAGE = "pref_language";
    public String languagePref_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String languagePref_ID = sharedPref.getString(KEY_PREF_LANGUAGE, "3");
        switch (languagePref_ID) {
            case "1":
                Locale localeEN = new Locale("EN");
                setLocaleOnCreate(localeEN);
                break;
            case "2":
                Locale localeDE = new Locale("DE");
                setLocaleOnCreate(localeDE);
                break;
            case "3":
                Locale localeIN = new Locale("IN");
                setLocaleOnCreate(localeIN);
                break;

        }
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String languagePref_ID_RES = sharedPref.getString(KEY_PREF_LANGUAGE, "3");
        if (!languagePref_ID.equals(languagePref_ID_RES)) {
            languagePref_ID_RES = languagePref_ID;
            switch (languagePref_ID_RES) {
                case "1":
                    Locale localeEN = new Locale("EN");
                    setLocaleOnCreate(localeEN);
                    break;
                case "2":
                    Locale localeDE = new Locale("DE");
                    setLocaleOnCreate(localeDE);
                    break;
                case "3":
                    Locale localeIN = new Locale("IN");
                    setLocaleOnCreate(localeIN);
                    break;

            }
        }
    }

    public void setLocaleOnCreate(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        recreate();
        //finish();
        //startActivity(getIntent());
    }

    public void startSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingAct.class);
        startActivity(intent);
    }
}