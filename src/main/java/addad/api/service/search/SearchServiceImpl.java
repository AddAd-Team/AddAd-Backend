package addad.api.service.search;

import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.SearchName;
import addad.api.domain.payload.request.SearchTag;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.domain.payload.response.SearchResponse;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.DefaultImg;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final UserRepository userRepository;
    private final DefaultImg defaultImg;

    @Override
    public List<SearchResponse> creatorSearchBasic(Pageable pageable) {
        Page<User> users = userRepository.findAllByUserinfo(Userinfo.creator, pageable);
        List<SearchResponse> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    SearchResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(defaultImg.userinfo(user.getProfileImg(), user.getUserinfo()))
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }

    @Override
    public List<SearchResponse> creatorSearchByName(Pageable pageable, SearchName searchName) {
        Page<User> users = userRepository.findAllByUserinfoAndNameContains(Userinfo.creator, searchName.getName(), pageable);
        List<SearchResponse> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    SearchResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(defaultImg.userinfo(user.getProfileImg(), user.getUserinfo()))
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }

    @Override
    public List<SearchResponse> creatorSearchByTag(Pageable pageable, SearchTag searchTag) {
        Page<User> users = userRepository.findAllByUserinfoAndHashtagContains(Userinfo.creator, searchTag.getTag(), pageable);
        List<SearchResponse> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    SearchResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(defaultImg.userinfo(user.getProfileImg(), user.getUserinfo()))
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }

    @Override
    public ProfileResponse findOneUserById(Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(UserNotFoundException::new);

        return ProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .description(user.getDescription())
                .profileImg(defaultImg.userinfo(user.getProfileImg(), user.getUserinfo()))
                .hashtag(user.getHashtag())
                .build();
    }
}
