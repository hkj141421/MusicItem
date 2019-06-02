package com.example.demo.Dao;

import com.example.demo.Entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MusicMapper {
    int deleteByPrimaryKey(Long musicid);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(Long musicid);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    int updateMusic(Long PlayNumber, Long MusicId);

    int updateMusicImg(@Param("img") String img, @Param("musicid") Long musicid);

    List<Music> LimitMusic(Integer begin, Integer size);

    Music selectByname(String MusicName, String Singer);

    int InsertNoexist(Music music);

    Music getMusic(String musicid);

    int AddMusic(Music music);

    int DeleteMusic(Long MusicId);

    int UpdateMusic(Music music);

    List<Music> getMusicList(Music music);

    List<Music> getAllMusic(@Param("pagenum") Long pagenum, @Param("size") Long size);

    List<Music> getLikeMusicList(Music music);

    List<Music> getMusicBySheetId(Long SheetId);

    List<Music> getPageMusicBySheetId(@Param("sheetid") Long SheetId, @Param("pagenumber") Long pagenumber, @Param("size") Long size);

    List<Music> getNewstSongList(@Param("offset") Integer offset, @Param("size") Integer size);

    List<HashMap<String, Object>> getlanguageBandSongList(@Param("pagenum") Integer offset, @Param("size") Integer size, @Param("bandname") String bandname);

    List<HashMap<String, Object>> getGlobalBandSongList(@Param("pagenum") Integer offset, @Param("size") Integer size);

    List<HashMap<String, Object>> getNewBandSongList(@Param("pagenum") Integer offset, @Param("size") Integer size);

    List<Music> selectRecommendMusic(HashMap<String, Object> map);

    List<Music> searchMusic(@Param("key") String key, @Param("pagenum") Integer offset, @Param("size") Integer size);

    Long countSearchMusic(@Param("key") String key);

    int CountRecommendMusic(HashMap<String, Object> map);

    List<Music> clearImg(@Param("pagenum") Integer offset, @Param("size") Integer size);

    List<HashMap<String, Object>> getAlbumBySingerName(String singername);

    List<Music> getMusicFromAlbum(String album);

    Long getAllMusicCount();

    Long Batchdeletion(@Param("array") List<Long> array);
}