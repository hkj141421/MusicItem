package com.example.demo.Dao;

import com.example.demo.Entity.Fans;
import org.springframework.stereotype.Repository;

@Repository
public interface FansMapper {
    int insert(Fans record);

    int insertSelective(Fans record);

    int CountFans(Long userid);

    int IsFans(Fans fans);

    int addFansInfo(Fans fans);

    int deleteFansInfo(Fans fans);

}