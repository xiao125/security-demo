package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @ApiModel：用在模型类上，对模型类做注释；
 *  @ApiModelProperty：用在属性上，对属性做注释
 *
 *  @JsonView :过滤序列化对象的字段属性,根据controller的JsonView属性，将实体类中不同标签的属性进行分类显示。
 *
 */


public class User {

    public interface  UserSimpleView {};
    public interface  UserDetailView extends UserSimpleView{};


    @JsonView(UserSimpleView.class)
    private String id;

    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
