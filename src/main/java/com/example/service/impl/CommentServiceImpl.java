    package com.example.service.impl;

    import com.example.constants.ErrorCode;
    import com.example.exception.BaseException;
    import com.example.exception.CommentServiceException;
    import com.example.model.*;
    import com.example.repository.*;
    import com.example.service.CommentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

    @Service
    public class CommentServiceImpl implements CommentService {

        @Autowired
        private CommentRepository commentRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PostRepository postRepository;

        @Autowired
        LikeRepository likeRepository;

        @Autowired
        DislikeRepository dislikeRepository;

        @Override
        public Comment addComment(String postId, String userId, String content) {
            try {
                Comment comment = new Comment();
                comment.setPostId(postId);
                comment.setUserId(userId);
                comment.setContent(content);
                //comment.setId(generateCommandId());
                return commentRepository.save(comment);
            } catch (BaseException e) {
                throw new CommentServiceException("Error adding comment: " + e.getMessage());
            }
        }

        @Override
        public Comment addReply(String parentCommentId, String userId, String content) {
            try {
                Optional<Comment> optionalComment = commentRepository.findById(parentCommentId);
                if(!optionalComment.isPresent()){
                    throw new CommentServiceException(ErrorCode.PARENT_COMMAND_NOT_FOUNT);
                }

                Comment parentComment=optionalComment.get();

                Comment reply = new Comment();
                reply.setParentComment(parentComment);
                reply.setUserId(userId);
                reply.setContent(content);
                commentRepository.save(reply);
                //reply.setId(generateCommandId());

                parentComment.getReplies().add(reply);
                return commentRepository.save(parentComment);
            } catch (BaseException e) {
                throw new CommentServiceException("Error adding reply: " + e.getMessage());
            }
        }

        private String generateCommandId() {
              return UUID.randomUUID().toString();
        }

        @Override
        public void likeComment(String commentId, String userId) {
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if(!optionalComment.isPresent()){
                throw new CommentServiceException(ErrorCode.COMMAND_NOT_FOUNT);
            }
            Optional<User> optionalUser = userRepository.findById(userId);
            if(!optionalUser.isPresent()){
                throw new CommentServiceException(ErrorCode.USER_NOT_FOUNT);
            }
            Comment comment=optionalComment.get();
            User user=optionalUser.get();
            Like like = new Like();
            like.setComment(comment);
            like.setUser(user);

            Like savedLike = likeRepository.save(like);

            comment.getLikes().add(savedLike);
            commentRepository.save(comment);
        }

        @Override
        public void dislikeComment(String commentId, String userId) {
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if(!optionalComment.isPresent()){
                throw new CommentServiceException(ErrorCode.COMMAND_NOT_FOUNT);
            }
            Optional<User> optionalUser = userRepository.findById(userId);
            if(!optionalUser.isPresent()){
                throw new CommentServiceException(ErrorCode.USER_NOT_FOUNT);
            }
            Comment comment=optionalComment.get();
            User user=optionalUser.get();

            Dislike dislike = new Dislike();
            dislike.setComment(comment);
            dislike.setUser(user);
            Dislike savedDislike = dislikeRepository.save(dislike);
            comment.getDislikes().add(savedDislike);

            commentRepository.save(comment);
        }

        @Override
        public List<Comment> getComments(String postId) {
            return commentRepository.findByPostIdAndParentCommentIsNull(postId);
        }
    }

