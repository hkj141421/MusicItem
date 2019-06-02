package com.example.demo.Service;

import com.example.demo.Entity.UserCollection;

import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
public interface CollectionService {
    /**
     * @param userid 用户编号
     * @return 该用户的所有收藏表信息
     */
    List<UserCollection> getUserAllCollectionList(Long userid);

    /**
     * @param userid  要查询的收藏表的所属用户
     * @param musicid 要查询的收藏表名
     * @return
     */
    List<UserCollection> getUserSingleCollection(Long userid, Long musicid);

    /**
     * @param userCollection 要插入的收藏表信息
     * @return 受影响的行数
     */
    int InsertCollectionRecord(UserCollection userCollection);

    /**
     * @param userid    删除记录的所属用户
     * @param TableName 删除记录的所属表
     * @param musicid   删除记录的音乐编号
     * @return
     */
    int DeleteCollectionRecord(Long userid, String TableName, Long musicid);

    /**
     * @param userid  删除表的所属用户
     * @param musicid 删除表的表名
     * @return
     */
    int DeleteCollectionTable(Long userid, Long musicid);

    /**
     * @param userCollection 要修改的收藏表信息
     * @return 受影响的行数
     */
    int UpdateCollectionRecord(UserCollection userCollection);
}
