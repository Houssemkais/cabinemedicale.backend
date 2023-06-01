package com.pfe.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public abstract class AbstractEntity implements Serializable {


    @Builder.Default
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;

    public AbstractEntity() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
