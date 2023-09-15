package com.openclassrooms.mddapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
/**
 * The type Date Table Model.
 */
@Data
@MappedSuperclass
public class DateTableModel {

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    protected Instant created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Instant updated_at;
}
