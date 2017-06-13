package com.seotm.jigsawpuzzleview.pattern.p12;

import com.seotm.jigsawpuzzleview.pattern.Position;
import com.seotm.jigsawpuzzleview.pattern.SegmentPositions;

/**
 * Created by seotm on 13.06.17.
 */

public class SegmentPositions12 implements SegmentPositions {
    @Override
    public Position getSegmentCenterPositionAt(int ordinalPosition, int w, int h) {
        switch (ordinalPosition) {
            case 1: return new Position(w*2/16, h*3/18);
            case 2: return new Position(w*6/16, h*3/18);
            case 3: return new Position(w*10/16, h*3/18);
            case 4: return new Position(w*14/16, h*3/18);
            case 5: return new Position(w*2/16, h*9/18);
            case 6: return new Position(w*6/16, h*9/18);
            case 7: return new Position(w*10/16, h*9/18);
            case 8: return new Position(w*14/16, h*9/18);
            case 9: return new Position(w*2/16, h*15/18);
            case 10: return new Position(w*6/16, h*15/18);
            case 11: return new Position(w*10/16, h*15/18);
            case 12: return new Position(w*14/16, h*15/18);
        }
        throw new RuntimeException("Internal error. Un expected position.");
    }
}
