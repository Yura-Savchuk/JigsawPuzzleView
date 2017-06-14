package com.seotm.jigsawpuzzleview.pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class Segments {

    private final int [] segmentsDrawableRes;
    private final Bitmap [] segmentsBitmap;

    private final Context context;
    private final SegmentSize segmentSize;

    private Segment [] sizedSegments;

    private int viewWidth = 0;
    private int viewHeight = 0;

    public Segments(@NonNull @DrawableRes int[] segments, @NonNull Context context, SegmentSize segmentSize) {
        segmentsDrawableRes = segments;
        segmentsBitmap = null;
        this.context = context;
        this.segmentSize = segmentSize;
    }

    public Segments(Bitmap[] bitmaps, @NonNull Context context, SegmentSize segmentSize) {
        segmentsDrawableRes = null;
        segmentsBitmap = bitmaps;
        this.context = context;
        this.segmentSize = segmentSize;
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
        createSegmentsArray();
        viewWidth = w;
        viewHeight = h;
        updateSegmentsSize();
    }

    private void updateSegmentsSize() {
        int segmentWidth = (int) (segmentSize.widthRatio*viewWidth);
        int segmentHeight = (int) (segmentSize.heightRatio*viewHeight);
        for (int i=0; i<sizedSegments.length; i++) {
            Bitmap bitmap;
            if (segmentsDrawableRes != null) {
                bitmap = createSegmentFromDrawable(segmentWidth, segmentHeight, i);
            } else {
                bitmap = createSegmentFromBitmap(segmentWidth, segmentHeight, i);
            }
            sizedSegments[i].setBitmap(bitmap);
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

    private void createSegmentsArray() {
        if (sizedSegments != null) return;
        sizedSegments = new Segment[getSegmentsCount()];
        for (int i=0; i<sizedSegments.length; i++) {
            sizedSegments[i] = new Segment(i);
        }
    }

    private int getSegmentsCount() {
        if (segmentsDrawableRes != null) {
            return segmentsDrawableRes.length;
        }
        if (segmentsBitmap == null) throw new RuntimeException("Internal error.");
        return segmentsBitmap.length;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void swapSegments(int index, Segment segment) {
        Segment segment2 = sizedSegments[index];
        if (segment2 == segment) return;
        Position position = segment.centerPosition;
        int order = segment.position;
        segment.centerPosition = segment2.centerPosition;
        segment.position = segment2.position;
        segment2.centerPosition = position;
        segment2.position = order;
        int segmentIndex = 0;
        for (int i=1; i<sizedSegments.length; i++) {
            if (sizedSegments[i] == segment) {
                segmentIndex = i;
                break;
            }
        }
        sizedSegments[index] = segment;
        sizedSegments[segmentIndex] = segment2;
    }
}
