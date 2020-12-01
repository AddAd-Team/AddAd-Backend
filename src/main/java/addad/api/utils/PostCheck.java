package addad.api.utils;

import addad.api.domain.entities.Application;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.enums.PostStatus;
import addad.api.domain.repository.ApplicationRepository;
import addad.api.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PostCheck implements CommandLineRunner {

    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final AsyncFunc asyncFunc;

    @Async
    @Override
    public void run(String... args) throws Exception {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        Date d1 = f.parse(hour + ":" + min + ":" + sec);
        Date d2 = f.parse("24:00:00");
        long diff = d2.getTime() - d1.getTime();
        Thread.sleep(diff);
        check();
    }

    public void check() throws Exception {
        List<Post> posts = postRepository.findAllByPostTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        for (Post post : posts) {
            postRepository.save(
                    post.ChangePostStatus(PostStatus.beforeSelection)
            );

            List<Application> applications = applicationRepository.findAllByPost_id(post.getId());

            firebaseCloudMessageService.sendMessageTo(
                    post.getUser().getDeviceToken(),
                    "Addad 알림",
                    post.getTitle() + " 광고에 " + applications.size() + "명이 신청하였습니다. 자세한 내용은 신청자 페이지를 참고해주세요."
            );
            asyncFunc.notificationLog(post.getUser().getId(), post,post.getTitle() + " 광고에 " + applications.size() + "명이", "신청하였습니다. 자세한 내용은 신청자 페이지를 참고해주세요.\"");
        }

        Thread.sleep(86400000);
        check();
    }
}
