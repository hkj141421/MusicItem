package com.example.demo.Service;

import com.example.demo.Entity.SongSheet;
import com.example.demo.Entity.SongSheetInfoKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2018/12/14.
 */
public interface SongSheetService {
    /**
     * 添加多张歌单
     *
     * @param SSlist
     * @return
     */
    public int addSongSheetList(List<SongSheet> SSlist);

    /**
     * 添加一张歌单
     *
     * @param sheet
     * @return 插入成功则返回主键，否则为null
     */
    public Long addSongSheet(SongSheet sheet);

    /**
     * 创建一张歌单
     *
     * @param songSheetInfo
     * @return
     */
    public int CreateSongSheet(SongSheet songSheetInfo);

    /**
     * 删除一张歌单
     *
     * @param SheetId
     * @return
     */
    public int deleteSongSheet(Long SheetId);

    /**
     * 修改一张歌单的信息
     *
     * @param SS
     * @return
     */
    public int UpdateSongSheet(SongSheet SS);

    /**
     * 获取一张歌单的信息，根据歌单名和制作人
     *
     * @param SS
     * @return
     */
    public List<SongSheet> getSongSheet(SongSheet SS);

    /**
     * 分页获取所有歌单信息
     *
     * @param start 页数
     * @param size  每页数量
     * @return
     */
    public List<SongSheet> getAllSongSheet(Integer start, Integer size);

    List<SongSheet> getSongSheetBytype(Integer start, Integer size, String type);

    /**
     * 获取用户的所有歌单信息
     *
     * @param id
     * @return
     */
    Map<String, List<SongSheet>> getUserSongSheet(Long id, String name);

    /**
     * 根据sheetid获得歌单,userid确定歌单是否被收藏
     *
     * @param sheetid
     * @return
     */
    HashMap<String, Object> getSheetByid(Long sheetid, Long userid);

    Long CounTypeSheet(String type);

    List<HashMap<String, Object>> getUserCreatSheet(Long id, String name);

    int AddMusicToSheet(SongSheetInfoKey songSheetInfoKey);

    List<HashMap<String, Object>> getSheetByType(String type, Integer pagenum, Integer size);

    List<HashMap<String, Object>> getSheetByTime(Integer pagenum, Integer size);

    List<SongSheet> searchPage(String key, Integer pagenum, Integer size);

    Long CountSearchPage(String key);

    List<SongSheet> getSheetPage(Long pagenum, Long size);

    Long getSheetCount();

    Long Batchdeletion(List<Long> array);

    int updateAllColum(SongSheet songSheet);
}
