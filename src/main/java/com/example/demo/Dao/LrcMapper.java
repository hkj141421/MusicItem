package com.example.demo.Dao;

import com.example.demo.Entity.Lrc;
import org.springframework.stereotype.Repository;

@Repository
public interface LrcMapper {
    int deleteByPrimaryKey(Long lrcid);

    int insert(Lrc record);

    int insertSelective(Lrc record);

    Lrc selectByPrimaryKey(Long lrcid);

    int updateByPrimaryKeySelective(Lrc record);

    int updateByPrimaryKeyWithBLOBs(Lrc record);

    int updateByPrimaryKey(Lrc record);

    Lrc selectByname(String LrcName);

    Lrc getLrctInfo(Long LrcId);

    int addLrcInfo(Lrc lrc);

    int deleteLrcInfo(Long LrcId);

    int updateLrctInfo(Lrc lrc);

}