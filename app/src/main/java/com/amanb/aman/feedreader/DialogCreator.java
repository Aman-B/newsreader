package com.amanb.aman.feedreader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Locale;

/**
 * Created by aman on 24/12/15.
 */
public class DialogCreator implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    //TODO: Check german in this;
    Dialog dialog;
    Locale myLocale;
    RadioGroup radioGroup;
    Context context;
    Intent refresh;
    public void createDialog(Context context)
    {
        this.context=context;
       dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Select language : ");
        dialog.setCancelable(true);


       //Spinner
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (context,R.array.language_array,android.R.layout.simple_spinner_item);

        // Specified the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button okay= (Button)dialog.findViewById(R.id.ok_button);
        okay.setOnClickListener(this);


        Button cancel = (Button) dialog.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(this);
        dialog.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cancel_button:
                dialog.cancel();
                break;

            case R.id.ok_button:
                Sharedpref sharedpref = new Sharedpref();

                sharedpref.storeInSharedPref("language",MainActivity.lang,context);

                setLocal(MainActivity.lang,context);
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

    public void setLocal(String lang) {
        Log.i("Locale is set",".");
         myLocale = new Locale(lang);

        Resources res =context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Log.i("Locale is set", " " + MainActivity.lang);

        choseActivity(lang,context);

        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        refresh.putExtra("language", MainActivity.lang);
        ((Activity)context).finish();
        MainActivity.con.startActivity(refresh);
        Log.i("Locale is set", " " + MainActivity.lang);

    }

    private void choseActivity(String lang,Context context) {
        switch (lang)
        {
            case "en":
                refresh = new Intent(context, NextActivity.class);
                break;

            case"hi":
                refresh= new Intent(context,NextActivity.class);
                break;

            case"de":
                refresh= new Intent(context,NextActivity.class);
                break;

        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected_language= parent.getSelectedItem().toString();
        Log.i("Selected lang ", selected_language );

        switch (selected_language)
        {
            case "English":
                MainActivity.lang="en";


                break;

            case "Hindi":
                MainActivity.lang="hi";
                break;

            case "German":
                MainActivity.lang="de";
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setLocal(String lang, Context applicationContext) {
        Log.i("Locale is set","!");
        context=applicationContext;
        myLocale = new Locale(lang);

        Resources res =context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Log.i("Locale is set", " " + lang);

        choseActivity(lang,context);

        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        refresh.putExtra("language", MainActivity.lang);
        ((Activity)context).finish();
        context.startActivity(refresh);
        Log.i("Locale is set", " " + MainActivity.lang);





    }
}
