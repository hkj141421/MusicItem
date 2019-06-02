package com.example.demo.test;

import com.example.demo.Util.EncodeUtils;
import com.example.demo.Util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by forget on 2018/12/7.
 */
public class XmlToJson {
    @Test
    public void readfile() throws IOException {
        String path = "C:\\Users\\forget\\Desktop\\LrcList";
        File file = new File(path);
        File[] filelist = file.listFiles();
        for (File f : filelist) {
            List<String> lrc = new ArrayList<String>();
            lrc = FileUtil.getLrcFile(f.getAbsolutePath());
            System.out.println(f.getName());
            for (String line : lrc) {
                System.out.println(line);
            }
            try {
                System.out.println(EncodeUtils.getEncode(f.getAbsolutePath(), true) + f.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() throws UnsupportedEncodingException {

    }


}
