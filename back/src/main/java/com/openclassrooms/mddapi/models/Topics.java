package com.openclassrooms.mddapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "TOPICS")
@EntityListeners(AuditingEntityListener.class)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Topics extends DateTableModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    @Column(name = "title")
    private String title;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany()
    @JoinTable(
            name = "SUBSCRIPTIONS",
            joinColumns = @JoinColumn( name = "topics_id" ),
            inverseJoinColumns = @JoinColumn( name = "users_id" )
    )
    private List<Users> users;
}
