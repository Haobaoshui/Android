package cn.edu.bistu.se.android.plotapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hbs on 2015-11-2.
 */
public class CustomView extends View {
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String getStrFunction() {
        return strFunction;
    }

    public void setStrFunction(String strFunction) {
        this.strFunction = strFunction;
    }

    String strFunction;


    @Override
    protected void onDraw(Canvas canvas) {
        if(strFunction==null || strFunction.length()==0)
            return;
        Rect rect=new Rect();
        rect.left=0;
        rect.top=0;
        rect.right = getWidth();
        rect.bottom=getHeight();

        Axis axis=new Axis(rect);


        axis.draw(canvas);
        Plot plot=new Plot(axis,strFunction,"x");
        plot.draw(canvas);


    }
}