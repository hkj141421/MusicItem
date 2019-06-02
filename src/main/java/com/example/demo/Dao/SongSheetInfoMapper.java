package com.example.demo.Dao;

import com.example.demo.Entity.SongSheetInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SongSheetInfoMapper {

    int deleteByPrimaryKey(SongSheetInfoKey key);

    int insert(SongSheetInfoKey record);

    int insertSelective(SongSheetInfoKey record);
}