package addad.api.domain.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long postId;

    @Column
    private Long userId;

    @Builder
    public Likes(Long postId, Long userId){
        this.postId = postId;
        this.userId = userId;
    }
}
