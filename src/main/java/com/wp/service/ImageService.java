package com.wp.service;

import com.wp.dao.ImageDao;
import com.wp.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;


    public List<Image> getByUserId(Integer userId) {
        return imageDao.getByUserId(userId);
    }

    public void addImage(Image image) {
        imageDao.addImage(image);
    }

    public void delById(Integer id) {
        imageDao.delById(id);
    }

}
