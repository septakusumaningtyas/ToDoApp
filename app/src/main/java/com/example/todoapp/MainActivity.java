package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView titlepage,subtitlepage,endpage;
    ImageButton btnAddNew;
    ImageButton btnSetting;

    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<ToDo> list;
    DoesAdapter doesAdapter;

    public static final String LIST_KEY = "list";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      loadLocale();
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);

        btnAddNew = findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewTaskAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ToDo>();

        reference = FirebaseDatabase.getInstance().getReference().child("ToDoApp");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    ToDo p = dataSnapshot1.getValue(ToDo.class);
                    list.add(p);
                }
                doesAdapter = new DoesAdapter(MainActivity.this,list);
                doesAdapter.notifyDataSetChanged();
                ourdoes.setAdapter(doesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
            }
        });

        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingAct.class);
                startActivity(intent);
//                showLanguageDialog();
            }
        });

    }

//    private void showLanguageDialog() {
//        final String[] listItems = {"English", "Indonesian", "Dutch"};
//        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                if(i == 0) {
//                    setLocale("English");
//                    recreate();
//                }
//                else if(i == 1) {
//                    setLocale("Indonesian");
//                    recreate();
//                }
//                else if (i == 2) {
//                    setLocale("Dutch");
//                    recreate();
//                }
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
//
//            }
//        });
//    }
//
//    private void loadLocale() {
//        SharedPreferences prefs = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
//        String language = prefs.getString("My_Lang", "");
//        setLocale(language);
//    }
//
//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
//
//        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
//        editor.putString("My_Lang", lang);
//        editor.apply();
//    }
}
