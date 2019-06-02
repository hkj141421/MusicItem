package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.Lrc;
import com.example.demo.Entity.Music;
import com.example.demo.Entity.SongSheetInfoKey;
import com.example.demo.Service.*;
import com.example.demo.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/12/8.
 */
@RestController
public class MusicController {
    @Autowired
    MusicService MService;
    @Autowired
    CollectionService CService;
    @Autowired
    LrcService LService;
    @Autowired
    SongSheetService songSheetService;
    @Autowired
    SingerService singerService;
    @Autowired
    UserService userService;

    @GetMapping("/api/lyrics/{id}")
    public AjaxResponseBody LrcController(@PathVariable Long id) throws IOException {//获取对应的歌词
        List<String> timelist = null;
        List<String> textlist = null;
        JSONArray Jarray = new JSONArray();
        Lrc l = new Lrc();
        l.setLrcid(id);
        Lrc lrc = LService.getLrcByid(id);
        if (lrc != null) {
            HashMap<String, List<String>> map = FileUtil.ParseLrc(lrc);
            timelist = map.get("time");
            textlist = map.get("text");
            try {
                Jarray.add(timelist);
                Jarray.add(textlist);
                return new AjaxResponseBody("200", "success", Jarray);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Jarray.add(timelist);
        Jarray.add(textlist);
        return new AjaxResponseBody("404", "找不到对应的歌词", Jarray);
    }


    @PutMapping("/api/user/music")
    public AjaxResponseBody AddMusic(@RequestBody Music music, @RequestParam Long lyricId) throws IOException {
        //添加音乐
        if (lyricId != null) {
            music.setLyricid(lyricId);
        }
        int i = 0;
        i = MService.InsertMusic(music);
        if (i != 0) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "fail", null);
        }
    }

    @PutMapping("/api/user/sheet/music")
    public AjaxResponseBody AddMusic(@RequestBody SongSheetInfoKey songSheetInfoKey) throws IOException {
        //添加音乐到歌单
        if (songSheetInfoKey.getMusicid() == null || songSheetInfoKey.getSheetid() == null) {
            return new AjaxResponseBody("233", "歌单id和音乐id不能为空", null);
        }
        int i = songSheetService.AddMusicToSheet(songSheetInfoKey);
        if (i != 0) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "fail", null);
        }
    }

    @GetMapping("/api/newmusic")
    public AjaxResponseBody NewstMusic(@RequestParam("offset") Integer offset, @RequestParam("size") Integer size) throws IOException {//获取最新音乐
        try {
            List<Music> MusicList;
            if (offset != null && size != null) {
                MusicList = MService.getNewstMusicList(offset, size);
                if (MusicList != null) {
                    return new AjaxResponseBody("200", "success", MusicList);
                }
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "找不到该资源", null);
    }

    @GetMapping("/api/musicband")
    public AjaxResponseBody getBandList(@RequestParam("pagenum") Integer pagenum, @RequestParam("size") Integer size, @RequestParam("type") String type) throws IOException {
        //获取音乐排行榜
        List<HashMap<String, Object>> musicband = null;

        if (type.equals("global")) {//全球榜
            musicband = MService.getGlobalMusicList(pagenum, size);
        } else if (type.equals("newmusic")) {   //新歌榜
            musicband = MService.getNewBandMusicList(pagenum, size);
        } else if (type.equals("chinese")) {
            musicband = MService.getBandMusicList("华语", pagenum, size);
        } else if (type.equals("english")) {
            musicband = MService.getBandMusicList("欧美", pagenum, size);
        }
        if (musicband != null) {
            return new AjaxResponseBody("200", "success", musicband);
        }
        return new AjaxResponseBody("233", "找不到资源", null);

    }

    @GetMapping("/test/{id}")
    public AjaxResponseBody test2(@PathVariable Long id) {
        AjaxResponseBody Res = new AjaxResponseBody();
        Music music = new Music();
        music.setMusicid(id);
        List<Music> list = MService.getMusicList(music);
        Res.setStatus("200");
        Res.setMsg("请求成功");
        Res.setData(list);
        return Res;
    }

    @GetMapping("/api/music/{musicid}")
    public AjaxResponseBody getMusicInfo(@PathVariable Long musicid) {
        Music music = MService.getMusicByid(musicid);
        if (music != null) {
            return new AjaxResponseBody("200", "success", music);
        } else {
            return new AjaxResponseBody("204", "找不到资源", null);
        }

    }

    @GetMapping("/api/recommend/music")
    public AjaxResponseBody getRecommendMusic(@RequestParam(name = "musicpreference", required = false) String musicpreference) {
        List<Music> recommendMusic = MService.getUserRecommendMusic(musicpreference, 9);
        if (recommendMusic != null) {
            return new AjaxResponseBody("200", "success", recommendMusic);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/search/music")
    public AjaxResponseBody searchMusic(@RequestParam(name = "key", required = false) String key, @RequestParam(name = "pagenum") Integer pagenum, @RequestParam(name = "size") Integer size) {
        List<Music> result = MService.searchPage(key, pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/search/music/pagecount")
    public AjaxResponseBody CountSearchMusic(@RequestParam(name = "key", required = false) String key) {
        Long result = MService.CountSearchPage(key);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/singer/album")
    public AjaxResponseBody getAlbum(@RequestParam(name = "name") String name) {
        List<HashMap<String, Object>> maps = MService.getAlbumByname(name);
        if (maps != null) {
            return new AjaxResponseBody("200", "success", maps);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/singer/album/music")
    public AjaxResponseBody getMusicByAlbum(@RequestParam(name = "album") String album) {
        List<Music> result = MService.getMusicFromAlbum(album);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/music")
    public AjaxResponseBody getAllMusic(@RequestParam(name = "pagenum") Long pagenum, @RequestParam("size") Long size) {
        List<Music> result = MService.getMusicPage(pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/music/count")
    public AjaxResponseBody getAllMusicCount() {
        Long result = MService.getMusicCount();
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @DeleteMapping("/api/backstage/batch/data")
    public AjaxResponseBody BatchDelete(@RequestParam("list") List<Long> list, @RequestParam("type") String type) {
        Long result = null;
        if (type.equals("singer")) {
            result = singerService.Batchdeletion(list);
        } else if (type.equals("music")) {
            result = MService.Batchdeletion(list);
        } else if (type.equals("sheet")) {
            result = songSheetService.Batchdeletion(list);
        } else if (type.equals("user")) {
            result = userService.Batchdeletion(list);
        }
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @PostMapping("/api/backstage/music")
    public AjaxResponseBody UpdateData(@RequestBody Music music) {
        int num = MService.updateAllColum(music);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", num);
        }
        return new AjaxResponseBody("233", "未修改任何数据", num);
    }

}
