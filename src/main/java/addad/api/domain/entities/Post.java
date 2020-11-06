package addad.api.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Like> like;

    @Builder Post(String title, String hashtag, String postImg, String description, String price, String postTime, String deadline) {
        this.title = title;
        this.hashtag = hashtag;
        this.postImg = postImg;
        this.description = description;
        this.price = price;
        this.postTime = postTime;
        this.deadline = deadline;
    }
<<<<<<< feature/mypage
}
=======
}
>>>>>>> develop
