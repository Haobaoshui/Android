package com.example.huarongpuzzlegame.game.uniform;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.huarongpuzzlegame.game.ImagePiece;

import java.util.ArrayList;
import java.util.List;

class ImageSplitter {

    /**
     * 把位图分割为指定行和列的碎片列表
     *
     * @param bitmap
     * @param rows
     * @param cols
     * @param destWidth
     * @param destHeight
     * @return
     */
    static List<ImagePiece> split(Bitmap bitmap, int rows, int cols, int destWidth, int destHeight) {
        List<ImagePiece> imagePieceList = new ArrayList<>(rows * cols);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceWidth = width / cols;
        int pieceHeight = height / rows;

        Matrix matrix = new Matrix();
        matrix.postScale(destWidth / (float) width, destHeight / (float) height);

        //分割
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                ImagePiece piece = new ImagePiece();


                int xValue = j * pieceWidth;
                int yValue = i * pieceHeight;
                piece.setBitmap(Bitmap.createBitmap(bitmap, xValue, yValue, pieceWidth, pieceHeight, matrix, true));
                imagePieceList.add(piece);
            }

        // if (imagePieceList.size() > 0) imagePieceList.remove(imagePieceList.size() - 1);

        return imagePieceList;
    }
}
