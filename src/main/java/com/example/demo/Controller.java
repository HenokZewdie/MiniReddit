package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by student on 6/22/17.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private LinkRepo linkRepo;

    HttpRequest request;

    private HttpSession sess =;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/create")
    public String newAct(Model model){
        model.addAttribute("act", new Account());
        return "create";
    }



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
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
        String date = sdf.format(new Date());
        myLink.setDate(date);
        linkRepo.save(myLink);
        Iterable<MyLink> values = linkRepo.findAll();
        model.addAttribute("values", values);
        return "main";
    }




}

