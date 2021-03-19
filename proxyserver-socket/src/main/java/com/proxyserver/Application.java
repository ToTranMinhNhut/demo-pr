/**
 * 
 */
package com.proxyserver;

import java.io.IOException;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.proxyserver.service.UserActivityService;

/**
 * @author nhut.to
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		UserActivityService service = context.getBean(UserActivityService.class);
		
		RestHighLevelClient client = service.createRestHighLevelClient("localhost", 9200, "http");
		service.getDistinctAgg(client, "proxyinfo", "user_id.keyword");
		
		/*
		 * UserActivityElasticSearchRepo repoE = context.getBean(UserActivityElasticSearchRepo.class);
		Iterable<UserActivityElastic> users = repoE.findAll();
		users.forEach(e -> System.out.println(e.toString()));
		 * UserActivityElastic user = repoE.findByUrl("github.com"); UserActivityDBRepo
		 * repoDB = context.getBean(UserActivityDBRepo.class);
		 * 
		 * UserActivityDB record = new UserActivityDB(user.getUserId(), user.getUrl(),
		 * 1, "1", 0); UserActivityDB success = repoDB.save(record); if (success !=
		 * null) { System.out.println(success.toString()); } else {
		 * System.out.println("record failed"); }
		 */
		
		client.close();
		
	}

}
