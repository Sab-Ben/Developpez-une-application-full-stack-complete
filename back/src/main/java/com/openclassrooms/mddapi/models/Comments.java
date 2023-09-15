package com.openclassrooms.mddapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * The type Comments extends Date Table Model.
 */

@Entity
@Table(name = "COMMENTS")
@EntityListeners(AuditingEntityListener.class)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comments extends DateTableModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "description")
    private String description;


    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(nullable = false, name = "author_id", referencedColumnName = "id")
    private Users author;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "posts_id", referencedColumnName = "id")
    private Posts posts;
}
