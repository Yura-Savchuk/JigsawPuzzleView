package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentsDrawer {

    private final Segments segments;
    private final SegmentPoint positions;

    public SegmentsDrawer(Segments segments, SegmentPoint positions) {
        this.segments = segments;
        this.positions = positions;
    }

    public void draw(@NonNull Canvas canvas) {
        if (!segments.isSized()) {
            segments.updateSize(canvas.getWidth(), canvas.getHeight());
        }
        Segment movableSegment = null;
        Segment[] segments = this.segments.getSegments();
        for (int i=0; i<segments.length; i++) {
            Segment segment = segments[i];
            if (segment.isMovable()) {
                movableSegment = segment;
            } else {
                drawSegment(segment, i, canvas);
            }
        }
        if (movableSegment != null) drawMovableSegment(movableSegment, canvas);
    }

    private void drawSegment(@NonNull Segment segment, int orderPosition, @NonNull Canvas canvas) {
        Position centerPosition = getCenterPosition(segment, orderPosition);
        drawSegment(segment, canvas, centerPosition);
    }

    private void drawSegment(@NonNull Segment segment, @NonNull Canvas canvas, Position centerPosition) {
        Bitmap bitmap = segment.getBitmap();
        int left = centerPosition.getX() - (bitmap.getWidth()/2);
        int top = centerPosition.getY() - (bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, left, top, null);
    }

    private Position getCenterPosition(@NonNull Segment segment, int orderPosition) {
        if (segment.centerPosition == null) {
            int width = segments.getViewWidth();
            int height = segments.getViewHeight();
            segment.centerPosition = positions.getSegmentCenterPositionAt(orderPosition + 1, width, height);
        }
        return segment.centerPosition;
    }

    private void drawMovableSegment(@NonNull Segment segment, @NonNull Canvas canvas) {
        if (!segment.isMovable()) throw new RuntimeException("Internal error.");
        drawSegment(segment, canvas, segment.getMotionPosition());
    }

}
