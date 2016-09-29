package com.wiseme.lvscabin.structure;

import com.wiseme.lvscabin.structure.Error;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface CommonView {

    void loadDone();

    void showError(Error error);
}
