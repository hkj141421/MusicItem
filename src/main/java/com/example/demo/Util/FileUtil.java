package com.example.demo.Util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.Lrc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/12/8.
 */
public class FileUtil {

    public static List<String> getLrcFile(String filepath) {
        List<String> Lrc = new ArrayList<String>();
        File lrcfile = new File(filepath);
        try {
            String FileEncod = null;
            BufferedReader reader;
            FileEncod = EncodeUtils.getEncode(lrcfile.getAbsolutePath(), true);
            InputStreamReader Input = new InputStreamReader(new FileInputStream(lrcfile), FileEncod);
            reader = new BufferedReader(Input);
            String line = null;
            line = reader.readLine();
            while (line != null) {
                Lrc.add(line);
                line = reader.readLine();
            }
            return Lrc;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("歌词文件读取异常");
        }
        return null;
    }

    public static HashMap<String, List<String>> ParseLrc(Lrc lrc) {
        if (lrc == null || lrc.getContent() == null) {
            return null;
        }
        HashMap<String, List<String>> LrcMap = new HashMap<String, List<String>>();
        List<String> time = new ArrayList<String>();
        List<String> text = new ArrayList<String>();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(lrc));
        String content = jsonObject.getString("content");
        String[] lrctext = content.split("\n");
        // System.out.println(Content);
        //  List<String> Lrcfile=FileUtil.getLrcFile(filepath);
        for (String line : lrctext) {
            if (line.indexOf("]") != -1) {
                if (line.substring(line.indexOf("]") + 1).length() != 0) {
                    time.add(line.substring(1, line.indexOf("]")));
                    text.add(line.substring(line.indexOf("]") + 1));
                }
            }
        }
        LrcMap.put("time", time);
        LrcMap.put("text", text);
        return LrcMap;
    }

    public static BufferedInputStream getFileInputStream(String path) {
        File f = new File(path);
        try {
            BufferedInputStream bufferIn = new BufferedInputStream(new FileInputStream(path));
            return bufferIn;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
