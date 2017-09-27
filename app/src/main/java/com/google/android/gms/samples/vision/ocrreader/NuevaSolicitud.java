package com.google.android.gms.samples.vision.ocrreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class NuevaSolicitud extends AppCompatActivity {

    TextView textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead, textDocumentosHead,
            conector1, conector2, conector3, conector4, conector5;

    EditText txtCorreo;

    NestedScrollView scrollNuevaSolicitud;

    DatePicker PickerNac;

    Button btnEnviarSolicitud;

    ImageButton ImgIdentificacionFrente, ImgIdentificacionAtras, ImgContrato1, ImgContrato2, ImgFirma;

    //Declaracion de los Spinner con sus respectivas listas para tipo de identificacion
    Spinner SpinnerTipoIdentificacion;
    String TipoIdentificacion[];
    String IdTipoIdentificacion[];
    String ValorTipoIdentificacion;
    String PosicionTipoIdentificacion;

    //Declaracion de los Spinner con sus respectivas listas para nacionalidad general
    Spinner SpinnerNacionalidadGeneral;
    String NacionalidadGeneral[];
    String IdNacionalidadGeneral[];
    String ValorNacionalidadGeneral;
    String PosicionNacionalidadGeneral;

    //Declaracion de los Spinner con sus respectivas listas para estado civil general
    Spinner SpinnerEdoCivilGeneral;
    String EdoCivilGeneral[];
    String IdEdoCivilGeneral[];
    String ValorEdoCivilGeneral;
    String PosicionEdoCivilGeneral;

    //Declaracion de los Spinner con sus respectivas listas para estado domicilio
    Spinner SpinnerEstadoDomicilio;
    String EstadoDomicilio[];
    String IdEstadoDomicilio[];
    String ValorEstadoDomicilio;
    String PosicionEstadoDomicilio;

    //Declaracion de los Spinner con sus respectivas listas para delegacion domicilio
    Spinner SpinnerDelegacionDomicilio;
    String DelegacionDomicilio[];
    String IdDelegacionDomicilio[];
    String ValorDelegacionDomicilio;
    String PosicionDelegacionDomicilio;

    //Declaracion de los Spinner con sus respectivas listas para compañia movil domicilio
    Spinner SpinnerCompaniaMovil;
    String companiaMovil[];
    String IdcompaniaMovil[];
    String ValorCompaniaMovil;
    String PosicionCompaniaMovil;

    //Declaracion de los Spinner con sus respectivas listas para compañia estatus de residencia domicilio
    Spinner SpinnerEstatusDomicilio;
    String estatusDomicilio[];
    String IdestatusDomicilio[];
    String ValorEstatusDomicilio;
    String PosicionEstatusDomicilio;

    //Declaracion de los Spinner con sus respectivas listas para compañia estatus de residencia domicilio
    Spinner SpinnerTipoContrato;
    String tipoContrato[];
    String IdtipoContrato[];
    String ValorTipoContrato;
    String PosicionTipoContrato;

    //Declaracion de los Spinner con sus respectivas listas para estado de datos economicos
    Spinner SpinnerEstadoIngresos;
    String ValorEstadoIngresos;
    String PosicionEstadoIngresos;

    //Declaracion de los Spinner con sus respectivas listas para delegacion de datos economicos
    Spinner SpinnerDelegacionIngresos;
    String ValorDelegacionIngresos;
    String PosicionDelegacionIngresos;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de primera referencia
    Spinner SpinnerNacionalidadPrimera;
    String ValorNacionalidadPrimera;
    String PosicionNacionalidadPrimera;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de segunda referencia
    Spinner SpinnerNacionalidadSegunda;
    String ValorNacionalidadSegunda;
    String PosicionNacionalidadSegunda;

    //Declaracion de los Spinner con sus respectivas listas para nancionalidad de tercera referencia
    Spinner SpinnerNacionalidadTercera;
    String ValorNacionalidadTercera;
    String PosicionNacionalidadTercera;

    //Declaración de los Edittext y radiobutton de generales
    EditText txtSolicitanteGeneral, txtSegundoNombreGeneral, txtPaternoGeneral, txtMaternoGeneral, txtNumeroIdentificacion, txtRFC, txtNumeroDependientes;
    RadioButton radioMujer, radioHombre;
    String Sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud2);

        //Creacion de los edittext y radiobutton de generales a través de su Id
        txtSolicitanteGeneral = (EditText) findViewById(R.id.txtSolicitanteGeneral);
        txtSegundoNombreGeneral = (EditText) findViewById(R.id.txtSegundoNombreGeneral);
        txtPaternoGeneral = (EditText) findViewById(R.id.txtPaternoGeneral);
        txtMaternoGeneral = (EditText) findViewById(R.id.txtMaternoGeneral);
        txtNumeroIdentificacion = (EditText) findViewById(R.id.txtNumeroIdentificacion);
        txtRFC = (EditText) findViewById(R.id.txtRFC);
        txtNumeroDependientes = (EditText) findViewById(R.id.txtNumeroDependientes);
        radioMujer = (RadioButton) findViewById(R.id.RadioMujer);
        radioHombre = (RadioButton) findViewById(R.id.RadioHombre);

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

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);

        scrollNuevaSolicitud = (NestedScrollView) findViewById(R.id.scrollNuevaSolicitud);

        PickerNac = (DatePicker) findViewById(R.id.PickerNac);
        PickerNac.setCalendarViewShown(false);

        ImgIdentificacionFrente = (ImageButton) findViewById(R.id.ImgIdentificacionFrenteNew);
        ImgIdentificacionAtras = (ImageButton) findViewById(R.id.ImgIdentificacionAtrasNew);
        ImgContrato1 = (ImageButton) findViewById(R.id.ImgContrato1New);
        ImgContrato2 = (ImageButton) findViewById(R.id.ImgContrato2New);
        ImgFirma = (ImageButton) findViewById(R.id.ImgFirmaNew);

        btnEnviarSolicitud = (Button) findViewById(R.id.btnEnviarSolicitud);

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
        final ArrayAdapter<String> listaTipoIdentificacion = new ArrayAdapter<String>(this, R.layout.spinner_item, TipoIdentificacion);
        SpinnerTipoIdentificacion.setAdapter(listaTipoIdentificacion);

        //Llenado de los spinner con los arreglos de nacionalidad general
        final ArrayAdapter<String> listaNacionalidadGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadGeneral.setAdapter(listaNacionalidadGeneral);

        //Llenado de los spinner con los arreglos de estado civil general
        final ArrayAdapter<String> listaEdoCivilGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, EdoCivilGeneral);
        SpinnerEdoCivilGeneral.setAdapter(listaEdoCivilGeneral);

        //Llenado de los spinner con los arreglos de estado domicilio
        final ArrayAdapter<String> listaEstadoDomicilio = new ArrayAdapter<String>(this, R.layout.spinner_item, EstadoDomicilio);
        SpinnerEstadoDomicilio.setAdapter(listaEstadoDomicilio);

        //Llenado de los spinner con los arreglos de estado domicilio
        final ArrayAdapter<String> listaDelegacionDomicilio = new ArrayAdapter<String>(this, R.layout.spinner_item, DelegacionDomicilio);
        SpinnerDelegacionDomicilio.setAdapter(listaDelegacionDomicilio);

        //Llenado de los spinner con los arreglos de compañia movil domicilio
        final ArrayAdapter<String> listaCompaniaMovil = new ArrayAdapter<String>(this, R.layout.spinner_item, companiaMovil);
        SpinnerCompaniaMovil.setAdapter(listaCompaniaMovil);

        //Llenado de los spinner con los arreglos de compañia movil domicilio
        final ArrayAdapter<String> listaEstatusResidencia = new ArrayAdapter<String>(this, R.layout.spinner_item, estatusDomicilio);
        SpinnerEstatusDomicilio.setAdapter(listaEstatusResidencia);

        //Llenado de los spinner con los arreglos de tipo de contrato en datos economicos
        final ArrayAdapter<String> listaTipoContrato = new ArrayAdapter<String>(this, R.layout.spinner_item, tipoContrato);
        SpinnerTipoContrato.setAdapter(listaTipoContrato);

        //Llenado de los spinner con los arreglos de estado en datos economicos
        final ArrayAdapter<String> listaEstadoIngresos = new ArrayAdapter<String>(this, R.layout.spinner_item, EstadoDomicilio);
        SpinnerEstadoIngresos.setAdapter(listaEstadoIngresos);

        //Llenado de los spinner con los arreglos de delegacion en datos economicos
        final ArrayAdapter<String> listaDelegacionIngresos = new ArrayAdapter<String>(this, R.layout.spinner_item, DelegacionDomicilio);
        SpinnerDelegacionIngresos.setAdapter(listaDelegacionIngresos);

        //Llenado de los spinner con los arreglos de nancionalidad en primera referencia
        final ArrayAdapter<String> listaNacionalidadPrimera = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadPrimera.setAdapter(listaNacionalidadPrimera);

        //Llenado de los spinner con los arreglos de nancionalidad en segunda referencia
        final ArrayAdapter<String> listaNacionalidadSegunda = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadSegunda.setAdapter(listaNacionalidadSegunda);

        //Llenado de los spinner con los arreglos de nancionalidad en tercera referencia
        final ArrayAdapter<String> listaNacionalidadTercera = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadTercera.setAdapter(listaNacionalidadTercera);

        textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ImgIdentificacionFrente.setEnabled(false);
            ImgIdentificacionAtras.setEnabled(false);
            ImgContrato1.setEnabled(false);
            ImgContrato2.setEnabled(false);
            ImgFirma.setEnabled(false);

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        SpinnerTipoIdentificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorTipoIdentificacion = listaTipoIdentificacion.getItem(position);
                PosicionTipoIdentificacion = IdTipoIdentificacion[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerNacionalidadGeneral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorNacionalidadGeneral = listaNacionalidadGeneral.getItem(position);
                PosicionNacionalidadGeneral = IdNacionalidadGeneral[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerEdoCivilGeneral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorEdoCivilGeneral = listaEdoCivilGeneral.getItem(position);
                PosicionEdoCivilGeneral = IdEdoCivilGeneral[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerEstadoDomicilio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorEstadoDomicilio = listaEstadoDomicilio.getItem(position);
                PosicionEstadoDomicilio = IdEstadoDomicilio[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerDelegacionDomicilio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorDelegacionDomicilio = listaDelegacionDomicilio.getItem(position);
                PosicionDelegacionDomicilio = IdDelegacionDomicilio[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerEstatusDomicilio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorEstatusDomicilio = listaEstatusResidencia.getItem(position);
                PosicionEstatusDomicilio = IdestatusDomicilio[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerCompaniaMovil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorCompaniaMovil = listaCompaniaMovil.getItem(position);
                PosicionCompaniaMovil = IdcompaniaMovil[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerTipoContrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorTipoContrato = listaTipoContrato.getItem(position);
                PosicionTipoContrato = IdtipoContrato[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerEstadoIngresos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorEstadoIngresos = listaEstadoIngresos.getItem(position);
                PosicionEstadoIngresos = IdEstadoDomicilio[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerDelegacionIngresos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorDelegacionIngresos = listaDelegacionIngresos.getItem(position);
                PosicionDelegacionIngresos = IdDelegacionDomicilio[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerNacionalidadPrimera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorNacionalidadPrimera = listaNacionalidadPrimera.getItem(position);
                PosicionNacionalidadPrimera = IdNacionalidadGeneral[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerNacionalidadSegunda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorNacionalidadSegunda = listaNacionalidadSegunda.getItem(position);
                PosicionNacionalidadSegunda = IdNacionalidadGeneral[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerNacionalidadTercera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorNacionalidadTercera = listaNacionalidadTercera.getItem(position);
                PosicionNacionalidadTercera = IdNacionalidadGeneral[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifica que radio button de sexo esta marcado
                if (radioHombre.isChecked()){

                    Sexo = "MASCULINO";
                }else if (radioMujer.isChecked()){

                    Sexo = "FEMENINO";
                }

                String dia, mes, anio;
                txtCorreo.setText("0894.andres@gmail.com");

                dia = String.valueOf(PickerNac.getDayOfMonth());
                mes = String.valueOf(PickerNac.getMonth() + 1);
                anio = String.valueOf(PickerNac.getYear());

                String solicitud_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<SolicitudType xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                        "  <Lattitude>19.4140762787095</Lattitude>\n" +
                        "  <Longuitud>-99.0129281651914</Longuitud>\n" +
                        "  <generales>\n" +
                        "    <Tpoidentif>"+PosicionTipoIdentificacion+"</Tpoidentif>\n" +
                        "    <Noidenficacion>"+txtNumeroIdentificacion.getText().toString()+"</Noidenficacion>\n" +
                        "    <Pmrnombre>"+txtSolicitanteGeneral.getText().toString()+"</Pmrnombre>\n" +
                        "    <Sdonombre>"+txtSegundoNombreGeneral.getText().toString()+"</Sdonombre>\n" +
                        "    <Apaterno>"+txtPaternoGeneral.getText().toString()+"</Apaterno>\n" +
                        "    <Amaterno>"+txtMaternoGeneral.getText().toString()+"</Amaterno>\n" +
                        "    <Sexo>"+Sexo+"</Sexo>\n" +
                        "    <Nacionalidad>"+PosicionNacionalidadGeneral+"</Nacionalidad>\n" +
                        "    <Fechanacdia>"+dia+"</Fechanacdia>\n" +
                        "    <Rfc>"+txtRFC.getText().toString()+"</Rfc>\n" +
                        "    <Edocivil>"+PosicionEdoCivilGeneral+"</Edocivil>\n" +
                        "    <Nodependiente>"+txtNumeroDependientes.getText().toString()+"</Nodependiente>\n" +
                        "    <Cveperspol>2</Cveperspol>\n" +
                        "    <FechasnacMes>"+mes+"</FechasnacMes>\n" +
                        "    <FechanacAnio>"+anio+"</FechanacAnio>\n" +
                        "  </generales>\n" +
                        "  <doc>\n" +
                        "    <IdentificacionFrentePath>TEC_636395911640643196.jpg</IdentificacionFrentePath>\n" +
                        "    <IdentificacionAtrasPath>TEC_636395912088133899.jpg</IdentificacionAtrasPath>\n" +
                        "    <Contrato1Path>TEC_636395912441057948.jpg</Contrato1Path>\n" +
                        "    <Contrato2Path>TEC_636395912942097782.jpg</Contrato2Path>\n" +
                        "    <Extra1>TEC_636395913267419058.jpg</Extra1>\n" +
                        "    <Extra2>................................................................</Extra2>\n" +
                        "    <Extra3>................................................................</Extra3>\n" +
                        "    <Extra4>................................................................</Extra4>\n" +
                        "    <Extra5>................................................................</Extra5>\n" +
                        "    <FirmaPath>TEC_636395912150998843.jpg</FirmaPath>\n" +
                        "  </doc>\n" +
                        "  <domicilio>\n" +
                        "    <Calle>NMMNMN</Calle>\n" +
                        "    <NoInt/>\n" +
                        "    <NoExt>8</NoExt>\n" +
                        "    <Cpdom>78787</Cpdom>\n" +
                        "    <Estado>"+PosicionEstadoDomicilio+"</Estado>\n" +
                        "    <Delegacion>"+PosicionDelegacionDomicilio+"</Delegacion>\n" +
                        "    <Colonia>JHJHJHJ</Colonia>\n" +
                        "    <TiempoResidencia>8</TiempoResidencia>\n" +
                        "    <EstatusResidencia>"+PosicionEstatusDomicilio+"</EstatusResidencia>\n" +
                        "    <MontoVivienda>8787878</MontoVivienda>\n" +
                        "    <Email>hghhgh@wweew.com</Email>\n" +
                        "    <Telcasa>8878787878</Telcasa>\n" +
                        "    <Telmovil>8778777777</Telmovil>\n" +
                        "    <CompaniaMovil>"+PosicionCompaniaMovil+"</CompaniaMovil>\n" +
                        "  </domicilio>\n" +
                        "  <Personapolitica>\n" +
                        "    <EsPersonaPolitica>SI</EsPersonaPolitica>\n" +
                        "    <TipoParentesco>87878YYYY</TipoParentesco>\n" +
                        "    <Descfuncion>JHJHJHJHJHJHJ</Descfuncion>\n" +
                        "    <Descparentesco>HHJJJJ</Descparentesco>\n" +
                        "    <TieneParentesco>SI</TieneParentesco>\n" +
                        "  </Personapolitica>\n" +
                        "  <Refer>\n" +
                        "    <Pmrnombre>AAAAAAAAA</Pmrnombre>\n" +
                        "    <Sdonombre/>\n" +
                        "    <Apaterno>OPPOPOP</Apaterno>\n" +
                        "    <Amaterno>MMNMMM</Amaterno>\n" +
                        "    <Nacionalidad>"+PosicionNacionalidadPrimera+"</Nacionalidad>\n" +
                        "    <TelefonoCasa>8878788788</TelefonoCasa>\n" +
                        "  </Refer>\n" +
                        "  <Refer2>\n" +
                        "    <Pmrnombre>BBBBBBBBB</Pmrnombre>\n" +
                        "    <Sdonombre/>\n" +
                        "    <Apaterno>OOOIOIOI</Apaterno>\n" +
                        "    <Amaterno>JHJHJHJH</Amaterno>\n" +
                        "    <Nacionalidad>"+PosicionNacionalidadSegunda+"</Nacionalidad>\n" +
                        "    <TelefonoCasa>5544554445</TelefonoCasa>\n" +
                        "  </Refer2>\n" +
                        "  <Refer3>\n" +
                        "    <Pmrnombre>CCCCCCCCCCCCCCCC</Pmrnombre>\n" +
                        "    <Sdonombre/>\n" +
                        "    <Apaterno>NBNBNBNBN</Apaterno>\n" +
                        "    <Amaterno>MMNMNMN</Amaterno>\n" +
                        "    <Nacionalidad>"+PosicionNacionalidadTercera+"</Nacionalidad>\n" +
                        "    <TelefonoCasa>7878787878</TelefonoCasa>\n" +
                        "  </Refer3>\n" +
                        "  <Promotor>\n" +
                        "    <Compania>STF</Compania>\n" +
                        "    <Usuario>jaimea</Usuario>\n" +
                        "    <Contrasenia>12345678</Contrasenia>\n" +
                        "  </Promotor>\n" +
                        "  <FolioLocal>0</FolioLocal>\n" +
                        "  <DiaCreacion>29</DiaCreacion>\n" +
                        "  <MesCreacion>8</MesCreacion>\n" +
                        "  <AnioCreacion>2017</AnioCreacion>\n" +
                        "  <Deconominos>\n" +
                        "    <TipoContrato>"+PosicionTipoContrato+"</TipoContrato>\n" +
                        "    <AntiguedadEmpleo>8</AntiguedadEmpleo>\n" +
                        "    <AniosCasada>0</AniosCasada>\n" +
                        "    <Ingresos>898777</Ingresos>\n" +
                        "    <NombreEmpresa>STF</NombreEmpresa>\n" +
                        "    <GiroEmpresa>IUIIUI</GiroEmpresa>\n" +
                        "    <Puesto>IUIUIUII</Puesto>\n" +
                        "    <Domicilio>\n" +
                        "      <Calle>UIUIUI</Calle>\n" +
                        "      <NoInt/>\n" +
                        "      <NoExt>III</NoExt>\n" +
                        "      <Cpdom>87878</Cpdom>\n" +
                        "      <Estado>"+PosicionEstadoIngresos+"</Estado>\n" +
                        "      <Delegacion>"+PosicionDelegacionIngresos+"</Delegacion>\n" +
                        "      <Colonia>UUIUIU</Colonia>\n" +
                        "      <TiempoResidencia>0</TiempoResidencia>\n" +
                        "      <EstatusResidencia>0</EstatusResidencia>\n" +
                        "      <MontoVivienda>0</MontoVivienda>\n" +
                        "      <Telcasa>7878787878</Telcasa>\n" +
                        "      <Telmovil/>\n" +
                        "    </Domicilio>\n" +
                        "    <OtrosIngresos>0</OtrosIngresos>\n" +
                        "    <FuenteOtrosIngresos>0</FuenteOtrosIngresos>\n" +
                        "  </Deconominos>\n" +
                        "</SolicitudType>";

                TextView textXML = (TextView) findViewById(R.id.TextXML);

                if (validarEmail(txtCorreo.getText().toString())) {

                    textXML.setText(solicitud_xml);
                } else {

                    toast("Email no valido");
                }
            }
        });

        scrollNuevaSolicitud.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > 0 && scrollY < 1700) {

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

                } else if (scrollY > 1700 && scrollY < 3550) {

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

                } else if (scrollY > 3550 && scrollY < 5850) {

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

                } else if (scrollY > 5850 && scrollY < 6800) {

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

                } else if (scrollY > 6800 && scrollY < 8900) {

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

                } else if (scrollY > 8900) {

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

    public void foto(View view) {

        switch (view.getId()) {

            case R.id.ImgIdentificacionFrenteNew:

                break;

            case R.id.ImgIdentificacionAtrasNew:

                break;

            case R.id.ImgContrato1New:

                break;

            case R.id.ImgContrato2New:

                break;

            case R.id.ImgFirmaNew:

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 0) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                ImgIdentificacionFrente.setEnabled(true);
                ImgIdentificacionAtras.setEnabled(true);
                ImgContrato1.setEnabled(true);
                ImgContrato2.setEnabled(true);
                ImgFirma.setEnabled(true);
            }
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
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
                IdtipoContrato = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < tipoContrato.length; i++) {

                    if (consulta.moveToNext()) {

                        tipoContrato[i] = consulta.getString(1);
                        IdtipoContrato[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 7", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 7", null);

            if (tamaño.moveToNext()) {

                tipoContrato = new String[Integer.parseInt(tamaño.getString(0))];
                IdtipoContrato = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < tipoContrato.length; i++) {

                    if (consulta.moveToNext()) {

                        tipoContrato[i] = consulta.getString(1);
                        IdtipoContrato[i] = consulta.getString(0);
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
                IdestatusDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < estatusDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        estatusDomicilio[i] = consulta.getString(1);
                        IdestatusDomicilio[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 4", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 4", null);

            if (tamaño.moveToNext()) {

                estatusDomicilio = new String[Integer.parseInt(tamaño.getString(0))];
                IdestatusDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < estatusDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        estatusDomicilio[i] = consulta.getString(1);
                        IdestatusDomicilio[i] = consulta.getString(0);
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
                IdcompaniaMovil = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < companiaMovil.length; i++) {

                    if (consulta.moveToNext()) {

                        companiaMovil[i] = consulta.getString(1);
                        IdcompaniaMovil[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 1", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 1", null);

            if (tamaño.moveToNext()) {

                companiaMovil = new String[Integer.parseInt(tamaño.getString(0))];
                IdcompaniaMovil = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < companiaMovil.length; i++) {

                    if (consulta.moveToNext()) {

                        companiaMovil[i] = consulta.getString(1);
                        IdcompaniaMovil[i] = consulta.getString(0);
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
                IdDelegacionDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < DelegacionDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        DelegacionDomicilio[i] = consulta.getString(1);
                        IdDelegacionDomicilio[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 6", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 6", null);

            if (tamaño.moveToNext()) {

                DelegacionDomicilio = new String[Integer.parseInt(tamaño.getString(0))];
                IdDelegacionDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < DelegacionDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        DelegacionDomicilio[i] = consulta.getString(1);
                        IdDelegacionDomicilio[i] = consulta.getString(0);
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
                IdEstadoDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EstadoDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        EstadoDomicilio[i] = consulta.getString(1);
                        IdEstadoDomicilio[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 5", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 5", null);

            if (tamaño.moveToNext()) {

                EstadoDomicilio = new String[Integer.parseInt(tamaño.getString(0))];
                IdEstadoDomicilio = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EstadoDomicilio.length; i++) {

                    if (consulta.moveToNext()) {

                        EstadoDomicilio[i] = consulta.getString(1);
                        IdEstadoDomicilio[i] = consulta.getString(0);
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
                IdEdoCivilGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EdoCivilGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        EdoCivilGeneral[i] = consulta.getString(1);
                        IdEdoCivilGeneral[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 3", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 3", null);

            if (tamaño.moveToNext()) {

                EdoCivilGeneral = new String[Integer.parseInt(tamaño.getString(0))];
                IdEdoCivilGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < EdoCivilGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        EdoCivilGeneral[i] = consulta.getString(1);
                        IdEdoCivilGeneral[i] = consulta.getString(0);
                    }
                }
            }
        }
    }

    public void consultaNacionalidadGeneral(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_A where id_tipo_catalogo = 9", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_tipo_catalogo = 9", null);

            if (tamaño.moveToNext()) {

                NacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];
                IdNacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < NacionalidadGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        NacionalidadGeneral[i] = consulta.getString(1);
                        IdNacionalidadGeneral[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 9", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 9", null);

            if (tamaño.moveToNext()) {

                NacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];
                IdNacionalidadGeneral = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < NacionalidadGeneral.length; i++) {

                    if (consulta.moveToNext()) {

                        NacionalidadGeneral[i] = consulta.getString(1);
                        IdNacionalidadGeneral[i] = consulta.getString(0);
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
                IdTipoIdentificacion = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < TipoIdentificacion.length; i++) {

                    if (consulta.moveToNext()) {

                        TipoIdentificacion[i] = consulta.getString(1);
                        IdTipoIdentificacion[i] = consulta.getString(0);
                    }
                }
            }

        } else if (letra.equals("B")) {

            Cursor tamaño = db.rawQuery("select count(*) from CATALOGO_B where id_tipo_catalogo = 2", null);
            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_tipo_catalogo = 2", null);

            if (tamaño.moveToNext()) {

                TipoIdentificacion = new String[Integer.parseInt(tamaño.getString(0))];
                IdTipoIdentificacion = new String[Integer.parseInt(tamaño.getString(0))];

                for (int i = 0; i < TipoIdentificacion.length; i++) {

                    if (consulta.moveToNext()) {

                        TipoIdentificacion[i] = consulta.getString(1);
                        IdTipoIdentificacion[i] = consulta.getString(0);
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
