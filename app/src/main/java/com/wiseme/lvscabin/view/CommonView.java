package com.wiseme.lvscabin.view;

import com.wiseme.lvscabin.structure.Error;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface CommonView<T> {

    void loadDone(T t);

    void showError(Error error);
}
