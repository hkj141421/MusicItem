package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Service.CollectionService;
import com.example.demo.Service.MusicService;
import com.example.demo.Service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/3/9.
 */
@RestController
public class SheetController {
    @Autowired
    MusicService MService;
    @Autowired
    SongSheetService SSService;
    @Autowired
    CollectionService collectionService;

    @GetMapping("/api/songsheet/user/{id}")
    public AjaxResponseBody SongSheetController(@PathVariable("id") Long id, @RequestParam("username") String username) {
        //获取用户所有的歌单信息
        try {
            Map<String, List<SongSheet>> map = SSService.getUserSongSheet(id, username);
            return new AjaxResponseBody("200", "success", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @GetMapping("/api/user/creatsheet")
    public AjaxResponseBody getCreateSheet(@RequestParam("userid") Long userid, @RequestParam("username") String username) {
        //获取用户创建的歌单信息
        try {
            List<HashMap<String, Object>> list = SSService.getUserCreatSheet(userid, username);
            return new AjaxResponseBody("200", "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @GetMapping("/api/songsheet/{id}")
    public AjaxResponseBody getMuiscFormSheet(@PathVariable("id") Long id, @RequestParam("pagenumber") Long pagenumber, @RequestParam("size") Long size) {
        //获取一张歌单的所有歌曲信息
        try {
            List<Music> list = MService.getPageMusicBySheetId(id, pagenumber, size);
            return new AjaxResponseBody("200", "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @GetMapping("/api/songsheet/info/{id}")
    public AjaxResponseBody getsheetinfobySheetid(@PathVariable("id") Long id, @RequestParam(name = "userid", required = false) Long userid) {
        //获取一张歌单的歌单信息
        try {
            System.out.println("userid=" + userid);
            HashMap<String, Object> songSheet = SSService.getSheetByid(id, userid);
            if (songSheet != null) {
                return new AjaxResponseBody("200", "success", songSheet);
            } else {
                return new AjaxResponseBody("233", "资源不存在", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @PutMapping("/api/user/songsheet")
    public AjaxResponseBody AddMusic(@RequestBody SongSheet sheet) throws IOException {

        try { //用户创建一张歌单
            Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
            String username = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUsername();
            sheet.setProducer(username);
            if (sheet.getIntroduce() == null || sheet.getSongsheetname() == null || sheet.getType() == null) {
                return new AjaxResponseBody("235", "歌单标题，简介和标签为必填项", null);
            }
            Long id = SSService.addSongSheet(sheet);
            if (id != null) {
                HashMap<String, Object> songSheet = SSService.getSheetByid(id, null);
                collectionService.InsertCollectionRecord(new UserCollection(userid, sheet.getSheetid()));
                return new AjaxResponseBody("200", "success", songSheet);
            } else {
                return new AjaxResponseBody("234", "该歌单已存在，请勿重复创建", null);
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @PutMapping("/api/user/collection/sheet")
    public AjaxResponseBody CollectSongController(@RequestParam("sheetid") Long sheetid) {
        try { //用户收藏一张歌单
            User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            UserCollection userCollection = new UserCollection();
            userCollection.setUserid(user.getUserid());
            userCollection.setSheetid(sheetid);
            int flag = collectionService.InsertCollectionRecord(userCollection);

            if (flag == 1) {
                return new AjaxResponseBody("200", "success", null);
            } else {
                return new AjaxResponseBody("233", "fail", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseBody("233", "fail", null);
        }
    }

    @GetMapping("/api/recommend/sheet")
    public AjaxResponseBody getSheetBytype(@RequestParam(name = "type", required = false) String type, @RequestParam("pagenum") Integer pagenum, @RequestParam("size") Integer size) {
        //获取收藏次数最多的歌单
        List<HashMap<String, Object>> list;
        if (type == null) {
            list = SSService.getSheetByTime(pagenum, size);
        } else {
            list = SSService.getSheetByType(type, pagenum, size);
        }
        if (list != null) return new AjaxResponseBody("200", "success", list);
        else return new AjaxResponseBody("233", "获取推荐歌单失败", null);
    }

    @GetMapping("api/sheet/type")
    public AjaxResponseBody getPageSheet(@RequestParam("pagenum") Integer pagenum, @RequestParam("size") Integer size, @RequestParam(name = "type", required = false) String type) {
        List<SongSheet> list = null;
        try {
            if (type.equals("全部")) list = SSService.getAllSongSheet(pagenum, size);
            else list = SSService.getSongSheetBytype(pagenum, size, type);
            if (list != null) return new AjaxResponseBody("200", "success", list);
            else return new AjaxResponseBody("233", "获取失败", null);
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @GetMapping("api/sheet/type/count")
    public AjaxResponseBody getCountSheet(@RequestParam(name = "type", required = false) String type) {
        try {
            if (type.equals("全部")) type = "";
            Long count = SSService.CounTypeSheet(type);
            return new AjaxResponseBody("200", "success", count);
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @GetMapping("/api/search/sheet")
    public AjaxResponseBody searchSheet(@RequestParam(name = "key", required = false) String key, @RequestParam(name = "pagenum") Integer pagenum, @RequestParam(name = "size") Integer size) {
        List<SongSheet> result = SSService.searchPage(key, pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/search/sheet/pagecount")
    public AjaxResponseBody CountSearchSheet(@RequestParam(name = "key", required = false) String key) {
        Long result = SSService.CountSearchPage(key);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/sheet")
    public AjaxResponseBody getAllSheet(@RequestParam(name = "pagenum") Long pagenum, @RequestParam("size") Long size) {
        List<SongSheet> result = SSService.getSheetPage(pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/sheet/count")
    public AjaxResponseBody getAllSheetCount() {
        Long result = SSService.getSheetCount();
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @PostMapping("/api/backstage/sheet")
    public AjaxResponseBody UpdateData(@RequestBody SongSheet songSheet) {
        int num = SSService.updateAllColum(songSheet);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", num);
        }
        return new AjaxResponseBody("233", "未修改任何数据", num);
    }

}
