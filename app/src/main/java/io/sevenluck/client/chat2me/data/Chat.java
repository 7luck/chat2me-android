package io.sevenluck.client.chat2me.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by loki on 6/19/16.
 */
public class Chat implements Serializable {

    private Long id;
    private Long entityId;
    private Date created;
    private String name;

    public Chat() {
    }

    public Chat(String name, Date created, Long entityId) {
        this.name = name;
        this.created = created;
        this.entityId = entityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
