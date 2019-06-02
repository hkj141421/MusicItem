package com.example.demo.Dao;

import com.example.demo.Entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long commentid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    //自定义

    List<Comment> getCommentInfo(Comment comment);

    List<Comment> getCommentPage(HashMap map);

    List<Comment> getNotReplyComment(Long MusicId, Integer start, Integer size);

    int addCommentInfo(Comment comment);

    int addReplyCommentInfo(Comment comment);

    int deleteCommentInfo(Long commentid);

    int updateCommentInfo(Comment comment);


    List<Map<String, Object>> selectCommentByMusicidFromMusic(@Param("musicid") Long musicid, @Param("start") Integer start, @Param("size") Integer size);

    List<Map<String, Object>> selectReplyByCommentidFromMusic(@Param("commentid") Long commentid, @Param("start") Integer start, @Param("size") Integer size);

    Long selectCommentCountByMusicidFromMusic(@Param("musicid") Long musicid);
}