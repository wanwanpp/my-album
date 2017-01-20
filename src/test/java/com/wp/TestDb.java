package com.wp;

import com.wp.junit.SpringJunitTest;
import com.wp.model.Image;
import com.wp.model.User;
import com.wp.service.ImageService;
import com.wp.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
public class TestDb extends SpringJunitTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Test
    public void testUser(){
//        User user = new User("wangping","980325");
//        userService.addUser(user);
        User wangping = userService.getUserByUsername("wangping");
        System.out.println(wangping);
    }

    @Test
    public void testImage(){
//        Image image = new Image();
//        image.setName("wangping");
//        image.setUrl("www.www");
//        image.setDate(new Date(System.currentTimeMillis()));
//        User user = new User();
//        user.setId(1);
//        image.setUser(user);
//        imageService.addImage(image);

        List<Image> images = imageService.getByUserId(1);
        images.stream().forEach(System.out::println);
    }

}
