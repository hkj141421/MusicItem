package com.example.demo.Util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by forget on 2019/5/18.
 */
public class VerificationCodeUtil {

    private static String randomVerificationCode = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生验证码的字符串

    private static String VerificationCode = "";

    private static Random random = new Random();

    private static int width = 100;//图片的宽

    private static int height = 40;//图片的高

    private static int linecount = 4;//干扰线的数量

    private static int CodeCount = 4;//验证码的数量

    public static String generateVerificationCode(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);
        VerificationCode = "";
        for (int i = 0; i < linecount; i++) {
            drawLine(g);
        }

        for (int i = 0; i < CodeCount; i++) {
            VerificationCode += drawString(g, i + 1);
        }
        g.dispose();
        session.setAttribute("VerificationCode", VerificationCode);

        BASE64Encoder encoder = new BASE64Encoder();
        ImageIO.write(image, "jpg", outputStream);
        bytes = outputStream.toByteArray();
        return encoder.encode(bytes);
    }

    public static void drawLine(Graphics graphics) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(width);
        int yl = random.nextInt(height);
        graphics.setColor(getRandomColor());
        graphics.drawLine(x, y, x + xl, y + yl);
    }

    public static String drawString(Graphics graphics, int i) {
        graphics.setColor(getRandomColor());
        graphics.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 20));
        String s = getRandomString(random.nextInt(randomVerificationCode.length())) + "";
        graphics.translate(random.nextInt(3), random.nextInt(3));
        graphics.drawString(s, 20 * i, 20);
        return s;
    }

    public static Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    public static char getRandomString(int index) {
        return randomVerificationCode.charAt(index);
    }

}
