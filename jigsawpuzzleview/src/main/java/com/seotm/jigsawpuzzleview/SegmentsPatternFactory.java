package com.seotm.jigsawpuzzleview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.seotm.jigsawpuzzleview.pattern.SegmentPattern12;
import com.seotm.jigsawpuzzleview.pattern.SegmentsPattern;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentsPatternFactory {

    private final Context context;

    public SegmentsPatternFactory(Context context) {
        this.context = context;
    }

    public SegmentsPattern createPattern(@NonNull @DrawableRes int [] segments) {
        switch (segments.length) {
            case 12: {
                return new SegmentPattern12(segments, context);
            }
        }
        throw new RuntimeException("Un supported segments count. Only 12 segment count supported.");
    }

}
