package com.example.demo.Service;

import com.example.demo.Entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2018/12/14.
 */
public interface CommentService {
    int addComment(Comment com);

    int addReplyComment(Comment com);

    int deleteComment(Long commentId);

    int updateComment(Comment com);

    int updateFabulous(Long fabulous, Long commentid);

    List<Comment> getComment(Comment com);

    List<Comment> getCommentPage(Comment com, Integer start, Integer size);

    List<Map<String, Object>> getCommentByMusicid(Long musicid, Integer start, Integer size);

    List<Map<String, Object>> getReplyFromMusic(Long commentid, Integer start, Integer size);

    Long countComment(Long musicid);

}
