package io.sevenluck.client.chat2me.domain;

import java.io.Serializable;

/**
 * Created by loki on 6/12/16.
 */
public class Chat implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String password;
    private boolean publicChat;

    public Chat() {
        this.publicChat = false;
        this.password = null;
    }

    public static Chat getInstance(String name) {
        Chat chat = new Chat();
        chat.setName(name);

        return chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublicChat() {
        return publicChat;
    }

    public void setPublicChat(boolean publicChat) {
        this.publicChat = publicChat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
