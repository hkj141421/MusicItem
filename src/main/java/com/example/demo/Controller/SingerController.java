package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.Singer;
import com.example.demo.Service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by forget on 2019/5/24.
 */
@RestController
public class SingerController {
    @Autowired
    SingerService singerService;

    @GetMapping("api/singer/random")
    public AjaxResponseBody getRandomSinger(@RequestParam("size") Long size) {
        List<Singer> singerList = singerService.getRandomSingerList(size);
        if (singerList != null) {
            return new AjaxResponseBody("200", "success", singerList);
        } else {
            return new AjaxResponseBody("233", "获取失败", null);
        }
    }

    @GetMapping("/api/search/singer")
    public AjaxResponseBody searchSinger(@RequestParam(name = "key", required = false) String key, @RequestParam(name = "pagenum") Integer pagenum, @RequestParam(name = "size") Integer size) {
        List<Singer> result = singerService.searchPage(key, pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/search/singer/pagecount")
    public AjaxResponseBody CountSearchSinger(@RequestParam(name = "key", required = false) String key) {
        Long result = singerService.CountSearchPage(key);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/singer")
    public AjaxResponseBody getSingerById(@RequestParam(name = "id") Long id) {
        Singer result = singerService.getSingerByid(id);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/singer")
    public AjaxResponseBody getAllSinger(@RequestParam(name = "pagenum") Long pagenum, @RequestParam("size") Long size) {
        List<Singer> result = singerService.getSingerpage(pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/singer/count")
    public AjaxResponseBody getAllSingerCount() {
        Long result = singerService.getSingerCount();
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @PostMapping("/api/backstage/singer")
    public AjaxResponseBody UpdateData(@RequestBody Singer singer) {
        int num = singerService.updateAllColum(singer);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", num);
        }
        return new AjaxResponseBody("233", "未修改任何数据", num);
    }
}
