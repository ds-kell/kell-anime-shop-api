package com.kell.webapp.exception;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException {

    private String entityName;
    private String entityId;

    public EntityNotFoundException() {
        super("err.api.entity-not-found", null);
    }

    public EntityNotFoundException(String entityName, String entityId) {
        super("err.api.entity-not-found", null);
        this.entityId = entityId;
        this.entityName = entityName;
    }

}
