package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.*;
import com.example.demo.Entity.*;
import com.example.demo.Service.LrcService;
import com.example.demo.ServiceImp.LrcServiceImpl;
import com.example.demo.ServiceImp.MusicServiceImpl;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.HttpUtil;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicDemoApplicationTests {

    @Autowired
    public Music music;
    @Autowired
    public Lrc lrc;
    @Autowired
    public LrcServiceImpl lrcService;
    @Autowired
    public MusicServiceImpl MService;
    @Autowired
    public SingerMapper singerMapper;
    @Autowired
    public LrcMapper lrcMapper;
    @Autowired
    public UserCollectionMapper collectionMapper;
    @Autowired
    public MusicMapper musicMapper;
    @Autowired
    public CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;
    @Autowired
    SongSheetMapper songSheetMapper;

    @Test
    public void contextLoads() throws Exception {
        String[] str = {"", ""};
    /*	for(int i=49;i<51;i++){
            writeData(i);
			System.out.println("已写入"+i+"张歌单");
		}*/
        //	writeData(40);

        //	parseTime(255732);
        //writeSingerBind();
//		for (int i=400;i<950;i++){
//			List<Music> list=musicMapper.LimitMusic(i*10,10);
//			for (Music music:list) {
//				String musicid=music.getMusicAddress();
//				musicid=musicid.substring(musicid.indexOf("=")+1,musicid.indexOf("&"));
//				updateMusic(Long.parseLong(musicid),music.getMusicId());
//			}
//			System.out.println("已扫描"+i*10+"首歌曲");
//
//		}
//		List<Map<String,Object>> list=commentMapper.selectReplyByCommentidFromSheet(1L);
//		for (Map<String,Object> map: list) {
//			for (String key:map.keySet()) {
//				System.out.print(key+"="+map.get(key)+",");
//			}
//			System.out.println();
//		}
//		VerificationCodeUtil.generateVerificationCode();
        updateMusicImg();
    }

    @Test
    public void updateMusicImg() throws Exception {
        int size = 50;
        int count = 0;
        int index = 44;
        List<Music> list = musicMapper.clearImg(index, size);
        list.get(1).getType().compareTo("1");
        while (true) {
            for (Music music : list) {

                String url = HttpUtil.getRedirectInfo(music.getMusicimg());
                if (url != null) {
                    count++;
                    musicMapper.updateMusicImg(url, music.getMusicid());
                }
            }
            index++;
            System.out.println("已处理" + (index) * size + "条数据,总共修改了" + count + "条数据");
            if (list.size() == 0) break;
            list = musicMapper.clearImg(((index * size) - count), size);
        }
    }

    @Test
    public void te() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://www.baidu.com/s?word=%E4%B8%AD%E8%BD%AF");
        HttpContext httpContext = new BasicHttpContext();
        HttpResponse response = httpClient.execute(get, httpContext);
        HttpEntity entity = response.getEntity();
    }

    @Test
    public void writeController() throws IOException {
        File file = new File("C:\\Users\\forget\\Desktop\\新建文本文档 (2).txt");
        Files.lines(Paths.get("C:\\Users\\forget\\Desktop\\新建文本文档 (2).txt"), Charset.defaultCharset()).forEach((f) -> System.out.println(f));
    }

    @Test
    public void getRedirectInfo() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("https://v1.itooi.cn/netease/pic?id=531295576");
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            HttpResponse response = httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
            HttpHost targetHost = (HttpHost) httpContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            //获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
            HttpUriRequest realRequest = (HttpUriRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
            String url = targetHost + realRequest.getURI().toString();
            if (response.getStatusLine().getStatusCode() == 404) {
                url = url.substring(0, url.length() - 14);
                System.out.println(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public static CloseableHttpResponse getJson(String url) throws Exception {
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        String resStr = null;
        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);
            //解析返结果
            HttpEntity entity = response.getEntity();
            if (response != null) {
                resStr = EntityUtils.toString(entity, "UTF-8");
            } else {
                System.out.println("响应内容不存在");
            }
            System.out.println(httpGet.getRequestLine());
            return response;
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public void test() {
        LrcService lrcService = new LrcServiceImpl();
        Lrc lrc = lrcMapper.getLrctInfo(47L);
        HashMap<String, List<String>> map = FileUtil.ParseLrc(lrc);
        List<String> timelist = map.get("time");
        List<String> textlist = map.get("text");
        for (String str : timelist) {
            System.out.println(str);
        }
        for (String str : textlist) {
            System.out.println(str);
        }
    }

    public void updateMusic(Long MusicId, Long id) {
        try {
            CloseableHttpResponse response = HttpUtil.getHttpEntity("https://v1.itooi.cn/netease/url?id=" + MusicId + "&quality=192000");
            HttpEntity httpEntity = response.getEntity();
            InputStream fileInputStream = httpEntity.getContent();
            byte[] buffer = new byte[10];
            fileInputStream.read(buffer);
        } catch (SocketException e) {
            musicMapper.updateMusic(0L, id);
        } catch (ConnectionClosedException e) {
            musicMapper.updateMusic(1L, id);
        } catch (IOException e) {
            System.out.println("发送错误的音乐Id" + id);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("发送错误的音乐Id" + id);
            e.printStackTrace();
        }
    }

    public void updatelrc() throws Exception {
        List<Music> list = null;
        //musicMapper.lesslrc(0,30);
        int i = 0;
        int cout = 0;

        for (Music music : list) {
            String musicid = music.getMusicaddress();
            musicid = musicid.substring(musicid.indexOf("=") + 1, musicid.indexOf("&"));
            //updateMusic(Long.getLong(musicid));
            String json = HttpUtil.getJson("https://v1.itooi.cn/netease/lrc?id=" + musicid);
            String str = json.substring(0, 1);
            if (!str.equals("{")) {
                Lrc lrc = new Lrc();
                lrc.setLrcid(music.getLyricid());
                lrc.setContent(json);
                lrcMapper.updateLrctInfo(lrc);
                cout++;
            }
        }
        System.out.println("已完成" + cout + "条歌词的匹配");
        i++;
        //list=musicMapper.lesslrc(i*10,10);

    }

    public void writeSingerBind() throws Exception {
        String url = "https://v1.itooi.cn/netease/artist/top?page=0&pageSize=100";
        String json = HttpUtil.getJson(url);
        JSONArray jsonArray = JSONObject.parseObject(json).getJSONArray("data");
        for (int i = 73; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String alias = jsonObject.getString(jsonObject.getJSONArray("alias").toString());
            writeSingerData(jsonObject.getLong("id"), jsonObject.getString("name"), jsonObject.getString("picUrl"), jsonObject.getInteger("albumSize"), alias);
            writeSingermusic(jsonObject.getString("id"));
            System.out.println("已收录排行榜第" + i + "为歌手" + jsonObject.getString("name"));
        }
    }

    public void writeSingermusic(String singerid) throws Exception {
        String url = "https://v1.itooi.cn/netease/song/artist?id=" + singerid;//请求音乐歌单信息
        String json = HttpUtil.getJson(url);
        JSONArray jsonArray = JSONObject.parseObject(json).getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray ar = jsonObject.getJSONArray("ar");
            Music music = new Music();
            music.setMusicname(jsonObject.getString("name"));
            Long lrcid = writeLrcData(jsonObject.getLong("id"), music.getMusicname());
            music.setLyricid(0l);
            String singer = "";
            for (int j = 0; j < ar.size(); j++) {
                singer = singer + ar.getJSONObject(j).getString("name") + "&";
            }
            singer = singer.substring(0, singer.lastIndexOf("&"));
            music.setSinger(singer);
            music.setAlbum(jsonObject.getJSONObject("al").getString("name"));
            music.setMusicaddress("https://v1.itooi.cn/netease/url?id=" + jsonObject.getString("id") + "&quality=192000");
            music.setMusicimg(jsonObject.getJSONObject("al").getString("picUrl"));
            music.setTime(parseTime(jsonObject.getInteger("dt")));
            music.setLyricid(lrcid);
            musicMapper.InsertNoexist(music);
        }
    }

    public void writeData(int page) throws Exception {
        String url = "https://v1.itooi.cn/netease/songList/hot?cat=%E5%85%A8%E9%83%A8&pageSize=1&page=" + page;//请求音乐歌单信息
        String json = HttpUtil.getJson(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject object = jsonArray.getJSONObject(j);
            JSONObject creator = object.getJSONObject("creator");
            SongSheet songSheetInfoV = new SongSheet();
            songSheetInfoV.setIntroduce(object.getString("description"));
            songSheetInfoV.setSongsheetname(object.getString("name"));
            songSheetInfoV.setType(object.getJSONArray("tags").toString());
            songSheetInfoV.setCoverimg(object.getString("coverImgUrl"));
            songSheetInfoV.setProducer(creator.getString("nickname"));
            songSheetMapper.InsertNoexist(songSheetInfoV);
            if (songSheetInfoV.getSheetid() == null) {
                songSheetInfoV.setSheetid(songSheetMapper.getSongSheetByCondition(songSheetInfoV).get(0).getSheetid());
                System.out.println("该歌单已存在,查询到sheetid=" + songSheetInfoV.getSheetid());
            }
            WritemusicData(object.getString("id"), object.getJSONArray("tags").toString(), songSheetInfoV.getSheetid());
            User user = writeUserData(object.getJSONObject("creator"));
            if (user.getUserid() != null) {
                writeUserCollect(user.getUserid(), songSheetInfoV.getSheetid());
            } else {
                User user1 = userMapper.selectByAccount(user.getUseraccount());
                writeUserCollect(user1.getUserid(), songSheetInfoV.getSheetid());
            }
        }
    }

    public void WritemusicData(String id, String type, Long sheetid) throws Exception {
        String json = HttpUtil.getJson("https://v1.itooi.cn/netease/songList?id=" + id + "&pageSize=20");
        JSONArray jsonArray = JSON.parseObject(json).getJSONObject("data").getJSONArray("tracks");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Long lrcId = writeLrcData(jsonObject.getLong("id"), jsonObject.getString("name"));
            Music music = new Music();
            music.setMusicname(jsonObject.getString("name"));
            music.setMusicimg("https://v1.itooi.cn/netease/pic?id=" + jsonObject.getString("id"));
            JSONArray artists = jsonObject.getJSONArray("artists");
            String singer = "";
            for (int t = 0; t < artists.size(); t++) {
                singer = singer + artists.getJSONObject(t).getString("name") + "&";
                writeSingerData(artists.getJSONObject(t).getLong("id"), artists.getJSONObject(t).getString("name"), jsonObject.getJSONObject("album").getString("picUrl"), jsonObject.getJSONObject("album").getInteger("size")); //写入歌手信息
            }
            singer = singer.substring(0, singer.lastIndexOf("&"));
            music.setSinger(singer);
            music.setType(type);
            music.setMusicaddress("https://v1.itooi.cn/netease/url?id=" + jsonObject.getString("id") + "&quality=192000");
            music.setTime(parseTime(jsonObject.getInteger("duration")));
            music.setPlaynumber(jsonObject.getLong("playedNum"));
            music.setAlbum(jsonObject.getJSONObject("album").getString("name"));
            music.setLyricid(lrcId);

            musicMapper.InsertNoexist(music);//将音乐信息插入表中
            if (music.getMusicid() != null) {
                songSheetMapper.InsertMusicToSheet(music.getMusicid(), sheetid);//将歌单和音乐关联起来
            } else {
                Music music1 = musicMapper.selectByname(music.getMusicname(), music.getSinger());
                songSheetMapper.InsertMusicToSheet(music1.getMusicid(), sheetid);
            }


        }

    }

    public static String parseTime(int s) {
        String str = "";
        if ((s / 60000) < 10 && (s / 60000) > 0) {
            str = "0" + (s / 60000) + ":";
        } else if ((s / 60000) < 1) {
            str = "00:";
        } else {
            str = s / 60000 + ":";
        }

        if ((s % 60000) < 10000) {
            str = str + "0" + ((s % 60000) / 1000);
        } else {
            str = str + ((s % 60000) / 1000);
        }
        return str;
    }

    public void writeSingerData(Long id, String name, String imgurl, Integer Albumsize) throws Exception {

        String json = HttpUtil.getJson("https://v1.itooi.cn/netease/artist/info?id=" + id);
        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("data");
        if (jsonObject != null) {
            Singer singer = new Singer();
            singer.setBriefdesc(jsonObject.getString("briefDesc"));
            singer.setSingername(name);
            singer.setSingerimg(imgurl);
            singer.setAlbumsize(Albumsize.doubleValue());
            singerMapper.InsertNoexist(singer);
        }
    }

    public void writeSingerData(Long id, String name, String imgurl, Integer Albumsize, String alias) throws Exception {

        String json = HttpUtil.getJson("https://v1.itooi.cn/netease/artist/info?id=" + id);
        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("data");
        if (jsonObject != null) {
            Singer singer = new Singer();
            singer.setBriefdesc(jsonObject.getString("briefDesc"));
            singer.setSingername(name);
            singer.setSingerimg(imgurl);
            singer.setAlbumsize(Albumsize.doubleValue());
            singer.setAlias(alias);
            singerMapper.InsertNoexist(singer);
        }
    }

    public Long writeLrcData(Long muiscid, String musicname) throws Exception {
        String lrctext = HttpUtil.getJson("https://v1.itooi.cn/netease/lrc?id=" + muiscid);
        Lrc lrc = new Lrc();
        lrc.setContent(lrctext);
        lrc.setLrcname(musicname);
        lrcMapper.addLrcInfo(lrc);
        if (lrc.getLrcid() == null) {
            lrc.setLrcid(lrcMapper.selectByname(musicname).getLrcid());
        }
        return lrc.getLrcid();
    }

    public User writeUserData(JSONObject jsonObject) {
        User user = new User();
        user.setUsername(jsonObject.getString("nickname"));
        user.setUserpassword("111111111");
        user.setUserheadimg(jsonObject.getString("avatarUrl"));
        user.setUseraccount(jsonObject.getString("userId"));
        user.setSignature(jsonObject.getString("signature"));
        userMapper.InsertNoexist(user);
        if (user.getUserid() == null) {
            user.setUserid(userMapper.selectByAccount(user.getUseraccount()).getUserid());
        }
        return user;
    }

    public void writeUserCollect(Long userid, Long sheetid) {
        UserCollection userCollection = new UserCollection();
        userCollection.setSheetid(sheetid);
        userCollection.setUserid(userid);
        collectionMapper.InsertNoexist(userCollection);
    }


}
