package com.example.huarongpuzzlegame.game.nonuniform;


public class GameNonUniformLogic {

    private static final String TAG = "GameNonUniformLogic";
    private final int mRows;//游戏行数
    private final int mCols;//游戏列数
    private final int mWidth;//屏幕宽度
    private final int mHeight;//屏幕高度
    private final int mBlankSize;//空格个数，空格不显示图片
    private GameMap mGameMap = null;
    private int mSteps;//游戏步骤

    /**
     * 保存屏幕中各位置的图片索引,0-7为合法的图片索引，-1表示该位置没有图片
     * [0][1][2]
     * [3][4][5]
     * [6][7][-1]
     */


    GameNonUniformLogic(int mRows, int mCols, int mWidth, int mHeight, int mBlankSize) {
        this.mRows = mRows;
        this.mCols = mCols;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mBlankSize = mBlankSize;
        mSteps = 0;
        mGameMap = null;
    }

    GameNonUniformLogic(int mRows, int mCols, int mWidth, int mHeight) {
        this.mRows = mRows;
        this.mCols = mCols;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        mBlankSize = 1;
        mSteps = 0;
        mGameMap = null;
    }

    /**
     * 判断游戏是否正确,分为两种情况，一种是特定图片在指定位置（例如华容道中曹操位于能一步到达出口的位置即可），另一种要求所有图片位与特定位置
     *
     * @return
     */
    static boolean isCorrect() {

        return true;
    }

    void move(int x0, int y0, int x1, int y1) {

        if (x0 == x1 && y0 == y1) return;

        int xLen = x1 - x0;
        int yLen = y1 - y0;
        if (xLen >= 0 && yLen >= 0) {
            if (xLen > yLen) move(x0, y0, DIRECTION.RIGHT);
            else if (yLen > xLen) move(x0, y0, DIRECTION.BOTTOM);
        } else if (xLen >= 0 && yLen <= 0) {
            if (xLen > -yLen) move(x0, y0, DIRECTION.RIGHT);
            else if (-yLen > xLen) move(x0, y0, DIRECTION.TOP);
        } else if (xLen <= 0 && yLen >= 0) {
            if (-xLen > yLen) move(x0, y0, DIRECTION.LEFT);
            else if (yLen > -xLen) move(x0, y0, DIRECTION.BOTTOM);
        } else if (xLen <= 0 && yLen <= 0) if (-xLen > -yLen) move(x0, y0, DIRECTION.LEFT);
        else if (-yLen > -xLen) move(x0, y0, DIRECTION.TOP);

    }

    /**
     * 初始化，将mScheenImageIndex数组填满不重复的随机数，其值范围为0~(mRows * mCols-1)
     */
    boolean intiGame() {
        if (mRows <= 0 || mCols <= 0 || mWidth <= 0 || mHeight <= 0) return false;


        return true;
    }


    /**
     * 得到指定位置(x,y)的地图单元
     *
     * @param x
     * @param y
     * @return
     */
    private MapCell getPosition(int x, int y) {
        int nCellWidth = mWidth / mCols;
        int nCellHeight = mHeight / mRows;

        int row = x / nCellHeight;
        int col = y / nCellWidth;

        return mGameMap.getMapCell(row, col);
    }

    /**
     * 向左移动
     *
     * @param position
     * @return
     */
    private boolean moveLeft(MapCell position) {

        mSteps++;
        return true;
    }

    /**
     * 向右移动
     *
     * @param position
     * @return
     */
    private boolean moveRight(MapCell position) {

        mSteps++;
        return true;
    }

    /**
     * 向上移动
     *
     * @param position
     * @return
     */
    private boolean moveTop(MapCell position) {

        mSteps++;
        return true;
    }

    /**
     * 向下移动
     *
     * @param position
     * @return
     */
    private boolean moveBottom(MapCell position) {

        mSteps++;
        return true;
    }

    private boolean move(MapCell position, DIRECTION direction) {
        if (position == null) return false;


        switch (direction) {
            case LEFT:
                //找到位置
                return moveLeft(position);

            case RIGHT:
                return moveRight(position);

            case TOP:
                return moveTop(position);
            case BOTTOM:
                return moveBottom(position);

        }
        return false;
    }

    /**
     * 移动图片；先判断某个位置是否可以向某个方向移动，即某个方向是否有空位置，然后移动
     *
     * @param x
     * @param y
     * @param direction
     * @return
     */
    private boolean move(int x, int y, DIRECTION direction) {

        MapCell mapCell = getPosition(x, y);

        return move(mapCell, direction);
    }

    /**
     * 得到图片索引
     *
     * @param row
     * @param col
     * @return
     */
    int getIndex(int row, int col) {
        MapCell mapCell = mGameMap.getMapCell(row, col);
        if (mapCell != null) return mapCell.getValue();
        return -1;
    }

    enum DIRECTION {LEFT, RIGHT, TOP, BOTTOM}


}
