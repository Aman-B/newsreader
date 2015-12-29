package com.example.aman.feedreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ConcurrentModificationException;
import java.util.Locale;

/**
 * Created by aman on 24/12/15.
 */
public class DialogCreator implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Dialog dialog;
    Locale myLocale;
    RadioGroup radioGroup;
    Context context;
    public void createDialog(Context context)
    {
        this.context=context;
       dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Select language : ");
        dialog.setCancelable(true);

        radioGroup= (RadioGroup)dialog.findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);



        RadioButton hindi = (RadioButton) dialog.findViewById(R.id.rb1);
        RadioButton english =(RadioButton) dialog.findViewById(R.id.rb2);
        Button done = (Button) dialog.findViewById(R.id.done_button);
        done.setOnClickListener(this);
        dialog.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.done_button:
                dialog.cancel();
                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int rgId=radioGroup.getCheckedRadioButtonId();
        View radioButton=radioGroup.findViewById(rgId);
        int idx= radioGroup.indexOfChild(radioButton);
        switch (idx)
        {
            case 0:
                MainActivity.lang="hi";
               // Toast.makeText(MainActivity.con,"selected : "+MainActivity.lang,Toast.LENGTH_SHORT).show();
                setLocal(MainActivity.lang);
                break;

            case 1:
                MainActivity.lang="en";
                //Toast.makeText(MainActivity.con,"selected : "+MainActivity.lang,Toast.LENGTH_SHORT).show();
                setLocal(MainActivity.lang);
                break;
        }

    }

    private void setLocal(String lang) {
        Log.i("Locale is set",".");
         myLocale = new Locale(lang);

        Resources res = MainActivity.con.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Log.i("Locale is set", " " + MainActivity.lang);

        Intent refresh = new Intent(MainActivity.con, MainActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Activity)context).finish();
        MainActivity.con.startActivity(refresh);
        Log.i("Locale is set", " " + MainActivity.lang);

    }





}
