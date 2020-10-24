package addad.api.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    private String hashtag;

    @Column()
    private String token;

    @Column()
    private String refresh_token;

    @Column()
    @Enumerated(EnumType.STRING)
    private Userinfo userinfo;

    @Column()
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
      private List<Like> likes;
}
