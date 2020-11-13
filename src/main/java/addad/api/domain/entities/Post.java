package addad.api.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String hashtag;

    @Column
    private String img;

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

    @Column
    private String createdAt;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @Builder
    public Post(Long userId, String title, String hashtag, String img, String description, String price, String postTime, String deadline, String createdAt) {
        this.userId = userId;
        this.title = title;
        this.hashtag = hashtag;
        this.img = img;
        this.description = description;
        this.price = price;
        this.postTime = postTime;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }
}
