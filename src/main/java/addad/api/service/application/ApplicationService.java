package addad.api.service.application;

import addad.api.domain.payload.request.UserList;
import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.domain.payload.response.SearchResponse;

import java.io.IOException;
import java.util.List;

public interface ApplicationService {
    List<ApplicationResponse> application(Long post_id);
    void apply(Long Id);
    void applicationAllow(List<UserList> userList, Long postId) throws IOException;
    void applicationDelete(Long postId);
}
