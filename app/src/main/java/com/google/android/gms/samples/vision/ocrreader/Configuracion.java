package com.google.android.gms.samples.vision.ocrreader;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Configuracion extends AppCompatActivity {

    Switch switchConfiguracion;

    TimerTask mTimerTask;
    final Handler handler = new Handler();
    Timer t = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        switchConfiguracion = (Switch) findViewById(R.id.switchConfiguracion);

        switchConfiguracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (switchConfiguracion.isChecked()) {

                    mTimerTask = new TimerTask() {
                        public void run() {
                            handler.post(new Runnable() {
                                public void run() {

                                    mensaje("Ha comenzado la sincronización");

                                    switchConfiguracion.setChecked(true);

                                }
                            });
                        }};

                    t.schedule(mTimerTask, 0, 4000);  //

                } else if (!switchConfiguracion.isChecked()) {

                    if(mTimerTask!=null){

                        mensaje("Se detuvo la sincronización");

                        switchConfiguracion.setChecked(false);
                        mTimerTask.cancel();
                    }

                }

            }
        });
    }

    public void mensaje(final String mensaje) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
