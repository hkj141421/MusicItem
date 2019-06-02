package com.example.demo.Service;

import com.example.demo.Entity.SongSheetComment;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/5/10.
 */
public interface SheetCommentService {
    int AddComment(SongSheetComment songSheetComment);

    int AddReply(SongSheetComment songSheetComment);

    int deleteComment(Long commentid);

    int UpdateComment(SongSheetComment songSheetComment);

    int UpdateFabulous(Long fabulous, Long commentid);

    List<Map<String, Object>> getCommentBySheetid(Long sheetid, Integer start, Integer size);

    List<Map<String, Object>> getReplyFromSheet(Long commentid, Integer start, Integer size);

    Long countComment(Long sheetid);
}
