package com.wp.controller;

import com.wp.model.Image;
import com.wp.model.User;
import com.wp.service.ImageService;
import com.wp.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/image.do")
    @ResponseBody
    public void image(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) MultipartFile image) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer type = Integer.valueOf(request.getParameter("type"));
        if (type == 1) {
            User user = (User) request.getSession().getAttribute("user");
            String imageName = request.getParameter("image_name");
            Image img = new Image();
            img.setDate(new Date(System.currentTimeMillis()));
            img.setName(imageName);
            img.setUser(user);
            img.setUrl(img.getUser().getUsername() + "/" + UUID.randomUUID());
            FileUtils.upload(image.getInputStream(), img.getUrl());
            imageService.addImage(img);
            request.getSession().setAttribute("imageList", imageService.getByUserId(img.getUser().getId()));
            response.sendRedirect(request.getContextPath()+"/home.do");
        } else if (type == 2) {
            String ids = request.getParameter("ids");
            String urls = request.getParameter("urls");

            String[] idArray = ids.split(",");
            String[] urlArray = urls.split(",");
            if (!"".equals(idArray[0]) && !"".equals(urlArray[0])) {
                for (int i = 0; i < idArray.length; i++) {
                    FileUtils.delete(urlArray[i]);
                    imageService.delById(Integer.valueOf(idArray[i]));

                }
            }
            request.getSession().setAttribute("imageList", imageService.getByUserId(((User) request.getSession().getAttribute("user")).getId()));
        }
    }
}
