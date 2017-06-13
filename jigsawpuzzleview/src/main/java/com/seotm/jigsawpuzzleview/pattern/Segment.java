package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class Segment {

    public int position;
    public Position centerPosition;
    private Bitmap bitmap;
    private Position motionPosition;

    public Segment(int position) {
        this.position = position;
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        if (this.bitmap != null) this.bitmap.recycle();
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setMotionPosition(int x, int y) {
        if (motionPosition == null) {
            motionPosition = new Position(x, y);
            return;
        }
        motionPosition.moveTo(x, y);
    }

    public boolean isMovable() {
        return motionPosition != null;
    }

    public Position getMotionPosition() {
        return motionPosition;
    }

    public void stopMoving() {
        motionPosition = null;
    }
}
