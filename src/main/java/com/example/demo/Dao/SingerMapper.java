package com.example.demo.Dao;

import com.example.demo.Entity.Singer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerMapper {
    int deleteByPrimaryKey(Long singerid);

    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Long singerid);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKeyWithBLOBs(Singer record);

    int updateByPrimaryKey(Singer record);

    int InsertNoexist(Singer singer);

    Singer getSinger(String singerid);

    int AddSinger(Singer singer);

    int DeleteSinger(Long SingerId);

    int UpdateSinger(Singer singer);

    List<Singer> getSingerList(Singer singer);

    List<Singer> getLikeSingerList(Singer singer);

    List<Singer> getRandomSingerList(Long size);

    List<Singer> searchSinger(@Param("key") String key, @Param("pagenum") Integer pagenum, @Param("size") Integer size);

    Long countSearchSinger(@Param("key") String key);

    List<Singer> getAllSinger(@Param("pagenum") Long pagenum, @Param("size") Long size);

    Long getAllSingerCount();

    Long Batchdeletion(@Param("array") List<Long> array);
}