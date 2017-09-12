package com.google.android.gms.samples.vision.ocrreader;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Login extends AppCompatActivity {

    EditText txtUsuario, txtPassword, txtEmpresa;

    Button btnAcceder;

    String usuario, password, empresa, IdUsuario, Compania, Nombre, Paterno, Tipo_Usuario, User, UUID, validaUUIDResult, letraBuzon, letraCatalago;

    TextView textLocalizacion;

    CheckBox checkNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtEmpresa = (EditText) findViewById(R.id.txtEmpresa);
        textLocalizacion = (TextView) findViewById(R.id.textLocalizacion);
        checkNuevo = (CheckBox) findViewById(R.id.checkNuevo);

        txtUsuario.setText("andresc");
        txtPassword.setText("12345678");
        txtEmpresa.setText("STF");

        UUID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        consultaParametroBuzon();

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtUsuario.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty() || txtEmpresa.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Verifique que los campos estén llenos", Toast.LENGTH_SHORT).show();
                } else if (checkNuevo.isChecked()) {

                    txtUsuario.setEnabled(false);
                    txtPassword.setEnabled(false);
                    txtEmpresa.setEnabled(false);

                    Logueo logueo = new Logueo();
                    logueo.execute();

                } else {

                    consulta(txtUsuario.getText().toString(), txtPassword.getText().toString(), txtEmpresa.getText().toString());
                }

            }
        });
    }

    private class Logueo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String res = "";

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost request = new HttpPost("https://stefaninimx.com/mx.com.stefanini.service.api.rest/Service1.svc/Login");

            StringEntity e = null;

            String json = "{'Promotoria':'','RegPromotor':'','Compania':'" + empresa + "','Formato':'','Usuario':'" + usuario + "','Contrasenia':'" + password + "','Coordinador':{'ClaveC':'','NombreC':''},'Gerente':{'ClaveG':'','NombreG':''},'TipoUsuario':'4'}";
            String mystring = json.replace("\'", "\"");

            try {
                e = new StringEntity(mystring);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            request.setEntity(e);

            request.setHeader("content-type", "application/json");

            HttpResponse response = null;

            try {
                response = httpClient.execute(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    res += EntityUtils.toString(entity);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                res += response.toString();
            }


            try {
                JSONObject jsonObject = new JSONObject(res);

                IdUsuario = jsonObject.getString("IdUsuario");
                Compania = jsonObject.getString("Compania");
                Nombre = jsonObject.getString("Nombre");
                Paterno = jsonObject.getString("Paterno");
                Tipo_Usuario = jsonObject.getString("Tipo_Usuario");
                User = jsonObject.getString("User");

            } catch (JSONException e1) {
                e1.printStackTrace();
            }


            if (Integer.parseInt(IdUsuario) != 0) {

                toast("Usuario existente en el WS");

                consultaBuzonActivo();
                consultaCatalagoActivo();
            } else {

                toast("Login Incorrecto");
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtUsuario = (EditText) findViewById(R.id.txtUsuario);
            usuario = txtUsuario.getText().toString();

            txtPassword = (EditText) findViewById(R.id.txtPassword);
            password = txtPassword.getText().toString();

            txtEmpresa = (EditText) findViewById(R.id.txtEmpresa);
            empresa = txtEmpresa.getText().toString();
        }
    }

    private class UUID extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String res = "";

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost request = new HttpPost("https://stefaninimx.com/mx.com.stefanini.service.api.rest/Service1.svc/validaUUID");

            StringEntity e = null;

            String json = "{'idUsuario':'" + IdUsuario + "', 'UUID':'" + UUID + "'}";
            String mystring = json.replace("\'", "\"");

            try {
                e = new StringEntity(mystring);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            request.setEntity(e);

            request.setHeader("content-type", "application/json");

            HttpResponse response = null;

            try {
                response = httpClient.execute(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    res += EntityUtils.toString(entity);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                res += response.toString();
            }


            try {
                JSONObject jsonObject = new JSONObject(res);

                validaUUIDResult = jsonObject.getString("validaUUIDResult");

                if (validaUUIDResult.equals("true")) {

                    toast("Ya está vinculado");

                    insertarUsuario(IdUsuario, usuario, password, "4", empresa);

                    llamarGetBuzon();

                } else {

                    toast("Este perfil del promotor: " + usuario + " no puede instalarse en este dispositivo " + UUID + ". Ya esta instalado en otro dispositivo. Solicite al administrador el permiso correspondiente");
                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtUsuario = (EditText) findViewById(R.id.txtUsuario);
            usuario = txtUsuario.getText().toString();

            txtPassword = (EditText) findViewById(R.id.txtPassword);
            password = txtPassword.getText().toString();

            txtEmpresa = (EditText) findViewById(R.id.txtEmpresa);
            empresa = txtEmpresa.getText().toString();

        }
    }

    private class GetBuzon extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String res = "";

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost request = new HttpPost("https://stefaninimx.com/mx.com.stefanini.service.api.rest/Service1.svc/GetBuzon");

            StringEntity e = null;

            String json = "{'Promotoria':'','RegPromotor':'','Compania':'" + empresa + "','Formato':'','Usuario':'" + usuario + "','Contrasenia':'" + password + "','Coordinador':{'ClaveC':'','NombreC':''},'Gerente':{'ClaveG':'','NombreG':''},'TipoUsuario':'4'}";
            String mystring = json.replace("\'", "\"");

            try {
                e = new StringEntity(mystring);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            request.setEntity(e);

            request.setHeader("content-type", "application/json");

            HttpResponse response = null;

            try {
                response = httpClient.execute(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    res += EntityUtils.toString(entity);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                res += response.toString();
            }


            try {

                JSONObject jsonObjectOld = new JSONObject(res);

                JSONArray jsonArray = jsonObjectOld.getJSONArray("solicitudes");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String BuzonIdSolicitud = jsonObject.getString("ID_SOLICITUD");
                    String BuzonFechaAlta = jsonObject.getString("FECHA_ALTA");
                    String BuzonEstatus = jsonObject.getString("ESTATUS");
                    String BuzonIdUsuario = IdUsuario;
                    String BuzonComentario = jsonObject.getString("COMENTARIO");
                    String BuzonMotivo = "1";
                    String BuzonFechaModificacion = jsonObject.getString("FECHA_MODIFICACION");
                    String BuzonSolicitudXML = jsonObject.getString("SOLICITUD_XML");
                    String BuzonPromedioScoring = jsonObject.getString("PROMEDIO_SCORING");
                    String BuzonProducto = jsonObject.getString("PRODUCTO");

                    insertarBuzon(BuzonIdSolicitud, BuzonFechaAlta, BuzonEstatus, BuzonIdUsuario, BuzonComentario, BuzonMotivo, BuzonFechaModificacion, BuzonSolicitudXML, BuzonPromedioScoring, BuzonProducto);

                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtUsuario = (EditText) findViewById(R.id.txtUsuario);
            usuario = txtUsuario.getText().toString();

            txtPassword = (EditText) findViewById(R.id.txtPassword);
            password = txtPassword.getText().toString();

            txtEmpresa = (EditText) findViewById(R.id.txtEmpresa);
            empresa = txtEmpresa.getText().toString();

        }
    }

    private class GetCatalogos extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String res = "";

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost request = new HttpPost("https://stefaninimx.com/mx.com.stefanini.service.api.rest/Service1.svc/GetCatalogosX");

            StringEntity e = null;

            String json = "{'Promotoria':'','RegPromotor':'','Compania':'" + empresa + "','Formato':'','Usuario':'" + usuario + "','Contrasenia':'" + password + "','Coordinador':{'ClaveC':'','NombreC':''},'Gerente':{'ClaveG':'','NombreG':''},'TipoUsuario':'4'}";
            String mystring = json.replace("\'", "\"");

            try {
                e = new StringEntity(mystring);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            request.setEntity(e);

            request.setHeader("content-type", "application/json");

            HttpResponse response = null;

            try {
                response = httpClient.execute(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    res += EntityUtils.toString(entity);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                res += response.toString();
            }


            try {

                JSONObject jsonObjectOld = new JSONObject(res);

                JSONArray jsonArray = jsonObjectOld.getJSONArray("catalogos");

                int i = 0;

                while (i < jsonArray.length()) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String CatalagoIdCatalago = jsonObject.getString("ID_CATALOGO");
                    String CatalagoDescripcion = jsonObject.getString("DESCRIPCION");
                    String CatalagoIdTipo = jsonObject.getString("ID_TIPO_CATALOGO");
                    String CatalagoEstatus = "1";
                    String CatalagoPadre = jsonObject.getString("PADRE");

                    insertarCatalagos(CatalagoIdCatalago, CatalagoDescripcion, CatalagoIdTipo, CatalagoEstatus, CatalagoPadre);

                    i++;

                }

                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent);


            } catch (JSONException e1) {
                e1.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtUsuario = (EditText) findViewById(R.id.txtUsuario);
            usuario = txtUsuario.getText().toString();

            txtPassword = (EditText) findViewById(R.id.txtPassword);
            password = txtPassword.getText().toString();

            txtEmpresa = (EditText) findViewById(R.id.txtEmpresa);
            empresa = txtEmpresa.getText().toString();

        }
    }

    public void llamarGetBuzon() {

        GetBuzon getBuzon = new GetBuzon();
        getBuzon.execute();
    }


    public void toast(final String xml) {

        runOnUiThread(new Runnable() {
            public void run() {

                Toast.makeText(getApplicationContext(), xml, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Mensaje(final String xml) {

        runOnUiThread(new Runnable() {
            public void run() {

                textLocalizacion.setText(xml);

            }
        });
    }

    public void consultaParametros() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from PARAMETRO where id_parametro = 3 and valor = 1", null);

        if (fila.moveToNext()) {

            toast("Ha iniciado sesión con anterioridad");
            //borrar(1);
        } else {

            toast("Primera vez en la aplicacion AfiliaMaas");

            db.close();
        }
    }


    public void consulta(String usuario, String password, String empresa) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from USUARIO where user = '" + usuario + "' and contrasenia = '" + password + "' and compania = '" + empresa + "'", null);

        if (fila.moveToNext()) {

            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
            startActivity(intent);

            toast("Bienvenido a la aplicación de AfiliaMas");
            toast("Consulta en la BD local");
        } else {

            toast("Login Incorrecto");
            toast("Consulta en la BD local");
            db.close();
        }
    }

    public void consultaBuzonActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consultaA = db.rawQuery("select * from PARAMETRO where id_parametro = 1", null);

        if (consultaA.moveToNext()) {

            letraBuzon = consultaA.getString(2);
        }

        if (letraBuzon.equals("A")) {

            toast("El buzón activo es A pero se insertará en el Buzón B");

            consultaBuzonEstatus(letraBuzon);
        } else if (letraBuzon.equals("B")) {

            toast("El buzón activo es B pero se insertará en el Buzón A");

            consultaBuzonEstatus(letraBuzon);
        }
    }

    public void consultaCatalagoActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consultaA = db.rawQuery("select * from PARAMETRO where id_parametro = 2", null);

        if (consultaA.moveToNext()) {

            letraCatalago = consultaA.getString(2);
        }

        if (letraCatalago.equals("A")) {

            toast("El catalago activo es A pero se insertará en el catalago B");

            borrarCatalagoB();

            GetCatalogos getCatalogos = new GetCatalogos();
            getCatalogos.execute();
        } else if (letraCatalago.equals("B")) {

            toast("El catalago activo es B pero se insertará en el catalago A");

            borrarCatalagoA();

            GetCatalogos getCatalogos = new GetCatalogos();
            getCatalogos.execute();
        }
    }

    public void consultaProductoActivo() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consultaA = db.rawQuery("select * from PARAMETRO where id_parametro = 8 and valor = 'A'", null);
        Cursor consultaB = db.rawQuery("select * from PARAMETRO where id_parametro = 8 and valor = 'B'", null);

        if (consultaA.moveToNext()) {

            toast("El producto activo es A pero se insertará en el catalago B");

        } else if (consultaB.moveToNext()) {

            toast("El producto activo es B pero se insertará en el catalago A");
        }

        db.close();
    }

    public void consultaBuzonEstatus(String letra) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        if (letra.equals("A")) {

            Cursor consultaA = db.rawQuery("select * from Buzon_A where ESTATUS = 6 or ESTATUS = 7", null);

            if (consultaA.moveToNext()) {

                AlertDialogA();

            } else {

                borrarBuzonB();

                UUID uuid = new UUID();
                uuid.execute();
            }

        } else if (letra.equals("B")) {

            Cursor consultaB = db.rawQuery("select * from Buzon_B where ESTATUS = 6 or ESTATUS = 7", null);

            if (consultaB.moveToNext()) {

                AlertDialogB();

            } else {

                borrarBuzonA();
                UUID uuid = new UUID();
                uuid.execute();
            }

        }
    }

    public void consultaGlobal() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();


        Cursor consultaA = db.rawQuery("select * from Buzon_B where ESTATUS = 1", null);

        if (consultaA.moveToNext()) {

            toast(consultaA.getString(1));

        } else {

            toast("No hay nada en buzon B");
        }
    }

    public void AlertDialogA() {

        runOnUiThread(new Runnable() {
            public void run() {

                // Instanciamos un nuevo AlertDialog Builder y le asociamos titulo y mensaje
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                alertDialogBuilder.setTitle("Aviso");
                alertDialogBuilder.setMessage("Tiene solicitudes pendientes. ¿Desea eliminarlas?");

                // Creamos un nuevo OnClickListener para el boton OK que realice la conexion
                DialogInterface.OnClickListener listenerOk = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        borrarBuzonB();

                        UUID uuid = new UUID();
                        uuid.execute();
                    }
                };

                // Creamos un nuevo OnClickListener para el boton Cancelar
                DialogInterface.OnClickListener listenerCancelar = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        toast("Operación cancelada");
                        return;
                    }
                };

                // Asignamos los botones positivo y negativo a sus respectivos listeners
                alertDialogBuilder.setPositiveButton("SI", listenerOk);
                alertDialogBuilder.setNegativeButton("NO", listenerCancelar);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public void AlertDialogB() {

        runOnUiThread(new Runnable() {
            public void run() {

                // Instanciamos un nuevo AlertDialog Builder y le asociamos titulo y mensaje
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                alertDialogBuilder.setTitle("Aviso");
                alertDialogBuilder.setMessage("Tiene solicitudes pendientes. ¿Desea eliminarlas?");

                // Creamos un nuevo OnClickListener para el boton OK que realice la conexion
                DialogInterface.OnClickListener listenerOk = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        borrarBuzonA();

                        UUID uuid = new UUID();
                        uuid.execute();

                    }
                };

                // Creamos un nuevo OnClickListener para el boton Cancelar
                DialogInterface.OnClickListener listenerCancelar = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        toast("Operación cancelada");
                        return;
                    }
                };

                // Asignamos los botones positivo y negativo a sus respectivos listeners
                alertDialogBuilder.setPositiveButton("SI", listenerOk);
                alertDialogBuilder.setNegativeButton("NO", listenerCancelar);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public void insertarUsuario(String IdUsuario, String Usuario, String Contrasenia, String Tipo, String Empresa) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consultaA = db.rawQuery("select * from USUARIO where id_usuario = " + Integer.parseInt(IdUsuario), null);

        if (consultaA.moveToNext()) {


        } else {

            ContentValues registro = new ContentValues();

            registro.put("id_usuario", Integer.parseInt(IdUsuario));
            registro.put("user", Usuario);
            registro.put("contrasenia", Contrasenia);
            registro.put("tipo_usuario", Integer.parseInt(Tipo));
            registro.put("compania", Empresa);

            db.insert("USUARIO", null, registro);
        }

        db.close();
    }

    public void insertarBuzon(String IdSolicitud, String FechaAlta, String Estatus, String IdUsuario, String Comentario, String Motivo, String FechaModificacion, String SolicitudXML, String PromedioScoring, String Producto) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        if (letraBuzon.equals("A")) {

            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro1 = new ContentValues();
            ContentValues actualizar = new ContentValues();

            registro1.put("id_solicitud", Integer.parseInt(IdSolicitud));
            registro1.put("fecha_alta", String.valueOf(FechaAlta));
            registro1.put("estatus", Integer.parseInt(Estatus));
            registro1.put("id_usuario", Integer.parseInt(IdUsuario));
            registro1.put("comentario", Comentario);
            registro1.put("motivo", Integer.parseInt(Motivo));
            registro1.put("fecha_modificacion", String.valueOf(FechaModificacion));
            registro1.put("solicitud_xml", SolicitudXML);
            registro1.put("promedio_scoring", PromedioScoring);
            registro1.put("producto", Producto);

            db.insert("BUZON_B", null, registro1);

            actualizar.put("valor", "B");

            int cant = db.update("PARAMETRO", actualizar, "id_parametro = 1", null);

            if (cant == 1) {

                Mensaje("Cargando Buzones.   Por favor espere");
                Mensaje("Cargando Buzones..   Por favor espere");
                Mensaje("Cargando Buzones...   Por favor espere");
                Mensaje("Cargando Buzones....   Por favor espere");
                Mensaje("Cargando Buzones.....   Por favor espere");
                Mensaje("Cargando Buzones......   Por favor espere");
                Mensaje("Cargando Buzones.......   Por favor espere");
                Mensaje("Cargando Buzones........   Por favor espere");
                Mensaje("Cargando Buzones.........   Por favor espere");
                Mensaje("Cargando Buzones..........   Por favor espere");
                Mensaje("Cargando Buzones...........   Por favor espere");

            } else {

                Mensaje("No se cargaron los Buzones");
            }

            db.close();
        } else if (letraBuzon.equals("B")) {

            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro1 = new ContentValues();
            ContentValues actualizar = new ContentValues();

            registro1.put("id_solicitud", Integer.parseInt(IdSolicitud));
            registro1.put("fecha_alta", String.valueOf(FechaAlta));
            registro1.put("estatus", Integer.parseInt(Estatus));
            registro1.put("id_usuario", Integer.parseInt(IdUsuario));
            registro1.put("comentario", Comentario);
            registro1.put("motivo", Integer.parseInt(Motivo));
            registro1.put("fecha_modificacion", String.valueOf(FechaModificacion));
            registro1.put("solicitud_xml", SolicitudXML);
            registro1.put("promedio_scoring", PromedioScoring);
            registro1.put("producto", Producto);

            db.insert("BUZON_A", null, registro1);

            actualizar.put("valor", "A");

            int cant = db.update("PARAMETRO", actualizar, "id_parametro = 1", null);

            if (cant == 1) {

                Mensaje("Cargando Buzones.    Por favor espere");
                Mensaje("Cargando Buzones..   Por favor espere");
                Mensaje("Cargando Buzones...   Por favor espere");
                Mensaje("Cargando Buzones....   Por favor espere");
                Mensaje("Cargando Buzones.....   Por favor espere");
                Mensaje("Cargando Buzones......   Por favor espere");
                Mensaje("Cargando Buzones.......   Por favor espere");
                Mensaje("Cargando Buzones........   Por favor espere");
                Mensaje("Cargando Buzones.........   Por favor espere");
                Mensaje("Cargando Buzones..........   Por favor espere");
                Mensaje("Cargando Buzones...........   Por favor espere");

            } else {

                Mensaje("No se cargaron los Buzones");
            }

            db.close();
        }
    }

    public void insertarCatalagos(String IdCatalago, String Descripcion, String IdTipo, String Estatus, String Padre) {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        if (letraCatalago.equals("A")) {

            ContentValues registro = new ContentValues();
            ContentValues actualizar = new ContentValues();

            registro.put("id_catalogo", Integer.parseInt(IdCatalago));
            registro.put("descripcion", Descripcion);
            registro.put("id_tipo_catalogo", IdTipo);
            registro.put("estatus", Estatus);
            registro.put("padre", Padre);

            db.insert("CATALOGO_B", null, registro);

            actualizar.put("valor", "B");

            int cant = db.update("PARAMETRO", actualizar, "id_parametro = 2", null);

            if (cant == 1) {

                Mensaje("Cargando Catalagos.   Por favor espere");
                Mensaje("Cargando Catalagos..   Por favor espere");
                Mensaje("Cargando Catalagos...   Por favor espere");
                Mensaje("Cargando Catalagos....   Por favor espere");
                Mensaje("Cargando Catalagos.....   Por favor espere");
                Mensaje("Cargando Catalagos......   Por favor espere");
                Mensaje("Cargando Catalagos.......   Por favor espere");
                Mensaje("Cargando Catalagos........   Por favor espere");
                Mensaje("Cargando Catalagos.........   Por favor espere");
                Mensaje("Cargando Catalagos..........   Por favor espere");
                Mensaje("Cargando Catalagos...........   Por favor espere");
            } else {

                Mensaje("No se cargaron los Catalagos");
            }

            db.close();
        } else if (letraCatalago.equals("B")) {

            ContentValues registroB = new ContentValues();
            ContentValues actualizar = new ContentValues();

            registroB.put("id_catalogo", Integer.parseInt(IdCatalago));
            registroB.put("descripcion", Descripcion);
            registroB.put("id_tipo_catalogo", IdTipo);
            registroB.put("estatus", Estatus);
            registroB.put("padre", Padre);

            db.insert("CATALOGO_A", null, registroB);

            actualizar.put("valor", "A");

            int cant = db.update("PARAMETRO", actualizar, "id_parametro = 2", null);

            if (cant == 1) {

                Mensaje("Cargando Catalagos.   Por favor espere");
                Mensaje("Cargando Catalagos..   Por favor espere");
                Mensaje("Cargando Catalagos...   Por favor espere");
                Mensaje("Cargando Catalagos....   Por favor espere");
                Mensaje("Cargando Catalagos.....   Por favor espere");
                Mensaje("Cargando Catalagos......   Por favor espere");
                Mensaje("Cargando Catalagos.......   Por favor espere");
                Mensaje("Cargando Catalagos........   Por favor espere");
                Mensaje("Cargando Catalagos.........   Por favor espere");
                Mensaje("Cargando Catalagos..........   Por favor espere");
                Mensaje("Cargando Catalagos...........   Por favor espere");
            } else {

                Mensaje("No se cargaron los Catalagos");
            }

            db.close();
        }
    }

    public void consultaParametroBuzon(){

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from PARAMETRO where id_parametro = 1", null);

        if (consulta.moveToNext()){

        }else {

            insertar();
        }

        db.close();
    }

    public void insertar(){

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro1 = new ContentValues();
        ContentValues registro2 = new ContentValues();
        ContentValues registro3 = new ContentValues();
        ContentValues registro4 = new ContentValues();
        ContentValues registro5 = new ContentValues();
        ContentValues registro6 = new ContentValues();
        ContentValues registro7 = new ContentValues();
        ContentValues registro8 = new ContentValues();

        registro1.put("id_parametro", 1);
        registro1.put("parametro", "BUZON_ACTIVO");
        registro1.put("valor", "A");
        registro1.put("estatus", "1");

        registro2.put("id_parametro", 2);
        registro2.put("parametro", "CATALOGO_ACTIVO");
        registro2.put("valor", "A");
        registro2.put("estatus", "1");

        registro3.put("id_parametro", 3);
        registro3.put("parametro", "CONFIG_INICIO");
        registro3.put("valor", "0");
        registro3.put("estatus", "1");

        registro4.put("id_parametro", 4);
        registro4.put("parametro", "CATALOGO_VERSION");
        registro4.put("valor", "1.0");
        registro4.put("estatus", "1");

        registro5.put("id_parametro", 5);
        registro5.put("parametro", "MODALIDAD");
        registro5.put("valor", "OFF");
        registro5.put("estatus", "1");

        registro6.put("id_parametro", 6);
        registro6.put("parametro", "AGENDA_ACTIVA");
        registro6.put("valor", "A");
        registro6.put("estatus", "1");

        registro7.put("id_parametro", 7);
        registro7.put("parametro", "REFRESH_MODALIDAD");
        registro7.put("valor", "0");
        registro7.put("estatus", "1");

        registro8.put("id_parametro", 8);
        registro8.put("parametro", "PRODUCTO_ACTIVO");
        registro8.put("valor", "A");
        registro8.put("estatus", "1");

        db.insert("PARAMETRO", null, registro1);
        db.insert("PARAMETRO", null, registro2);
        db.insert("PARAMETRO", null, registro3);
        db.insert("PARAMETRO", null, registro4);
        db.insert("PARAMETRO", null, registro5);
        db.insert("PARAMETRO", null, registro6);
        db.insert("PARAMETRO", null, registro7);
        db.insert("PARAMETRO", null, registro8);

        toast("Se han insertado los parametros básicos");

        db.close();
    }

    public void modificarParametroBuzon() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues modificar = new ContentValues();

        modificar.put("estatus", "6");

        int cant = db.update("BUZON_A", modificar, "id_solicitud = 1", null);

        if (cant == 1) {

            toast("Actualizado");
        }

        db.close();
    }

    public void borrarUsuarios() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from USUARIO");

        toast("Borrado usuarios");

        db.close();

    }

    public void borrarBuzonA() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from BUZON_A");

        toast("Borrado Buzon A");

        db.close();

    }

    public void borrarBuzonB() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from BUZON_B");

        toast("Borrado Buzon B");

        db.close();

    }

    public void borrarCatalagoA() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from CATALOGO_A");

        toast("Borrado Catalogo A");

        db.close();

    }

    public void borrarCatalagoB() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from CATALOGO_B");

        toast("Borrado Catalogo B");

        db.close();

    }

    public void borrarProductoA() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from PRODUCTO_A");

        toast("Borrado Producto A");

        db.close();

    }

    public void borrarProductoB() {

        AdminSQLite admin = new AdminSQLite(getApplicationContext(), "usuario", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from PRODUCTO_B");

        toast("Borrado Producto B");

        db.close();

    }
}