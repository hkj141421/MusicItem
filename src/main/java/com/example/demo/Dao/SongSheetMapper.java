package com.example.demo.Dao;

import com.example.demo.Entity.SongSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SongSheetMapper {
    int deleteByPrimaryKey(Long sheetid);

    int insert(SongSheet record);

    int insertSelective(SongSheet record);

    SongSheet selectByPrimaryKey(Long sheetid);

    int updateByPrimaryKeySelective(SongSheet record);

    int updateByPrimaryKeyWithBLOBs(SongSheet record);

    int updateByPrimaryKey(SongSheet record);

    HashMap<String, Object> getSheetById(Long SheetId);

    int InsertMusicToSheet(@Param("MusicId") Long MusicId, @Param("SheetId") Long SheetId);

    int InsertNoexist(SongSheet songSheet);

    List<SongSheet> getSongSheetByCondition(SongSheet songSheet);

    List<SongSheet> getUserSongsheetinfoByCollect(@Param("id") Long id, @Param("Producer") String Producer);

    List<SongSheet> getUserSongsheetinfoByself(@Param("id") Long id, @Param("Producer") String Producer);

    List<SongSheet> getAllSongSheetInfo(@Param("start") Integer start, @Param("size") Integer size);

    int CreateSongSheet(SongSheet songSheet);

    int deleteSongSheet(Long SheetId);

    int updateSongSheetInfo(SongSheet songSheet);

    List<HashMap<String, Object>> selectSheetByType(@Param("type") String type, @Param("pagenum") Integer start, @Param("size") Integer size);

    List<HashMap<String, Object>> selectSheetByTime(@Param("pagenum") Integer start, @Param("size") Integer size);

    List<HashMap<String, Object>> selectUserCreateSheet(@Param("userid") Long userid, @Param("username") String username);

    List<SongSheet> selectPageSheet(@Param("pagenum") Integer start, @Param("size") Integer size, @Param("type") String type);

    Long selectCountSheet(@Param("type") String type);

    Long countSearchSheet(@Param("key") String key);

    List<SongSheet> searchSheet(@Param("key") String key, @Param("pagenum") Integer start, @Param("size") Integer size);

    List<SongSheet> getAllSheet(@Param("pagenum") Long pagenum, @Param("size") Long size);

    Long getAllSheetCount();

    Long Batchdeletion(@Param("array") List<Long> array);
}