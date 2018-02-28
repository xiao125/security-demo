package com.imooc.dto;

/**
 * @ApiModel：用在模型类上，对模型类做注释；
 *  @ApiModelProperty：用在属性上，对属性做注释
 */


public class User {

    private String username;
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
}
