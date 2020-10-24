package addad.api.domain.entities;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(LikeId.class)
@Table(name = "likes")
public class Like {
    @Id
    @Column()
    private Integer userId;

    @Id
    @Column()
    private Integer postId;
}
