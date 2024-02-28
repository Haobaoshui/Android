package com.example.huarongpuzzlegame.game.nonuniform;

public class MapCell {
    private int beginRow;
    private int beginCol;
    private int endRow;
    private int endCol;
    private int value;

    MapCell(int beginRow, int beginCol, int endRow, int endCol, int value) {
        this.beginRow = beginRow;
        this.beginCol = beginCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    boolean isInCell(int row, int col) {
        if (row >= beginRow && row < endRow && col >= beginCol && col < endCol) return true;
        return false;
    }

    public int getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(int beginRow) {
        this.beginRow = beginRow;
    }

    public int getBeginCol() {
        return beginCol;
    }

    public void setBeginCol(int beginCol) {
        this.beginCol = beginCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }
}
