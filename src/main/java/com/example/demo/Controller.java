package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by student on 6/22/17.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private LinkRepo linkRepo;

   /* @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/create")
    public String newAct(Model model){
        model.addAttribute("act", new Account());
        return "create";
    }*/



    @RequestMapping("/")
    public String init(Model model){

        return "main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String go(Model model){
       model.addAttribute("mylink", new MyLink());
       return "add";
    }

    @PostMapping("/addLink")
    public String add(@ModelAttribute MyLink myLink, Model model){
        myLink.setDate(new Date());
        linkRepo.save(myLink);
        Iterable<MyLink> values = linkRepo.findAllByOrderByDateDesc();
        model.addAttribute("values", values);
        return "main";
    }

    @RequestMapping("user")
    public String userOrder(Model model){
        Iterable<MyLink> values = linkRepo.findAllByUserNameOrderByDateDesc("hi");
        model.addAttribute("values", values);
        return "main";
    }

    @RequestMapping("date")
    public String dateOrder(Model model){
        Iterable<MyLink> values = linkRepo.findAllByOrderByDateDesc();
        model.addAttribute("values", values);
        return "main";
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/miniReddit");
        dataSource.setUsername("userName");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource()).usersByUsernameQuery("select username,password,enabled from users where username=?").authoritiesByUsernameQuery("select username,authority from authorities where username=?");

    }

}

