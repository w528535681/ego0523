package com.shsxt.manager.model;

import com.shsxt.common.result.BaseResult;

public class ResultInfo<T> extends BaseResult {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
