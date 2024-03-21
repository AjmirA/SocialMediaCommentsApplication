import com.example.model.Comment;
import com.example.model.Dislike;
import com.example.model.Like;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.DislikeRepository;
import com.example.repository.LikeRepository;
import com.example.repository.UserRepository;
import com.example.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private DislikeRepository dislikeRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddComment() {
        String postId = "postId";
        String userId = "userId";
        String content = "content";

        Comment savedComment = new Comment();
        savedComment.setId("commentId");
        savedComment.setPostId(postId);
        savedComment.setUserId(userId);
        savedComment.setContent(content);

        when(commentRepository.save(any())).thenReturn(savedComment);

        Comment result = commentService.addComment(postId, userId, content);

        assertNotNull(result);
        assertEquals("commentId", result.getId());
        assertEquals(postId, result.getPostId());
        assertEquals(userId, result.getUserId());
        assertEquals(content, result.getContent());

        verify(commentRepository, times(1)).save(any());
    }

    @Test
    public void testAddReply() {
        String parentCommentId = "parentCommentId";
        String userId = "userId";
        String content = "Content";

        Comment parentComment = new Comment();
        parentComment.setId(parentCommentId);
        parentComment.setUserId(userId);
        parentComment.setContent(content);

        when(commentRepository.findById(parentCommentId)).thenReturn(Optional.of(parentComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(parentComment);

        Comment reply = commentService.addReply(parentCommentId, userId, content);

        assertNotNull(reply);
        //assertEquals(parentComment, reply.getParentComment());
        assertEquals(userId, reply.getUserId());
        assertEquals(content, reply.getContent());
       // assertTrue(parentComment.getReplies().contains(reply));
    }

    @Test
    public void testLikeComment() {
        String commentId = "commentId";
        String userId = "userId";

        Comment comment = new Comment();
        comment.setId(commentId);

        User user = new User();
        user.setId(userId);

        Like like = new Like();
        like.setComment(comment);
        like.setUser(user);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(likeRepository.save(like)).thenReturn(like);

        commentService.likeComment(commentId, userId);

        assertEquals(1, comment.getLikes().size());
        assertEquals(user, comment.getLikes().get(0).getUser());
    }

    @Test
    public void testDislikeComment() {
        String commentId = "commentId";
        String userId = "userId";

        Comment comment = new Comment();
        comment.setId(commentId);

        User user = new User();
        user.setId(userId);

        Dislike dislike = new Dislike();
        dislike.setComment(comment);
        dislike.setUser(user);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(dislikeRepository.save(dislike)).thenReturn(dislike);

        commentService.dislikeComment(commentId, userId);

        assertEquals(1, comment.getDislikes().size());
        assertEquals(user, comment.getDislikes().get(0).getUser());
    }


    @Test
    public void testGetComments() {
        // Mock data
        String postId = "post123";
        List<Comment> expectedComments = Arrays.asList(
                new Comment("1", postId, null, null, "user1", "Comment 1", null, null),
                new Comment("2", postId, null, null, "user2", "Comment 2", null, null)
        );

        // Mock behavior of commentRepository.findByPostIdAndParentCommentIsNull
        when(commentRepository.findByPostIdAndParentCommentIsNull(postId)).thenReturn(expectedComments);

        // Call the service method
        List<Comment> actualComments = commentService.getComments(postId);

        // Verify that the repository method is called with the correct postId
        verify(commentRepository, times(1)).findByPostIdAndParentCommentIsNull(postId);

        // Verify that the returned comments match the expected comments
        assertEquals(expectedComments.size(), actualComments.size());
        for (int i = 0; i < expectedComments.size(); i++) {
            assertEquals(expectedComments.get(i), actualComments.get(i));
        }
    }
}
