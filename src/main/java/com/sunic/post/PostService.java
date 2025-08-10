// PostService.java
import com.sunic.post.Post;
import com.sunic.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 문제점: 모든 게시물을 한 번에 조회하여 메모리 및 성능 문제 발생 가능
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        
        // Entity를 DTO로 변환
        return posts.stream()
            .map(post -> new PostDto(post.getId(), post.getTitle(), post.getAuthorName()))
            .collect(Collectors.toList());
    }
}
