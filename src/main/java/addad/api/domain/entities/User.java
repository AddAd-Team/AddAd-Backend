package addad.api.domain.entities;

import addad.api.domain.payload.request.ModifyProfile;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private Long id;

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
    private String profileImg;

    @Column()
    private String refreshToken;

    @Column()
    @Enumerated(EnumType.STRING)
    private Userinfo userinfo;

    @Column()
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Like> likes;

    @Builder
    public User(String userEmail, String userPw, String userName, String hashtag, Userinfo userinfo) {
        this.email = userEmail;
        this.password = userPw;
        this.name = userName;
        this.hashtag = hashtag;
        this.userinfo = userinfo;
    }

    public User ChangeProfile(String Image, ModifyProfile modifyProfile) {
        this.name = modifyProfile.getName();
        this.profileImg = Image;
        this.hashtag = modifyProfile.getHashtag();
        this.description = modifyProfile.getDescription();

        return this;
    }

}
