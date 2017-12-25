package mdd.logistics.controller.user.vo;

import mdd.logistics.domain.User;

import java.io.Serializable;

public class LoginVo implements Serializable{
    private static final long serialVersionUID = 4029775792440567923L;

    private User user;

    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
