package com.example.huarongpuzzlegame.game.nonuniform;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.huarongpuzzlegame.game.ImagePiece;

import java.util.ArrayList;
import java.util.List;

public class ImageNonUniformSplitter {

    static ImagePiece split(Bitmap bitmap, int rows, int cols, int beginRow, int endRow, int beginCol, int endCol, int destWidth, int destHeight) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceWidth = width / cols;
        int pieceHeight = height / rows;


        Matrix matrix = new Matrix();
        matrix.postScale(destWidth / (float) width, destHeight / (float) height);


        ImagePiece piece = new ImagePiece();


        int xValue = beginCol * pieceWidth;
        int yValue = beginRow * pieceHeight;
        piece.setBitmap(Bitmap.createBitmap(bitmap, xValue, yValue, pieceWidth * (endCol - beginCol), pieceHeight * (endRow - beginRow), matrix, true));
        return piece;


    }

    static List<ImagePiece> split(Bitmap bitmap, GameMap gameMap, int destWidth, int destHeight) {
        List<ImagePiece> imagePieceList = new ArrayList<>();

        return imagePieceList;
    }
}
