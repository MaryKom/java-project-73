package hexlet.code.app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 1)
    private String firstName;


    @NotBlank
    @Size(min = 1)
    private String lastName;


    @NotBlank
    @Email
    @Column(unique = true)
    private String email;


    @NotBlank
    @JsonIgnore
    private String password;
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private Date createdAt;

}
