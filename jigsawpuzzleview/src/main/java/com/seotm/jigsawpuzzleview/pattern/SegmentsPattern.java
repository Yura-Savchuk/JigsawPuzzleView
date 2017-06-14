package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import com.seotm.jigsawpuzzleview.state.StateSavable;

/**
 * Created by seotm on 13.06.17.
 */

public interface SegmentsPattern extends SegmentMoving, StateSavable {

    void onDraw(@NonNull Canvas canvas);
    void updateSize(int w, int h, int oldw, int oldh);
    void blendSegments();

}
