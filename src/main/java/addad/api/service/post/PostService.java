package addad.api.service.post;

import addad.api.domain.payload.request.PostRequest;

public interface PostService {
    void write(PostRequest postWriteRequest);
}
