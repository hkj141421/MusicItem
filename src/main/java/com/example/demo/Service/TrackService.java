package com.example.demo.Service;

import com.example.demo.Entity.Track;

import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/12/14.
 */
public interface TrackService {
    int addTrack(Track t);

    int deleteTrack(Long trackId);

    int updateTrackFabulous(Track t);

    List<Track> getTrackListById(Long userId, Long pagenum, Long size);

    List<HashMap<String, Object>> loadTrack(Long userid, Long pagenum, Long pagesize, Long nopagesize);

    HashMap<String, String> CountSocialData(Long userid);
}
