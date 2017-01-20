package com.wp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
@Getter
@Setter
@ToString
public class Image implements Serializable {

    private Integer id;
    private String name;
    private String url;
    private Date date;
    private User user;
}
