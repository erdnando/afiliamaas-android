package com.google.android.gms.samples.vision.ocrreader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NuevaSolicitud extends AppCompatActivity {

    TextView textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead, textDocumentosHead,
            conector1, conector2, conector3, conector4, conector5;

    NestedScrollView scrollNuevaSolicitud;

    //Declaracion de los Spinner con sus respectivas listas para tipo de identificacion
    Spinner SpinnerTipoIdentificacion;
    String TipoIdentificacion[];

    //Declaracion de los Spinner con sus respectivas listas para nacionalidad general
    Spinner SpinnerNacionalidadGeneral;
    String NacionalidadGeneral[];

    //Declaracion de los Spinner con sus respectivas listas para estado civil general
    Spinner SpinnerEdoCivilGeneral;
    String EdoCivilGeneral[];

    //Declaracion de los Spinner con sus respectivas listas para estado domicilio
    Spinner SpinnerEstadoDomicilio;
    String EstadoDomicilio[];

    //Declaracion de los Spinner con sus respectivas listas para delegacion domicilio
    Spinner SpinnerDelegacionDomicilio;
    String DelegacionDomicilio[];

    //Declaracion de los Spinner con sus respectivas listas para compañia movil domicilio
    Spinner SpinnerCompaniaMovil;
    String companiaMovil[];

    //Declaracion de los Spinner con sus respectivas listas para compañia estatus de residencia domicilio
    Spinner SpinnerEstatusDomicilio;
    String estatusDomicilio[];

    //Declaracion de los Spinner con sus respectivas listas para compañia estatus de residencia domicilio
    Spinner SpinnerTipoContrato;
    String tipoContrato[];

    //Declaracion de los Spinner con sus respectivas listas para estado de datos economicos
    Spinner SpinnerEstadoIngresos;

    //Declaracion de los Spinner con sus respectivas listas para delegacion de datos economicos
    Spinner SpinnerDelegacionIngresos;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de primera referencia
    Spinner SpinnerNacionalidadPrimera;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de segunda referencia
    Spinner SpinnerNacionalidadSegunda;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de tercera referencia
    Spinner SpinnerNacionalidadTercera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud2);

        conector1 = (TextView) findViewById(R.id.conector1);
        conector2 = (TextView) findViewById(R.id.conector2);
        conector3 = (TextView) findViewById(R.id.conector3);
        conector4 = (TextView) findViewById(R.id.conector4);
        conector5 = (TextView) findViewById(R.id.conector5);

        textGeneralesHead = (TextView) findViewById(R.id.textGeneralesHead);
        textDomicilioHead = (TextView) findViewById(R.id.textDomicilioHead);
        textDatosEconomicosHead = (TextView) findViewById(R.id.textDatosEconomicosHead);
        textPersonaPoliticaHead = (TextView) findViewById(R.id.textPersonaPoliticaHead);
        textReferenciasFamiliaresHead = (TextView) findViewById(R.id.textReferenciasFamiliaresHead);
        textDocumentosHead = (TextView) findViewById(R.id.textDocumentosHead);

        scrollNuevaSolicitud = (NestedScrollView) findViewById(R.id.scrollNuevaSolicitud);

        //Generacion de los view de los Spinner
        SpinnerTipoIdentificacion = (Spinner) findViewById(R.id.SpinnerTipoIdentificacion);
        SpinnerNacionalidadGeneral = (Spinner) findViewById(R.id.SpinnerNacionalidadGeneral);
        SpinnerEdoCivilGeneral = (Spinner) findViewById(R.id.SpinnerEdoCivilGeneral);
        SpinnerEstadoDomicilio = (Spinner) findViewById(R.id.SpinnerEstadoDomicilio);
        SpinnerDelegacionDomicilio = (Spinner) findViewById(R.id.SpinnerDelegacionDomicilio);
        SpinnerCompaniaMovil = (Spinner) findViewById(R.id.SpinnerCompaniaMovil);
        SpinnerEstatusDomicilio = (Spinner) findViewById(R.id.SpinnerEstatusDomicilio);
        SpinnerTipoContrato = (Spinner) findViewById(R.id.SpinnerTipoContrato);
        SpinnerEstadoIngresos = (Spinner) findViewById(R.id.SpinnerEstadoIngresos);
        SpinnerDelegacionIngresos = (Spinner) findViewById(R.id.SpinnerDelegacionIngresos);
        SpinnerNacionalidadPrimera = (Spinner) findViewById(R.id.SpinnerNacionalidadPrimera);
        SpinnerNacionalidadSegunda = (Spinner) findViewById(R.id.SpinnerNacionalidadSegunda);
        SpinnerNacionalidadTercera = (Spinner) findViewById(R.id.SpinnerNacionalidadTercera);

        //Verifica cual es el catalago activo y hace una cosulta para tomar los valores y llenar el spinner
        consultaCatalogoActivo();

        //Llenado de los spinner con los arreglos de tipo identificacion
        final ArrayAdapter <String> listaTipoIdentificacion = new ArrayAdapter<String>(this, R.layout.spinner_item, TipoIdentificacion);
        SpinnerTipoIdentificacion.setAdapter(listaTipoIdentificacion);

        //Llenado de los spinner con los arreglos de nacionalidad general
        final ArrayAdapter <String> listaNacionalidadGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadGeneral.setAdapter(listaNacionalidadGeneral);

        //Llenado de los spinner con los arreglos de estado civil general
        final ArrayAdapter <String> listaEdoCivilGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, EdoCivilGeneral);
        SpinnerEdoCivilGeneral.setAdapter(listaEdoCivilGeneral);

        //Llenado de los spinner con los arreglos de estado domicilio
        ArrayAdapter <String> listaEstadoDomicilio = new ArrayAdapter<String>(this, R.layout.spinner_item, EstadoDomicilio);
        SpinnerEstadoDomicilio.setAdapter(listaEstadoDomicilio);

        //Llenado de los spinner con los arreglos de estado domicilio
        ArrayAdapter <String> listaDelegacionDomicilio = new ArrayAdapter<String>(this, R.layout.spinner_item, DelegacionDomicilio);
        SpinnerDelegacionDomicilio.setAdapter(listaDelegacionDomicilio);

        //Llenado de los spinner con los arreglos de compañia movil domicilio
        ArrayAdapter <String> listaCompaniaMovil = new ArrayAdapter<String>(this, R.layout.spinner_item, companiaMovil);
        SpinnerCompaniaMovil.setAdapter(listaCompaniaMovil);

        //Llenado de los spinner con los arreglos de compañia movil domicilio
        ArrayAdapter <String> listaEstatusResidencia = new ArrayAdapter<String>(this, R.layout.spinner_item, estatusDomicilio);
        SpinnerEstatusDomicilio.setAdapter(listaEstatusResidencia);

        //Llenado de los spinner con los arreglos de tipo de contrato en datos economicos
        ArrayAdapter <String> listaTipoContrato = new ArrayAdapter<String>(this, R.layout.spinner_item, tipoContrato);
        SpinnerTipoContrato.setAdapter(listaTipoContrato);

        //Llenado de los spinner con los arreglos de estado en datos economicos
        ArrayAdapter <String> listaEstadoIngresos = new ArrayAdapter<String>(this, R.layout.spinner_item, EstadoDomicilio);
        SpinnerEstadoIngresos.setAdapter(listaEstadoIngresos);

        //Llenado de los spinner con los arreglos de delegacion en datos economicos
        ArrayAdapter <String> listaDelegacionIngresos = new ArrayAdapter<String>(this, R.layout.spinner_item, DelegacionDomicilio);
        SpinnerDelegacionIngresos.setAdapter(listaDelegacionIngresos);

        //Llenado de los spinner con los arreglos de nancionalidad en primera referencia
        ArrayAdapter <String> listaNacionalidadPrimera = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadPrimera.setAdapter(listaNacionalidadPrimera);

        //Llenado de los spinner con los arreglos de nancionalidad en segunda referencia
        ArrayAdapter <String> listaNacionalidadSegunda = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadSegunda.setAdapter(listaNacionalidadSegunda);

        //Llenado de los spinner con los arreglos de nancionalidad en tercera referencia
        ArrayAdapter <String> listaNacionalidadTercera = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadTercera.setAdapter(listaNacionalidadTercera);

        textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));

        SpinnerTipoIdentificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String valor = listaTipoIdentificacion.getItem(position);

                toast(valor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerNacionalidadGeneral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String valor = listaNacionalidadGeneral.getItem(position);

                toast(valor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerEdoCivilGeneral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String valor = listaEdoCivilGeneral.getItem(position);

                toast(valor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector2.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector5.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 1150 && scrollY < 3070) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector5.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 3070 && scrollY < 5170) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector5.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 5170 && scrollY < 6240) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#64B5F6"));
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#00E676"));
                    conector4.setBackgroundColor(Color.parseColor("#64B5F6"));
                    conector5.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 6240 && scrollY < 8350) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#64B5F6"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#00E676"));
                    conector4.setBackgroundColor(Color.parseColor("#00E676"));
                    conector5.setBackgroundColor(Color.parseColor("#64B5F6"));

                } else if (scrollY > 8350) {

                    textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDomicilioHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDatosEconomicosHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textPersonaPoliticaHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textReferenciasFamiliaresHead.setBackgroundColor(Color.parseColor("#00E676"));
                    textDocumentosHead.setBackgroundColor(Color.parseColor("#00E676"));

                    conector1.setBackgroundColor(Color.parseColor("#00E676"));
                    conector2.setBackgroundColor(Color.parseColor("#00E676"));
                    conector3.setBackgroundColor(Color.parseColor("#00E676"));
                    conector4.setBackgroundColor(Color.parseColor("#00E676"));
                    conector5.setBackgroundColor(Color.parseColor("#00E676"));
                }
            }
        });
    }

    public void consultaCatalogoActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from PARAMETRO where id_parametro = 2", null);

        if (consulta.moveToNext()) {

            consultaTipoIdentificacion(consulta.getString(2));
            consultaNacionalidadGeneral(consulta.getString(2));
            consultaEdoCivil(consulta.getString(2));
            consultaEstado(consulta.getString(2));
            consultaDelegacionDomicilio(consulta.getString(2));
            consultaCompaniaMovil(consulta.getString(2));
            consultaEstatusReisdencia(consulta.getString(2));
            consultaTipoContrato(consulta.getString(2));
        }
    }

    private void consultaTipoContrato(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 7", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 7", null);

            if (tamaño.moveToNext()) {

                tipoContrato = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < tipoContrato.length; i++) {

                    if (consulta.moveToNext()) {

                        tipoContrato[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 7", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 7", null);

            if (tamaño.moveToNext()) {

                tipoContrato = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < tipoContrato.length; i++) {

                    if (consulta.moveToNext()) {

                        tipoContrato [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    private void consultaEstatusReisdencia(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 4", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 4", null);

            if (tamaño.moveToNext()) {

                estatusDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < estatusDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        estatusDomicilio[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 4", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 4", null);

            if (tamaño.moveToNext()) {

                estatusDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < estatusDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        estatusDomicilio [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    private void consultaCompaniaMovil(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 1", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 1", null);

            if (tamaño.moveToNext()) {

                companiaMovil = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < companiaMovil.length; i++) {

                    if (consulta.moveToNext()) {

                        companiaMovil[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 1", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 1", null);

            if (tamaño.moveToNext()) {

                companiaMovil = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < companiaMovil.length; i++) {

                    if (consulta.moveToNext()) {

                        companiaMovil [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    private void consultaDelegacionDomicilio(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 6", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 6", null);

            if (tamaño.moveToNext()) {

                DelegacionDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < DelegacionDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        DelegacionDomicilio[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 6", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 6", null);

            if (tamaño.moveToNext()) {

                DelegacionDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < DelegacionDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        DelegacionDomicilio [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    private void consultaEstado(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 5", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 5", null);

            if (tamaño.moveToNext()) {

                EstadoDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EstadoDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        EstadoDomicilio[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 5", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 5", null);

            if (tamaño.moveToNext()) {

                EstadoDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EstadoDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        EstadoDomicilio [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    private void consultaEdoCivil(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 3", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 3", null);

            if (tamaño.moveToNext()) {

                EdoCivilGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EdoCivilGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        EdoCivilGeneral[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 3", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 3", null);

            if (tamaño.moveToNext()) {

                EdoCivilGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EdoCivilGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        EdoCivilGeneral [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    public void consultaNacionalidadGeneral(String letra){

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 9", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 9", null);

            if (tamaño.moveToNext()) {

                NacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < NacionalidadGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        NacionalidadGeneral[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 9", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 9", null);

            if (tamaño.moveToNext()) {

                NacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < NacionalidadGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        NacionalidadGeneral [i] = consulta.getString(1);
                    }
                }
            }
        }
    }

    public void consultaTipoIdentificacion(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 2", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 2", null);

            if (tamaño.moveToNext()) {

                TipoIdentificacion = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < TipoIdentificacion.length; i++) {

                    if (consulta.moveToNext()) {

                        TipoIdentificacion[i] = consulta.getString(1);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 2", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 2", null);

            if (tamaño.moveToNext()) {

                TipoIdentificacion = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < TipoIdentificacion.length; i++) {

                    if (consulta.moveToNext()) {

                        TipoIdentificacion[i] = consulta.getString(1);
                    }
                }
            }
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
}
