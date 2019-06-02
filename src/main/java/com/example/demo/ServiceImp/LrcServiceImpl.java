package com.example.demo.ServiceImp;

import com.example.demo.Dao.LrcMapper;
import com.example.demo.Entity.Lrc;
import com.example.demo.Service.LrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by forget on 2018/12/14.
 */
@Service
public class LrcServiceImpl implements LrcService {
    @Autowired
    LrcMapper lmapper;

    @Override
    public int addLrc(Lrc lrc) {
        return lmapper.addLrcInfo(lrc);
    }

    @Override
    public int deleteLrc(Long lrcid) {
        return lmapper.deleteLrcInfo(lrcid);
    }

    @Override
    public int updateLrc(Lrc lrc) {
        return lmapper.updateLrctInfo(lrc);
    }

    @Override
    public Lrc getLrc(Lrc lrc) {
        return null;
    }

    @Override
    public Lrc getLrcByid(Long lrcid) {
        return lmapper.getLrctInfo(lrcid);
    }
}
