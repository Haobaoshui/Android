package cn.edu.bistu.cs.se.matrixapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hbs on 2015-11-1.
 */
public class CustomView extends View {
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        //从res/drawable中装载图像
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.android);

        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        //1.原始图像
         canvas.drawBitmap(bmp,0,0,paint);

        //2.变换后图像，先平移，再旋转
        Matrix matrix = new Matrix();
        matrix.setTranslate(0, bmpHeight / 2);
        matrix.preRotate(180, bmpWidth / 2, bmpHeight / 2);
        canvas.drawBitmap(bmp, matrix, paint);
    }
}
