package com.seotm.jigsawpuzzleview;

/**
 * Created by seotm on 14.06.17.
 */

class PuzzleGatherListenerProxy implements PuzzleGatherListener {

    PuzzleGatherListener listener;

    @Override
    public void onGathered() {
        if (listener != null) {
            listener.onGathered();
        }
    }
}
