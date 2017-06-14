package com.seotm.jigsawpuzzleview.pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.seotm.jigsawpuzzleview.PuzzleGatherListener;
import com.seotm.jigsawpuzzleview.state.StateSavable;

/**
 * Created by seotm on 13.06.17.
 */

public class Segments implements StateSavable {

    final int [] segmentsDrawableRes;
    final Bitmap [] segmentsBitmap;
    Segment [] sizedSegments;

    private final Context context;
    private final SegmentSize segmentSize;
    private final PuzzleGatherListener gatherListener;
    private final SegmentsOrder order = new SegmentsOrder(this);

    private int viewWidth = 0;
    private int viewHeight = 0;

    public Segments(@NonNull @DrawableRes int[] segments, @NonNull Context context, SegmentSize segmentSize, @NonNull PuzzleGatherListener gatherListener) {
        segmentsDrawableRes = segments;
        this.gatherListener = gatherListener;
        segmentsBitmap = null;
        this.context = context;
        this.segmentSize = segmentSize;
        order.setupOrderFromInitialData();
    }

    public Segments(Bitmap[] bitmaps, @NonNull Context context, SegmentSize segmentSize, @NonNull PuzzleGatherListener gatherListener) {
        this.gatherListener = gatherListener;
        segmentsDrawableRes = null;
        segmentsBitmap = bitmaps;
        this.context = context;
        this.segmentSize = segmentSize;
        order.setupOrderFromInitialData();
    }

    public boolean isSized() {
        return sizedSegments != null;
    }

    public Segment [] getSegments() {
        if (sizedSegments == null) {
            throw new RuntimeException("Segments size must be counted first. Call isSized() method to check.");
        }
        return sizedSegments;
    }


    public void updateSize(int w, int h) {
        order.createSegmentsArray();
        viewWidth = w;
        viewHeight = h;
        refreshSizedSegmentsArray();
    }

    private void refreshSizedSegmentsArray() {
        int segmentWidth = (int) (segmentSize.widthRatio*viewWidth);
        int segmentHeight = (int) (segmentSize.heightRatio*viewHeight);
        for (int i=0; i<sizedSegments.length; i++) {
            Bitmap bitmap;
            if (segmentsDrawableRes != null) {
                bitmap = createSegmentFromDrawable(segmentWidth, segmentHeight, i);
            } else {
                bitmap = createSegmentFromBitmap(segmentWidth, segmentHeight, i);
            }
            int position = order.getPositionForIndex(i);
            sizedSegments[position].setBitmap(bitmap);
        }
    }

    private Bitmap createSegmentFromDrawable(int segmentWidth, int segmentHeight, int item) {
        if (segmentsDrawableRes == null) throw new RuntimeException("Internal error.");
        int drawableRes = segmentsDrawableRes[item];
        return new BitmapFactoryExtension(context)
                .createSegmentBitmap(drawableRes, segmentWidth, segmentHeight);
    }

    private Bitmap createSegmentFromBitmap(int segmentWidth, int segmentHeight, int item) {
        if (segmentsBitmap == null) throw new RuntimeException("Internal error.");
        Bitmap segmentBitmap = segmentsBitmap[item];
        return  Bitmap.createScaledBitmap(segmentBitmap, segmentWidth, segmentHeight, true);
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void swapSegments(int index1, int index2) {
        order.swapSegments(index1, index2);
    }

    public void stopMoving(@NonNull Segment segment) {
        segment.stopMoving();
        if (order.isSegmentsInSequentialOrder()) {
            gatherListener.onGathered();
        }
    }

    public void blendSegments() {
        order.blend();
        if (viewWidth != 0 && viewHeight != 0) {
            updateSize(viewWidth, viewHeight);
        }
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {
        order.saveState(bundle);
    }

    @Override
    public void restoreState(@NonNull Bundle bundle) {
        order.restoreState(bundle);
    }

}
