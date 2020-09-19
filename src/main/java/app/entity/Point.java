package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "integer default 0")
    private int point;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
