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

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

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

    Switch switchGeneral;
    TextView textHombre, textMujer;

    NestedScrollView scrollCargarSolicitud;

    String IdSolicitud, solicitudxml;

    //Variables generales
    String Pmrnombre, Sdonombre, Apaterno, Amaterno, Sexo, Nacionalidad, Fechanacdia, FechanacMes, FechanacAnio, Rfc, Edocivil, Nodependientes;

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

        switchGeneral = (Switch) findViewById(R.id.switchGeneral);
        textMujer = (TextView) findViewById(R.id.textMujer);
        textHombre = (TextView) findViewById(R.id.textHombre);

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
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(solicitudxml));

            Document doc = builder.parse(src);

            //Variables para generales
            Pmrnombre = doc.getElementsByTagName("Pmrnombre").item(0).getTextContent();
            Sdonombre = doc.getElementsByTagName("Sdonombre").item(0).getTextContent();
            Apaterno = doc.getElementsByTagName("Apaterno").item(0).getTextContent();
            Amaterno = doc.getElementsByTagName("Amaterno").item(0).getTextContent();
            Sexo = doc.getElementsByTagName("Sexo").item(0).getTextContent();
            Nacionalidad = doc.getElementsByTagName("Nacionalidad").item(0).getTextContent();
            Fechanacdia = doc.getElementsByTagName("Fechanacdia").item(0).getTextContent();
            FechanacMes = doc.getElementsByTagName("FechasnacMes").item(0).getTextContent();
            FechanacAnio = doc.getElementsByTagName("FechanacAnio").item(0).getTextContent();
            Rfc = doc.getElementsByTagName("Rfc").item(0).getTextContent();
            Edocivil = doc.getElementsByTagName("Edocivil").item(0).getTextContent();
            Nodependientes = doc.getElementsByTagName("Nodependiente").item(0).getTextContent();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Fijar variables a los edittext de generales
        txtSolicitanteGeneral.setText(Pmrnombre);
        txtSegundoNombreGeneral.setText(Sdonombre);
        txtPaternoGeneral.setText(Apaterno);
        txtMaternoGeneral.setText(Amaterno);
        txtNacionalidad.setText(Nacionalidad);
        txtNacimiento.setText(Fechanacdia + "/" + FechanacMes + "/" + FechanacAnio);
        txtRFC.setText(Rfc);
        txtEstadoCivil.setText(Edocivil);
        txtNumeroDependientes.setText(Nodependientes);

        if (Sexo.equals("MASCULINO")) {

            switchGeneral.setChecked(true);
            textMujer.setEnabled(false);
        } else {

            switchGeneral.setChecked(false);
            textHombre.setEnabled(false);

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
