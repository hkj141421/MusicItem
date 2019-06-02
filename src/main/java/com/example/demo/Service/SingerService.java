package com.example.demo.Service;

import com.example.demo.Entity.Singer;

import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
public interface SingerService {
    public List<Singer> getSingerList(Singer singer);

    public List<Singer> getLikeSingerList(Singer singer);

    public Integer InsertSinger(Singer singer);

    public Integer UpdateSinger(Singer singer);

    public Integer DeleteSinger(Long singerid);

    List<Singer> getRandomSingerList(Long size);

    List<Singer> searchPage(String key, Integer pagenum, Integer size);

    Long CountSearchPage(String key);

    Singer getSingerByid(Long singerid);

    List<Singer> getSingerpage(Long pagenum, Long size);

    Long getSingerCount();

    Long Batchdeletion(List<Long> array);

    int updateAllColum(Singer singer);
}
