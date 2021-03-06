package addad.api.domain.entities;

import addad.api.domain.entities.enums.PostStatus;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.ModifyPost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    private String post_img;

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
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Column
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL)
    private List<Application> applicaions;

    @Builder
    public Post(Long userId, String title, String hashtag, String img, String description, String price, String postTime, String deadline , PostStatus postStatus, String createdAt) {
        this.user_id = userId;
        this.title = title;
        this.hashtag = hashtag;
        this.post_img = img;
        this.description = description;
        this.price = price;
        this.postTime = postTime;
        this.deadline = deadline;
        this.postStatus = postStatus;
        this.createdAt = createdAt;
    }

    public Post ChangePost(String Image, ModifyPost modifyPost) {
        this.post_img = Image;
        this.title = modifyPost.getTitle();
        this.description = modifyPost.getDescription();
        this.hashtag = modifyPost.getHashtag();
        this.price = modifyPost.getPrice();
        this.postTime = modifyPost.getPostTime();
        this.deadline = modifyPost.getDeadline();

        return this;
    }

    public Post ChangePostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;

        return this;
    }
}
