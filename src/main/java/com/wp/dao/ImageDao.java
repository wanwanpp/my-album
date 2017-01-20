package com.wp.dao;

import com.wp.model.Image;

import java.util.List;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
public interface ImageDao {

    List<Image> getByUserId(Integer userId);

    void addImage(Image image);

    void delById(Integer id);
}
