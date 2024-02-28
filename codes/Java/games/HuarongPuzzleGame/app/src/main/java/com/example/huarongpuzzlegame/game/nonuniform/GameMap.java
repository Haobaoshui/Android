package com.example.huarongpuzzlegame.game.nonuniform;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏地图，相同区域值相同
 * [0][0][1][1]
 * [0][0][2][2]
 * [3][3][3][3]
 * [4][4][4][4]
 */
public class GameMap {
    private int mRows;
    private int mCols;


    /**
     * 保存屏幕中各位置的图片索引,-1表示该位置没有图片
     */
    private int[][] mMap = null;
    private List<MapCell> mMapCellList = null;

    public GameMap(int mRows, int mCols) {
        this.mRows = mRows;
        this.mCols = mCols;
    }

    public List<MapCell> getmMapCellList() {
        return mMapCellList;
    }

    public MapCell getMapCell(int row, int col) {
        for (MapCell mapCell : mMapCellList) if (mapCell.isInCell(row, col)) return mapCell;
        return null;
    }

    /**
     * 设置经典布局
     */
    public void setClassicLayout() {
        mRows = 5;
        mCols = 4;
        mMap = new int[][]{
                {0, 1, 1, 2},
                {0, 1, 1, 2},
                {3, -1, -1, 5},
                {3, 4, 4, 5},
                {6, 7, 8, 9},
        };
    }

    public boolean initMap() {
        if (mRows <= 0 || mCols <= 0) return false;
        mMap = new int[mRows][mCols];
        for (int i = 0; i < mRows; i++) for (int j = 0; j < mCols; j++) mMap[i][j] = -1;
        mMapCellList = genMap();
        return true;
    }

    /**
     * 给地图中指定位置赋值
     *
     * @param row
     * @param col
     * @param value
     * @return
     */
    private boolean setValue(int row, int col, int value) {
        if (row < 0 || col < 0 || row >= mRows || col >= mCols) return false;
        mMap[row][col] = value;
        mMapCellList = genMap();
        return true;
    }

    private boolean hasValue(int value) {
        for (int i = 0; i < mRows; i++)
            for (int j = 0; j < mCols; j++) if (mMap[i][j] == value) return true;
        return false;
    }

    /**
     * 给特定区域赋值
     *
     * @param beginRow
     * @param beginCol
     * @param endRow
     * @param endCol
     * @param value
     */
    private void setValue(int beginRow, int beginCol, int endRow, int endCol, int value) {
        for (int i = beginRow; i < endRow; i++)
            for (int j = beginCol; j < endCol; j++) setValue(i, j, value);
    }

    /**
     * 给特定区域赋值
     *
     * @param beginRow
     * @param beginCol
     * @param endRow
     * @param endCol
     */
    public void add(int beginRow, int beginCol, int endRow, int endCol) {
        for (int value = 0; value < mRows * mCols; value++) {
            boolean flag = false;
            for (int i = 0; i < mRows; i++)
                for (int j = 0; j < mCols; j++) {
                    if (mMap[i][j] == value) flag = true;
                    break;
                }
            if (!flag) setValue(beginRow, beginCol, endRow, endCol, value);
        }
    }

    /**
     * 生成地图中各个单元
     *
     * @return
     */
    private List<MapCell> genMap() {

        List<MapCell> mapCellList = new ArrayList<>();
        for (int value = 0; value < mRows * mCols; value++) {
            MapCell mapCell = getMapCell(value);
            if (mapCell != null) mapCellList.add(mapCell);
        }
        if (mapCellList.size() > 0) return mapCellList;
        return null;
    }

    /**
     * 根据值获得地图单元
     *
     * @param value
     * @return
     */
    private MapCell getMapCell(int value) {
        int beginRow = -1;
        int beginCol = -1;
        int endRow = -1;
        int endCol = -1;
        for (int row = 0; row < mRows; row++)
            for (int col = 0; col < mCols; col++)
                if (mMap[row][col] == value) {
                    beginRow = row;
                    beginCol = col;
                    break;
                }

        if (beginRow == -1) return null;

        for (int row = mRows - 1; row >= beginRow; row--)
            for (int col = mCols - 1; col >= beginCol; col--)
                if (mMap[row][col] == value) {
                    endRow = row + 1;
                    endCol = col + 1;
                    break;
                }
        return new MapCell(beginRow, beginCol, endRow, endCol, value);
    }
}
