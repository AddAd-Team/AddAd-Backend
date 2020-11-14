package addad.api.utils;

import addad.api.domain.entities.enums.Userinfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DefaultImg {
    public String userinfo(String image, Userinfo userinfo) {
        if (image.isEmpty() && userinfo == Userinfo.creator) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/creator.jpg";
        } else if (image.isEmpty() && userinfo == Userinfo.advertiser) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%84%8C%E1%85%AE111.jpg";
        }

        return image;
    }

    public String basic(String image) {
        if (image.isEmpty()) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%84%8C%E1%85%AE111.jpg";
        }

        return image;
    }
}
