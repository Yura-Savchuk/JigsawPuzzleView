package com.seotm.jigsawpuzzleview.state;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by seotm on 14.06.17.
 */

public interface StateSavable {

    void saveState(@NonNull Bundle bundle);
    void restoreState(@NonNull Bundle bundle);

}
