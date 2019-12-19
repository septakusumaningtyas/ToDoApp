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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingAct extends AppCompatActivity {
    public static final String KEY_PREF_LANGUAGE = "list";
    public String languagePref_ID="0";

    final String English = getString(R.string.English);
    final String Deutch = getString(R.string.Deutch);
    final String Indonesia = getString(R.string.Indonesia);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String languagePref_ID_RES = sharedPref.getString(KEY_PREF_LANGUAGE, "3");
        if (!languagePref_ID.equals(languagePref_ID_RES)) {
            languagePref_ID_RES = languagePref_ID;
            switch (languagePref_ID_RES) {
                case "0":
                    Locale localeEN = new Locale(English);
                    setLocale(localeEN);
                    break;
                case "1":
                    Locale localeDE = new Locale(Deutch);
                    setLocale(localeDE);
                    break;
                case "2":
                    Locale localeIN = new Locale(Indonesia);
                    setLocale(localeIN);
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
}