package com.google.android.gms.samples.vision.ocrreader;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.Lienzo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NuevaSolicitud extends AppCompatActivity {

    TextView textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead, textDocumentosHead,
            conector1, conector2, conector3, conector4, conector5;

    NestedScrollView scrollNuevaSolicitud;

    DatePicker PickerNac;

    Button btnEnviarSolicitud;

    //Valores para abrir la camara y tomar la foto
    private static final int CAMERA_REQUEST1 = 1881;
    private static final int CAMERA_REQUEST2 = 1882;
    private static final int CAMERA_REQUEST3 = 1883;
    private static final int CAMERA_REQUEST4 = 1884;

    private static final int CAMERA_EXTRA1 = 1885;
    private static final int CAMERA_EXTRA2 = 1886;
    private static final int CAMERA_EXTRA3 = 1887;
    private static final int CAMERA_EXTRA4 = 1888;
    private static final int CAMERA_EXTRA5 = 1889;

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

    //Declaracion del spinner numero de dependientes con su lista en datos generales
    Spinner SpinnerNumeroDependientes;
    String NumeroDependientes[];
    String ValorNumeroDependientes;

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
    EditText txtSolicitanteGeneral, txtSegundoNombreGeneral, txtPaternoGeneral, txtMaternoGeneral, txtNumeroIdentificacion, txtRFC;
    RadioButton radioMujer, radioHombre;
    RadioGroup radioSexo;
    String Sexo = "FEMENINO";

    //Declaracion de los edittext de domicilio
    EditText txtCalle, txtNoExterior, txtNoInterior, txtColonia, txtCP, txtTiempoResidencia, txtMontoVivienda, txtCorreo, txtTelefonoCasa, txtTelefonoCelular;

    //Declaracion de los edittes de datos económicos
    EditText txtNombreEmpresa, txtGiro, txtAntiguedadEmpleo, txtPuesto, txtIngreso, txtTiempoCasado, txtFuenteIngresos, txtOtrosIngresos, txtCalleIngresos,
            txtNoExteriorIngresos, txtNoInteriorIngresos, txtColoniaIngresos, txtCPIngresos, txtTelefonoOficina, txtExtension;

    //Declaracion de los radioButton y edittext para persona politica
    RadioButton radioButton, radioButton2, radioButton5, radioButton6;
    RadioGroup radioGrupo1, radioGrupo2;
    String Grupo1 = "NO";
    String Grupo2 = "NO";
    EditText txtFuncionPolitica, txtFuncionParentesco, txtParentescoPolitico;

    //Declaracion de los edittext de referencias familiares
    EditText txtNombrePrimera, txtPaternoPrimera, txtMaternoPrimera, txtTelefonoPrimera,
            txtNombreSegunda, txtPaternoSegunda, txtMaternoSegunda, txtTelefonoSegunda,
            txtNombreTercera, txtPaternoTercera, txtMaternoTercera, txtTelefonoTercera;

    //Variables de los datos del usuario logueado
    String usuario, password, empresa, idUsuario;

    //Variables para guardar la imagen en Base 64 y su nombre (Tec_)
    String Base64IdentificacionFrente, Base64IdentificacionAnverso, Base64Contrato1, Base64Contrato2, Base64Firma, Base64Extra1 = "", Base64Extra2 = "", Base64Extra3 = "", Base64Extra4 = "", Base64Extra5 = "";
    String NombreIdentificacionFrente = "", NombreIdentificacionAnverso = "", NombreContrato1 = "", NombreContrato2 = "", NombreFirma = "", NombreExtra1 = "", NombreExtra2 = "", NombreExtra3 = "", NombreExtra4 = "", NombreExtra5 = "";

    //Declaracion de los imageButton y textview para la parte de documentos
    ImageButton ImgExtra1, ImgExtra2, ImgExtra3, ImgExtra4, ImgExtra5;

    //Identificador
    TextView textExtra1, textExtra2, textExtra3, textExtra4, textExtra5;

    //Nombre de la foto en textview de extras
    TextView textNombreExtra1, textNombreExtra2, textNombreExtra3, textNombreExtra4, textNombreExtra5;

    //Textview de pruebas
    TextView textPruebas;

    String buzon = "";

    int idAutoIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud2);

        consultaBuzonActivo();

        //Creacion del view de TexviewPruebas
        textPruebas = (TextView) findViewById(R.id.textPruebas);

        //Se reciben los datos desde el login
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        password = intent.getStringExtra("password");
        empresa = intent.getStringExtra("empresa");
        idUsuario = intent.getStringExtra("idUsuario");

        //Creacion de los edittext y radiobutton de generales a través de su Id
        txtSolicitanteGeneral = (EditText) findViewById(R.id.txtSolicitanteGeneral);
        txtSegundoNombreGeneral = (EditText) findViewById(R.id.txtSegundoNombreGeneral);
        txtPaternoGeneral = (EditText) findViewById(R.id.txtPaternoGeneral);
        txtMaternoGeneral = (EditText) findViewById(R.id.txtMaternoGeneral);
        txtNumeroIdentificacion = (EditText) findViewById(R.id.txtNumeroIdentificacion);
        txtRFC = (EditText) findViewById(R.id.txtRFC);
        radioMujer = (RadioButton) findViewById(R.id.RadioMujer);
        radioHombre = (RadioButton) findViewById(R.id.RadioHombre);
        radioSexo = (RadioGroup) findViewById(R.id.GrupoSexo);

        //Creacion de los edittext de domicilio a través de su ID
        txtCalle = (EditText) findViewById(R.id.txtCalle);
        txtNoExterior = (EditText) findViewById(R.id.txtNoExterior);
        txtNoInterior = (EditText) findViewById(R.id.txtNoInterior);
        txtColonia = (EditText) findViewById(R.id.txtColonia);
        txtCP = (EditText) findViewById(R.id.txtCP);
        txtTiempoResidencia = (EditText) findViewById(R.id.txtTiempoResidencia);
        txtMontoVivienda = (EditText) findViewById(R.id.txtMontoVivienda);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtTelefonoCasa = (EditText) findViewById(R.id.txtTelefonoCasa);
        txtTelefonoCelular = (EditText) findViewById(R.id.txtTelefonoCelular);

        //Creacion de los edittext de datos económicos a través de su Id
        txtNombreEmpresa = (EditText) findViewById(R.id.txtNombreEmpresa);
        txtGiro = (EditText) findViewById(R.id.txtGiro);
        txtAntiguedadEmpleo = (EditText) findViewById(R.id.txtAntiguedadEmpleo);
        txtPuesto = (EditText) findViewById(R.id.txtPuesto);
        txtIngreso = (EditText) findViewById(R.id.txtIngreso);
        txtTiempoCasado = (EditText) findViewById(R.id.txtTiempoCasado);
        txtFuenteIngresos = (EditText) findViewById(R.id.txtFuenteIngresos);
        txtOtrosIngresos = (EditText) findViewById(R.id.txtOtrosIngresos);
        txtCalleIngresos = (EditText) findViewById(R.id.txtCalleIngresos);
        txtNoExteriorIngresos = (EditText) findViewById(R.id.txtNoExteriorIngresos);
        txtNoInteriorIngresos = (EditText) findViewById(R.id.txtNoInteriorIngresos);
        txtColoniaIngresos = (EditText) findViewById(R.id.txtColoniaIngresos);
        txtCPIngresos = (EditText) findViewById(R.id.txtCPIngresos);
        txtTelefonoOficina = (EditText) findViewById(R.id.txtTelefonoOficina);
        txtExtension = (EditText) findViewById(R.id.txtExtension);

        //Creacion de los radioButton y edittext de persona politica a través de su Id
        radioGrupo1 = (RadioGroup) findViewById(R.id.Grupo1);
        radioGrupo2 = (RadioGroup) findViewById(R.id.Grupo2);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton6);
        txtFuncionPolitica = (EditText) findViewById(R.id.txtFuncionPolitica);
        txtFuncionParentesco = (EditText) findViewById(R.id.txtFuncionParentesco);
        txtParentescoPolitico = (EditText) findViewById(R.id.txtParentescoPolitico);

        //Creación de los edittext de referencias familiares a través de su ID
        txtNombrePrimera = (EditText) findViewById(R.id.txtNombrePrimera);
        txtPaternoPrimera = (EditText) findViewById(R.id.txtPaternoPrimera);
        txtMaternoPrimera = (EditText) findViewById(R.id.txtMaternoPrimera);
        txtTelefonoPrimera = (EditText) findViewById(R.id.txtNombrePrimera);
        txtNombreSegunda = (EditText) findViewById(R.id.txtNombreSegundaR);
        txtPaternoSegunda = (EditText) findViewById(R.id.txtPaternoSegunda);
        txtMaternoSegunda = (EditText) findViewById(R.id.txtMaternoSegunda);
        txtTelefonoSegunda = (EditText) findViewById(R.id.txtTelefonoSegunda);
        txtNombreTercera = (EditText) findViewById(R.id.txtNombreTercera);
        txtPaternoTercera = (EditText) findViewById(R.id.txtPaternoTercera);
        txtMaternoTercera = (EditText) findViewById(R.id.txtMaternoTercera);
        txtTelefonoTercera = (EditText) findViewById(R.id.txtNombreTercera);

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

        PickerNac = (DatePicker) findViewById(R.id.PickerNac);
        PickerNac.setCalendarViewShown(false);
        PickerNac.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

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
        SpinnerNumeroDependientes = (Spinner) findViewById(R.id.SpinnerNumeroDependientes);
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

        //Creación de los view de los imageButton y textview para la parte de documentos
        ImgExtra1 = (ImageButton) findViewById(R.id.ImgExtra1);
        ImgExtra2 = (ImageButton) findViewById(R.id.ImgExtra2);
        ImgExtra3 = (ImageButton) findViewById(R.id.ImgExtra3);
        ImgExtra4 = (ImageButton) findViewById(R.id.ImgExtra4);
        ImgExtra5 = (ImageButton) findViewById(R.id.ImgExtra5);

        //Identficador
        textExtra1 = (TextView) findViewById(R.id.textExtra1);
        textExtra2 = (TextView) findViewById(R.id.textExtra2);
        textExtra3 = (TextView) findViewById(R.id.textExtra3);
        textExtra4 = (TextView) findViewById(R.id.textExtra4);
        textExtra5 = (TextView) findViewById(R.id.textExtra5);

        //Nombre de las fotos de extras
        textNombreExtra1 = (TextView) findViewById(R.id.textNombreExtra1);
        textNombreExtra2 = (TextView) findViewById(R.id.textNombreExtra2);
        textNombreExtra3 = (TextView) findViewById(R.id.textNombreExtra3);
        textNombreExtra4 = (TextView) findViewById(R.id.textNombreExtra4);
        textNombreExtra5 = (TextView) findViewById(R.id.textNombreExtra5);

        //Se ocultan los edittext de persona politica ya que estan marcados como no
        txtFuncionPolitica.setVisibility(View.GONE);
        txtFuncionParentesco.setVisibility(View.GONE);
        txtParentescoPolitico.setVisibility(View.GONE);

        txtFuncionPolitica.setText("");
        txtFuncionParentesco.setText("");
        txtParentescoPolitico.setText("");

        //Se ocultan los imagebutton y textview para que se hagan visibles al pulsar uno secuncialmente
        ImgExtra2.setVisibility(View.GONE);
        ImgExtra3.setVisibility(View.GONE);
        ImgExtra4.setVisibility(View.GONE);
        ImgExtra5.setVisibility(View.GONE);

        //Identificador
        textExtra2.setVisibility(View.GONE);
        textExtra3.setVisibility(View.GONE);
        textExtra4.setVisibility(View.GONE);
        textExtra5.setVisibility(View.GONE);

        //Etiquetas para los nombres de las fotos de extras
        textNombreExtra2.setVisibility(View.GONE);
        textNombreExtra3.setVisibility(View.GONE);
        textNombreExtra4.setVisibility(View.GONE);
        textNombreExtra5.setVisibility(View.GONE);

        //Verifica cual es el catalago activo y hace una cosulta para tomar los valores y llenar el spinner
        consultaCatalogoActivo();

        Calendar c = Calendar.getInstance();
        PickerNac.setMaxDate(c.getTimeInMillis());
        PickerNac.updateDate(1999, 00, 01);

        //Lista de numero de dependientes con sus respectivos datos
        NumeroDependientes = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        //Llenado de los spinner con los arreglos de tipo identificacion
        final ArrayAdapter<String> listaTipoIdentificacion = new ArrayAdapter<String>(this, R.layout.spinner_item, TipoIdentificacion);
        SpinnerTipoIdentificacion.setAdapter(listaTipoIdentificacion);

        //Llenado de los spinner con los arreglos de nacionalidad general
        final ArrayAdapter<String> listaNacionalidadGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, NacionalidadGeneral);
        SpinnerNacionalidadGeneral.setAdapter(listaNacionalidadGeneral);

        //Llenado de los spinner con los arreglos de estado civil general
        final ArrayAdapter<String> listaEdoCivilGeneral = new ArrayAdapter<String>(this, R.layout.spinner_item, EdoCivilGeneral);
        SpinnerEdoCivilGeneral.setAdapter(listaEdoCivilGeneral);

        //Llenado del spinner con su lista de numero de dependintes en datos generales
        final ArrayAdapter<String> listaNumeroDependientes = new ArrayAdapter<String>(this, R.layout.spinner_item, NumeroDependientes);
        SpinnerNumeroDependientes.setAdapter(listaNumeroDependientes);

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

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

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

        SpinnerNumeroDependientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ValorNumeroDependientes = listaNumeroDependientes.getItem(position);
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

                if (txtSolicitanteGeneral.getText().toString().isEmpty() || txtPaternoGeneral.getText().toString().isEmpty() || txtMaternoGeneral.getText().toString().isEmpty()
                        || txtNumeroIdentificacion.getText().toString().isEmpty() || txtRFC.getText().toString().isEmpty() || txtCalle.getText().toString().isEmpty() || txtNoExterior.getText().toString().isEmpty()
                        || txtColonia.getText().toString().isEmpty() || txtCP.getText().toString().isEmpty() || txtTiempoResidencia.getText().toString().isEmpty() || txtMontoVivienda.getText().toString().isEmpty()
                        || txtCorreo.getText().toString().isEmpty() || txtTelefonoCasa.getText().toString().isEmpty() || txtTelefonoCelular.getText().toString().isEmpty() || txtNombreEmpresa.getText().toString().isEmpty()
                        || txtGiro.getText().toString().isEmpty() || txtAntiguedadEmpleo.getText().toString().isEmpty() || txtPuesto.getText().toString().isEmpty() || txtIngreso.getText().toString().isEmpty()
                        || txtCalleIngresos.getText().toString().isEmpty() || txtNoExteriorIngresos.getText().toString().isEmpty() || txtColoniaIngresos.getText().toString().isEmpty()
                        || txtCPIngresos.getText().toString().isEmpty() || txtTelefonoOficina.getText().toString().isEmpty() || txtNombrePrimera.getText().toString().isEmpty()
                        || txtPaternoPrimera.getText().toString().isEmpty() || txtMaternoPrimera.getText().toString().isEmpty() || txtTelefonoPrimera.getText().toString().isEmpty()
                        || txtNombreSegunda.getText().toString().isEmpty() || txtPaternoSegunda.getText().toString().isEmpty() || txtMaternoSegunda.getText().toString().isEmpty()
                        || txtTelefonoSegunda.getText().toString().isEmpty()){

                    if (txtSolicitanteGeneral.getText().toString().isEmpty()){

                        txtSolicitanteGeneral.setBackgroundResource(R.drawable.edittext_redondo_error);
                    }

                    AlertDialogCheck();

                }

                /*idAutoIncrement();

                String Estatus = "6";
                String comentario = "";
                String motivo = "0";
                String producto = "";
                String promedio = "";

                String diaActual, mesActual, anioActual;
                int anioActualResta;

                Calendar calendar = Calendar.getInstance();
                diaActual = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                mesActual = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                anioActual = String.valueOf(calendar.get(Calendar.YEAR));
                anioActualResta = calendar.get(Calendar.YEAR);

                String fechaActual = diaActual + "/" + mesActual + "/" + anioActual;

                String diaNac, mesNac, anioNac;
                int anioNacResta;

                diaNac = String.valueOf(PickerNac.getDayOfMonth());
                mesNac = String.valueOf(PickerNac.getMonth() + 1);
                anioNac = String.valueOf(PickerNac.getYear());

                anioNacResta = PickerNac.getYear();

                int edad = anioActualResta - anioNacResta;

                if (edad >= 18) {

                    if (validarEmail(txtCorreo.getText().toString())) {

                        String solicitud_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<SolicitudType xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                                "  <Lattitude>19.4140762787095</Lattitude>\n" +
                                "  <Longuitud>-99.0129281651914</Longuitud>\n" +
                                "  <generales>\n" +
                                "    <Tpoidentif>" + PosicionTipoIdentificacion + "</Tpoidentif>\n" +
                                "    <Noidenficacion>" + txtNumeroIdentificacion.getText().toString() + "</Noidenficacion>\n" +
                                "    <Pmrnombre>" + txtSolicitanteGeneral.getText().toString() + "</Pmrnombre>\n" +
                                "    <Sdonombre>" + txtSegundoNombreGeneral.getText().toString() + "</Sdonombre>\n" +
                                "    <Apaterno>" + txtPaternoGeneral.getText().toString() + "</Apaterno>\n" +
                                "    <Amaterno>" + txtMaternoGeneral.getText().toString() + "</Amaterno>\n" +
                                "    <Sexo>" + Sexo + "</Sexo>\n" +
                                "    <Nacionalidad>" + PosicionNacionalidadGeneral + "</Nacionalidad>\n" +
                                "    <Fechanacdia>" + diaNac + "</Fechanacdia>\n" +
                                "    <Rfc>" + txtRFC.getText().toString() + "</Rfc>\n" +
                                "    <Edocivil>" + PosicionEdoCivilGeneral + "</Edocivil>\n" +
                                "    <Nodependiente>" + ValorNumeroDependientes + "</Nodependiente>\n" +
                                "    <Cveperspol>2</Cveperspol>\n" +
                                "    <FechasnacMes>" + mesNac + "</FechasnacMes>\n" +
                                "    <FechanacAnio>" + anioNac + "</FechanacAnio>\n" +
                                "  </generales>\n" +
                                "  <doc>\n" +
                                "    <IdentificacionFrentePath>" + NombreIdentificacionFrente + "</IdentificacionFrentePath>\n" +
                                "    <IdentificacionAtrasPath>" + NombreIdentificacionAnverso + "</IdentificacionAtrasPath>\n" +
                                "    <Contrato1Path>" + NombreContrato1 + "</Contrato1Path>\n" +
                                "    <Contrato2Path>" + NombreContrato2 + "</Contrato2Path>\n" +
                                "    <Extra1>" + NombreExtra1 + "</Extra1>\n" +
                                "    <Extra2>" + NombreExtra2 + "</Extra2>\n" +
                                "    <Extra3>" + NombreExtra3 + "</Extra3>\n" +
                                "    <Extra4>" + NombreExtra4 + "</Extra4>\n" +
                                "    <Extra5>" + NombreExtra5 + "</Extra5>\n" +
                                "    <FirmaPath>" + NombreFirma + "</FirmaPath>\n" +
                                "  </doc>\n" +
                                "  <domicilio>\n" +
                                "    <Calle>" + txtCalle.getText().toString() + "</Calle>\n" +
                                "    <NoInt>" + txtNoInterior.getText().toString() + "</NoInt>\n" +
                                "    <NoExt>" + txtNoExterior.getText().toString() + "</NoExt>\n" +
                                "    <Cpdom>" + txtCP.getText().toString() + "</Cpdom>\n" +
                                "    <Estado>" + PosicionEstadoDomicilio + "</Estado>\n" +
                                "    <Delegacion>" + PosicionDelegacionDomicilio + "</Delegacion>\n" +
                                "    <Colonia>" + txtColonia.getText().toString() + "</Colonia>\n" +
                                "    <TiempoResidencia>" + txtTiempoResidencia.getText().toString() + "</TiempoResidencia>\n" +
                                "    <EstatusResidencia>" + PosicionEstatusDomicilio + "</EstatusResidencia>\n" +
                                "    <MontoVivienda>" + txtMontoVivienda.getText().toString() + "</MontoVivienda>\n" +
                                "    <Email>" + txtCorreo.getText().toString() + "</Email>\n" +
                                "    <Telcasa>" + txtTelefonoCasa.getText().toString() + "</Telcasa>\n" +
                                "    <Telmovil>" + txtTelefonoCelular.getText().toString() + "</Telmovil>\n" +
                                "    <CompaniaMovil>" + PosicionCompaniaMovil + "</CompaniaMovil>\n" +
                                "  </domicilio>\n" +
                                "  <Personapolitica>\n" +
                                "    <EsPersonaPolitica>" + Grupo1 + "</EsPersonaPolitica>\n" +
                                "    <TipoParentesco>" + txtParentescoPolitico.getText().toString() + "</TipoParentesco>\n" +
                                "    <Descfuncion>" + txtFuncionPolitica.getText().toString() + "</Descfuncion>\n" +
                                "    <Descparentesco>" + txtFuncionParentesco.getText().toString() + "</Descparentesco>\n" +
                                "    <TieneParentesco>" + Grupo2 + "</TieneParentesco>\n" +
                                "  </Personapolitica>\n" +
                                "  <Refer>\n" +
                                "    <Pmrnombre>" + txtNombrePrimera.getText().toString() + "</Pmrnombre>\n" +
                                "    <Sdonombre/>\n" +
                                "    <Apaterno>" + txtPaternoPrimera.getText().toString() + "</Apaterno>\n" +
                                "    <Amaterno>" + txtMaternoPrimera.getText().toString() + "</Amaterno>\n" +
                                "    <Nacionalidad>" + PosicionNacionalidadPrimera + "</Nacionalidad>\n" +
                                "    <TelefonoCasa>" + txtTelefonoPrimera.getText().toString() + "</TelefonoCasa>\n" +
                                "  </Refer>\n" +
                                "  <Refer2>\n" +
                                "    <Pmrnombre>" + txtNombreSegunda.getText().toString() + "</Pmrnombre>\n" +
                                "    <Sdonombre/>\n" +
                                "    <Apaterno>" + txtPaternoSegunda.getText().toString() + "</Apaterno>\n" +
                                "    <Amaterno>" + txtMaternoSegunda.getText().toString() + "</Amaterno>\n" +
                                "    <Nacionalidad>" + PosicionNacionalidadSegunda + "</Nacionalidad>\n" +
                                "    <TelefonoCasa>" + txtTelefonoSegunda.getText().toString() + "</TelefonoCasa>\n" +
                                "  </Refer2>\n" +
                                "  <Refer3>\n" +
                                "    <Pmrnombre>" + txtNombreTercera.getText().toString() + "</Pmrnombre>\n" +
                                "    <Sdonombre/>\n" +
                                "    <Apaterno>" + txtPaternoTercera.getText().toString() + "</Apaterno>\n" +
                                "    <Amaterno>" + txtMaternoTercera.getText().toString() + "</Amaterno>\n" +
                                "    <Nacionalidad>" + PosicionNacionalidadTercera + "</Nacionalidad>\n" +
                                "    <TelefonoCasa>" + txtTelefonoTercera.getText().toString() + "</TelefonoCasa>\n" +
                                "  </Refer3>\n" +
                                "  <Promotor>\n" +
                                "    <Compania>" + empresa + "</Compania>\n" +
                                "    <Usuario>" + usuario + "</Usuario>\n" +
                                "    <Contrasenia>" + password + "</Contrasenia>\n" +
                                "  </Promotor>\n" +
                                "  <FolioLocal>0</FolioLocal>\n" +
                                "  <DiaCreacion>" + diaActual + "</DiaCreacion>\n" +
                                "  <MesCreacion>" + mesActual + "</MesCreacion>\n" +
                                "  <AnioCreacion>" + anioActual + "</AnioCreacion>\n" +
                                "  <Deconominos>\n" +
                                "    <TipoContrato>" + PosicionTipoContrato + "</TipoContrato>\n" +
                                "    <AntiguedadEmpleo>" + txtAntiguedadEmpleo.getText().toString() + "</AntiguedadEmpleo>\n" +
                                "    <AniosCasada>" + txtTiempoCasado.getText().toString() + "</AniosCasada>\n" +
                                "    <Ingresos>" + txtIngreso.getText().toString() + "</Ingresos>\n" +
                                "    <NombreEmpresa>" + txtNombreEmpresa.getText().toString() + "</NombreEmpresa>\n" +
                                "    <GiroEmpresa>" + txtGiro.getText().toString() + "</GiroEmpresa>\n" +
                                "    <Puesto>" + txtPuesto.getText().toString() + "</Puesto>\n" +
                                "    <Domicilio>\n" +
                                "      <Calle>" + txtCalleIngresos.getText().toString() + "</Calle>\n" +
                                "      <NoInt>" + txtNoInteriorIngresos.getText().toString() + "</NoInt>\n" +
                                "      <NoExt>" + txtNoExteriorIngresos.getText().toString() + "</NoExt>\n" +
                                "      <Cpdom>" + txtCPIngresos.getText().toString() + "</Cpdom>\n" +
                                "      <Estado>" + PosicionEstadoIngresos + "</Estado>\n" +
                                "      <Delegacion>" + PosicionDelegacionIngresos + "</Delegacion>\n" +
                                "      <Colonia>" + txtColoniaIngresos.getText().toString() + "</Colonia>\n" +
                                "      <TiempoResidencia>0</TiempoResidencia>\n" +
                                "      <EstatusResidencia>0</EstatusResidencia>\n" +
                                "      <MontoVivienda>0</MontoVivienda>\n" +
                                "      <Telcasa>" + txtTelefonoOficina.getText().toString() + "</Telcasa>\n" +
                                "      <Telmovil/>\n" +
                                "    </Domicilio>\n" +
                                "    <OtrosIngresos>" + txtOtrosIngresos.getText().toString() + "</OtrosIngresos>\n" +
                                "    <FuenteOtrosIngresos>" + txtFuenteIngresos.getText().toString() + "</FuenteOtrosIngresos>\n" +
                                "  </Deconominos>\n" +
                                "</SolicitudType>";

                        insertarSolicitud(idAutoIncrement, fechaActual, Estatus, idUsuario, comentario, motivo, fechaActual, solicitud_xml, promedio, producto, Base64Contrato1, Base64Contrato2, Base64IdentificacionAnverso, Base64IdentificacionFrente, Base64Firma, Base64Extra1, Base64Extra2, Base64Extra3, Base64Extra4, Base64Extra5);

                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                        finish();

                        //borrar();

                    } else {

                        toast("Email no valido");
                    }

                } else {

                    toast("Verifique la fecha de nacimiento");
                }*/
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

                } else if (scrollY > 5850 && scrollY < 7250) {

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

                } else if (scrollY > 7250 && scrollY < 9400) {

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

                } else if (scrollY > 9400) {

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

    public void AlertDialogCheck() {

        runOnUiThread(new Runnable() {
            public void run() {

                final ArrayList seletedItems=new ArrayList();

                final String[] respuesta = new String[1];

                CharSequence[] item = {"Si"};

                // Instanciamos un nuevo AlertDialog Builder y le asociamos titulo y mensaje
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NuevaSolicitud.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Solictud incompleta, ¿Desea guardarla?");
                alertDialogBuilder.setMultiChoiceItems(item, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked){

                            seletedItems.add(which);

                            respuesta[0] = String.valueOf(seletedItems.get(which));

                        }else {

                            seletedItems.remove(which);
                            respuesta[0] = String.valueOf(seletedItems.isEmpty());
                        }
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (respuesta[0] != null){

                            toast("si hay");
                        }else {

                            toast("no hay");
                        }
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public void foto(View view) {

        Fragment fragment = null;

        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        String nombre = "TEC_" + seconds + ".jpg";

        Lienzo lienzo = (Lienzo) findViewById(R.id.Lienzo);

        Intent abrirCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch (view.getId()) {

            case R.id.ImgIdentificacionFrenteNew:

                //abrirCamara.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(abrirCamara, CAMERA_REQUEST1);

                break;

            case R.id.ImgIdentificacionAtrasNew:

                startActivityForResult(abrirCamara, CAMERA_REQUEST2);

                break;

            case R.id.ImgContrato1New:

                //abrirCamara.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(abrirCamara, CAMERA_REQUEST3);

                break;

            case R.id.ImgContrato2New:

                startActivityForResult(abrirCamara, CAMERA_REQUEST4);

                break;

            case R.id.ImgFirmaNew:

                fragment = new FragmentFirma();

                scrollNuevaSolicitud.setVisibility(View.GONE);

                break;

            case R.id.ImgExtra1:

                startActivityForResult(abrirCamara, CAMERA_EXTRA1);

                break;

            case R.id.ImgExtra2:

                startActivityForResult(abrirCamara, CAMERA_EXTRA2);

                break;

            case R.id.ImgExtra3:

                startActivityForResult(abrirCamara, CAMERA_EXTRA3);

                break;

            case R.id.ImgExtra4:

                startActivityForResult(abrirCamara, CAMERA_EXTRA4);

                break;

            case R.id.ImgExtra5:

                startActivityForResult(abrirCamara, CAMERA_EXTRA5);

                break;

            case R.id.btnBorrarFirma:

                lienzo.clearCanvas();

                break;

            case R.id.btnGuardarFirma:

                Bitmap viewCapture = null;

                lienzo.setDrawingCacheEnabled(true);

                viewCapture = Bitmap.createBitmap(lienzo.getDrawingCache());

                lienzo.setDrawingCacheEnabled(false);

                ImgFirma.setImageBitmap(viewCapture);

                lienzo.clearCanvas();

                NombreFirma = nombre;

                TextView textNombreFirma = (TextView) findViewById(R.id.textNombreFirma);
                textNombreFirma.setText(NombreFirma);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                viewCapture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                Base64Firma = Base64.encodeToString(byteArray, Base64.DEFAULT);

                fragment = new FragmentFirma();

                if (fragment != null) {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.hide(fragment);
                    ft.commit();
                }

                scrollNuevaSolicitud.setVisibility(View.VISIBLE);

                break;
        }

        if (fragment != null) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        String nombre = "TEC_" + seconds + ".jpg";

        if (requestCode == CAMERA_REQUEST1 && resultCode == RESULT_OK) {

            Bitmap foto1 = (Bitmap) data.getExtras().get("data");
            ImgIdentificacionFrente.setImageBitmap(foto1);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            foto1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64IdentificacionFrente = Base64.encodeToString(byteArray, Base64.DEFAULT);

            TextView textNombreIdentificacionFrente = (TextView) findViewById(R.id.textNombreIdentificacionFrente);
            textNombreIdentificacionFrente.setText(nombre);

            NombreIdentificacionFrente = nombre;
        }

        if (requestCode == CAMERA_REQUEST2 && resultCode == RESULT_OK) {

            Bitmap foto2 = (Bitmap) data.getExtras().get("data");
            ImgIdentificacionAtras.setImageBitmap(foto2);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            foto2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64IdentificacionAnverso = Base64.encodeToString(byteArray, Base64.DEFAULT);

            TextView textNombreIdentificacionAnverso = (TextView) findViewById(R.id.textNombreIdentificacionAnverso);
            textNombreIdentificacionAnverso.setText(nombre);

            NombreIdentificacionAnverso = nombre;
        }

        if (requestCode == CAMERA_REQUEST3 && resultCode == RESULT_OK) {

            Bitmap foto3 = (Bitmap) data.getExtras().get("data");
            ImgContrato1.setImageBitmap(foto3);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            foto3.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Contrato1 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            TextView textNombreContrato1 = (TextView) findViewById(R.id.textNombreContrato1);
            textNombreContrato1.setText(nombre);

            NombreContrato1 = nombre;
        }

        if (requestCode == CAMERA_REQUEST4 && resultCode == RESULT_OK) {
            Bitmap foto4 = (Bitmap) data.getExtras().get("data");
            ImgContrato2.setImageBitmap(foto4);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            foto4.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Contrato2 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            TextView textNombreContrato2 = (TextView) findViewById(R.id.textNombreContrato2);
            textNombreContrato2.setText(nombre);

            NombreContrato2 = nombre;
        }

        if (requestCode == CAMERA_EXTRA1 && resultCode == RESULT_OK) {

            Bitmap fotoExtra1 = (Bitmap) data.getExtras().get("data");
            ImgExtra1.setImageBitmap(fotoExtra1);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fotoExtra1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Extra1 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            textNombreExtra1.setText(nombre);

            ImgExtra2.setVisibility(View.VISIBLE);
            textExtra2.setVisibility(View.VISIBLE);
            textNombreExtra2.setVisibility(View.VISIBLE);

        }

        if (requestCode == CAMERA_EXTRA2 && resultCode == RESULT_OK) {

            Bitmap fotoExtra2 = (Bitmap) data.getExtras().get("data");
            ImgExtra2.setImageBitmap(fotoExtra2);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fotoExtra2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Extra2 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            textNombreExtra2.setText(nombre);

            ImgExtra3.setVisibility(View.VISIBLE);
            textExtra3.setVisibility(View.VISIBLE);
            textNombreExtra3.setVisibility(View.VISIBLE);

        }

        if (requestCode == CAMERA_EXTRA3 && resultCode == RESULT_OK) {

            Bitmap fotoExtra3 = (Bitmap) data.getExtras().get("data");
            ImgExtra3.setImageBitmap(fotoExtra3);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fotoExtra3.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Extra3 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            textNombreExtra3.setText(nombre);

            ImgExtra4.setVisibility(View.VISIBLE);
            textExtra4.setVisibility(View.VISIBLE);
            textNombreExtra4.setVisibility(View.VISIBLE);

        }

        if (requestCode == CAMERA_EXTRA4 && resultCode == RESULT_OK) {

            Bitmap fotoExtra4 = (Bitmap) data.getExtras().get("data");
            ImgExtra4.setImageBitmap(fotoExtra4);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fotoExtra4.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Extra4 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            textNombreExtra4.setText(nombre);

            ImgExtra5.setVisibility(View.VISIBLE);
            textExtra5.setVisibility(View.VISIBLE);
            textNombreExtra5.setVisibility(View.VISIBLE);

        }

        if (requestCode == CAMERA_EXTRA5 && resultCode == RESULT_OK) {

            Bitmap fotoExtra5 = (Bitmap) data.getExtras().get("data");
            ImgExtra5.setImageBitmap(fotoExtra5);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fotoExtra5.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Base64Extra5 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            textNombreExtra5.setText(nombre);

        }
    }

    public void sexo(View view) {

        //Verifica que radio button de sexo esta marcado
        if (radioHombre.isChecked()) {

            Sexo = "MASCULINO";
        } else if (radioMujer.isChecked()) {

            Sexo = "FEMENINO";
        }
    }

    public void Grupo1(View view) {

        //Obtención del valor de los radio button de los 2 grupos de persona politica
        //Grupo1
        if (radioButton.isChecked()) {

            Grupo1 = "SI";
            txtFuncionPolitica.setVisibility(View.VISIBLE);
        } else if (radioButton2.isChecked()) {

            Grupo1 = "NO";
            txtFuncionPolitica.setVisibility(View.GONE);
        }
    }

    public void Grupo2(View view) {

        //Obtención del valor de los radio button de los 2 grupos de persona politica
        //Grupo2
        if (radioButton5.isChecked()) {

            Grupo2 = "SI";
            txtFuncionParentesco.setVisibility(View.VISIBLE);
            txtParentescoPolitico.setVisibility(View.VISIBLE);
        } else if (radioButton6.isChecked()) {

            Grupo2 = "NO";
            txtFuncionParentesco.setVisibility(View.GONE);
            txtParentescoPolitico.setVisibility(View.GONE);
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

                toast("Permisos concedidos");
            }
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void consultaBuzonActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from PARAMETRO where id_parametro = 1", null);

        if (consulta.moveToNext()) {

            buzon = consulta.getString(2);
        }
    }

    public void borrar() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from Buzon_B where estatus = 6");

        toast("Borrado registro");

        db.close();
    }

    public void idAutoIncrement() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        int id = 0;

        if (buzon.equals("A")) {
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor consulta = db.rawQuery("select count (*) from BUZON_A where estatus = 6 or estatus = 0", null);

            if (consulta.moveToNext()) {

                id = Integer.parseInt(consulta.getString(0));

                idAutoIncrement = id + 1;

            } else {

                idAutoIncrement = id + 1;

            }

        } else if (buzon.equals("B")) {
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor consulta = db.rawQuery("select count (*) from BUZON_B where estatus = 6 or estatus = 0", null);

            if (consulta.moveToNext()) {

                id = Integer.parseInt(consulta.getString(0));

                idAutoIncrement = id + 1;

            } else {

                idAutoIncrement = id + 1;

            }
        }
    }

    public void insertarSolicitud(long idSolicitud, String fechaAlta, String estatus, String idUsuario, String comentario, String motivo, String fechaModificacion, String solicitudXML, String promedio, String producto, String contrato1, String contrato2, String identificacionAnverso, String identificacionFrente, String firma, String extra1, String extra2, String extra3, String extra4, String extra5) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        if (buzon.equals("A")) {

            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("id_solicitud", idSolicitud);
            registro.put("fecha_alta", String.valueOf(fechaAlta));
            registro.put("estatus", Integer.parseInt(estatus));
            registro.put("id_usuario", Integer.parseInt(idUsuario));
            registro.put("comentario", comentario);
            registro.put("motivo", Integer.parseInt(motivo));
            registro.put("fecha_modificacion", String.valueOf(fechaModificacion));
            registro.put("solicitud_xml", solicitudXML);
            registro.put("promedio_scoring", promedio);
            registro.put("producto", producto);

            registro.put("Doc_C164", contrato1);
            registro.put("Doc_C264", contrato2);
            registro.put("Doc_IA64", identificacionAnverso);
            registro.put("Doc_IF64", identificacionFrente);
            registro.put("F164", firma);
            registro.put("Ext1", extra1);
            registro.put("Ext2", extra2);
            registro.put("Ext3", extra3);
            registro.put("Ext4", extra4);
            registro.put("Ext5", extra5);

            db.insert("BUZON_A", null, registro);

            db.close();

        } else if (buzon.equals("B")) {

            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("id_solicitud", idSolicitud);
            registro.put("fecha_alta", String.valueOf(fechaAlta));
            registro.put("estatus", Integer.parseInt(estatus));
            registro.put("id_usuario", Integer.parseInt(idUsuario));
            registro.put("comentario", comentario);
            registro.put("motivo", Integer.parseInt(motivo));
            registro.put("fecha_modificacion", String.valueOf(fechaModificacion));
            registro.put("solicitud_xml", solicitudXML);
            registro.put("promedio_scoring", promedio);
            registro.put("producto", producto);

            registro.put("Doc_C164", contrato1);
            registro.put("Doc_C264", contrato2);
            registro.put("Doc_IA64", identificacionAnverso);
            registro.put("Doc_IF64", identificacionFrente);
            registro.put("F164", firma);
            registro.put("Ext1", extra1);
            registro.put("Ext2", extra2);
            registro.put("Ext3", extra3);
            registro.put("Ext4", extra4);
            registro.put("Ext5", extra5);

            db.insert("BUZON_B", null, registro);

            db.close();

        }

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
