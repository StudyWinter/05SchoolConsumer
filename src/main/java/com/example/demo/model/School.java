package com.example.demo.model;

import lombok.Data;

@Data
public class School {
    private Integer scid;         //主键
    private String name;          //学校名称
    private String description;   //简介描述
    private String schoolWeb;     //学校官网
}
