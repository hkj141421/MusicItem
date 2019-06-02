package com.example.demo.Controller;


import com.example.demo.Entity.*;
import com.example.demo.Service.FansService;
import com.example.demo.Service.SheetCommentService;
import com.example.demo.Service.TrackCommentService;
import com.example.demo.Service.TrackService;
import com.example.demo.ServiceImp.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/3/28.
 */
@RestController
public class SocialController {
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private TrackCommentService trackCommentService;
    @Autowired
    private SheetCommentService sheetCommentService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private FansService fansService;

    /**
     * @param content 评论的内容
     * @param muiscid 评论的音乐ID
     * @return 处理结果
     */
    @PutMapping("/api/user/music/comment/{muiscid}")
    public AjaxResponseBody commentMusic(@Valid @NotEmpty @RequestParam String content, @PathVariable Long muiscid) {
        //添加音乐评论
        try {
            Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
            Comment comment = new Comment();
            comment.setUserid(userid);
            comment.setContent(content);
            comment.setDate(new Timestamp(System.currentTimeMillis()));
            comment.setFabulous(0L);
            comment.setMusicid(muiscid);

            int num = commentService.addComment(comment);
            if (num == 1) {

                comment.setFabulous(0L);
                return new AjaxResponseBody("200", "success", comment);
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "fail", null);
    }

    @PutMapping("/api/user/music/reply/{commentid}")
    public AjaxResponseBody replyMusicComment(@Valid @NotEmpty @RequestParam String content, @PathVariable Long commentid, @RequestParam("musicid") Long musicid) {
        //添加音乐评论的回复
        try {
            Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
            Comment comment = new Comment();
            comment.setUserid(userid);
            comment.setContent(content);
            comment.setDate(new Timestamp(System.currentTimeMillis()));
            comment.setReplycommentid(commentid);
            comment.setMusicid(musicid);

            int num = commentService.addReplyComment(comment);
            if (num == 1) {
                comment.setFabulous(0L);
                return new AjaxResponseBody("200", "success", comment);
            } else {
                return new AjaxResponseBody("233", "添加评论出错", null);
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @PostMapping("/api/user/music/comment/fabulous")
    public AjaxResponseBody likemuiscComment(@RequestParam("fabulous") Long fabulous, @RequestParam("commentid") Long commentid) {
        int i = commentService.updateFabulous(fabulous, commentid);
        if (i == 1) {
            return new AjaxResponseBody("200", "SUCCESS", null);
        }
        return new AjaxResponseBody("233", "音乐评论点赞失败", null);
    }

    @PutMapping("/api/user/track/comment/{trackid}")
    public AjaxResponseBody commentTrack(@RequestParam("content") String content, @PathVariable("trackid") Long trackid) {
        //添加动态评论
        Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
        TrackComment trackComment = new TrackComment();
        trackComment.setContent(content);
        trackComment.setTrackid(trackid);
        trackComment.setDate(new Timestamp(System.currentTimeMillis()));
        trackComment.setUserid(userid);
        int i = trackCommentService.addTrackComment(trackComment);
        if (i == 1) {
            trackComment.setFabulous(0L);
            return new AjaxResponseBody("200", "success", trackComment);
        } else {
            return new AjaxResponseBody("233", "添加评论失败", null);
        }

    }

    @PutMapping("/api/user/track/reply/{commentid}")
    public AjaxResponseBody replyTrackComment(@RequestParam("content") String content, @PathVariable("commentid") Long commentid, @RequestParam("trackid") Long trackid) {
        //添加动态评论的回复
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        TrackComment trackComment = new TrackComment();
        trackComment.setContent(content);
        trackComment.setTrackid(trackid);
        trackComment.setDate(new Timestamp(System.currentTimeMillis()));
        trackComment.setUserid(user.getUserid());
        trackComment.setReplycommentid(commentid);
        int i = trackCommentService.addReplyTrackComment(trackComment);
        if (i == 1) {
            trackComment.setFabulous(0L);
            return new AjaxResponseBody("200", "success", trackComment);
        } else {
            return new AjaxResponseBody("233", "添加评论失败", null);
        }

    }

    @PostMapping("/api/user/track/comment/fabulous")
    public AjaxResponseBody liketrackComment(@RequestParam("fabulous") Long fabulous, @RequestParam("commentid") Long commentid) {
        int i = trackCommentService.updateFabulous(fabulous, commentid);
        if (i == 1) {
            return new AjaxResponseBody("200", "SUCCESS", null);
        }
        return new AjaxResponseBody("233", "动态评论点赞失败", null);
    }

    @PutMapping("/api/user/songsheet/reply/{commentid}")
    public AjaxResponseBody commentSongSheet(@RequestParam("content") String content, @PathVariable("commentid") Long commentid, @RequestParam("sheetid") Long sheetid) {
        //添加歌单评论的回复
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        SongSheetComment songSheetComment = new SongSheetComment();
        songSheetComment.setContent(content);
        songSheetComment.setDate(new Timestamp(System.currentTimeMillis()));
        songSheetComment.setSheetid(sheetid);
        songSheetComment.setUserid(user.getUserid());
        songSheetComment.setReplycommentid(commentid);
        int i = sheetCommentService.AddReply(songSheetComment);
        if (i == 1) {
            songSheetComment.setFabulous(0L);
            return new AjaxResponseBody("200", "success", songSheetComment);
        } else {
            return new AjaxResponseBody("233", "添加评论失败", null);
        }
    }


    @PutMapping("/api/user/songsheet/comment/{sheetid}")
    public AjaxResponseBody replySheetMusic(@Valid @NotEmpty @RequestParam("content") String content, @PathVariable Long sheetid) {
        //添加歌单评论
        try {
            SongSheetComment comment = new SongSheetComment();
            User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            comment.setUserid(user.getUserid());
            comment.setContent(content);
            comment.setDate(new Timestamp(System.currentTimeMillis()));
            comment.setSheetid(sheetid);
            int num = sheetCommentService.AddComment(comment);
            if (num == 1) {
                comment.setFabulous(0L);
                return new AjaxResponseBody("200", "success", comment);
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "fail", null);
    }

    @PostMapping("/api/user/songsheet/comment/fabulous")
    public AjaxResponseBody likesheetComment(@RequestParam("fabulous") Long fabulous, @RequestParam("commentid") Long commentid) {
        int i = sheetCommentService.UpdateFabulous(fabulous, commentid);
        if (i == 1) {
            return new AjaxResponseBody("200", "SUCCESS", null);
        }
        return new AjaxResponseBody("233", "歌单评论点赞失败", null);
    }

//    @GetMapping("/api/user/music/comment/{id:[1-9][0-9][0-9]*}")
//    public AjaxResponseBody getMusicCommentById(@PathVariable Long musicid){
//        try {
//            JSONArray array=new JSONArray();
//            JSONObject object=new JSONObject();
//            List<Comment> commentList=commentService.getCommentByMusicId(musicid,0,10);
//            List<Comment> replyList;
//            Comment comment=new Comment();
//            for (Comment item: commentList) {
//                comment.setReplycommentid(item.getCommentid());
//                replyList=null;
//                replyList=commentService.getComment(comment);
//                if(replyList!=null){
//                    object.put("reply",replyList);
//                }
//                else {
//                    object.put("reply",null);
//                }
//                object.put("comment",item);
//                array.add(object);
//            }
//            if(commentList!=null){
//                return new AjaxResponseBody("200","success",array);
//            }
//                return new AjaxResponseBody("233","找不到音乐对应的评论",null);
//        } catch (Exception e) {
//            return new AjaxResponseBody("500",e.getMessage(),null);
//        }
//    }

    @GetMapping("/api/songsheet/comment/{sheetid}")
    public AjaxResponseBody GetSheetComment(@PathVariable("sheetid") Long sheetid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得歌单评论
        List<Map<String, Object>> list = sheetCommentService.getCommentBySheetid(sheetid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @GetMapping("/api/songsheet/comment/count/{sheetid}")
    public AjaxResponseBody GetSheetCommentCount(@PathVariable("sheetid") Long sheetid) {
        //获得歌单评论的数量
        Long count = sheetCommentService.countComment(sheetid);
        return new AjaxResponseBody("200", "success", count);
    }

    @GetMapping("/api/music/comment/count/{musicid}")
    public AjaxResponseBody GetMusicCommentCount(@PathVariable("musicid") Long musicid) {
        //获得音乐评论的数量
        Long count = commentService.countComment(musicid);
        return new AjaxResponseBody("200", "success", count);
    }

    @GetMapping("/api/track/comment/count/{trackid}")
    public AjaxResponseBody GetTrackCommentCount(@PathVariable("trackid") Long trackid) {
        //获得动态评论的数量
        Long count = trackCommentService.countComment(trackid);
        return new AjaxResponseBody("200", "success", count);
    }

    @GetMapping("/api/songsheet/reply/{commentid}")
    public AjaxResponseBody GetSheetReply(@PathVariable("commentid") Long commentid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得歌单评论的回复
        List<Map<String, Object>> list = sheetCommentService.getReplyFromSheet(commentid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @GetMapping("/api/music/reply/{commentid}")
    public AjaxResponseBody GetMusicReply(@PathVariable("commentid") Long commentid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得音乐评论的回复
        List<Map<String, Object>> list = commentService.getReplyFromMusic(commentid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @GetMapping("/api/music/comment/{musicid}")
    public AjaxResponseBody GetMusicComment(@PathVariable("musicid") Long musicid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得音乐评论
        List<Map<String, Object>> list = commentService.getCommentByMusicid(musicid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @GetMapping("/api/track/comment/{trackid}")
    public AjaxResponseBody GetTrackComment(@PathVariable("trackid") Long trackid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得动态评论
        List<Map<String, Object>> list = trackCommentService.getCommentByTrackid(trackid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @GetMapping("/api/track/reply/{commentid}")
    public AjaxResponseBody GetTrackReply(@PathVariable("commentid") Long commentid, @RequestParam("pagenumber") Integer pagenumber, @RequestParam("size") Integer size) {
        //获得动态评论的回复
        List<Map<String, Object>> list = trackCommentService.getReplyFromTrack(commentid, pagenumber, size);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @PutMapping("/api/user/track")
    public AjaxResponseBody AddTrack(@RequestBody Track track) {
        //添加动态
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        track.setDate(new Date(System.currentTimeMillis()));
        track.setUserid(user.getUserid());
        System.out.println(track.getDate());
        int num = trackService.addTrack(track);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", track);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }

    }

    @PutMapping("/api/user/fans")
    public AjaxResponseBody AddFans(@RequestParam("likeuserid") Long likeuserid) {
        //粉丝关注
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Fans fans = new Fans();
        fans.setUserid(user.getUserid());
        fans.setLikeuserid(likeuserid);
        int num = fansService.addFans(fans);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }
    }

    @DeleteMapping("/api/user/fans")
    public AjaxResponseBody DeleteFans(@RequestParam("likeuserid") Long likeuserid) {
        //粉丝取消关注
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Fans fans = new Fans();
        fans.setUserid(user.getUserid());
        fans.setLikeuserid(likeuserid);
        int num = fansService.removeFans(fans);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "找不到资源", null);
        }
    }

    @GetMapping("/api/track")
    public AjaxResponseBody LoadTrack(@RequestParam("pagenum") Long pagenum) {   //分页获取动态
        Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
        List<HashMap<String, Object>> list = trackService.loadTrack(userid, pagenum, 5L, 5L);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        }
        return new AjaxResponseBody("233", "加载失败", null);
    }

    @GetMapping("/api/personal/track")
    public AjaxResponseBody LoadTrack(@RequestParam("pagenum") Long pagenum, @RequestParam("userid") Long userid) {   //分页获取动态
        List<Track> list = trackService.getTrackListById(userid, pagenum, 5L);
        if (list != null) {
            return new AjaxResponseBody("200", "success", list);
        }
        return new AjaxResponseBody("233", "加载失败", null);
    }

    @PostMapping("/api/track/fabulous")
    public AjaxResponseBody updatetrackFabulous(@RequestParam("fabulous") Long fabulous, @RequestParam("trackid") Long trackid) {   //修改动态赞数
        Track track = new Track();
        track.setFabulous(fabulous);
        track.setTrackid(trackid);
        int num = trackService.updateTrackFabulous(track);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", null);
        }
        return new AjaxResponseBody("233", "加载失败", null);
    }

    @GetMapping("/api/user/social/data")
    public AjaxResponseBody getsocialData(@RequestParam("userid") Long userid) {   //获取用户的粉丝数，动态数，关注数
        try {
            HashMap<String, String> res = trackService.CountSocialData(userid);
            if (res != null) {
                return new AjaxResponseBody("200", "success", res);
            }
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

}
