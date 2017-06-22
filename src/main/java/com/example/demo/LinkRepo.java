package com.example.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by student on 6/22/17.
 */
public interface LinkRepo extends CrudRepository<MyLink, String> {
}
