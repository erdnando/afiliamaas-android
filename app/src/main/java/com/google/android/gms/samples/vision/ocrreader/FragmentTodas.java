package com.google.android.gms.samples.vision.ocrreader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class FragmentTodas extends Fragment {

    TableLayout tablaTodas;

    TextView textIdSolicitud, textNombre, textFechaAlta;

    String IdSolicitud[], Nombre[], FechaAlta[], nombre, paterno, materno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todas, container, false);

        tablaTodas = (TableLayout) v.findViewById(R.id.TablaTodas);

        consultaBuzonActivo();

        setNumberFile();

        return v;
    }

    public void consultaBuzonActivo() {

        AdminSQLite admin = new AdminSQLite(getContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consultaA = db.rawQuery("select * from PARAMETRO where id_parametro = 1", null);

        if (consultaA.moveToNext()) {

            selectCountBuzon(consultaA.getString(2));

        }

        db.close();
    }

    public void selectCountBuzon(String buzon) {

        AdminSQLite admin = new AdminSQLite(getContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        if (buzon.equals("A")) {

            Cursor consultaCount = db.rawQuery("select COUNT(*) from BUZON_A", null);

            if (consultaCount.moveToNext()) {

                IdSolicitud = new String[Integer.parseInt(consultaCount.getString(0))];
                Nombre = new String[Integer.parseInt(consultaCount.getString(0))];
                FechaAlta = new String[Integer.parseInt(consultaCount.getString(0))];

                Cursor consulta = db.rawQuery("select * from Buzon_A", null);

                for (int i = 0; i < IdSolicitud.length; i++) {

                    if (consulta.moveToNext()) {

                        try {
                            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            InputSource src = new InputSource();
                            src.setCharacterStream(new StringReader(consulta.getString(7)));

                            Document doc = builder.parse(src);
                            nombre = doc.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                            paterno = doc.getElementsByTagName("Apaterno").item(0).getTextContent();
                            materno = doc.getElementsByTagName("Amaterno").item(0).getTextContent();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        IdSolicitud[i] = consulta.getString(0);
                        Nombre[i] = nombre + " " + paterno + " " + materno;
                        FechaAlta[i] = consulta.getString(1);

                        toast("hola v");

                    }

                }

            }

        } else if (buzon.equals("B")) {

            Cursor consultaCount = db.rawQuery("select COUNT(*) from BUZON_B", null);

            if (consultaCount.moveToNext()) {

                IdSolicitud = new String[Integer.parseInt(consultaCount.getString(0))];
                Nombre = new String[Integer.parseInt(consultaCount.getString(0))];
                FechaAlta = new String[Integer.parseInt(consultaCount.getString(0))];

                Cursor consulta = db.rawQuery("select * from Buzon_B", null);

                for (int i = 0; i < IdSolicitud.length; i++) {

                    if (consulta.moveToNext()) {

                        try {
                            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            InputSource src = new InputSource();
                            src.setCharacterStream(new StringReader(consulta.getString(7)));

                            Document doc = builder.parse(src);
                            nombre = doc.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                            paterno = doc.getElementsByTagName("Apaterno").item(0).getTextContent();
                            materno = doc.getElementsByTagName("Amaterno").item(0).getTextContent();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        IdSolicitud[i] = consulta.getString(0);
                        Nombre[i] = nombre + " " + paterno + " " + materno;
                        FechaAlta[i] = consulta.getString(1);

                    }

                }

            }

        }

        db.close();

    }

    public void toast(final String mensaje) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setNumberFile() {

        getActivity().runOnUiThread(new Runnable() {
            @SuppressLint("NewApi")
            public void run() {

                for (int i = 0; i < IdSolicitud.length; i++) {

                    TableRow currentRow = new TableRow(getContext());
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

                    textIdSolicitud = new TextView(getContext());
                    textIdSolicitud.setText(IdSolicitud[i]);
                    textIdSolicitud.setTextSize(14);
                    textIdSolicitud.setTextColor(Color.BLACK);
                    textIdSolicitud.setWidth(38);
                    textIdSolicitud.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    textNombre = new TextView(getContext());
                    textNombre.setText(Nombre[i]);
                    textNombre.setTextSize(14);
                    textNombre.setTextColor(Color.BLACK);
                    textNombre.setWidth(38);
                    textNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    textFechaAlta = new TextView(getContext());
                    textFechaAlta.setText(FechaAlta[i]);
                    textFechaAlta.setTextSize(14);
                    textFechaAlta.setTextColor(Color.BLACK);
                    textFechaAlta.setWidth(38);
                    textFechaAlta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    currentRow.setLayoutParams(params);
                    currentRow.addView(textIdSolicitud);
                    currentRow.addView(textNombre);
                    currentRow.addView(textFechaAlta);
                    currentRow.setBackgroundColor(Color.WHITE);
                    currentRow.setPadding(0, 0, 0, 60);

                    tablaTodas.addView(currentRow);

                    currentRow.setClickable(true);

                    currentRow.setOnClickListener(
                            new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            TableRow tableRow = (TableRow) v;
                            TextView textIdSolicitud = (TextView) tableRow.getChildAt(0);
                            String IdSolicitud = textIdSolicitud.getText().toString();

                            Intent intent = new Intent(getContext(), CargarSolicitud.class);
                            intent.putExtra("IdSolicitud", IdSolicitud);
                            startActivity(intent);
                        }
                    });

                }

            }

        });
    }

}
