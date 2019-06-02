package com.example.demo.Dao;

import com.example.demo.Entity.TrackComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrackCommentMapper {

    int deleteByPrimaryKey(Long commentid);

    int insert(TrackComment record);

    int insertSelective(TrackComment record);

    TrackComment selectByPrimaryKey(Long commentid);

    int updateByPrimaryKeySelective(TrackComment record);

    int updateByPrimaryKeyWithBLOBs(TrackComment record);

    int updateByPrimaryKey(TrackComment record);

    List<TrackComment> getTrackCommentInfoById(Long trackId);

    int addTrackCommentInfo(TrackComment trackComment);

    int addReplyTrackCommentInfo(TrackComment trackComment);

    int deleteTrackCommentInfo(Long CommentId);

    int updateTrackCommentInfo(TrackComment trackComment);


    List<Map<String, Object>> selectCommentByTrackidFromTrack(@Param("trackid") Long trackid, @Param("start") Integer start, @Param("size") Integer size);

    List<Map<String, Object>> selectReplyBycommentidFromTrack(@Param("commentid") Long commentid, @Param("start") Integer start, @Param("size") Integer size);

    Long selectCommentCountByTrackidFromTrack(Long trackid);
}