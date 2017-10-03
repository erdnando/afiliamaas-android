package com.google.android.gms.samples.vision.ocrreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Firmar extends AppCompatActivity {

    private CanvasView canvasView;

    Button btnBorrarCanvas, btnGuardarCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmar);

        canvasView = (CanvasView) findViewById(R.id.canvas);
        btnBorrarCanvas = (Button) findViewById(R.id.btnBorrarFirma);
        btnGuardarCanvas = (Button) findViewById(R.id.btnGuardarFirma);
    }

    public void borrarCanvas(View v) {

        canvasView.clearCanvas();
    }

    public void guardarCanvas(View v) {
    }
}
