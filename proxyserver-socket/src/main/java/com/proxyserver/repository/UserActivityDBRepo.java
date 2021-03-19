/**
 * 
 */

package com.proxyserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proxyserver.models.UserActivityDB;


/**
 * @author nhut.to
 *
 */
@Repository
public interface UserActivityDBRepo extends CrudRepository<UserActivityDB, String> {

}
