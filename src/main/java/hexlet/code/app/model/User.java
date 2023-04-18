package hexlet.code.app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String firstName;


    @NotBlank
    private String lastName;


    @NotBlank
    @Column(unique = true)
    private String email;


    @NotBlank
    @JsonIgnore
    private String password;
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private Date createdAt;

    public User(final Long id) {
        this.id = id;
    }
}
