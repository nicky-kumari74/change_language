package com.example.changelanguage;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button btn;
String s=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadlocal();
        btn=findViewById(R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languagechanger();
            }
        });
    }

    private void languagechanger() {
        String lan[]={"English","Hindi","Bengali","Urdu","Tamil","Gujrati"};
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(this);
        mbuilder.setTitle("Choose Language");
        mbuilder.setSingleChoiceItems(lan, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==1){
                    setLocale("hi");
                    recreate();
                }
                if(which==2){
                    setLocale("bn");
                    recreate();
                }
                if(which==3){
                    setLocale("ur");
                    recreate();
                }
                if(which==4){
                    setLocale("ta");
                    recreate();
                }
                if(which==5){
                    setLocale("gu");
                    recreate();
                }
            }
        });
        mbuilder.create();
        mbuilder.show();
    }

    void setLocale(String language) {
        Log.e("setlocal",language);
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences sp = getSharedPreferences("first", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("lang",language);
        ed.apply();
    }
void loadlocal(){
        SharedPreferences preferences=getSharedPreferences("first", Activity.MODE_PRIVATE);
    Log.e("loadlocal","hii");
        if(preferences.contains("lang")){
        s=preferences.getString("lang","");
        setLocale(s);
        }
}

}