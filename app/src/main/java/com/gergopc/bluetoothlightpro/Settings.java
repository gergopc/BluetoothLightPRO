package com.gergopc.bluetoothlightpro;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

   //Is the program in setup mode_
   boolean setup_mode;

   //Setup mode booleans
   boolean isSetOn = false;
   boolean isSetOff = false;
   boolean isSetDefBluetooth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle b = getIntent().getExtras();
        setup_mode = b.getBoolean("setup");
       final EditText switchCharInput = (EditText) findViewById(R.id.switchCharInput);


        Button setON = (Button) findViewById(R.id.setON);
      setON.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String sw = switchCharInput.getText().toString();

              if(checkChar(sw)){
                  setON(sw);
              }else{
                  alert("You can type only a character!", v);
              }
          }
      });

        Button setOFF = (Button) findViewById(R.id.setOFF);
        setOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sw = switchCharInput.getText().toString();
                if(checkChar(sw)){
                    setOFF(sw);
                }else{
                    alert("You can type only a character!", v);
                }

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(checkSetup()){
                moveTaskToBack(true);
                return true;
            }else{
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
    public boolean checkSetup(){

        boolean ret=true;

        if(setup_mode==true) {
            if (isSetOn || isSetOff || isSetDefBluetooth) {
                ret=true;
            }else if(!isSetOn || isSetOff || isSetDefBluetooth){

            }else if(isSetOn || !isSetOff || isSetDefBluetooth){

        }else if(isSetOn || !isSetOff || isSetDefBluetooth){

    }
        }else{
            ret = true;
        }
        return ret;
    }
    public boolean checkChar(String input){
    System.out.println(input.length());
    System.out.println(input);
    if(input.length() == 1){
        return true;
    } else {

        return false;
    }
}

    private void setON(String input) {

        TinyDB tinydb = new TinyDB(getApplicationContext());
       tinydb.putString("onChar", input);
        isSetOn = true;
    }
    private void setOFF(String input) {
        TinyDB tinydb = new TinyDB(getApplicationContext());
        tinydb.putString("onChar", input);
        isSetOff = true;
    }
    public void alert(String s, View view) {
        Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void log(String input){

        System.out.println(input.toString());
    }
}
