package com.google.android.gms.samples.vision.ocrreader.ui.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View{

    public int width;
    public  int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    Context context;

    public Lienzo(Context context, AttributeSet attrs){

        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){

        //Configuracion del area sobre la que pintar

        this.context = context;

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    //Tamaño asignado a la vista
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    //Pinta la vista será llamado desde el onTouchEvent
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath, mPaint);
    }

    private void StartTouch(float x, float y){

        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void  moveTouch(float x, float y){

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOLERANCE || dy >= TOLERANCE){

            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);

            mX = x;
            mY = y;

        }
    }

    public void clearCanvas(){

        mPath.reset();
        invalidate();
    }

    private void upTouch(){

        mPath.lineTo(mX, mY);
    }

    //Registra los touch del usuario
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                StartTouch(x, y);
                invalidate();

                break;

            case MotionEvent.ACTION_MOVE:

                moveTouch(x, y);
                invalidate();

                break;

            case MotionEvent.ACTION_UP:

                upTouch();
                invalidate();

                break;
        }
        return true;
    }
}
