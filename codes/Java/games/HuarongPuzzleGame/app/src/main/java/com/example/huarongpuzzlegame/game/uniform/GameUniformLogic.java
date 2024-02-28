package com.example.huarongpuzzlegame.game.uniform;

import java.util.Random;

/**
 * 游戏，图片分割为相同大小
 */
class GameUniformLogic {

    private static final String TAG = "GameLogic";

    private final int mRows;//游戏行数
    private final int mCols;//游戏列数
    private final int mWidth;//屏幕宽度
    private final int mHeight;//屏幕高度
    private final int mBlankSize;//空格个数，空格不显示图片
    private int mSteps;//游戏步骤
    /**
     * 保存屏幕中各位置的图片索引,0-7为合法的图片索引，-1表示该位置没有图片
     * [0][1][2]
     * [3][4][5]
     * [6][7][-1]
     */
    private int[][] mScheenImageIndex = null;

    GameUniformLogic(int mRows, int mCols, int mWidth, int mHeight, int mBlankSize) {
        this.mRows = mRows;
        this.mCols = mCols;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mBlankSize = mBlankSize;
        mSteps = 0;
    }

    GameUniformLogic(int mRows, int mCols, int mWidth, int mHeight) {
        this.mRows = mRows;
        this.mCols = mCols;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        mBlankSize = 1;
        mSteps = 0;
    }

    /**
     * 判断游戏是否正确
     *
     * @return
     */
    boolean isCorrect() {
        int index = 0;
        for (int i = 0; i < mRows; i++)
            for (int j = 0; j < mCols; j++)
                if (mScheenImageIndex[i][j] != -1)
                    if (mScheenImageIndex[i][j] != i * mRows + j) return false;
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
        mScheenImageIndex = new int[mRows][mCols];
        for (int i = 0; i < mRows; i++)
            for (int j = 0; j < mCols; j++) mScheenImageIndex[i][j] = -1;

        Random random = new Random();
        for (int i = 0; i < mRows; i++)
            for (int j = 0; j < mCols; j++)
                while (true) {
                    int r = random.nextInt(mRows * mCols);
                    if (!hasIndex(r)) {
                        mScheenImageIndex[i][j] = r;
                        break;
                    }
                }

        //设置空格位置
        for (int i = 0; i < mBlankSize; i++) {
            int row = random.nextInt(mRows);
            int col = random.nextInt(mCols);

            mScheenImageIndex[row][col] = -1;
        }


        return true;
    }

    /**
     * 二维数组是否存在特定索引，注意，二维数组中值不能重复，-1除外。-1表示该位置没有图片
     *
     * @param index
     * @return
     */
    private boolean hasIndex(int index) {
        for (int i = 0; i < mRows; i++)
            for (int j = 0; j < mCols; j++) if (mScheenImageIndex[i][j] == index) return true;
        return false;
    }

    /**
     * 得到指定位置(x,y)的行和列
     *
     * @param x
     * @param y
     * @return
     */
    private Position getPosition(int x, int y) {
        int nCellWidth = mWidth / mCols;
        int nCellHeight = mHeight / mRows;
        for (int i = 0; i <= mRows; i++)
            for (int j = 0; j < mCols; j++) {
                int left = j * nCellWidth;
                int top = i * nCellHeight;
                if (x >= left && x < left + nCellWidth && y >= top && y < top + nCellHeight)
                    return new Position(i, j);
            }
        return null;
    }

    /**
     * 向左移动
     *
     * @param position
     * @return
     */
    private boolean moveLeft(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int start = 0;
        start = -1;
        for (int i = 0; i < col; i++) if (mScheenImageIndex[row][i] == -1) start = i;
        if (start == -1) return false;

        for (int i = start; i < col; i++) mScheenImageIndex[row][i] = mScheenImageIndex[row][i + 1];
        mScheenImageIndex[row][col] = -1;
        mSteps++;
        return true;
    }

    /**
     * 向右移动
     *
     * @param position
     * @return
     */
    private boolean moveRight(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int start = 0;
        start = -1;
        for (int i = col + 1; i < mCols; i++) if (mScheenImageIndex[row][i] == -1) start = i;
        if (start == -1) return false;

        for (int i = start; i > col; i--) mScheenImageIndex[row][i] = mScheenImageIndex[row][i - 1];
        mScheenImageIndex[row][col] = -1;
        mSteps++;
        return true;
    }

    /**
     * 向上移动
     *
     * @param position
     * @return
     */
    private boolean moveTop(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int start = 0;
        start = -1;
        for (int i = 0; i < row; i++) if (mScheenImageIndex[i][col] == -1) start = i;
        if (start == -1) return false;

        for (int i = start; i < row; i++) mScheenImageIndex[i][col] = mScheenImageIndex[i + 1][col];
        mScheenImageIndex[row][col] = -1;
        mSteps++;
        return true;
    }

    /**
     * 向下移动
     *
     * @param position
     * @return
     */
    private boolean moveBottom(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int start = 0;
        start = -1;
        for (int i = row + 1; i < mRows; i++)
            if (mScheenImageIndex[i][col] == -1) {
                start = i;
                break;
            }

        if (start == -1) return false;


        for (int i = start; i > row; i--) mScheenImageIndex[i][col] = mScheenImageIndex[i - 1][col];
        mScheenImageIndex[row][col] = -1;
        mSteps++;
        return true;
    }

    private boolean move(Position position, DIRECTION direction) {
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

        if (mScheenImageIndex == null) return false;

        Position position = getPosition(x, y);

        return move(position, direction);
    }

    /**
     * 得到图片索引
     *
     * @param row
     * @param col
     * @return
     */
    int getIndex(int row, int col) {
        if (row < 0 || col < 0 || row >= mRows || col >= mCols) return -1;
        return mScheenImageIndex[row][col];
    }

    enum DIRECTION {LEFT, RIGHT, TOP, BOTTOM}

    class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }
}
