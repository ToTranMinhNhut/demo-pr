/**
 * 
 */

package com.proxyserver.service;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxyserver.repository.UserActivityElasticSearchRepo;

/**
 * @author nhut.to
 *
 */
@Service
public class UserActivityService implements IUserActivityService {

	@Autowired
	private UserActivityElasticSearchRepo repo;

	/**
	 * 
	 * @param host
	 * @param port
	 * @param protocol
	 * @return
	 */
	public RestHighLevelClient createRestHighLevelClient(final String host, final int port, final String protocol) {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, protocol)));
		return client;
	}

	public void getDistinctAgg(RestHighLevelClient client, String indexName, String fieldName) throws IOException {
		SearchRequest searchRequest = new SearchRequest(indexName);
		searchRequest.searchType();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());

		AggregationBuilder aggregation = AggregationBuilders.terms(fieldName + "_aggs").field(fieldName)
				.subAggregation(AggregationBuilders.terms("url_aggs").field("url.keyword").size(1000)).size(500);
		searchSourceBuilder.aggregation(aggregation);

		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		System.out.println(searchResponse);
		Terms genders = searchResponse.getAggregations().get(fieldName + "_aggs");

		for (Terms.Bucket entry : genders.getBuckets()) {
			System.out.println(entry.getKey()); // term
			System.out.println("-------------------------");
			Terms urls = entry.getAggregations().get("url_aggs");
			urls.getBuckets().forEach(e -> System.out.println(e.getKey() + ": " + e.getDocCount()));
		}
		
	}
}
