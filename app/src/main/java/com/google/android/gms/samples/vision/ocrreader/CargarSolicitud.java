package com.google.android.gms.samples.vision.ocrreader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CargarSolicitud extends AppCompatActivity {

    //TextView de cabecera y separadores
    TextView textIdSolicitud, textGenerales, textDomicilio, textDatosEconomicos, textPersonaPolitica, textReferenciasFamiliares,
            textGeneralesHead, textDomicilioHead, textDatosEconomicosHead, textPersonaPoliticaHead, textReferenciasFamiliaresHead, textDocumentosHead,
            conector1, conector2, conector3, conector4, conector5;

    //Cajas de texto y radiButton generales
    EditText txtSolicitanteGeneral, txtSegundoNombreGeneral, txtPaternoGeneral, txtMaternoGeneral, txtTipoGeneral, txtNumeroIdentificacion, txtNacionalidad, txtNacimiento,
            txtRFC, txtEstadoCivil, txtNumeroDependientes;

    RadioGroup GrupoSexo;
    RadioButton RadioHombre, RadioMujer;

    //Variables generales
    String Pmrnombre, Sdonombre, Apaterno, Amaterno, Sexo, Nacionalidad, Fechanacdia, FechanacMes, FechanacAnio, Rfc, Edocivil, Nodependientes, TipoIdentificacion, NumeroIdentificacion;

    //Cajas de texto de Domicilio
    EditText txtCalle, txtNoExterior, txtNoInterior, txtCP, txtEstado, txtMunicipio, txtColonia, txtTiempoResidencia, txtEstatusResidencia, txtMontoVivienda,
            txtCorreo, txtTelCasa, txtTelCelular, txtCompaniaMovil;

    //Variables domicilio
    String Calle, NoInt, NoExt, Cpdom, Estado, Delegacion, Colonia, TiempoResidencia, EstatusResidencia, MontoVivienda, Email, Telcasa, TelMovil, CompaniaMovil;

    //Cajas de texto de datos económicos
    EditText txtNombreEmpresa, txtGiro, txtAntiguedad, txtTipo, txtPuesto, txtIngresos, txtTiempoCasado, txtFuenteIngresos, txtOtrosIngresos, txtCalleIngresos,
            txtNoExteriorIngresos, txtNoInteriorIngresos, txtColoniaIngresos, txtEstadoIngresos, txtDelegacionIngresos, txtCPIngresos, txtTelOficina;

    //Variables Datos Económicos
    String NombreEmpresa, GiroEmpresa, AntiguedadEmpleo, TipoContrato, Puesto, Ingresos, AniosCasada, FuenteOtrosIngresos, OtrosIngresos, CalleIngresos, NoExtIngresos, NoIntIngresos,
            ColoniaIngresos, EstadoIngresos, DelegacionIngresos, CpdomIngresos, TelcasaIngresos;

    //RadioButton y Radio Group de persona politica
    RadioGroup Grupo1, Grupo2;
    RadioButton radioButton, radioButton2, radioButton5, radioButton6;

    //Variables de persona politica
    String TieneParentesco, EsPersonaPolitica;

    //Cajas de texto para referencias familiares
    EditText txtNombrePrimera, txtPaternoPrimera, txtMaternoPrimera, txtNacionalidadPrimera, txtTelefonoPrimera,
            txtNombreSegunda, txtPaternoSegunda, txtMaternoSegunda, txtNacionalidadSegunda, txtTelefonoSegunda,
            txtNombreTercera, txtPaternoTercera, txtMaternoTercera, txtNacionalidadTercera, txtTelefonoTercera;

    //Variables para referencias familiares
    String NombrePrimera, PaternoPrimera, MaternoPrimera, NacionalidadPrimera, TelefonoPrimera,
            NombreSegunda, PaternoSegunda, MaternoSegunda, NacionalidadSegunda, TelefonoSegunda,
            NombreTercera, PaternoTercera, MaternoTercera, NacionalidadTercera, TelefonoTercera;

    //Variables para la consulta de la tabla catalogos
    String consultaTipoIdentificacion, consultaNacionalidadGeneral, consultaNacionalidadPrimera, consultaNacionalidadSegunda, consultaNacionalidadTercera, consultaEdocivil,
            consultaEstadoDomicilio, consultaEstadoIngresos, consultaDelegacionDomicilio, consultaDelegacionIngresos, consultaCompaniaMovil, consultaEstatusResidencia, consultaTipoContrato;

    //Creación de imageButton para la parte de documentos
    ImageButton ImgIdentificacionFrente, ImgIdentificacionAtras, ImgContrato1, ImgContrato2, ImgFirma;

    //Variables para documentos
    String Doc_C164, Doc_C264, Doc_IA64, Doc_IF64, F164;

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
        conector5 = (TextView) findViewById(R.id.conector5);

        textGeneralesHead = (TextView) findViewById(R.id.textGeneralesHead);
        textDomicilioHead = (TextView) findViewById(R.id.textDomicilioHead);
        textDatosEconomicosHead = (TextView) findViewById(R.id.textDatosEconomicosHead);
        textPersonaPoliticaHead = (TextView) findViewById(R.id.textPersonaPoliticaHead);
        textReferenciasFamiliaresHead = (TextView) findViewById(R.id.textReferenciasFamiliaresHead);
        textDocumentosHead = (TextView) findViewById(R.id.textDocumentosHead);

        scrollCargarSolicitud = (NestedScrollView) findViewById(R.id.scrollCargarSolicitud);

        //Creacion de las cajas de texto y RadioButton de generales
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

        GrupoSexo = (RadioGroup) findViewById(R.id.GrupoSexo);
        RadioHombre = (RadioButton) findViewById(R.id.RadioHombre);
        RadioMujer = (RadioButton) findViewById(R.id.RadioMujer);

        //Creacion cajas de texto de Domicilio
        txtCalle = (EditText) findViewById(R.id.txtCalle);
        txtNoExterior = (EditText) findViewById(R.id.txtNoExterior);
        txtNoInterior = (EditText) findViewById(R.id.txtNoInterior);
        txtCP = (EditText) findViewById(R.id.txtCP);
        txtEstado = (EditText) findViewById(R.id.txtEstado);
        txtMunicipio = (EditText) findViewById(R.id.txtMunicipio);
        txtColonia = (EditText) findViewById(R.id.txtColonia);
        txtTiempoResidencia = (EditText) findViewById(R.id.txtTiempoResidencia);
        txtEstatusResidencia = (EditText) findViewById(R.id.txtEstatusResidencia);
        txtMontoVivienda = (EditText) findViewById(R.id.txtMontoVivienda);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtTelCasa = (EditText) findViewById(R.id.txtTelefonoCasa);
        txtTelCelular = (EditText) findViewById(R.id.txtTelefonoCelular);
        txtCompaniaMovil = (EditText) findViewById(R.id.txtCompañiaMovil);

        //Creación cajas de texto de datos Económicos
        txtNombreEmpresa = (EditText) findViewById(R.id.txtNombreEmpresa);
        txtGiro = (EditText) findViewById(R.id.txtGiro);
        txtAntiguedad = (EditText) findViewById(R.id.txtAnguedadEmpleo);
        txtTipo = (EditText) findViewById(R.id.txtTipoContrato);
        txtPuesto = (EditText) findViewById(R.id.txtPuesto);
        txtIngresos = (EditText) findViewById(R.id.txtIngreso);
        txtTiempoCasado = (EditText) findViewById(R.id.txtTiempoCasado);
        txtFuenteIngresos = (EditText) findViewById(R.id.txtFuenteIngresos);
        txtOtrosIngresos = (EditText) findViewById(R.id.txtOtrosIngresos);
        txtCalleIngresos = (EditText) findViewById(R.id.txtCalleIngresos);
        txtNoExteriorIngresos = (EditText) findViewById(R.id.txtNoExteriorIngresos);
        txtNoInteriorIngresos = (EditText) findViewById(R.id.txtNoInteriorIngresos);
        txtColoniaIngresos = (EditText) findViewById(R.id.txtColoniaIngresos);
        txtEstadoIngresos = (EditText) findViewById(R.id.txtEstadoIngresos);
        txtDelegacionIngresos = (EditText) findViewById(R.id.txtDelegacionIngresos);
        txtCPIngresos = (EditText) findViewById(R.id.txtCPIngresos);
        txtTelOficina = (EditText) findViewById(R.id.txtTelefonoOficina);

        //Creación del radioGroup y radioButton de persona politica
        Grupo1 = (RadioGroup) findViewById(R.id.Grupo1);
        Grupo2 = (RadioGroup) findViewById(R.id.Grupo2);

        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton6);

        //Creación de cajas de texto de referencias familiares
        txtNombrePrimera = (EditText) findViewById(R.id.txtNombrePrimera);
        txtPaternoPrimera = (EditText) findViewById(R.id.txtPaternoPrimera);
        txtMaternoPrimera = (EditText) findViewById(R.id.txtMaternoPrimera);
        txtNacionalidadPrimera = (EditText) findViewById(R.id.txtNacionalidadPrimera);
        txtTelefonoPrimera = (EditText) findViewById(R.id.txtTelefonoPrimera);

        txtNombreSegunda = (EditText) findViewById(R.id.txtNombreSegundaR);
        txtPaternoSegunda = (EditText) findViewById(R.id.txtPaternoSegunda);
        txtMaternoSegunda = (EditText) findViewById(R.id.txtMaternoSegunda);
        txtNacionalidadSegunda = (EditText) findViewById(R.id.txtNacionalidadSegunda);
        txtTelefonoSegunda = (EditText) findViewById(R.id.txtTelefonoSegunda);

        txtNombreTercera = (EditText) findViewById(R.id.txtNombreTercera);
        txtPaternoTercera = (EditText) findViewById(R.id.txtPaternoTercera);
        txtMaternoTercera = (EditText) findViewById(R.id.txtMaternoTercera);
        txtNacionalidadTercera = (EditText) findViewById(R.id.txtNacionalidadTercera);
        txtTelefonoTercera = (EditText) findViewById(R.id.txtTelefonoTercera);

        //Creacion de los imageButton para la asignación de documentos
        ImgIdentificacionFrente = (ImageButton) findViewById(R.id.ImgIdentificacionFrente);
        ImgIdentificacionAtras = (ImageButton) findViewById(R.id.ImgIdentificacionAtras);
        ImgContrato1 = (ImageButton) findViewById(R.id.ImgContrato1);
        ImgContrato2 = (ImageButton) findViewById(R.id.ImgContrato2);
        ImgFirma = (ImageButton) findViewById(R.id.ImgFirma);

        textGeneralesHead.setBackgroundColor(Color.parseColor("#00E676"));

        textIdSolicitud.setText("Id Solicitud: " + IdSolicitud);

        buzonActivo();
        catalogoActivo();

        scrollCargarSolicitud.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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

                } else if (scrollY > 5170 && scrollY < 7250) {

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

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(solicitudxml));

            Document doc = builder.parse(src);
            doc.getDocumentElement().normalize();

            NodeList Generales = doc.getElementsByTagName("generales");

            for (int i = 0; i < Generales.getLength(); i++) {

                Node node = Generales.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para variables generales
                    Pmrnombre = element.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                    Sdonombre = element.getElementsByTagName("Sdonombre").item(0).getTextContent();
                    Apaterno = element.getElementsByTagName("Apaterno").item(0).getTextContent();
                    Amaterno = element.getElementsByTagName("Amaterno").item(0).getTextContent();
                    TipoIdentificacion = element.getElementsByTagName("Tpoidentif").item(0).getTextContent();
                    NumeroIdentificacion = element.getElementsByTagName("Noidenficacion").item(0).getTextContent();
                    Sexo = element.getElementsByTagName("Sexo").item(0).getTextContent();
                    Nacionalidad = element.getElementsByTagName("Nacionalidad").item(0).getTextContent();
                    Fechanacdia = element.getElementsByTagName("Fechanacdia").item(0).getTextContent();
                    FechanacMes = element.getElementsByTagName("FechasnacMes").item(0).getTextContent();
                    FechanacAnio = element.getElementsByTagName("FechanacAnio").item(0).getTextContent();
                    Rfc = element.getElementsByTagName("Rfc").item(0).getTextContent();
                    Edocivil = element.getElementsByTagName("Edocivil").item(0).getTextContent();
                    Nodependientes = element.getElementsByTagName("Nodependiente").item(0).getTextContent();
                }
            }

            NodeList Domilicio = doc.getElementsByTagName("domicilio");

            for (int i = 0; i < Domilicio.getLength(); i++) {

                Node node = Domilicio.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para variables de domicilio
                    Calle = element.getElementsByTagName("Calle").item(0).getTextContent();
                    NoExt = element.getElementsByTagName("NoExt").item(0).getTextContent();
                    NoInt = element.getElementsByTagName("NoInt").item(0).getTextContent();
                    Cpdom = element.getElementsByTagName("Cpdom").item(0).getTextContent();
                    Estado = element.getElementsByTagName("Estado").item(0).getTextContent();
                    Delegacion = element.getElementsByTagName("Delegacion").item(0).getTextContent();
                    Colonia = element.getElementsByTagName("Colonia").item(0).getTextContent();
                    TiempoResidencia = element.getElementsByTagName("TiempoResidencia").item(0).getTextContent();
                    EstatusResidencia = element.getElementsByTagName("EstatusResidencia").item(0).getTextContent();
                    MontoVivienda = element.getElementsByTagName("MontoVivienda").item(0).getTextContent();
                    Email = element.getElementsByTagName("Email").item(0).getTextContent();
                    Telcasa = element.getElementsByTagName("Telcasa").item(0).getTextContent();
                    TelMovil = element.getElementsByTagName("Telmovil").item(0).getTextContent();
                    CompaniaMovil = element.getElementsByTagName("CompaniaMovil").item(0).getTextContent();
                }
            }

            NodeList DatosEconomicos = doc.getElementsByTagName("Deconominos");
            NodeList DomicilioEconomico = doc.getElementsByTagName("Domicilio");

            for (int i = 0; i < DatosEconomicos.getLength(); i++) {

                Node node = DatosEconomicos.item(i);
                Node nodeDomicilio = DomicilioEconomico.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para las variables de datos economicos
                    NombreEmpresa = element.getElementsByTagName("NombreEmpresa").item(0).getTextContent();
                    GiroEmpresa = element.getElementsByTagName("GiroEmpresa").item(0).getTextContent();
                    AntiguedadEmpleo = element.getElementsByTagName("AntiguedadEmpleo").item(0).getTextContent();
                    TipoContrato = element.getElementsByTagName("TipoContrato").item(0).getTextContent();
                    Puesto = element.getElementsByTagName("Puesto").item(0).getTextContent();
                    Ingresos = element.getElementsByTagName("Ingresos").item(0).getTextContent();
                    AniosCasada = element.getElementsByTagName("AniosCasada").item(0).getTextContent();
                    FuenteOtrosIngresos = element.getElementsByTagName("FuenteOtrosIngresos").item(0).getTextContent();
                    OtrosIngresos = element.getElementsByTagName("OtrosIngresos").item(0).getTextContent();
                }

                if (nodeDomicilio.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) nodeDomicilio;

                    //Asignacion de valores para las variables de domicilio de datos económicos
                    CalleIngresos = element.getElementsByTagName("Calle").item(0).getTextContent();
                    NoExtIngresos = element.getElementsByTagName("NoExt").item(0).getTextContent();
                    NoIntIngresos = element.getElementsByTagName("NoInt").item(0).getTextContent();
                    ColoniaIngresos = element.getElementsByTagName("Colonia").item(0).getTextContent();
                    EstadoIngresos = element.getElementsByTagName("Estado").item(0).getTextContent();
                    DelegacionIngresos = element.getElementsByTagName("Delegacion").item(0).getTextContent();
                    CpdomIngresos = element.getElementsByTagName("Cpdom").item(0).getTextContent();
                    TelcasaIngresos = element.getElementsByTagName("Telcasa").item(0).getTextContent();
                }
            }

            NodeList Personapolitica = doc.getElementsByTagName("Personapolitica");

            for (int i = 0; i < Personapolitica.getLength(); i++) {

                Node node = Personapolitica.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para las variables de persona politica
                    TieneParentesco = element.getElementsByTagName("TieneParentesco").item(0).getTextContent();
                    EsPersonaPolitica = element.getElementsByTagName("EsPersonaPolitica").item(0).getTextContent();
                }
            }

            NodeList Refer = doc.getElementsByTagName("Refer");

            for (int i = 0; i < Refer.getLength(); i++) {

                Node node = Refer.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para las variables de primera referencia
                    NombrePrimera = element.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                    PaternoPrimera = element.getElementsByTagName("Apaterno").item(0).getTextContent();
                    MaternoPrimera = element.getElementsByTagName("Amaterno").item(0).getTextContent();
                    NacionalidadPrimera = element.getElementsByTagName("Nacionalidad").item(0).getTextContent();
                    TelefonoPrimera = element.getElementsByTagName("TelefonoCasa").item(0).getTextContent();
                }
            }

            NodeList Refer2 = doc.getElementsByTagName("Refer2");

            for (int i = 0; i < Refer2.getLength(); i++) {

                Node node = Refer2.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para las variables de segunda referencia
                    NombreSegunda = element.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                    PaternoSegunda = element.getElementsByTagName("Apaterno").item(0).getTextContent();
                    MaternoSegunda = element.getElementsByTagName("Amaterno").item(0).getTextContent();
                    NacionalidadSegunda = element.getElementsByTagName("Nacionalidad").item(0).getTextContent();
                    TelefonoSegunda = element.getElementsByTagName("TelefonoCasa").item(0).getTextContent();
                }
            }

            NodeList Refer3 = doc.getElementsByTagName("Refer3");

            for (int i = 0; i < Refer3.getLength(); i++) {

                Node node = Refer3.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    //Asignacion de valores para las variables de tercera referencia
                    NombreTercera = element.getElementsByTagName("Pmrnombre").item(0).getTextContent();
                    PaternoTercera = element.getElementsByTagName("Apaterno").item(0).getTextContent();
                    MaternoTercera = element.getElementsByTagName("Amaterno").item(0).getTextContent();
                    NacionalidadTercera = element.getElementsByTagName("Nacionalidad").item(0).getTextContent();
                    TelefonoTercera = element.getElementsByTagName("TelefonoCasa").item(0).getTextContent();
                }
            }

            //Decodificacion de base64 a imagen para los documentos de los imageButton y asignación
            byte[] BytesDocC1 = Base64.decode(Doc_C164, Base64.DEFAULT);
            Bitmap docC1Decodificado = BitmapFactory.decodeByteArray(BytesDocC1, 0, BytesDocC1.length);
            ImgContrato1.setImageBitmap(docC1Decodificado);

            byte[] BytesDocC2 = Base64.decode(Doc_C264, Base64.DEFAULT);
            Bitmap docC2Decodificado = BitmapFactory.decodeByteArray(BytesDocC2, 0, BytesDocC2.length);
            ImgContrato2.setImageBitmap(docC2Decodificado);

            byte[] BytesDocIF = Base64.decode(Doc_IF64, Base64.DEFAULT);
            Bitmap docIFDecodificado = BitmapFactory.decodeByteArray(BytesDocIF, 0, BytesDocIF.length);
            ImgIdentificacionFrente.setImageBitmap(docIFDecodificado);

            byte[] BytesDocIA = Base64.decode(Doc_IA64, Base64.DEFAULT);
            Bitmap docIADecodificado = BitmapFactory.decodeByteArray(BytesDocIA, 0, BytesDocIA.length);
            ImgIdentificacionAtras.setImageBitmap(docIADecodificado);

            byte[] BytesFirma = Base64.decode(F164, Base64.DEFAULT);
            Bitmap docFirmaDecodificado = BitmapFactory.decodeByteArray(BytesFirma, 0, BytesFirma.length);
            ImgFirma.setImageBitmap(docFirmaDecodificado);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        catalogoActivo();

        //Asignar variables a los edittext y RadioButton de generales
        txtSolicitanteGeneral.setText(Pmrnombre);
        txtSegundoNombreGeneral.setText(Sdonombre);
        txtPaternoGeneral.setText(Apaterno);
        txtMaternoGeneral.setText(Amaterno);
        txtTipoGeneral.setText(consultaTipoIdentificacion);
        txtNumeroIdentificacion.setText(NumeroIdentificacion);
        txtNacionalidad.setText(consultaNacionalidadGeneral);
        txtNacimiento.setText(Fechanacdia + "/" + FechanacMes + "/" + FechanacAnio);
        txtRFC.setText(Rfc);
        txtEstadoCivil.setText(consultaEdocivil);
        txtNumeroDependientes.setText(Nodependientes);

        if (Sexo.equals("MASCULINO")) {

            RadioHombre.setChecked(true);
            RadioMujer.setEnabled(false);
        } else if (Sexo.equals("FEMENINO")) {

            RadioMujer.setChecked(true);
            RadioHombre.setEnabled(false);
        }

        //Asignar variables a los edittext de domicilio
        txtCalle.setText(Calle);
        txtNoExterior.setText(NoExt);
        txtNoInterior.setText(NoInt);
        txtCP.setText(Cpdom);
        txtEstado.setText(consultaEstadoDomicilio);
        txtMunicipio.setText(consultaDelegacionDomicilio);
        txtColonia.setText(Colonia);
        txtTiempoResidencia.setText(TiempoResidencia);
        txtEstatusResidencia.setText(consultaEstatusResidencia);
        txtMontoVivienda.setText(MontoVivienda);
        txtCorreo.setText(Email);
        txtTelCasa.setText(Telcasa);
        txtTelCelular.setText(TelMovil);
        txtCompaniaMovil.setText(consultaCompaniaMovil);

        //Asignar variables a los edittext de datos económicos
        txtNombreEmpresa.setText(NombreEmpresa);
        txtGiro.setText(GiroEmpresa);
        txtAntiguedad.setText(AntiguedadEmpleo);
        txtTipo.setText(consultaTipoContrato);
        txtPuesto.setText(Puesto);
        txtIngresos.setText(Ingresos);
        txtTiempoCasado.setText(AniosCasada);
        txtFuenteIngresos.setText(FuenteOtrosIngresos);
        txtOtrosIngresos.setText(OtrosIngresos);
        txtCalleIngresos.setText(CalleIngresos);
        txtNoExteriorIngresos.setText(NoExtIngresos);
        txtNoInteriorIngresos.setText(NoIntIngresos);
        txtColoniaIngresos.setText(ColoniaIngresos);
        txtEstadoIngresos.setText(consultaEstadoIngresos);
        txtDelegacionIngresos.setText(consultaDelegacionIngresos);
        txtCPIngresos.setText(CpdomIngresos);
        txtTelOficina.setText(TelcasaIngresos);

        //Asignar variables a los RadioButton de persona politica
        if (EsPersonaPolitica.equals("SI")) {

            radioButton.setChecked(true);
            radioButton2.setEnabled(false);
        } else if (EsPersonaPolitica.equals("NO")) {

            radioButton2.setChecked(true);
            radioButton.setEnabled(false);
        }

        if (TieneParentesco.equals("SI")) {

            radioButton5.setChecked(true);
            radioButton6.setEnabled(false);
        } else if (TieneParentesco.equals("NO")) {

            radioButton6.setChecked(true);
            radioButton5.setEnabled(false);
        }

        //Asignar valores a los edittext de referencias familiares
        txtNombrePrimera.setText(NombrePrimera);
        txtPaternoPrimera.setText(PaternoPrimera);
        txtMaternoPrimera.setText(MaternoPrimera);
        txtNacionalidadPrimera.setText(consultaNacionalidadPrimera);
        txtTelefonoPrimera.setText(TelefonoPrimera);

        txtNombreSegunda.setText(NombreSegunda);
        txtPaternoSegunda.setText(PaternoSegunda);
        txtMaternoSegunda.setText(MaternoSegunda);
        txtNacionalidadSegunda.setText(consultaNacionalidadSegunda);
        txtTelefonoSegunda.setText(TelefonoSegunda);

        txtNombreTercera.setText(NombreTercera);
        txtPaternoTercera.setText(PaternoTercera);
        txtMaternoTercera.setText(MaternoTercera);
        txtNacionalidadTercera.setText(consultaNacionalidadTercera);
        txtTelefonoTercera.setText(TelefonoTercera);

    }

    public void click(View v) {

        Intent intent = new Intent(getApplicationContext(), Imagen.class);

        switch (v.getId()) {

            case R.id.ImgIdentificacionFrente:

                intent.putExtra("Datos", "Identificacion Frente");
                intent.putExtra("Foto", Doc_IF64);
                startActivity(intent);

                break;

            case R.id.ImgIdentificacionAtras:

                intent.putExtra("Datos", "Identificion Atrás");
                intent.putExtra("Foto", Doc_IA64);
                startActivity(intent);

                break;

            case R.id.ImgContrato1:

                intent.putExtra("Datos", "Contrato 1");
                intent.putExtra("Foto", Doc_C164);
                startActivity(intent);

                break;

            case R.id.ImgContrato2:

                intent.putExtra("Datos", "Contrato 2");
                intent.putExtra("Foto", Doc_C264);
                startActivity(intent);

                break;

            case R.id.ImgFirma:

                intent.putExtra("Datos", "Firma");
                intent.putExtra("Foto", F164);
                startActivity(intent);

                break;
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
                Doc_C164 = consulta.getString(10);
                Doc_C264 = consulta.getString(11);
                Doc_IA64 = consulta.getString(12);
                Doc_IF64 = consulta.getString(13);
                F164 = consulta.getString(14);
            }

            db.close();

        } else if (letra.equals("B")) {

            Cursor consulta = db.rawQuery("select * from BUZON_B where id_solicitud = " + IdSolicitud, null);

            if (consulta.moveToNext()) {

                solicitudxml = consulta.getString(7);
                Doc_C164 = consulta.getString(10);
                Doc_C264 = consulta.getString(11);
                Doc_IA64 = consulta.getString(12);
                Doc_IF64 = consulta.getString(13);
                F164 = consulta.getString(14);
            }

            db.close();
        }

        db.close();
    }

    public void catalogoActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from PARAMETRO where id_parametro = 2", null);

        if (consulta.moveToNext()) {

            consultaCatalogoActivo(consulta.getString(2));
        }

        db.close();
    }

    public void consultaCatalogoActivo(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor consulta = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + TipoIdentificacion, null);

            if (consulta.moveToNext()) {

                consultaTipoIdentificacion = consulta.getString(1);
            }

            Cursor consulta2 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + Nacionalidad, null);

            if (consulta2.moveToNext()) {

                consultaNacionalidadGeneral = consulta2.getString(1);
            }

            Cursor consulta3 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + NacionalidadPrimera, null);

            if (consulta3.moveToNext()) {

                consultaNacionalidadPrimera = consulta3.getString(1);
            }

            Cursor consulta4 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + NacionalidadSegunda, null);

            if (consulta4.moveToNext()) {

                consultaNacionalidadSegunda = consulta4.getString(1);
            }

            if (NacionalidadTercera == null || NacionalidadTercera.isEmpty()) {

                //toast("No existen registro en 3ra referencia");
            } else {

                Cursor consulta5 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + NacionalidadTercera, null);

                if (consulta5.moveToNext()) {

                    consultaNacionalidadTercera = consulta5.getString(1);
                }
            }

            Cursor consulta6 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + Edocivil, null);

            if (consulta6.moveToNext()) {

                consultaEdocivil = consulta6.getString(1);
            }

            Cursor consulta7 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + Estado, null);

            if (consulta7.moveToNext()) {

                consultaEstadoDomicilio = consulta7.getString(1);
            }

            Cursor consulta8 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + EstadoIngresos, null);

            if (consulta8.moveToNext()) {

                consultaEstadoIngresos = consulta8.getString(1);
            }

            Cursor consulta9 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + Delegacion, null);

            if (consulta9.moveToNext()) {

                consultaDelegacionDomicilio = consulta9.getString(1);
            }

            Cursor consulta10 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + DelegacionIngresos, null);

            if (consulta10.moveToNext()) {

                consultaDelegacionIngresos = consulta10.getString(1);
            }

            Cursor consulta11 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + CompaniaMovil, null);

            if (consulta11.moveToNext()) {

                consultaCompaniaMovil = consulta11.getString(1);
            }

            Cursor consulta12 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + EstatusResidencia, null);

            if (consulta12.moveToNext()) {

                consultaEstatusResidencia = consulta12.getString(1);
            }

            Cursor consulta13 = db.rawQuery("select * from CATALOGO_A where id_catalogo = " + TipoContrato, null);

            if (consulta13.moveToNext()) {

                consultaTipoContrato = consulta13.getString(1);
            }

        } else if (letra.equals("B")) {

            Cursor consulta = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + TipoIdentificacion, null);

            if (consulta.moveToNext()) {

                consultaTipoIdentificacion = consulta.getString(1);
            }

            Cursor consulta2 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + Nacionalidad, null);

            if (consulta2.moveToNext()) {

                consultaNacionalidadGeneral = consulta2.getString(1);
            }

            Cursor consulta3 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + NacionalidadPrimera, null);

            if (consulta3.moveToNext()) {

                consultaNacionalidadPrimera = consulta3.getString(1);
            }

            Cursor consulta4 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + NacionalidadSegunda, null);

            if (consulta4.moveToNext()) {

                consultaNacionalidadSegunda = consulta4.getString(1);
            }

            if (NacionalidadTercera == null || NacionalidadTercera.isEmpty()) {

                //toast("No existen registro en 3ra referencia");
            } else {

                Cursor consulta5 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + NacionalidadTercera, null);

                if (consulta5.moveToNext()) {

                    consultaNacionalidadTercera = consulta5.getString(1);
                }
            }

            Cursor consulta6 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + Edocivil, null);

            if (consulta6.moveToNext()) {

                consultaEdocivil = consulta6.getString(1);
            }

            Cursor consulta7 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + Estado, null);

            if (consulta7.moveToNext()) {

                consultaEstadoDomicilio = consulta7.getString(1);
            }

            Cursor consulta8 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + EstadoIngresos, null);

            if (consulta8.moveToNext()) {

                consultaEstadoIngresos = consulta8.getString(1);
            }

            Cursor consulta9 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + Delegacion, null);

            if (consulta9.moveToNext()) {

                consultaDelegacionDomicilio = consulta9.getString(1);
            }

            Cursor consulta10 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + DelegacionIngresos, null);

            if (consulta10.moveToNext()) {

                consultaDelegacionIngresos = consulta10.getString(1);
            }

            Cursor consulta11 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + CompaniaMovil, null);

            if (consulta11.moveToNext()) {

                consultaCompaniaMovil = consulta11.getString(1);
            }

            Cursor consulta12 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + EstatusResidencia, null);

            if (consulta12.moveToNext()) {

                consultaEstatusResidencia = consulta12.getString(1);
            }

            Cursor consulta13 = db.rawQuery("select * from CATALOGO_B where id_catalogo = " + TipoContrato, null);

            if (consulta13.moveToNext()) {

                consultaTipoContrato = consulta13.getString(1);
            }
        }

        db.close();
    }
}