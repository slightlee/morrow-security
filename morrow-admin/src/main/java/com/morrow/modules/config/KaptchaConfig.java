package com.morrow.modules.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 生成验证码配置
 *
 * @Author Tomorrow
 * @Date 2020/9/6 3:23 上午
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        // 图片边框
        properties.put("kaptcha.border", "yes");
        // 边框颜色
      //  properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.put("kaptcha.textproducer.font.color", "0,134,139");
        // 图片宽
    //    properties.setProperty("kaptcha.image.width", "110");
        // 图片高
     //   properties.setProperty("kaptcha.image.height", "40");
        // 字体大小
       // properties.setProperty("kaptcha.textproducer.font.size", "30");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 验证码长度
        properties.put("kaptcha.textproducer.char.length", "5");
        // 文字间隔
        properties.put("kaptcha.textproducer.char.space", "3");
        // 干扰 颜色
        properties.put("kaptcha.noise.color", "193,255,193");
        properties.put("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");


        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
