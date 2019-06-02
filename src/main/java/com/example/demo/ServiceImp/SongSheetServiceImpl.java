package com.example.demo.ServiceImp;

import com.example.demo.Dao.SongSheetInfoMapper;
import com.example.demo.Dao.SongSheetMapper;
import com.example.demo.Dao.UserCollectionMapper;
import com.example.demo.Entity.SongSheet;
import com.example.demo.Entity.SongSheetInfoKey;
import com.example.demo.Entity.UserCollection;
import com.example.demo.Service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2018/12/14.
 */
@Service
public class SongSheetServiceImpl implements SongSheetService {
    @Autowired
    SongSheetMapper ssmapper;
    @Autowired
    SongSheetInfoMapper songSheetInfoMapper;
    @Autowired
    UserCollectionMapper userCollectionMapper;

    @Override
    public int addSongSheetList(List<SongSheet> SSlist) {
        return 0;
    }

    @Override
    public int CreateSongSheet(SongSheet songSheet) {
        return 0;
    }

    @Override
    public Long addSongSheet(SongSheet sheet) {
        ssmapper.InsertNoexist(sheet);
        return sheet.getSheetid();
    }

    @Override
    public int deleteSongSheet(Long SheetId) {
        return ssmapper.deleteSongSheet(SheetId);
    }


    @Override
    public int UpdateSongSheet(SongSheet SS) {
        return ssmapper.updateSongSheetInfo(SS);
    }

    @Override
    public List<SongSheet> getSongSheet(SongSheet SS) {
        return ssmapper.getSongSheetByCondition(SS);
    }

    @Override
    public List<SongSheet> getAllSongSheet(Integer start, Integer size) {
        return ssmapper.getAllSongSheetInfo((start - 1) * size, size);
    }

    @Override
    public List<SongSheet> getSongSheetBytype(Integer start, Integer size, String type) {
        return ssmapper.selectPageSheet((start - 1) * size, size, "%" + type + "%");
    }

    @Override
    public Map<String, List<SongSheet>> getUserSongSheet(Long id, String name) {
        Map<String, List<SongSheet>> map = new HashMap<>();
        map.put("collect", ssmapper.getUserSongsheetinfoByCollect(id, name));
        map.put("self", ssmapper.getUserSongsheetinfoByself(id, name));
        return map;
    }

    @Override
    public List<HashMap<String, Object>> getUserCreatSheet(Long id, String name) {
        return ssmapper.selectUserCreateSheet(id, name);
    }

    @Override
    public HashMap<String, Object> getSheetByid(Long sheetid, Long userid) {
        HashMap<String, Object> map = ssmapper.getSheetById(sheetid);
        map.put("collectcount", userCollectionMapper.getCollectCountBysheetid(sheetid));
        if (userid != null) {
            UserCollection userCollection = userCollectionMapper.getCollection(new UserCollection(userid, sheetid));
            if (userCollection != null) map.put("collectstatus", true);
            else map.put("collectstatus", false);
        } else {
            map.put("collectstatus", false);
        }
        return map;
    }

    @Override
    public Long CounTypeSheet(String type) {
        return ssmapper.selectCountSheet("%" + type + "%");
    }

    /**
     * 添加音乐到歌单
     *
     * @param songSheetInfoKey
     * @return
     */
    @Override
    public int AddMusicToSheet(SongSheetInfoKey songSheetInfoKey) {
        return songSheetInfoMapper.insert(songSheetInfoKey);
    }

    @Override
    public List<HashMap<String, Object>> getSheetByType(String type, Integer pagenum, Integer size) {
        return ssmapper.selectSheetByType("%" + type + "%", (pagenum - 1) * size, size);
    }

    @Override
    public List<HashMap<String, Object>> getSheetByTime(Integer pagenum, Integer size) {
        return ssmapper.selectSheetByTime((pagenum - 1) * size, size);
    }

    @Override
    public List<SongSheet> searchPage(String key, Integer pagenum, Integer size) {
        return ssmapper.searchSheet("%" + key + "%", (pagenum - 1) * size, size);
    }

    @Override
    public Long CountSearchPage(String key) {
        return ssmapper.countSearchSheet("%" + key + "%");
    }

    @Override
    public List<SongSheet> getSheetPage(Long pagenum, Long size) {
        return ssmapper.getAllSheet((pagenum - 1) * size, size);
    }

    @Override
    public Long getSheetCount() {
        return ssmapper.getAllSheetCount();
    }

    @Override
    public Long Batchdeletion(List<Long> array) {
        if (array.size() == 0) return 0L;
        return ssmapper.Batchdeletion(array);
    }

    @Override
    public int updateAllColum(SongSheet songSheet) {
        return ssmapper.updateByPrimaryKeySelective(songSheet);
    }

}
