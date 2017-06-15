package com.Vshop.core.captcha;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class PatchcaUtils {
    private static Random random = new Random();
    private static final String DEFAULT_CHARACTERS = "23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ"; // 自己设置！
    private static int DEFAULT_FONT_SIZE = 25;
    private static int DEFAULT_WORD_LENGTH = 4;
    private static int DEFAULT_WIDTH = 80;
    private static int DEFAULT_HEIGHT = 35;

    public static String generateImage(HttpServletResponse response)throws IOException {
        return generateImage(DEFAULT_FONT_SIZE, DEFAULT_WORD_LENGTH, DEFAULT_CHARACTERS, DEFAULT_WIDTH, DEFAULT_HEIGHT,response);
    }
    public static String generateImage(int fontSize, int wordLength, String characters, int width, int height,HttpServletResponse response) throws IOException {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        // 字体大小设置
        RandomFontFactory ff = new RandomFontFactory();
        ff.setMinSize(fontSize);
        ff.setMaxSize(fontSize);
        cs.setFontFactory(ff);
        // 生成的单词设置
        RandomWordFactory rwf = new RandomWordFactory();
        rwf.setCharacters(characters);
        rwf.setMinLength(wordLength);
        rwf.setMaxLength(wordLength);
        cs.setWordFactory(rwf);

        // 生成图片大小（像素）
        cs.setWidth(width);
        cs.setHeight(height);


        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }

        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        System.out.println("验证码=" + token);
        return token.toUpperCase();
    }

    private static void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
}
