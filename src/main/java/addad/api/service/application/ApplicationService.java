package addad.api.service.application;

import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.domain.payload.response.SearchResponse;

import java.util.List;

public interface ApplicationService {
    List<ApplicationResponse> application();
}
