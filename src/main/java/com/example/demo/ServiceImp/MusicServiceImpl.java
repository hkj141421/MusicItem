package com.example.demo.ServiceImp;

import com.example.demo.Dao.LrcMapper;
import com.example.demo.Dao.MusicMapper;
import com.example.demo.Entity.Lrc;
import com.example.demo.Entity.Music;
import com.example.demo.Entity.SongSheetInfoV;
import com.example.demo.Service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper mmpper;
    @Autowired
    LrcMapper lrcMapper;

    @Override
    public List<Music> getMusicList(Music music) {
        return mmpper.getMusicList(music);
    }

    @Override
    public List<Music> getLikeMusicList(Music music) {
        return mmpper.getLikeMusicList(music);
    }

    @Override
    public List<Music> getNewstMusicList(Integer offset, Integer size) {
        return mmpper.getNewstSongList((offset - 1) * size, size);
    }

    @Override
    public List<HashMap<String, Object>> getBandMusicList(String language, Integer offset, Integer size) {
        return mmpper.getlanguageBandSongList((offset - 1) * size, size, "%" + language + "%");
    }

    @Override
    public List<HashMap<String, Object>> getNewBandMusicList(Integer offset, Integer size) {
        return mmpper.getNewBandSongList((offset - 1) * size, size);
    }

    @Override
    public List<HashMap<String, Object>> getGlobalMusicList(Integer offset, Integer size) {
        return mmpper.getGlobalBandSongList((offset - 1) * size, size);
    }

    @Override
    public List<Music> getSongList(SongSheetInfoV sheet) {
        return null;
    }

    @Override
    public int InsertMusic(Music music) {
        Lrc lrc = new Lrc();
        lrc.setLrcname(music.getMusicname());
        lrc.setContent("[00:00.00] 暂无歌词");
        this.lrcMapper.insert(lrc);
        music.setLyricid(lrc.getLrcid());
        return mmpper.AddMusic(music);
    }

    @Override
    public int UpdateMusic(Music music) {
        return mmpper.UpdateMusic(music);
    }

    @Override
    public Integer DeleteMusic(Long musicid) {
        return mmpper.DeleteMusic(musicid);
    }

    @Override
    public List<Music> getMusicBySheetId(Long SheetId) {
        return mmpper.getMusicBySheetId(SheetId);
    }

    @Override
    public List<Music> getPageMusicBySheetId(Long SheetId, Long pagenumber, Long size) {
        return mmpper.getPageMusicBySheetId(SheetId, (pagenumber - 1) * size, size);
    }

    @Override
    public Music getMusicByid(Long musicid) {
        return mmpper.selectByPrimaryKey(musicid);
    }


    @Override
    public List<Music> getUserRecommendMusic(String musicpreference, Integer size) {
        List<String> pre = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        if (musicpreference != null) {
            musicpreference.substring(1, musicpreference.length() - 1);
            String[] str = musicpreference.split(",");
            for (String s : str) {
                pre.add(s);
            }
        }
        map.put("musicpreference", pre);
        map.put("size", size);
        return mmpper.selectRecommendMusic(map);
    }

    @Override
    public List<Music> searchPage(String key, Integer pagenum, Integer size) {
        return mmpper.searchMusic("%" + key + "%", (pagenum - 1) * size, size);
    }

    @Override
    public Long CountSearchPage(String key) {
        return mmpper.countSearchMusic("%" + key + "%");
    }

    @Override
    public List<Music> getMusicFromAlbum(String album) {
        return mmpper.getMusicFromAlbum(album);
    }

    @Override
    public List<HashMap<String, Object>> getAlbumByname(String singername) {
        return mmpper.getAlbumBySingerName(singername);
    }

    @Override
    public List<Music> getMusicPage(Long pagenum, Long size) {
        return mmpper.getAllMusic((pagenum - 1) * size, size);
    }

    @Override
    public Long getMusicCount() {
        return mmpper.getAllMusicCount();
    }

    @Override
    public Long Batchdeletion(List<Long> array) {
        if (array.size() == 0) return 0L;
        return mmpper.Batchdeletion(array);
    }

    @Override
    public int updateAllColum(Music music) {
        return mmpper.updateByPrimaryKeySelective(music);
    }
}
