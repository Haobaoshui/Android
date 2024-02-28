package com.example.huarongpuzzlegame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImagePiece {

    private Bitmap bitmap;

    public void onDraw(Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setStrokeWidth((float) 5);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.drawRect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight(), paint);
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
