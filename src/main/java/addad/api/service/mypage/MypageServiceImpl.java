package addad.api.service.mypage;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.*;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.ModifyPost;
import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ADListResponse;
import addad.api.domain.payload.response.ADResponse;
import addad.api.domain.payload.response.PostResponse;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.domain.repository.*;
import addad.api.exception.IncorrectPasswordException;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import addad.api.service.post.PostServiceImpl;
import addad.api.utils.DefaultImg;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final AuthenticationFacade authenticationFacade;
    private final S3Service s3Service;
    private final ApplicationRepository applicationRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ContactRepository contactRepository;
    private final LikesRepository likesRepository;
    private final DefaultImg defaultImg;
    private final PostServiceImpl PostServiceImpl;

    @Override
    public void passwordAuth (String Password) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .filter(data -> passwordEncoder.matches(Password, data.getPassword()))
                .orElseThrow(IncorrectPasswordException::new);
    }

    @Override
    public void passwordChange (String Password) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        String password = passwordEncoder.encode(Password);
        user.passwordChange(password);

        userRepository.save(user);
    }

    @Override
    public ProfileResponse profile() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        List<ADResponse> responses = new ArrayList<>();
        List<Contact> contacts = contactRepository.findAllByCreator_id(user.getId());

        for (Contact contact : contacts) {
            responses.add(
                    ADResponse.builder()
                            .postId(contact.getPostId())
                            .postImg(contact.getPost().getPost_img())
                            .title(contact.getPost().getTitle())
                            .hashtag(contact.getPost().getHashtag())
                            .postTime(contact.getPost().getPostTime())
                            .build()
            );
        }

        return ProfileResponse.builder()
                .email(authenticationFacade.getUserEmail())
                .name(user.getName())
                .profileImg(defaultImg.userinfo(user.getProfileImg(), user.getUserinfo()))
                .hashtag(user.getHashtag())
                .description(user.getDescription())
                .contactAd(responses)
                .build();
    }

    @Override
    public ProfileResponse ModifyProfile(ModifyProfile modifyProfile) throws IOException {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        String changeImg;
        if (!(modifyProfile.getImage().isEmpty())) {
            changeImg = s3Service.Upload(modifyProfile.getImage(), "userImg/");
        } else changeImg = user.getProfileImg();

        User ChangedUser = user.ChangeProfile(changeImg, modifyProfile);
        userRepository.save(user);

        return ProfileResponse.builder()
                .email(authenticationFacade.getUserEmail())
                .name(ChangedUser.getName())
                .profileImg(ChangedUser.getProfileImg())
                .hashtag(ChangedUser.getHashtag())
                .description(ChangedUser.getDescription())
                .build();
    }

    @Override
    public PostResponse ModifyPost(ModifyPost modifyPost, Long postId) throws IOException{
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        String changeImg;
        if (!(modifyPost.getImage().isEmpty())) {
            changeImg = s3Service.Upload(modifyPost.getImage(), "post_img/");
        } else changeImg = post.getUser().getProfileImg();

        Post ChangedPost = post.ChangePost(changeImg, modifyPost);
        postRepository.save(post);

        return PostResponse.builder()
                .title(ChangedPost.getTitle())
                .hashtag(ChangedPost.getHashtag())
                .image(ChangedPost.getPost_img())
                .description(ChangedPost.getDescription())
                .price(ChangedPost.getPrice())
                .postTime(ChangedPost.getPostTime())
                .deadline(ChangedPost.getDeadline())
                .build();
    }

    @Override
    public List<ADResponse> ADList() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        List<ADResponse> responses = new ArrayList<>();

        if (user.getUserinfo() == Userinfo.creator) {
            List<Application> contactApply = applicationRepository.findAllByUser_id(user.getId());

            for (Application contactapply : contactApply) {
                responses.add(
                        ADResponse.builder()
                                .postId(contactapply.getPost().getId())
                                .title(contactapply.getPost().getTitle())
                                .postImg(contactapply.getPost().getPost_img())
                                .hashtag(contactapply.getPost().getHashtag())
                                .postTime(contactapply.getPost().getPostTime())
                                .build()
                );
            }

            return responses;
        }

        if (user.getUserinfo() == Userinfo.advertiser) {
            List<Post> posts = postRepository.findByUserId(user.getId());

            for (Post post : posts) {
                responses.add(
                        ADResponse.builder()
                                .postId(post.getId())
                                .title(post.getTitle())
                                .postImg(post.getPost_img())
                                .hashtag(post.getHashtag())
                                .postTime(post.getPostTime())
                                .build()
                );
            }

            return responses;
        }

        return responses;
    }

    public List<ADListResponse> likeAd() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        List<Likes> likes = likesRepository.findAllByUser_id(user.getId());
        List<ADListResponse> responses = new ArrayList<>();

        for (Likes like : likes) {
            responses.add(
                    ADListResponse.builder()
                            .postId(like.getPost_id())
                            .title(like.getPost().getTitle())
                            .postImg(like.getPost().getPost_img())
                            .postTime(like.getPost().getPostTime())
                            .recruitmentClosing(PostServiceImpl.dateCalculation(like.getPost().getPostTime()))
                            .hashtag(like.getPost().getHashtag())
                            .build()
            );
        }

        return responses;
    }
}