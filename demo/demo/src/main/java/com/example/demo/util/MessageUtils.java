package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtils {
    private final MessageSource messageSource;

    public String populate(String key) {
        return populate(key, null);
    }

    public String populate(String key, String[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
//        return messageSource.getMessage(key, params, new Locale("vi_VN"));
    }
}
