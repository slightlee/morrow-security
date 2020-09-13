package com.morrow.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化
 *
 * @Author Tomorrow
 * @Date 2020/9/6 1:26 上午
 */
public class MessageUtils {

    private static MessageSource messageSource;
    static {
        messageSource = (MessageSource)SpringContextUtils.getBean("messageSource");
    }

    public static String getMessage(int code){
        return getMessage(code, new String[0]);
    }

    public static String getMessage(int code, String... params){
        return messageSource.getMessage(code+"", params, LocaleContextHolder.getLocale());
    }
}
