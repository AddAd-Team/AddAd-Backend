package addad.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Contact {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String postImg;

    @Column
    private String hashtag;

    @Column
    private String postTime;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "creator_profile_image")
    private String creatorProfileImage;

    @Column(name = "advertiser_id")
    private Long advertiserId;

    @Column(name = "advertiser_name")
    private String advertiserName;

    @Column(name = "advertiser_profile_image")
    private String advertiserProfileImage;

    @Builder
    public Contact(String title, String postImg, String hashtag, String postTime, Long creatorId, String creatorName, String creatorProfileImage, Long advertiserId, String advertiserName, String advertiserProfileImage, Long postId) {
        this.title = title;
        this.postImg = postImg;
        this.hashtag = hashtag;
        this.postTime = postTime;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.creatorProfileImage = creatorProfileImage;
        this.advertiserId = advertiserId;
        this.advertiserName = advertiserName;
        this.advertiserProfileImage = advertiserProfileImage;
        this.postId = postId;
    }
}
