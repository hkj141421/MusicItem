package com.example.demo.ServiceImp;

import com.example.demo.Dao.TrackMapper;
import com.example.demo.Entity.Track;
import com.example.demo.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/12/14.
 */
@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    TrackMapper tmapper;

    @Override
    public int addTrack(Track t) {
        return tmapper.addTrackInfo(t);
    }

    @Override
    public int deleteTrack(Long trackId) {
        return tmapper.deleteTrackInfo(trackId);
    }

    @Override
    public int updateTrackFabulous(Track t) {
        return tmapper.updateFabulous(t);
    }

    @Override
    public List<Track> getTrackListById(Long userId, Long pagenum, Long pagesize) {
        return tmapper.getTracktInfoById((pagenum - 1) * pagesize, pagesize, userId);
    }

    @Override
    public List<HashMap<String, Object>> loadTrack(Long userid, Long pagenum, Long pagesize, Long nopagesize) {

        return tmapper.selectNewestTrack(userid, (pagenum - 1) * pagesize, pagesize, (pagenum - 1) * pagesize, 3L);
    }

    @Override
    public HashMap<String, String> CountSocialData(Long userid) {
        return tmapper.CountSocialData(userid);
    }
}
