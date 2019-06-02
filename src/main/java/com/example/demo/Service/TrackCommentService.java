package com.example.demo.Service;

import com.example.demo.Entity.TrackComment;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/3/26.
 */
public interface TrackCommentService {
    int addTrackComment(TrackComment com);

    int addReplyTrackComment(TrackComment com);

    int deleteTrackComment(Long commentid);

    int updateTrackComment(TrackComment com);

    int updateFabulous(Long fabulous, Long commentid);

    List<TrackComment> getTrackComment(Long trackId);


    List<Map<String, Object>> getCommentByTrackid(Long trackid, Integer start, Integer size);

    List<Map<String, Object>> getReplyFromTrack(Long commentid, Integer start, Integer size);

    Long countComment(Long trackid);
}
