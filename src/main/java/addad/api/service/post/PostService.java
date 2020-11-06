package addad.api.service.post;

import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.payload.response.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    void write(PostRequest postRequest);
    List<FeedResponse> getFeed(Pageable pageable);
}