package com.seotm.jigsawpuzzleview.pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

/**
 * Created by seotm on 14.06.17.
 */

class BitmapFactoryExtension {

    private final Context context;

    BitmapFactoryExtension(Context context) {
        this.context = context;
    }

    Bitmap createSegmentBitmap(@DrawableRes int drawableRes, int widht, int height) {
        Bitmap originalBitmap = android.graphics.BitmapFactory.decodeResource(context.getResources(), drawableRes, null);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, widht, height, true);
        originalBitmap.recycle();
        return scaledBitmap;
    }

}
