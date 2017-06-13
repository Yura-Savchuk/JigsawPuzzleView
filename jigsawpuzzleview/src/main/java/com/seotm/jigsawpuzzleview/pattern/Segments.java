package com.seotm.jigsawpuzzleview.pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class Segments {

    private final int [] segmentsDrawableRes;
    private final Context context;
    private final SegmentSize segmentSize;

    private Segment [] sizedSegments;

    public Segments(@NonNull @DrawableRes int[] segments, @NonNull Context context, SegmentSize segmentSize) {
        segmentsDrawableRes = segments;
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
        int segmentWidth = (int) (segmentSize.widthRatio*w);
        int segmentHeight = (int) (segmentSize.heightRatio*h);
        for (int i=0; i<sizedSegments.length; i++) {
            int segmentDrawableRes = segmentsDrawableRes[i];
            Bitmap bitmap = createSegmentBitmap(segmentDrawableRes, segmentWidth, segmentHeight);
            sizedSegments[i].setBitmap(bitmap);
        }
    }

    private void createSegmentsArray() {
        if (sizedSegments != null) return;
        sizedSegments = new Segment[segmentsDrawableRes.length];
        for (int i=0; i<sizedSegments.length; i++) {
            sizedSegments[i] = new Segment(i);
        }
    }

    private Bitmap createSegmentBitmap(@DrawableRes int drawableRes, int widht, int height) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), drawableRes, null);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, widht, height, true);
        originalBitmap.recycle();
        return scaledBitmap;
    }
}
