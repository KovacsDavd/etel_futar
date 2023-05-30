package hu.ulyssys.javaee.rest.model;

import java.time.LocalDateTime;

public abstract class CoreModel {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long creatorID;
    private Long modifiedID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Long creatorID) {
        this.creatorID = creatorID;
    }

    public Long getModifiedID() {
        return modifiedID;
    }

    public void setModifiedID(Long modifiedID) {
        this.modifiedID = modifiedID;
    }
}
