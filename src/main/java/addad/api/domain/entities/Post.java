package addad.api.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
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
    private String title;

    @Column
    private String description;

    @Column
    private Integer price;

    @Column
    private String postTime;

    @Column
    private String deadline;


    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Like> like;


}
