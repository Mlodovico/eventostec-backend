package com.eventostec.api.adapters.outbound.entities;

import com.eventostec.api.domain.event.Event;

import java.util.Date;
import java.util.UUID;

public class JpaEventEntity {

    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Date date;

    public JpaEventEntity(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.imgUrl = event.getImgUrl();
        this.eventUrl = event.getEventUrl();
        this.remote = event.getRemote();
        this.date = event.getDate();
    }
}
