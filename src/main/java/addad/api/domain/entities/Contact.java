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

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "advertiser_id")
    private Long advertiserId;

    @ManyToOne
    @JoinColumn(name="creator_id", insertable = false, updatable = false)
    @JsonIgnore
    private User creator;

    @ManyToOne
    @JoinColumn(name="advertiser_id", insertable = false, updatable = false)
    @JsonIgnore
    private User advertiser;

    @ManyToOne
    @JoinColumn(name="post_id", insertable = false, updatable = false)
    @JsonIgnore
    private Post post;

    @Builder
    public Contact(Long creatorId, Long advertiserId, Long postId) {
        this.creatorId = creatorId;
        this.advertiserId = advertiserId;
        this.postId = postId;
    }
}
