package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBSCRIPTIONS")
public class Subscriptions {

    @Id
    Long users_id;

    @Id
    Long topics_id;

}
