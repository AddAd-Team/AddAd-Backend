package addad.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long post_id;

    @Column()
    private Long creator_id;

    @Column()
    private Long advertiser_id;

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
    public Contact(Long creator_id, Long advertiser_id, Long post_id) {
        this.creator_id = creator_id;
        this.advertiser_id = advertiser_id;
        this.post_id = post_id;
    }
}
