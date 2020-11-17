package addad.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long post_id;

    @Column()
    private Long user_id;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", insertable = false, updatable = false)
    @JsonIgnore
    private Post post;

    @Builder
    Application(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}