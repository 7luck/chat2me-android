package io.sevenluck.client.chat2me.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by loki on 6/12/16.
 */
public class ChatTo implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String password;
    private boolean publicChat;
    private Date inserted;

    public ChatTo() {
        this.publicChat = false;
        this.password = null;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public static ChatTo getInstance(String name) {
        ChatTo chatTo = new ChatTo();
        chatTo.setName(name);


        return chatTo;
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
