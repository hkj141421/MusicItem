package com.example.demo.Dao;

import com.example.demo.Entity.SongSheetComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/5/10.
 */
@Repository
public interface SheetConmentMapper {
    int addCommentInfo(SongSheetComment songSheetComment);

    int addReplyCommentInfo(SongSheetComment songSheetComment);

    int deleteCommentInfo(Long commentid);

    int updateCommentInfo(SongSheetComment songSheetComment);

    List<Map<String, Object>> selectCommentBySheetidFromSheet(@Param("sheetid") Long sheetid, @Param("start") Integer start, @Param("size") Integer size);

    List<Map<String, Object>> selectReplyByCommentidFromSheet(@Param("commentid") Long commentid, @Param("start") Integer start, @Param("size") Integer size);

    Long selectCommentCountBySheetidFromSheet(@Param("sheetid") Long sheetid);
}
