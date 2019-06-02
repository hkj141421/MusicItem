package com.example.demo.Service;

import com.example.demo.Entity.Fans;

/**
 * Created by forget on 2018/12/14.
 */
public interface FansService {
    public int addFans(Fans fans);

    public int removeFans(Fans fans);

    public int countFans(Long userid);

    public boolean isFans(Fans fans);
}
