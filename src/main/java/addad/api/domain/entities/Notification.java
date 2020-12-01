package addad.api.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String profileImg;

    @Column
    private String title;

    @Column
    private String description;

    @Builder
    public Notification(Long userId, String profileImg, String title, String description) {
        this.userId = userId;
        this.profileImg = profileImg;
        this.title = title;
        this.description = description;
    }
}
