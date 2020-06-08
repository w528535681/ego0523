package com.shsxt.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.common.result.EgoPageInfo;
import com.shsxt.common.util.JsonUtil;
import com.shsxt.rpc.service.SearchService;
import com.shsxt.rpc.vo.GoodsVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 搜索服务测试类
 *
 * @author zhoubin
 * @create 2020/1/2
 * @since 1.0.0
 */
@SpringBootTest
public class SearchServiceTest {

	@Reference(interfaceClass = SearchService.class)
	private SearchService searchService;

	@Test
	public void testSearch(){
		EgoPageInfo<GoodsVo> pageInfo = searchService.doSearch("中国移动联通电信", 1, 10);
		System.out.println(JsonUtil.object2JsonStr(pageInfo));
	}

}