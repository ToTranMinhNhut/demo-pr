package com.proxyserver.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.proxyserver.models.UserActivityElastic;

@Repository
public interface UserActivityElasticSearchRepo extends ElasticsearchRepository<UserActivityElastic, String> {

	UserActivityElastic findByUrl(String url);
}
