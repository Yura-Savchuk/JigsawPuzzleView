package com.seotm.jigsawpuzzleview.pattern.p12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.seotm.jigsawpuzzleview.PuzzleGatherListener;
import com.seotm.jigsawpuzzleview.pattern.Position;
import com.seotm.jigsawpuzzleview.pattern.Segment;
import com.seotm.jigsawpuzzleview.pattern.SegmentPoint;
import com.seotm.jigsawpuzzleview.pattern.SegmentSize;
import com.seotm.jigsawpuzzleview.pattern.Segments;
import com.seotm.jigsawpuzzleview.pattern.SegmentsDrawer;
import com.seotm.jigsawpuzzleview.pattern.SegmentsPattern;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentPattern12 implements SegmentsPattern {

    private static final SegmentSize SEGMENT_SIZE = new SegmentSize(6f/16f, 5f/9f);
    private static final float LEDGE_PART_RATIO = 1f/6f;

    private static final int EMPTY_INDEX = -1;

    private final Segments segments;
    private final SegmentsDrawer segmentsDrawer;
    private final SegmentPoint segmentPoint = new SegmentPoint12();

    public SegmentPattern12(@NonNull @DrawableRes int [] segments, @NonNull Context context, @NonNull PuzzleGatherListener gatherListener) {
        this.segments = new Segments(segments, context, SEGMENT_SIZE, gatherListener);
        segmentsDrawer = new SegmentsDrawer(this.segments, segmentPoint);
    }

    public SegmentPattern12(Bitmap[] segments, @NonNull Context context, @NonNull PuzzleGatherListener gatherListener) {
        this.segments = new Segments(segments, context, SEGMENT_SIZE, gatherListener);
        segmentsDrawer = new SegmentsDrawer(this.segments, segmentPoint);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        segmentsDrawer.draw(canvas);
    }

    @Override
    public void updateSize(int w, int h, int oldw, int oldh) {
        segments.updateSize(w, h);
    }

    @Override
    public void blendSegments() {
        segments.blendSegments();
    }

    @Override
    public void setSegmentMovableAt(int x, int y) {
        if (!segments.isSized()) return;
        for (Segment segment : segments.getSegments()) {
            int centerX = segment.centerPosition.getX();
            int centerY = segment.centerPosition.getY();
            int segmentWidth = segment.getBitmap().getWidth();
            int segmentHeight = segment.getBitmap().getHeight();
            int ledgeSize = (int) (LEDGE_PART_RATIO*segmentWidth);
            int segmentAbsoluteWidth = segmentWidth - 2*ledgeSize;
            int segmentAbsoluteHeight = segmentHeight - 2*ledgeSize;
            int left = centerX - segmentAbsoluteWidth/2;
            int top = centerY - segmentAbsoluteHeight/2;
            int right = centerX + segmentAbsoluteWidth/2;
            int bottom = centerY + segmentAbsoluteHeight/2;
            if (x >= left && x <= right && y >= top && y <= bottom) {
                segment.setMotionPosition(x, y);
                break;
            }
        }
    }

    @Override
    public void moveMovableSegmentTo(int x, int y) {
        Segment segment = getMovableSegment();
        if (segment == null) return;
        int segmentWidth = segment.getBitmap().getWidth();
        int ledgeSize = (int) (LEDGE_PART_RATIO*segmentWidth);
        int minX = (segmentWidth - 2*ledgeSize)/2;
        int maxX = segments.getViewWidth() - minX;
        int minY = (segment.getBitmap().getHeight() - 2*ledgeSize)/2;
        int maxY = segments.getViewHeight() - minY;
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
        segment.setMotionPosition(x, y);
    }

    private Segment getMovableSegment() {
        for (Segment segment : segments.getSegments()) {
            if (segment.isMovable()) {
                return segment;
            }
        }
        return null;
    }

    private int getMovableSegmentIndex() {
        Segment [] segments = this.segments.getSegments();
        for (int i=0; i<segments.length; i++) {
            Segment segment = segments[i];
            if (segment.isMovable()) {
                return i;
            }
        }
        return EMPTY_INDEX;
    }

    @Override
    public void updatePositions() {
        int movableSegmentIndex = getMovableSegmentIndex();
        if (movableSegmentIndex == EMPTY_INDEX) return;
        Segment movableSegment = segments.getSegments()[movableSegmentIndex];
        Position motionPos = movableSegment.getMotionPosition();
        int [] distanceToVertexes = getDistanceToVertexesFromPosition(motionPos);
        int minDistanceIndex = getMinDistanceIndex(distanceToVertexes);
        segments.swapSegments(minDistanceIndex, movableSegmentIndex);
        segments.stopMoving(movableSegment);
    }

    private int[] getDistanceToVertexesFromPosition(Position position) {
        Position[] segmentVertexes = getAllSegmentVertexes();
        int [] distanceToVertexes = new int[segmentVertexes.length];
        for (int i=0; i<distanceToVertexes.length; i++) {
            Position vertex = segmentVertexes[i];
            distanceToVertexes[i] = getDistanceBeetwenPoints(vertex, position);
        }
        return distanceToVertexes;
    }

    private int getDistanceBeetwenPoints(Position vertex, Position motionPos) {
        int x1 = vertex.getX();
        int y1 = vertex.getY();
        int x2 = motionPos.getX();
        int y2 = motionPos.getY();
        return (int) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    private int getMinDistanceIndex(int [] distances) {
        int minDistanceIndex = 0;
        for (int i=1; i<distances.length; i++) {
            int distance = distances[i];
            int minDistance = distances[minDistanceIndex];
            if (distance < minDistance) {
                minDistanceIndex = i;
            }
        }
        return minDistanceIndex;
    }

    private Position[] getAllSegmentVertexes() {
        Position[] vertexes = new Position[12];
        int width = segments.getViewWidth();
        int height = segments.getViewHeight();
        for (int i=0; i<vertexes.length; i++) {
            vertexes[i] = segmentPoint.getSegmentCenterPositionAt(i+1, width, height);
        }
        return vertexes;
    }
}
