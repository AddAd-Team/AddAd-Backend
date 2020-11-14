package addad.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "post")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long user_id;

    @Column
    private String hashtag;

    @Column
    private String postImg;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String price;

    @Column
    private String postTime;

    @Column
    private String deadline;

    @Column()
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Application> applicaions;

    @Builder
    Post(String title, Long user_id, String hashtag, String postImg, String description, String price, String postTime, String deadline) {
        this.title = title;
        this.user_id = user_id;
        this.hashtag = hashtag;
        this.postImg = postImg;
        this.description = description;
        this.price = price;
        this.postTime = postTime;
        this.deadline = deadline;
    }
}
