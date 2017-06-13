package com.seotm.jigsawpuzzleview.pattern;

/**
 * Created by seotm on 13.06.17.
 */

public interface SegmentMoving {

    void setSegmentMovableAt(int x, int y);
    void moveMovableSegmentTo(int x, int y);
    void updatePositions();

}
