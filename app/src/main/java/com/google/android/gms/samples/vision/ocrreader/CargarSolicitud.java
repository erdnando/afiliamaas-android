package com.google.android.gms.samples.vision.ocrreader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CargarSolicitud extends AppCompatActivity {

    TextView textIdSolicitud, textGenerales, textDomicilio, textDatosEconomicos, textPersonaPolitica, textReferenciasFamiliares,
            textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead,
            conector1, conector2, conector3, conector4;

    //Cajas de texto generales
    EditText txtSolicitanteGeneral, txtSegundoNombreGeneral, txtPaternoGeneral, txtMaternoGeneral, txtTipoGeneral, txtNumeroIdentificacion, txtNacionalidad, txtNacimiento,
            txtRFC, txtEstadoCivil, txtNumeroDependientes;

    Switch switchHombre, switchMujer;

    NestedScrollView scrollCargarSolicitud;

    String IdSolicitud, solicitudxml;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_solicitud);

        Intent intent = getIntent();

        IdSolicitud = intent.getStringExtra("IdSolicitud");

        textIdSolicitud = (TextView) findViewById(R.id.TextIdSolicitud);
        textGenerales = (TextView) findViewById(R.id.TextGenerales);
        textDomicilio = (TextView) findViewById(R.id.TextDomicilio);
        textDatosEconomicos = (TextView) findViewById(R.id.TextDatosEconomicos);
        textPersonaPolitica = (TextView) findViewById(R.id.TextPersonaPolitica);
        textReferenciasFamiliares = (TextView) findViewById(R.id.TextReferenciasFamiliares);

        conector1 = (TextView) findViewById(R.id.conector1);
        conector2 = (TextView) findViewById(R.id.conector2);
        conector3 = (TextView) findViewById(R.id.conector3);
        conector4 = (TextView) findViewById(R.id.conector4);

        textGeneralesHead = (TextView) findViewById(R.id.textGeneralesHead);
        textDomicilioHead = (TextView) findViewById(R.id.textDomicilioHead);
        textDatosEconomicosHead = (TextView) findViewById(R.id.textDatosEconomicosHead);
        textPersonaPoliticaHead = (TextView) findViewById(R.id.textPersonaPoliticaHead);
        textReferenciasFamiliaresHead = (TextView) findViewById(R.id.textReferenciasFamiliaresHead);

        scrollCargarSolicitud = (NestedScrollView) findViewById(R.id.scrollCargarSolicitud);

        //Creacion de las cajas de texto y switch de generales
        txtSolicitanteGeneral = (EditText) findViewById(R.id.txtSolicitanteGeneral);
        txtSegundoNombreGeneral = (EditText) findViewById(R.id.txtSegundoNombreGeneral);
        txtPaternoGeneral = (EditText) findViewById(R.id.txtPaternoGeneral);
        txtMaternoGeneral = (EditText) findViewById(R.id.txtMaternoGeneral);
        txtTipoGeneral = (EditText) findViewById(R.id.txtTipoGeneral);
        txtNumeroIdentificacion = (EditText) findViewById(R.id.txtNumeroIdentificacion);
        txtNacionalidad = (EditText) findViewById(R.id.txtNacionalidad);
        txtNacimiento = (EditText) findViewById(R.id.txtNacimiento);
        txtRFC = (EditText) findViewById(R.id.txtRFC);
        txtEstadoCivil = (EditText) findViewById(R.id.txtEstadoCivil);
        txtNumeroDependientes = (EditText) findViewById(R.id.txtNumeroDependientes);

        switchHombre = (Switch) findViewById(R.id.switchHombre);
        switchMujer = (Switch) findViewById(R.id.switchMujer);

        textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));

        textIdSolicitud.setText("Id Solicitud: " + IdSolicitud);

        buzonActivo();

        scrollCargarSolicitud.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void toast(final String mensaje) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buzonActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from PARAMETRO where id_parametro = 1", null);

        if (consulta.moveToNext()) {

            consultaBuzon(consulta.getString(2));
        }

        db.close();
    }

    public void consultaBuzon(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor consulta = db.rawQuery("select * from BUZON_A where id_solicitud = " + IdSolicitud, null);

            if (consulta.moveToNext()) {

                solicitudxml = consulta.getString(7);
            }

            db.close();

        } else if (letra.equals("B")) {

            Cursor consulta = db.rawQuery("select * from BUZON_B where id_solicitud = " + IdSolicitud, null);

            if (consulta.moveToNext()) {

                solicitudxml = consulta.getString(7);
            }

            db.close();
        }

        db.close();
    }


}
