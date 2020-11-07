package addad.api.service.post;

import addad.api.domain.payload.request.PostRequest;
import org.springframework.web.multipart.MultipartFile;


public interface PostService {
    void write(PostRequest postRequest, MultipartFile file);

}
