package com.example.demo.Service;

import com.example.demo.Entity.Lrc;

/**
 * Created by forget on 2018/12/14.
 */
public interface LrcService {
    /**
     * 添加歌词信息
     *
     * @param lrc
     * @return
     */
    public int addLrc(Lrc lrc);

    /**
     * 删除歌词信息
     *
     * @param lrcid 删除条件
     * @return
     */
    public int deleteLrc(Long lrcid);

    /**
     * 修改歌词信息
     *
     * @param lrc 要修改的信息
     * @return
     */
    public int updateLrc(Lrc lrc);

    /**
     * 获取歌词信息
     *
     * @param lrc 查询条件
     * @return
     */
    public Lrc getLrc(Lrc lrc);

    Lrc getLrcByid(Long lrcid);
}
