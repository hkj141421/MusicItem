package com.example.demo.Service;

import com.example.demo.Entity.Music;
import com.example.demo.Entity.SongSheetInfoV;

import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
public interface MusicService {
    public List<Music> getMusicList(Music music);

    /**
     * 模糊查询音乐
     *
     * @param music
     * @return
     */
    public List<Music> getLikeMusicList(Music music);

    /**
     * 查询最新的音乐
     *
     * @param offset 偏移量
     * @param size   大小
     * @return
     */
    public List<Music> getNewstMusicList(Integer offset, Integer size);

    /**
     * 查询语言榜单音乐
     *
     * @param language
     * @param offset
     * @param size
     * @return
     */
    public List<HashMap<String, Object>> getBandMusicList(String language, Integer offset, Integer size);

    /**
     * @param offset 页码
     * @param size   尺寸
     * @return
     */
    List<HashMap<String, Object>> getNewBandMusicList(Integer offset, Integer size);

    /**
     * @param offset 页码
     * @param size   尺寸
     * @return
     */
    List<HashMap<String, Object>> getGlobalMusicList(Integer offset, Integer size);

    /**
     * 输入歌单名，歌单制作者，返回歌单中的音乐数据
     *
     * @param sheet
     * @return
     */
    public List<Music> getSongList(SongSheetInfoV sheet);

    /**
     * 插入音乐信息
     *
     * @param music
     * @return
     */
    public int InsertMusic(Music music);

    /**
     * 修改音乐信息
     *
     * @param music
     * @return
     */
    public int UpdateMusic(Music music);

    /**
     * 删除音乐信息
     *
     * @param musicid
     * @return
     */
    public Integer DeleteMusic(Long musicid);

    List<Music> getMusicBySheetId(Long SheetId);

    List<Music> getPageMusicBySheetId(Long SheetId, Long pagenumber, Long size);

    Music getMusicByid(Long musicid);

    List<Music> getUserRecommendMusic(String musicpreference, Integer size);

    List<Music> searchPage(String key, Integer pagenum, Integer size);

    Long CountSearchPage(String key);

    List<Music> getMusicFromAlbum(String album);

    List<HashMap<String, Object>> getAlbumByname(String singername);

    List<Music> getMusicPage(Long pagenum, Long size);

    Long getMusicCount();

    Long Batchdeletion(List<Long> array);

    int updateAllColum(Music music);
}
