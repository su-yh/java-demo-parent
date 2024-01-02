package com.suyh.entity;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private static final long serialVersionUID = -8315297734779761541L;

    private String id;
    private String userName;
    private String password;
    private String Salt;    // 盐值加密
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

    public User(String id, String userName, String password, Set<Role> roleSet) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roleSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", Salt='" + Salt + '\'' +
                ", roles=" + roles +
                '}';
    }
}
