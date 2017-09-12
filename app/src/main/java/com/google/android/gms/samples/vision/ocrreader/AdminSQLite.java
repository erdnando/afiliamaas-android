package com.google.android.gms.samples.vision.ocrreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLite extends SQLiteOpenHelper{

    public AdminSQLite(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){

        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Se crean la tablas de la bd
        db.execSQL("create table BUZON_A (id_solicitud integer primary key, fecha_alta datetime, estatus integer, id_usuario integer, comentario text, motivo integer, fecha_modificacion datetime, solicitud_xml text, promedio_scoring text, producto text)");
        db.execSQL("create table BUZON_B (id_solicitud integer primary key, fecha_alta datetime, estatus integer, id_usuario integer, comentario text, motivo integer, fecha_modificacion datetime, solicitud_xml text, promedio_scoring text, producto text)");
        db.execSQL("create table CATALOGO_A (id_catalogo integer primary key, descripcion text, id_tipo_catalogo text, estatus text, padre text)");
        db.execSQL("create table CATALOGO_B (id_catalogo integer primary key, descripcion text, id_tipo_catalogo text, estatus text, padre text)");
        db.execSQL("create table CITASMETRICA (total text, nueva text, cancelada text, realizada text, caducada text, exitosa text)");
        db.execSQL("create table PARAMETRO (id_parametro integer primary key, parametro text, valor text, estatus text)");
        db.execSQL("create table PRODUCTO_A (id_producto integer primary key, nombre text, descripcion text, estatus text, fecha_creacion text, id_empresa integer, comercial text)");
        db.execSQL("create table PRODUCTO_B (id_producto integer primary key, nombre text, descripcion text, estatus text, fecha_creacion text, id_empresa integer, comercial text)");
        db.execSQL("create table RegAgendaA (id_citas integer primary key, id_promotor integer, id_producto integer, dia text, mes text, anio text, inicio text, fin text, estatus text, resultado_cita text, observaciones text, nombre text, secondnombre text, paterno text, materno text, sexo text, ingresos text, dia_nac text, mes_nac text, anio_nac text, calle text, noint text, noext text, cpdom text, estado text, delegacion text, colonia text, tiemporesidencia text, estatusresidencia text, montovivienda text, email text, telcasa text, telmovil text, companiamovil text, estatusenvio text, rfc text, idestado text, iddelmun text)");
        db.execSQL("create table RegAgendaB (id_citas integer primary key, id_promotor integer, id_producto integer, dia text, mes text, anio text, inicio text, fin text, estatus text, resultado_cita text, observaciones text, nombre text, secondnombre text, paterno text, materno text, sexo text, ingresos text, dia_nac text, mes_nac text, anio_nac text, calle text, noint text, noext text, cpdom text, estado text, delegacion text, colonia text, tiemporesidencia text, estatusresidencia text, montovivienda text, email text, telcasa text, telmovil text, companiamovil text, estatusenvio text, rfc text, idestado text, iddelmun text)");
        db.execSQL("create table USUARIO (id_usuario integer primary key, user text, contrasenia text, tipo_usuario integer, compania text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("create table if not exists BUZON_A");
        db.execSQL("create table if not exists BUZON_B");
        db.execSQL("create table if not exists CATALOGO_A");
        db.execSQL("create table if not exists CATALOGO_B");
        db.execSQL("create table if not exists CITASMETRICA");
        db.execSQL("create table if not exists PARAMETRO");
        db.execSQL("create table if not exists PRODUCTO_A");
        db.execSQL("create table if not exists PRODUCTO_B");
        db.execSQL("create table if not exists RegAgendaA");
        db.execSQL("create table if not exists RegAgendaB");
        db.execSQL("create table if not exists USUARIO");

        /*db.execSQL("create table BUZON_A (id_solicitud integer primary key, fecha_alta datetime, estatus integer, id_usuario integer, comentario text, motivo integer, fecha_modificacion datetime, solicitud_xml text, promedio_scoring text, producto text)");
        db.execSQL("create table BUZON_B (id_solicitud integer primary key, fecha_alta datetime, estatus integer, id_usuario integer, comentario text, motivo integer, fecha_modificacion datetime, solicitud_xml text, promedio_scoring text, producto text)");
        db.execSQL("create table CATALOGO_A (id_catalogo integer primary key, descripcion text, id_tipo_catalogo text, estatus text, padre text)");
        db.execSQL("create table CATALOGO_B (id_catalogo integer primary key, descripcion text, id_tipo_catalogo text, estatus text, padre text)");
        db.execSQL("create table CITASMETRICA (total text, nueva text, cancelada text, realizada text, caducada text, exitosa text)");
        db.execSQL("create table PARAMETRO (id_parametro integer primary key, parametro text, valor text, estatus text)");
        db.execSQL("create table PRODUCTO_A (id_producto integer primary key, nombre text, descripcion text, estatus text, fecha_creacion text, id_empresa integer, comercial text)");
        db.execSQL("create table PRODUCTO_B (id_producto integer primary key, nombre text, descripcion text, estatus text, fecha_creacion text, id_empresa integer, comercial text)");
        db.execSQL("create table RegAgendaA (id_citas integer primary key, id_promotor integer, id_producto integer, dia text, mes text, anio text, inicio text, fin text, estatus text, resultado_cita text, observaciones text, nombre text, secondnombre text, paterno text, materno text, sexo text, ingresos text, dia_nac text, mes_nac text, anio_nac text, calle text, noint text, noext text, cpdom text, estado text, delegacion text, colonia text, tiemporesidencia text, estatusresidencia text, montovivienda text, email text, telcasa text, telmovil text, companiamovil text, estatusenvio text, rfc text, idestado text, iddelmun text)");
        db.execSQL("create table RegAgendaB (id_citas integer primary key, id_promotor integer, id_producto integer, dia text, mes text, anio text, inicio text, fin text, estatus text, resultado_cita text, observaciones text, nombre text, secondnombre text, paterno text, materno text, sexo text, ingresos text, dia_nac text, mes_nac text, anio_nac text, calle text, noint text, noext text, cpdom text, estado text, delegacion text, colonia text, tiemporesidencia text, estatusresidencia text, montovivienda text, email text, telcasa text, telmovil text, companiamovil text, estatusenvio text, rfc text, idestado text, iddelmun text)");
        db.execSQL("create table USUARIO (id_usuario integer primary key, user text, contrasenia text, tipo_usuario text, compania text)");*/

    }
}