package com.seotm.jigsawpuzzleview.motion;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.seotm.jigsawpuzzleview.PuzzleGatherListener;
import com.seotm.jigsawpuzzleview.pattern.SegmentsPattern;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentMotionImpl implements SegmentMotion {

    private final SegmentsPattern segmentsPattern;
    private final View view;

    public SegmentMotionImpl(SegmentsPattern segmentsPattern, View view) {
        this.segmentsPattern = segmentsPattern;
        this.view = view;
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                segmentsPattern.setSegmentMovableAt(x, y);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                segmentsPattern.moveMovableSegmentTo(x, y);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                segmentsPattern.updatePositions();
                view.invalidate();
                break;
            }
        }
    }
}
