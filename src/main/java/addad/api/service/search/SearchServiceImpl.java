package addad.api.service.search;

import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.SearchName;
import addad.api.domain.payload.request.SearchTag;
import addad.api.domain.repository.UserRepository;
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

    @Override
    public List<User> creatorSearchBasic(Pageable pageable) {
        Page<User> users = userRepository.findAllByUserinfo(Userinfo.creator, pageable);
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    User.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(user.getProfileImg())
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }

    @Override
    public List<User> creatorSearchByName(Pageable pageable, SearchName searchName) {
        Page<User> users = userRepository.findAllByUserinfoAndNameContains(Userinfo.creator, searchName.getName(), pageable);
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    User.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(user.getProfileImg())
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }

    @Override
    public List<User> creatorSearchByTag(Pageable pageable, SearchTag searchTag) {
        Page<User> users = userRepository.findAllByUserinfoAndHashtagContains(Userinfo.creator, searchTag.getTag(), pageable);
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(
                    User.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .profileImg(user.getProfileImg())
                            .hashtag(user.getHashtag())
                            .build()
            );
        }

        return userList;
    }
}
