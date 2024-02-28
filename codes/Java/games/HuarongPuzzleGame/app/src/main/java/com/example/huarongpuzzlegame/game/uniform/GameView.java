package com.example.huarongpuzzlegame.game.uniform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.huarongpuzzlegame.R;
import com.example.huarongpuzzlegame.game.ImagePiece;

import java.util.List;

public class GameView extends View {

    private final int mRows = 2;
    private final int mCols = 2;
    private GameUniformLogic mGameLogic = null;
    private int mWidth = 0;
    private int mHeight = 0;
    private List<ImagePiece> imagePieceList = null;
    private int downX = 0;
    private int downY = 0;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    public void splitImage(int width, int height) {
        mWidth = width;
        mHeight = height;
        //分割成4行4列
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.james);
        imagePieceList = ImageSplitter.split(bitmap, mRows, mCols, width, height);

        mGameLogic = new GameUniformLogic(mRows, mCols, width, height);
        if (!mGameLogic.intiGame()) mGameLogic = null;

        //刷新屏幕
        invalidate();
    }


    /**
     * 绘制分割线
     *
     * @param canvas
     */
    private void onDrawBoard(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth((float) 5);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        //绘制分割线
        for (int i = 0; i <= mRows; i++)
            canvas.drawLine(0, i * mHeight / mRows, mWidth, i * mHeight / mRows, paint);
        for (int i = 0; i < mCols; i++)
            canvas.drawLine(i * mWidth / mCols, 0, i * mWidth / mCols, mHeight, paint);

    }

    private void onDrawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawRect(0, 0, mWidth, mHeight, paint);


    }

    /**
     * 绘制图片
     *
     * @param canvas
     */
    private void onDrawImages(Canvas canvas) {
        if (mGameLogic == null || imagePieceList == null || imagePieceList.size() == 0) return;


        int offset_x = 0;
        int offset_y = 0;
        int cellWidth = mWidth / mRows;
        int cellHeight = mHeight / mCols;


        for (int row = 0; row < mRows; row++)
            for (int col = 0; col < mCols; col++) {
                int index = mGameLogic.getIndex(row, col);

                if (index < 0) continue;
                ImagePiece imagePiece = imagePieceList.get(index);
                Bitmap bitmap = imagePiece.getBitmap();
                int x = offset_x + col * cellWidth;
                int y = offset_y + row * cellHeight;
                imagePiece.onDraw(canvas, x, y);
            }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
        onDrawBackground(canvas);

        //绘制图片
        onDrawImages(canvas);

        //绘制分割线
        onDrawBoard(canvas);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int newX = 0;
        int newY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:

                newX = (int) event.getX();
                newY = (int) event.getY();
                mGameLogic.move(downX, downY, newX, newY);

                invalidate();
                if (mGameLogic.isCorrect()) {
                    Toast toast = Toast.makeText(getContext(), "Toast提示消息", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
        }
        return true;
    }
}
