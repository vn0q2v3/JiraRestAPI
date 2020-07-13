package com.walmart.JiraRest.jirarestdb.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walmart.JiraRest.jirarestdb.domain.User;
@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<User, Long> {

}
