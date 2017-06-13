package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class Segment {

    public int position;
    private Bitmap bitmap;

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
}
