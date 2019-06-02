package com.example.demo.Dao;

import com.example.demo.Entity.Track;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface TrackMapper {
    int deleteByPrimaryKey(Long trackid);

    int insert(Track record);

    int insertSelective(Track record);

    Track selectByPrimaryKey(Long trackid);

    int updateByPrimaryKeySelective(Track record);

    int updateByPrimaryKeyWithBLOBs(Track record);

    int updateByPrimaryKey(Track record);

    List<Track> getTracktInfoById(@Param("pagenum") Long pagenum, @Param("size") Long size, @Param("userid") Long UserId);

    List<Track> getTrackInfoByTime(Date startDate, Date endDate);

    int addTrackInfo(Track track);

    int deleteTrackInfo(Long TrackId);

    int updateFabulous(Track track);

    List<HashMap<String, Object>> selectNewestTrack(@Param("userid") Long userid, @Param("pagenum") Long pagenumber, @Param("pagesize") Long pagesize, @Param("NofollowPagenum") Long NofollowPagenum, @Param("NofollowPagesize") Long NofollowPagesize);

    HashMap<String, String> CountSocialData(Long userid);
}