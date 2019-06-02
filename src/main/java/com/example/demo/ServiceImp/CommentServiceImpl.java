package com.example.demo.ServiceImp;

import com.example.demo.Dao.CommentMapper;
import com.example.demo.Entity.Comment;
import com.example.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2018/12/14.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper cmmapper;

    @Override
    public int addComment(Comment com) {
        return cmmapper.addCommentInfo(com);
    }

    @Override
    public int addReplyComment(Comment com) {
        return cmmapper.addReplyCommentInfo(com);
    }

    @Override
    public int deleteComment(Long commentId) {
        return cmmapper.deleteCommentInfo(commentId);
    }

    @Override
    public int updateComment(Comment com) {
        return cmmapper.updateCommentInfo(com);
    }

    @Override
    public int updateFabulous(Long fabulous, Long commentid) {
        Comment comment = new Comment();
        comment.setCommentid(commentid);
        comment.setFabulous(fabulous);
        return this.updateComment(comment);
    }

    @Override
    public List<Comment> getComment(Comment com) {
        return cmmapper.getCommentInfo(com);
    }

    @Override
    public List<Comment> getCommentPage(Comment com, Integer start, Integer size) {
        HashMap<String, String> map = new HashMap<>();
        map.put("content", com.getContent());
        map.put("CommentId", com.getCommentid().toString());
        map.put("date", com.getDate().toString());
        map.put("Fabulous", com.getFabulous().toString());
        map.put("MusicId", com.getMusicid().toString());
        map.put("UserId", com.getUserid().toString());
        map.put("ReplyCommentId", com.getReplycommentid().toString());
        map.put("start", start.toString());
        map.put("size", size.toString());
        return cmmapper.getCommentPage(map);
    }


    @Override
    public List<Map<String, Object>> getCommentByMusicid(Long musicid, Integer start, Integer size) {
        return cmmapper.selectCommentByMusicidFromMusic(musicid, (start - 1) * size, size);
    }

    @Override
    public List<Map<String, Object>> getReplyFromMusic(Long commentid, Integer start, Integer size) {
        return cmmapper.selectReplyByCommentidFromMusic(commentid, (start - 1) * size, size);
    }

    @Override
    public Long countComment(Long musicid) {
        return cmmapper.selectCommentCountByMusicidFromMusic(musicid);
    }


    /**
     * @param musicId 音乐id
     * @return
     * @throws Exception
     */
    public List<Comment> getCommentByMusicId(Long musicId, Integer start, Integer size) throws Exception {
        if (musicId == null || musicId == 0) {
            throw new Exception("音乐id不能为空或为0");
        }
        return cmmapper.getNotReplyComment(musicId, start, size);
    }

    public List<Comment> getCommentByReplyId(Long musicId, Long ReplyId) throws Exception {
        if (ReplyId == null || ReplyId == 0L) {
            throw new Exception("被回复评论id不能为空或为0");
        }
        Comment comment = new Comment();
        comment.setReplycommentid(ReplyId);
        comment.setMusicid(musicId);
        return cmmapper.getCommentInfo(comment);
    }


}
