package com.seotm.jigsawpuzzleview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.seotm.jigsawpuzzleview.pattern.SegmentsPattern;

/**
 * Created by seotm on 13.06.17.
 */

public class JigsawPuzzleView extends View {

    private SegmentsPattern segmentsPattern;

    public JigsawPuzzleView(Context context) {
        super(context);
    }

    public JigsawPuzzleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JigsawPuzzleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSegments(@NonNull @DrawableRes int [] segments) {
        SegmentsPatternFactory patternFct = new SegmentsPatternFactory(getContext());
        segmentsPattern = patternFct.createPattern(segments);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (segmentsPattern != null) {
            segmentsPattern.updateSize(w, h, oldw, oldh);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (segmentsPattern != null) {
            segmentsPattern.onDraw(canvas);
        }
    }
}
