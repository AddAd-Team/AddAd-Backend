package addad.api.domain.entities;

import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.ModifyProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Post> posts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Contact> creator;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Contact> advertiser;

    @Builder
    public User(String userEmail, String userPw, String userName, String hashtag, Userinfo userinfo) {
        this.email = userEmail;
        this.password = userPw;
        this.name = userName;
        this.hashtag = hashtag;
        this.userinfo = userinfo;
    }

    public User changeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }

    public User ChangeProfile(String Image, ModifyProfile modifyProfile) {
        this.name = modifyProfile.getName();
        this.profileImg = Image;
        this.hashtag = modifyProfile.getHashtag();
        this.description = modifyProfile.getDescription();

        return this;
    }

    public void passwordChange(String password) {
        this.password = password;
    }
}
