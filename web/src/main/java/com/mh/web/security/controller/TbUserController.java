package com.mh.web.security.controller;

import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@RestController
public class TbUserController {

    @Autowired
    private ITbUserService iTbUserService;

    @RequestMapping("/get")
    public void getuser(){

        TbUser user = iTbUserService.getById(1);



        System.out.println( iTbUserService.getById(1).getPassword());

    }

    @RequestMapping("/insert")
    public void insert(){
        TbUser user = new TbUser();

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String localDateTime = formatter.format(date);
        System.out.println(formatter.format(date));


        user.setUsername("user");
        user.setPassword("user");
        user.setCreatetime(localDateTime);
        user.setUpdatetime(localDateTime);
        iTbUserService.save(user);
        System.out.println(user.toString());
    }

    @PostMapping("/index")
    public String index() {
        System.out.println("index");
        return "index";
    }

    @GetMapping("/admin/hello")
    public String adminhello() {
        System.out.println("adminhello");
        return "adminhello";
    }

    @GetMapping("/user/hello")
    public String userhello() {
        System.out.println("userhello");
        return "userhello";
    }


    @GetMapping("/test1")
    public String test1() {
        System.out.println("test1");
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        System.out.println("test2");
        return "test2";
    }
}
