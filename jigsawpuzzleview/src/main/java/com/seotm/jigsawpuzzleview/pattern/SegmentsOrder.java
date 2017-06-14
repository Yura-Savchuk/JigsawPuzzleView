package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.Random;

/**
 * Created by seotm on 14.06.17.
 */

class SegmentsOrder {

    private final SparseIntArray order = new SparseIntArray();
    private final Segments segments;

    SegmentsOrder(Segments segments) {
        this.segments = segments;
    }

    void setupOrderFromInitialData() {
        if (order.size() > 0) order.clear();
        int segmentsCount = getSegmentsCount();
        for (int i=0; i<segmentsCount; i++) {
            order.put(i, i);
        }
    }

    private int getSegmentsCount() {
        Bitmap [] segmentsBitmap = segments.segmentsBitmap;
        int [] segmentsDrawableRes = segments.segmentsDrawableRes;
        if (segmentsBitmap != null) {
            return segmentsBitmap.length;
        }
        if (segmentsDrawableRes == null) throw new RuntimeException("Internal error.");
        return segmentsDrawableRes.length;
    }

    void createSegmentsArray() {
        if (segments.sizedSegments != null) return;
        Segment [] sizedSegments = new Segment[getSegmentsCount()];
        for (int i=0; i<sizedSegments.length; i++) {
            sizedSegments[i] = new Segment();
        }
        segments.sizedSegments = sizedSegments;
    }

    void swapSegments(int index1, int index2) {
        if (index1 == index2) return;
        swapSegmentsInArray(index1, index2);
        swapSegmentsOrder(index1, index2);
    }

    private void swapSegmentsInArray(int index1, int index2) {
        Segment[] sizedSegments = segments.sizedSegments;
        Segment segment1 = sizedSegments[index1];
        Segment segment2 = sizedSegments[index2];
        Position position = segment1.centerPosition;
        segment1.centerPosition = segment2.centerPosition;
        segment2.centerPosition = position;
        sizedSegments[index1] = segment2;
        sizedSegments[index2] = segment1;
    }

    private void swapSegmentsOrder(int index1, int index2) {
        int key1 = order.indexOfValue(index1);
        int key2 = order.indexOfValue(index2);
        order.put(key1, index2);
        order.put(key2, index1);
    }

    boolean isSegmentsInSequentialOrder() {
        for (int i=0; i<order.size(); i++) {
            if (order.get(i) != i) {
                return false;
            }
        }
        return true;
    }

    int getPostionForIndex(int index) {
        return order.get(index);
    }

    void blend() {
        Random random = new Random();
        for (int i=0; i<order.size(); i++) {
            int currentIndex = order.get(i);
            int randomIndex = random.nextInt(order.size()-1);
            if (currentIndex != randomIndex) {
                if (segments.sizedSegments != null) {
                    swapSegmentsInArray(currentIndex, randomIndex);
                }
                swapSegmentsOrder(currentIndex, randomIndex);
            }
        }
    }
}
