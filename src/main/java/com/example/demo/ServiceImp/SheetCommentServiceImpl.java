package com.example.demo.ServiceImp;

import com.example.demo.Dao.SheetConmentMapper;
import com.example.demo.Entity.SongSheetComment;
import com.example.demo.Service.SheetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/5/10.
 */
@Service
public class SheetCommentServiceImpl implements SheetCommentService {
    @Autowired
    SheetConmentMapper sheetConmentMapper;

    @Override
    public int AddComment(SongSheetComment songSheetComment) {
        return sheetConmentMapper.addCommentInfo(songSheetComment);
    }

    @Override
    public int AddReply(SongSheetComment songSheetComment) {
        return sheetConmentMapper.addReplyCommentInfo(songSheetComment);
    }

    @Override
    public int deleteComment(Long commentid) {
        return sheetConmentMapper.deleteCommentInfo(commentid);
    }

    @Override
    public int UpdateComment(SongSheetComment songSheetComment) {
        return sheetConmentMapper.updateCommentInfo(songSheetComment);
    }

    @Override
    public int UpdateFabulous(Long fabulous, Long commentid) {
        SongSheetComment comment = new SongSheetComment();
        comment.setFabulous(fabulous);
        comment.setCommentid(commentid);
        return this.UpdateComment(comment);
    }

    @Override
    public List<Map<String, Object>> getCommentBySheetid(Long sheetid, Integer start, Integer size) {
        return sheetConmentMapper.selectCommentBySheetidFromSheet(sheetid, (start - 1) * size, size);
    }

    @Override
    public List<Map<String, Object>> getReplyFromSheet(Long commentid, Integer start, Integer size) {
        return sheetConmentMapper.selectReplyByCommentidFromSheet(commentid, (start - 1) * size, size);
    }

    @Override
    public Long countComment(Long sheetid) {
        return sheetConmentMapper.selectCommentCountBySheetidFromSheet(sheetid);
    }
}
