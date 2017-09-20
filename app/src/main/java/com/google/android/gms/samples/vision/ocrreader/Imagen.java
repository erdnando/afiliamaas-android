package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class Imagen extends AppCompatActivity {

    TextView textDatos;

    ImageView Foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        textDatos = (TextView) findViewById(R.id.textDatos);
        Foto = (ImageView) findViewById(R.id.Foto);

        Intent intent = getIntent();

        String datos = intent.getStringExtra("Datos");
        String foto = intent.getStringExtra("Foto");

        textDatos.setText(datos);

        byte[] ByteFoto = Base64.decode(foto, Base64.DEFAULT);
        Bitmap FotoDecodificada = BitmapFactory.decodeByteArray(ByteFoto, 0, ByteFoto.length);
        Foto.setImageBitmap(FotoDecodificada);
    }
}
