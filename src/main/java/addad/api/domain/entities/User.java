package addad.api.domain.entities;

import addad.api.domain.entities.enums.Userinfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String refreshToken;

    @Column()
    @Enumerated(EnumType.STRING)
    private Userinfo userinfo;

    @Column(nullable = false)
    private Integer userPrivate;

    @Column()
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
      private List<Like> likes;

    @Builder
    public User(String userEmail, String userPw, String userName, String hashtag) {
        this.email = userEmail;
        this.password = userPw;
        this.name = userName;
        this.hashtag = hashtag;
    }

    public User changeRefrehToken(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }
}
