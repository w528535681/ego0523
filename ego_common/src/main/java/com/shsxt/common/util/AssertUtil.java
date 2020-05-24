package com.shsxt.common.util;

import com.shsxt.common.enums.BaseResultEnum;
import com.shsxt.common.result.BaseResult;

public class AssertUtil {

    public static BaseResult  isTrue(Boolean flag,String msg){
        BaseResult baseResult = new BaseResult();
        if (flag){
            baseResult.setCode(BaseResultEnum.ERROR.getCode());
            baseResult.setMessage(msg);
        }
        return baseResult;
    }
}
