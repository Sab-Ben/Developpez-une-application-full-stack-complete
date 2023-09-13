package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
/**
 * The type Subscriptions.
 */
@Entity
@Table(name = "SUBSCRIPTIONS")
public class Subscriptions {

    @Id
    Long users_id;

    @Id
    Long topics_id;

}
