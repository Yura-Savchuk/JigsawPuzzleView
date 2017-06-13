package com.seotm.jigsawpuzzleview.pattern;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentsDrawer {

    private final Segments segments;
    private final SegmentPositions positions;

    public SegmentsDrawer(Segments segments, SegmentPositions positions) {
        this.segments = segments;
        this.positions = positions;
    }

    public void draw(@NonNull Canvas canvas) {
        if (!segments.isSized()) {
            segments.updateSize(canvas.getWidth(), canvas.getHeight());
        }
        Segment movableSegment = null;
        for (Segment segment : segments.getSegments()) {
            if (segment.isMovable()) {
                movableSegment = segment;
            } else {
                drawSegment(canvas, segment);
            }
        }
        if (movableSegment != null) {
            drawSegment(canvas, movableSegment);
        }
    }

    private void drawSegment(@NonNull Canvas canvas, @NonNull Segment segment) {
        Position centerPosition = getCenterPosition(segment);
        Bitmap bitmap = segment.getBitmap();
        int left = centerPosition.getX() - (bitmap.getWidth()/2);
        int top = centerPosition.getY() - (bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, left, top, null);
    }

    private Position getCenterPosition(@NonNull Segment segment) {
        if (segment.isMovable()) {
            return segment.getMotionPosition();
        }
        if (segment.centerPosition == null) {
            int width = segments.getViewWidth();
            int height = segments.getViewHeight();
            segment.centerPosition = positions.getSegmentCenterPositionAt(segment.position+1, width, height);
        }
        return segment.centerPosition;
    }

}
