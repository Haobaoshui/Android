package cn.edu.bistu.cs.se.lineargradientapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
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

        //设置画布背景色
        canvas.drawColor(Color.WHITE);

        Paint paint=new Paint();

        //绘制矩形，内部不填充
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(10, 10, 100, 100, paint);

        //使用着色器绘制矩形
         Shader shader=new LinearGradient(0,0,50,50,new int[]{Color.RED,Color.GREEN,Color.BLUE},null, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        canvas.drawRect(10, 110, 200, 200, paint);

        //使用着色器填充矩形
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(shader);
        canvas.drawRect(10, 210, 300, 300, paint);
    }
}
