package com.example.zuoyangding.aroundme.Activity;

/**
 * Created by Kenny on 2/28/2017.
 */

public class chatMessage {

    private String message;
    private String nickname;

    public chatMessage(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
