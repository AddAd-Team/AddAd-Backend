package addad.api.service.post;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Application;
import addad.api.domain.entities.Likes;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.DetailFeedResponse;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.repository.ApplicationRepository;
import addad.api.domain.repository.LikesRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.DefaultImg;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final AuthenticationFacade authenticationFacade;
    private final DefaultImg defaultImg;



    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {
        String imgUrl = s3Service.Upload(postRequest.getImage(), "post_img/");

        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .userId(user.getId())
                        .hashtag(postRequest.getHashtag())
                        .img(imgUrl)
                        .userId(user.getId())
                        .description(postRequest.getDescription())
                        .price(postRequest.getPrice())
                        .postTime(postRequest.getPostTime())
                        .deadline(postRequest.getDeadline())
                        .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build()
        );
    }

    @Override
    public List<FeedResponse> getFeed(Pageable pageable){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Page<Post> posts = postRepository.findAllBy(pageable);
        List<FeedResponse> feedResponses = new ArrayList<>();
        for (Post post : posts) {

            Optional<Likes> likes = likesRepository.findByUser_idAndAndPost_id(user.getId(), post.getId());

            feedResponses.add(
                    FeedResponse.builder()
                            .userId(post.getUser().getId())
                            .postId(post.getId())
                            .profileImg(defaultImg.basic(post.getUser().getProfileImg()))
                            .title(post.getTitle())
                            .postImg(post.getPost_img())
                            .price(post.getPrice())
                            .postTime(post.getPostTime())
                            .DateRemaining(dateCalculation(post.getPostTime()))
                            .hashtag(post.getHashtag())
                            .likes(likes.isPresent())
                            .build()
            );
        }

        return feedResponses;
    }
  
    public DetailFeedResponse getDetailFeed(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        Optional<Likes> likes = likesRepository.findByUser_idAndAndPost_id(post.getUser().getId(), post.getId());
        Optional<Application> application = applicationRepository.findByUser_idAndAndPost_id(post.getUser().getId(), post.getId());


        return DetailFeedResponse.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .postImg(post.getPost_img())
                .profileImg(post.getUser().getProfileImg())
                .price(post.getPrice())
                .postTime(post.getPostTime())
                .deadline(post.getDeadline())
                .recruitmentClosing(dateCalculation(post.getPostTime()))
                .adClosing(dateCalculation(post.getDeadline()))
                .hashtag(post.getHashtag())
                .description(post.getDescription())
                .likes(likes.isPresent())
                .application(application.isPresent())
                .build();
    }

    @Override
    public void deleteFeed(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteById(postId);

    }

    public String dateCalculation(String strStartDate) {
        String strEndDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String strFormat = "yyyyMMdd";

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(strStartDate);
            Date endDate = sdf.parse(strEndDate);

            long diffDay = (startDate.getTime() - endDate.getTime()) / (24*60*60*1000);
            return String.valueOf(diffDay);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return "";
    }
}