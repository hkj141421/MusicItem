package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.Music;
import com.example.demo.Entity.SecurityUser;
import com.example.demo.Entity.User;
import com.example.demo.Service.MusicService;
import com.example.demo.Service.UserService;
import com.example.demo.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by forget on 2018/11/15.
 */
@RestController
public class UploadController {
    @Autowired
    MusicService musicService;
    @Autowired
    UserService userService;

    /**
     * @param request
     * @param response
     * @param file
     * @throws IOException
     */
    @PutMapping("/api/user/file")
    public AjaxResponseBody uploadfile(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException {
        //上传文件
        try {
            String last = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String filename = System.currentTimeMillis() + last;
            String path = "E:\\ItemStatic\\upload\\" + filename;

            if (!file.isEmpty() && file.getSize() < 50 * 1024 * 1024) {
                File up_file = new File(path);
                file.transferTo(up_file);
                String url = "http://localhost:1122/upload/" + filename;
                return new AjaxResponseBody("200", "success", path);
            }
        } catch (IOException e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "上传文件失败", null);
    }

    @PutMapping("/api/user/info/images")
    public AjaxResponseBody uploadHeadImg(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException {
        //上传用户头像
        try {
            String username = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
            String last = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String path = "E:\\ItemStatic\\upload\\user\\Avatar\\" + username + last;

            if (!file.isEmpty() && file.getSize() < 2 * 1024 * 1024) {
                File up_file = new File(path);
                file.transferTo(up_file);
                String url = "http://localhost:1122/upload/user/Avatar/" + username + last;
                User user = new User();
                user.setUserid(userid);
                user.setUserheadimg(url);
                this.userService.UpdateUser(user);
                return new AjaxResponseBody("200", "success", url);
            }
        } catch (IOException e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "文件超过2MB", null);
    }

    @GetMapping("/api/user/music/{id:[1-9][0-9][0-9]*}")
    public void DownloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable Long musicid) throws Exception {
        //下载文件
        Music music = new Music();
        String path = null;
        music.setMusicid(musicid);
        List<Music> mlist = musicService.getMusicList(music);
        if (mlist != null) {
            path = mlist.get(0).getMusicaddress();
            response.reset();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + mlist.get(0).getMusicname());
            BufferedInputStream in = FileUtil.getFileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.write("fail");
            out.close();
        }
    }

    @PutMapping("/api/user/track/images")
    public AjaxResponseBody uploadTrackImg(HttpServletRequest request, HttpServletResponse response, MultipartFile images) throws IOException {
        //上传动态图片
        try {
            String imagesPath = "";
            int num = 0;
            String last = images.getOriginalFilename().substring(images.getOriginalFilename().indexOf("."));
            Long time = System.currentTimeMillis();
            String path = "E:\\ItemStatic\\upload\\track\\images\\" + time + last;

            if (!images.isEmpty() && images.getSize() < 5 * 1024 * 1024) {
                File up_file = new File(path);
                images.transferTo(up_file);
                imagesPath = "http://localhost:1122/upload/track/images/" + time + last;
            }
            return new AjaxResponseBody("200", "success", imagesPath);
        } catch (IOException e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @DeleteMapping("/api/upload/file")
    public AjaxResponseBody deleteTrackImg(@RequestParam("imagespath") String imagespaths) throws IOException {
        //删除动态图片
        try {
            String filename = "";
            if (imagespaths.indexOf("upload") != -1) {
                filename = imagespaths.substring(imagespaths.indexOf("upload"));
            } else {
                filename = imagespaths.substring(imagespaths.indexOf("static"));
            }
            filename.replace("/", "\\");
            String path = "E:\\ItemStatic\\" + filename;
            File file = new File(path);
            if (file.isFile()) {
                if (file.delete()) {
                    return new AjaxResponseBody("200", "success", null);
                }

            } else {
                return new AjaxResponseBody("233", "文件不存在", null);
            }

            return new AjaxResponseBody("233", "删除文件失败", null);
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

}
