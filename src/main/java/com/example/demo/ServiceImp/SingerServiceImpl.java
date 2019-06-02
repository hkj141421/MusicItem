package com.example.demo.ServiceImp;

import com.example.demo.Dao.SingerMapper;
import com.example.demo.Entity.Singer;
import com.example.demo.Service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapper smapper;

    @Override
    public List<Singer> getSingerList(Singer singer) {
        return smapper.getSingerList(singer);
    }

    @Override
    public List<Singer> getLikeSingerList(Singer singer) {
        return smapper.getLikeSingerList(singer);
    }

    @Override
    public Integer InsertSinger(Singer singer) {
        return smapper.AddSinger(singer);
    }

    @Override
    public Integer UpdateSinger(Singer singer) {
        return smapper.UpdateSinger(singer);
    }

    @Override
    public Integer DeleteSinger(Long singerid) {
        return smapper.DeleteSinger(singerid);
    }

    @Override
    public List<Singer> getRandomSingerList(Long size) {
        return smapper.getRandomSingerList(size);
    }

    @Override
    public List<Singer> searchPage(String key, Integer pagenum, Integer size) {
        return smapper.searchSinger("%" + key + "%", (pagenum - 1) * size, size);
    }

    @Override
    public Long CountSearchPage(String key) {
        return smapper.countSearchSinger("%" + key + "%");
    }

    @Override
    public Singer getSingerByid(Long singerid) {
        return smapper.getSinger(singerid + "");
    }

    @Override
    public List<Singer> getSingerpage(Long pagenum, Long size) {
        return smapper.getAllSinger((pagenum - 1) * size, size);
    }

    @Override
    public Long getSingerCount() {
        return smapper.getAllSingerCount();
    }

    @Override
    public Long Batchdeletion(List<Long> array) {
        if (array.size() == 0) return 0L;
        return smapper.Batchdeletion(array);
    }

    @Override
    public int updateAllColum(Singer singer) {
        return smapper.updateByPrimaryKeySelective(singer);
    }
}
