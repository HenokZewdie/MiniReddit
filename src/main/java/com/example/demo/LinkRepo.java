package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
/**
 * Created by student on 6/22/17.
 */
public interface LinkRepo extends CrudRepository<MyLink, String> {

    public List<MyLink> findAllByOrderByDateDesc();

    public List<MyLink> findAllByUserNameOrderByDateDesc(String username);
}
