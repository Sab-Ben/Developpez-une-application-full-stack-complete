package com.openclassrooms.mddapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.concurrent.Flow;
/**
 * The type User extends Date Table Model.
 */
@Data
@Entity
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
public class Users extends DateTableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @NonNull
    @Size(min = 3, max = 20)
    @Column(name = "username")
    private String username;

    @NonNull
    @Size(min = 8)
    @Column(name = "password")
    @JsonIgnore
    private String password;


}
