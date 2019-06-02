package com.example.demo.ServiceImp;

import com.example.demo.Dao.TrackCommentMapper;
import com.example.demo.Entity.TrackComment;
import com.example.demo.Service.TrackCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/3/26.
 */
@Service
public class TrackCommentServiceImpl implements TrackCommentService {
    @Autowired
    private TrackCommentMapper TCMapper;

    @Override
    public int addTrackComment(TrackComment com) {
        return TCMapper.addTrackCommentInfo(com);
    }

    @Override
    public int addReplyTrackComment(TrackComment com) {
        return TCMapper.addReplyTrackCommentInfo(com);
    }

    @Override
    public int deleteTrackComment(Long commentid) {
        return TCMapper.deleteTrackCommentInfo(commentid);
    }

    @Override
    public int updateTrackComment(TrackComment com) {
        return TCMapper.updateByPrimaryKeySelective(com);
    }

    @Override
    public int updateFabulous(Long fabulous, Long commentid) {
        TrackComment trackComment = new TrackComment();
        trackComment.setFabulous(fabulous);
        trackComment.setCommentid(commentid);
        return this.updateTrackComment(trackComment);
    }

    @Override
    public List<TrackComment> getTrackComment(Long trackId) {
        return TCMapper.getTrackCommentInfoById(trackId);
    }

    @Override
    public List<Map<String, Object>> getCommentByTrackid(Long trackid, Integer start, Integer size) {
        return TCMapper.selectCommentByTrackidFromTrack(trackid, (start - 1) * size, size);
    }

    @Override
    public List<Map<String, Object>> getReplyFromTrack(Long commentid, Integer start, Integer size) {
        return TCMapper.selectReplyBycommentidFromTrack(commentid, (start - 1) * size, size);
    }

    @Override
    public Long countComment(Long trackid) {
        return TCMapper.selectCommentCountByTrackidFromTrack(trackid);
    }
}
