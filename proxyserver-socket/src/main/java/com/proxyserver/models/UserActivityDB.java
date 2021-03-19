package com.proxyserver.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nhut.to
 *
 */
@Entity
@Table(name = "user_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityDB {

	@Id
	private String user_id;

	private String url;
	private int count;
	private String date;
	private double total_time;
}
