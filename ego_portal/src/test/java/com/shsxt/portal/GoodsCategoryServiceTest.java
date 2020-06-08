package com.shsxt.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.common.util.JsonUtil;
import com.shsxt.rpc.service.GoodsCategroyService;
import com.shsxt.rpc.vo.GoodsCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsCategoryServiceTest {


    @Reference(interfaceClass = GoodsCategroyService.class)
    private GoodsCategroyService goodsCategroyService;

    @Test
    public void testRpc(){
        System.out.println(goodsCategroyService);
        List<GoodsCategoryVo> goodsCategoryVos = goodsCategroyService.selectAllList();
        System.out.println(JsonUtil.object2JsonStr(goodsCategoryVos));
    }
}
