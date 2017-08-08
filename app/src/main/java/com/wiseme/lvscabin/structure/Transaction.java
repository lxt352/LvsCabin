package com.wiseme.lvscabin.structure;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface Transaction<T> {

    void onSuccess(T t);

    void onFail(Error error);
}
