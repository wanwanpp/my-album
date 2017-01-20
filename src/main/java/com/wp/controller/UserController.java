package com.wp.controller;

import com.wp.model.User;
import com.wp.service.ImageService;
import com.wp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/user.do")
    @ResponseBody
    public void user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        Integer type = Integer.valueOf(request.getParameter("type"));
        if (type == 1) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String result = null;
            User user = null;
            if (username.isEmpty()) {
                result = "1";
            } else if (password.isEmpty()) {
                result = "2";
            } else if ((user = userService.getUserByUsername(username)) == null) {
                result = "3";
            } else {
                if (!user.getPassword().equals(password)) {
                    result = "4";
                } else {
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("imageList", imageService.getByUserId(user.getId()));
                    result = "5";
                }
            }
            writer.print(result);
        }else if (type==2){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            String result = null;
            if (username.isEmpty()){
                result="1";
            }else if (password.isEmpty()){
                result="2";
            }else if (repassword.isEmpty()){
                result="3";
            }else if (!repassword.equals(password)){
                result="4";
            }else if (userService.getUserByUsername(username)!=null){
                result="5";
            }else {
                User user = new User(username,password);
                userService.addUser(user);
                result="6";
            }
            writer.print(result);
        }
        else if (type==3){
            request.getSession().invalidate();
        }

    }
}
