package com.shsxt.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shsxt.common.result.EgoPageInfo;
import com.shsxt.rpc.service.SearchService;
import com.shsxt.rpc.vo.GoodsVo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service(interfaceClass = SearchService.class)
@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 搜索服务实现类
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer pageNum, Integer pageSize) {

        EgoPageInfo<GoodsVo> pageInfo;
        try {
            //指定索引库
            SearchRequest searchRequest = new SearchRequest("ego");
            //构建查询对象
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //设置查询条件
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(searchStr,"goodsName"));
            //设置分页条件
            searchSourceBuilder.from(pageNum-1).size(pageSize);
            //构建高亮对象
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            //设置高亮字段和样式
            highlightBuilder.field("goodsName")
                    .preTags("<span style='color:red;'>")
                    .postTags("</span>");
            //把高亮对象放到查询对象
            searchSourceBuilder.highlighter(highlightBuilder);
            //把查询对象放入request中
            searchRequest.source(searchSourceBuilder);
            //执行请求
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //拿到结果，处理结果
            //总条数
            Long size = searchResponse.getHits().getTotalHits().value;

            if (0>=size){
                return null;
            }
            //命中的数据
            List<GoodsVo> list = new ArrayList<>();
            SearchHit[] hits =  searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                Integer goodsId = (Integer) hit.getSourceAsMap().get("goodsId");
                String  goodsName = (String) hit.getSourceAsMap().get("goodsName");

                String goodsNameHl = String.valueOf( hit.getHighlightFields().get("goodsName").fragments()[0]);

                BigDecimal marketPrice = new BigDecimal(String.valueOf(hit.getSourceAsMap().get("marketPrice")));

                String originalImg = (String) hit.getSourceAsMap().get("originalImg");

                GoodsVo goodsVo = new GoodsVo(goodsId,goodsName,goodsNameHl,marketPrice,originalImg);
                list.add(goodsVo);
            }

            pageInfo = new EgoPageInfo<>(pageNum,pageSize,size.intValue());
            pageInfo.setResult(list);
            return pageInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
