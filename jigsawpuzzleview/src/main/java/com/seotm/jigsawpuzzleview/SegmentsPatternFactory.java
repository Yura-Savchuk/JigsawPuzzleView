package com.seotm.jigsawpuzzleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.seotm.jigsawpuzzleview.pattern.p12.SegmentPattern12;
import com.seotm.jigsawpuzzleview.pattern.SegmentsPattern;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentsPatternFactory {

    private final Context context;
    private final PuzzleGatherListener gatherListener;

    public SegmentsPatternFactory(@NonNull Context context, @NonNull PuzzleGatherListenerProxy gatherListener) {
        this.context = context;
        this.gatherListener = gatherListener;
    }

    public SegmentsPattern createPattern(@NonNull @DrawableRes int [] segments) {
        switch (segments.length) {
            case 12: {
                return new SegmentPattern12(segments, context, gatherListener);
            }
        }
        throw new RuntimeException("Un supported segments count. Only 12 segment count supported.");
    }

    public SegmentsPattern createPattern(Bitmap[] bitmaps) {
        switch (bitmaps.length) {
            case 12: {
                return new SegmentPattern12(bitmaps, context, gatherListener);
            }
        }
        throw new RuntimeException("Un supported segments count. Only 12 segment count supported.");
    }
}
