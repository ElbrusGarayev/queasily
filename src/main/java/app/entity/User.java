package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(mappedBy = "user")
    private Point point;

    private boolean isEnabled;
}
