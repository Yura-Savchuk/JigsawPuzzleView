package com.seotm.jigsawpuzzleview.pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentPattern12 implements SegmentsPattern {

    private static final SegmentSize SEGMENT_SIZE = new SegmentSize(6f/16f, 5f/9f);

    private final Segments segments;

    public SegmentPattern12(@NonNull @DrawableRes int [] segments, @NonNull Context context) {
        this.segments = new Segments(segments, context, SEGMENT_SIZE);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        if (!segments.isSized()) {
            segments.updateSize(canvas.getWidth(), canvas.getHeight());
        }
        for (Segment segment : segments.getSegments()) {
            drawSegment(canvas, segment);
        }
    }

    private void drawSegment(@NonNull Canvas canvas, @NonNull Segment segment) {
        Position centerPosition;
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        switch (segment.position + 1) {
            case 1: {
                centerPosition = new Position(canvasWidth*2/16, canvasHeight*3/18);
                break;
            }
            case 2: {
                centerPosition = new Position(canvasWidth*6/16, canvasHeight*3/18);
                break;
            }
            case 3: {
                centerPosition = new Position(canvasWidth*10/16, canvasHeight*3/18);
                break;
            }
            case 4: {
                centerPosition = new Position(canvasWidth*14/16, canvasHeight*3/18);
                break;
            }
            case 5: {
                centerPosition = new Position(canvasWidth*2/16, canvasHeight*9/18);
                break;
            }
            case 6: {
                centerPosition = new Position(canvasWidth*6/16, canvasHeight*9/18);
                break;
            }
            case 7: {
                centerPosition = new Position(canvasWidth*10/16, canvasHeight*9/18);
                break;
            }
            case 8: {
                centerPosition = new Position(canvasWidth*14/16, canvasHeight*9/18);
                break;
            }
            case 9: {
                centerPosition = new Position(canvasWidth*2/16, canvasHeight*15/18);
                break;
            }
            case 10: {
                centerPosition = new Position(canvasWidth*6/16, canvasHeight*15/18);
                break;
            }
            case 11: {
                centerPosition = new Position(canvasWidth*10/16, canvasHeight*15/18);
                break;
            }
            case 12: {
                centerPosition = new Position(canvasWidth*14/16, canvasHeight*15/18);
                break;
            }
            default: {
                throw new RuntimeException("Internal error. Un expected position.");
            }
        }
        Bitmap bitmap = segment.getBitmap();
        int left = centerPosition.x - (bitmap.getWidth()/2);
        int top = centerPosition.y - (bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, left, top, null);
    }

    @Override
    public void updateSize(int w, int h, int oldw, int oldh) {
        segments.updateSize(w, h);
    }

}
