package com.seotm.pazzle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seotm.jigsawpuzzleview.JigsawPuzzleView;

public class MainActivity extends AppCompatActivity {

    private static final int [] SEGMENTS_DRAWABLE_RES = {
            R.drawable.pazzle_segment_1,
            R.drawable.pazzle_segment_2,
            R.drawable.pazzle_segment_3,
            R.drawable.pazzle_segment_4,
            R.drawable.pazzle_segment_5,
            R.drawable.pazzle_segment_6,
            R.drawable.pazzle_segment_7,
            R.drawable.pazzle_segment_8,
            R.drawable.pazzle_segment_9,
            R.drawable.pazzle_segment_10,
            R.drawable.pazzle_segment_11,
            R.drawable.pazzle_segment_12
    };

    private Bitmap [] createSegmentsBitmap() {
        return new Bitmap[] {
                createBitmap(R.drawable.pazzle_segment_1),
                createBitmap(R.drawable.pazzle_segment_2),
                createBitmap(R.drawable.pazzle_segment_3),
                createBitmap(R.drawable.pazzle_segment_4),
                createBitmap(R.drawable.pazzle_segment_5),
                createBitmap(R.drawable.pazzle_segment_6),
                createBitmap(R.drawable.pazzle_segment_7),
                createBitmap(R.drawable.pazzle_segment_8),
                createBitmap(R.drawable.pazzle_segment_9),
                createBitmap(R.drawable.pazzle_segment_10),
                createBitmap(R.drawable.pazzle_segment_11),
                createBitmap(R.drawable.pazzle_segment_12)
        };
    }

    private Bitmap createBitmap(@DrawableRes int drawableRes) {
        return BitmapFactory.decodeResource(getResources(), drawableRes, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JigsawPuzzleView puzzleView = (JigsawPuzzleView) findViewById(R.id.jigsawPuzzleView);
        puzzleView.setSegments(createSegmentsBitmap());
    }
}
