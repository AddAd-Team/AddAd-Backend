package addad.api.domain.entities;

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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column(length = 35)
    private String hashtag;

    @Column
    private String postImg;

    @Column(length = 35)
    private String title;

    @Column(length = 400)
    private String description;

    @Column
    private String price;

    @Column
    private String postTime;

    @Column
    private String deadline;

    @Column
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Like> like;

    @Builder Post(String title, String hashtag, String postImg, String description, Integer price, String postTime, String deadline, LocalDateTime createdAt) {
        this.title = title;
        this.hashtag = hashtag;
        this.postImg = postImg;
        this.description = description;
        this.price = price;
        this.postTime = postTime;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }
}
