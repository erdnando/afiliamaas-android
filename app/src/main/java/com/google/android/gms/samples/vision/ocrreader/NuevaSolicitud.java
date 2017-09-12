package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NuevaSolicitud extends AppCompatActivity {

    TextView textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead,
            conector1, conector2, conector3, conector4;

    Button btnScaner;

    NestedScrollView scrollNuevaSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud2);

        btnScaner = (Button) findViewById(R.id.btnOCR);

        conector1 = (TextView) findViewById(R.id.conector1);
        conector2 = (TextView) findViewById(R.id.conector2);
        conector3 = (TextView) findViewById(R.id.conector3);
        conector4 = (TextView) findViewById(R.id.conector4);

        textGeneralesHead = (TextView) findViewById(R.id.textGeneralesHead);
        textDomicilioHead = (TextView) findViewById(R.id.textDomicilioHead);
        textDatosEconomicosHead = (TextView) findViewById(R.id.textDatosEconomicosHead);
        textPersonaPoliticaHead = (TextView) findViewById(R.id.textPersonaPoliticaHead);
        textReferenciasFamiliaresHead = (TextView) findViewById(R.id.textReferenciasFamiliaresHead);

        scrollNuevaSolicitud = (NestedScrollView) findViewById(R.id.scrollNuevaSolicitud);

        textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));

        btnScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        scrollNuevaSolicitud.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > 0 && scrollY < 1150) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector2.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 1150 && scrollY < 3070) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 3070 && scrollY < 5170) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 5170 && scrollY < 6240) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#00E676"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 6240) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#00E676"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#00E676"));
                    conector4.setBackgroundColor(Color.parseColor("#00E676"));
                }
            }
        });
    }
}
