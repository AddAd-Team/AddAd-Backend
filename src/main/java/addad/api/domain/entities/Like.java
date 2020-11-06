package addad.api.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@IdClass(LikeId.class)
@Table(name = "like")
public class Like {
    @Id
    @Column()
    private Integer userId;

    @Id
    @Column()
    private Integer postId;

    @Builder
    public Like(Integer userId, Integer postId){
        this.userId = userId;
        this.postId = postId;
    }
}
