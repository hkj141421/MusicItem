package com.example.demo.ServiceImp;

import com.example.demo.Dao.FansMapper;
import com.example.demo.Entity.Fans;
import com.example.demo.Service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by forget on 2018/12/14.
 */
@Service
public class FansServiceImpl implements FansService {
    @Autowired
    FansMapper fmapper;

    @Override
    public int addFans(Fans fans) {
        return fmapper.addFansInfo(fans);
    }

    @Override
    public int removeFans(Fans fans) {
        return fmapper.deleteFansInfo(fans);
    }

    @Override
    public int countFans(Long userid) {
        return fmapper.CountFans(userid);
    }

    @Override
    public boolean isFans(Fans fans) {
        if (fmapper.IsFans(fans) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
