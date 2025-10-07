package com.eventostec.api.adapters.outbound.entities;

import java.util.Date;
import java.util.UUID;

public class JpaEventEntitie {

    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Date date;

}
