package com.proxyserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Document(indexName = "proxyinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityElastic {

	
	@Id
	private String id;
	
	@Field(name = "user_id")
	private String userId;

	@Field(name = "url")
	private String url;

	@Field(name = "@timestamp")
	private String time;

}
