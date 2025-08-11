// PostService.java
import com.sunic.post.Post;
import com.sunic.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 개선: Pageable 객체를 파라미터로 받아 페이징 처리
    public Page<PostDto> getAllPosts(Pageable pageable) {
        // Repository에서 Pageable을 인자로 넘겨주면 자동으로 페이징 쿼리 실행
        Page<Post> posts = postRepository.findAll(pageable);
        
        // Page 객체는 map 기능을 지원하므로 DTO 변환이 더 간결해짐
        return posts.map(post -> new PostDto(post.getId(), post.getTitle(), post.getAuthorName()));
    }
}
