package com.foxes.chapter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxes.book.feign.BookFeign;
import com.foxes.chapter.dao.ESFoxManageMapper;
import com.foxes.chapter.service.ESBookSearchService;
import com.foxesnovel.search.pojo.BookInfo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ESBookSearchServiceImpl implements ESBookSearchService {
    @Autowired
    private ESFoxManageMapper esFoxManageMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private BookFeign bookFeign;


    @Override
    public Map search(Map<String, String> searchMap) {
        if (searchMap != null && searchMap.size() != 0) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

            Map<String, Object> resultMap = new HashMap<>();
            //关键字查询
            if (StringUtils.isNotEmpty(searchMap.get("keywords"))) {

                boolQuery.must(QueryBuilders
                        .queryStringQuery(searchMap.get("keywords")).field("name").field("author"));
            }
            //男女频选则
            if (StringUtils.isNotEmpty(searchMap.get("channel"))) {
                boolQuery.filter(QueryBuilders.termQuery("channel", searchMap.get("channel")));
            }
            //类别选则
            if (StringUtils.isNotEmpty(searchMap.get("category"))) {
                boolQuery.filter(QueryBuilders.termQuery("category", searchMap.get("category")));
            }
            //完结选择
            if (StringUtils.isNotEmpty(searchMap.get("status"))) {
                boolQuery.filter(QueryBuilders.termQuery("status", Integer.parseInt(searchMap.get("status"))));
            }

            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(boolQuery);
            //类别聚合查询
            String category = "categoryName";
            nativeSearchQueryBuilder.addAggregation(
                    AggregationBuilders.terms(category).field("categoryName"));

            //设置高亮
            HighlightBuilder.Field fname = new HighlightBuilder.Field("name")
                    .preTags("<span style='color:red'>")
                    .postTags("</span>");
            HighlightBuilder.Field fauthor = new HighlightBuilder.Field("author")
                    .preTags("<span style='color:red'>")
                    .postTags("</span>");
            nativeSearchQueryBuilder.withHighlightFields(fname, fauthor);
           /* HighlightBuilder highlightBuilder = new HighlightBuilder(); //生成高亮查询器
            highlightBuilder.field("name");      //高亮查询字段
            highlightBuilder.field("author");    //高亮查询字段
            highlightBuilder.requireFieldMatch(false);     //如果要多个字段高亮,这项要为false
            highlightBuilder.preTags("<span style=\"color:red\">");   //高亮设置
            highlightBuilder.postTags("</span>");

            //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
            highlightBuilder.fragmentSize(800000); //最大高亮分片数
            highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
            nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);*/
            //设置分页

            String pageSize = searchMap.get("pageSize");
            String pageNum = null;
            try {
                pageNum = String.valueOf((int)Math.floor(Double.parseDouble(searchMap.get("pageNum"))));
                if (StringUtils.isEmpty(pageNum) ||Integer.parseInt(pageNum) < 1) {
                    pageNum = "1";
                }
            } catch (NullPointerException e) {
                pageNum = "1";
            }
            if (StringUtils.isEmpty(pageSize)) {
                pageSize = "10";
            }
            nativeSearchQueryBuilder.withPageable(
                    PageRequest.of(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
            //设置排序
            if (StringUtils.isNotEmpty(searchMap.get("sortField"))) {

                nativeSearchQueryBuilder.withSort(
                        SortBuilders.fieldSort(searchMap.get("sortField")).order(SortOrder.DESC));
            }

            //开始查询
            AggregatedPage<BookInfo> bookInfos = this.searchBook(nativeSearchQueryBuilder);

            if (Integer.parseInt(pageNum) > bookInfos.getTotalPages() && bookInfos.getTotalPages()!=0) {

                pageNum = String.valueOf(bookInfos.getTotalPages());
                nativeSearchQueryBuilder.withPageable(
                        PageRequest.of(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
                bookInfos = this.searchBook(nativeSearchQueryBuilder);
            }

            //封装查询结果
            resultMap.put("rows", bookInfos.getContent());
            //总页数
            resultMap.put("totalPages", bookInfos.getTotalPages());
            //总记录数
            resultMap.put("total", bookInfos.getTotalElements());
            //当前页
            resultMap.put("pageNum", pageNum);
            resultMap.put("pageSize", pageSize);
            //封装聚合结果
            ParsedStringTerms terms = (ParsedStringTerms) bookInfos.getAggregation(category);

            List<String> li = terms.getBuckets().stream().map(
                    bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
            resultMap.put("categoryNames", li);


            return resultMap;
        }
        return null;
    }

    private AggregatedPage<BookInfo> searchBook(NativeSearchQueryBuilder na) {
        AggregatedPage<BookInfo> bookInfos = elasticsearchRestTemplate.queryForPage(
                na.build(), BookInfo.class, new SearchResultMapper() {
                    @Override
                    public <T> AggregatedPage<T> mapResults(
                            SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                        SearchHits hits = searchResponse.getHits();
                        List<T> list = new ArrayList<>();
                        if (hits != null) {

                            for (SearchHit hit : hits) {
                                ObjectMapper om = new ObjectMapper();
                                BookInfo bookInfo = null;
                                try {
                                    String s = hit.getSourceAsString();
                                    bookInfo = om.readValue(s, new TypeReference<BookInfo>() {
                                    });
                                } catch (JsonProcessingException e) {

                                    e.printStackTrace();
                                    throw new RuntimeException("查询结果转换失败");
                                }
                                list.add((T) bookInfo);
                            }
                        }
                        return new AggregatedPageImpl<T>(
                                list, pageable, hits.getTotalHits().value, searchResponse.getAggregations());
                    }

                    @Override
                    public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                        return null;
                    }
                });
        return bookInfos;

    }

}
