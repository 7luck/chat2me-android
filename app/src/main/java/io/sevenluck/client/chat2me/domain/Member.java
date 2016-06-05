package io.sevenluck.client.chat2me.domain;

import java.io.Serializable;

/**
 * Created by loki on 6/5/16.
 */
public class Member implements Serializable {

    private String nickname;
    private String password;
    private String authtoken;

    public Member() {
    }

    public Member(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    @Override
    public String toString() {
        return "Member{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
