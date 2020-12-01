package addad.api.utils;

import addad.api.domain.entities.Contact;
import addad.api.domain.entities.Notification;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.UserList;
import addad.api.domain.repository.ContactRepository;
import addad.api.domain.repository.NotificationRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncFunc {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final ContactRepository contactRepository;

    @Async
    public void saveDeviceToken(User user, String deviceToken) {
        userRepository.save(user.changeDeviceToken(deviceToken));

        return;
    }

    @Async
    public void notificationLog(Long userId, Post post, String description) {
        notificationRepository.save(
                Notification.builder()
                        .userId(userId)
                        .profileImg(post.getUser().getProfileImg())
                        .title(post.getTitle())
                        .description(description)
                        .build()
        );
    }

    @Async
    public void contactSave(Long userId, Post post, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        contactRepository.save(
                    Contact.builder()
                        .title(post.getTitle())
                        .postImg(post.getPost_img())
                        .hashtag(post.getHashtag())
                        .postTime(post.getPostTime())
                        .advertiserId(post.getUser_id())
                        .advertiserName(post.getUser().getName())
                        .advertiserProfileImage(post.getUser().getProfileImg())
                        .creatorId(user.getId())
                        .creatorName(user.getName())
                        .creatorProfileImage(user.getName())
                        .postId(postId)
                        .build()
            );
    }
}
