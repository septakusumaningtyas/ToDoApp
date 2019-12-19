package com.example.todoapp;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingFragment extends Fragment {

    private Switch myswitch;
    private Context context;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context contextThemeWrapper;

        SharedPreferences preferences = this.getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        boolean useDarkMode = preferences.getBoolean("DARK_MODE", false);

        if (useDarkMode) {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.ActivityThemeDark);
        } else {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.ActivityThemeLight);
        }

        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.activity_setting_fragment, container, false);

        myswitch=view.findViewById(R.id.my_switch);
        myswitch.setChecked(useDarkMode);

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchDarkMode(b);
            }
        });

        return view;
    }

    private void switchDarkMode(boolean darkMode) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE).edit();
        editor.putBoolean("DARK_MODE", darkMode);
        editor.apply();

        Intent i=new Intent(getActivity().getApplicationContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();

        getActivity().overridePendingTransition(0, 0);
    }
}
