package com.sipuang.xianyitong.domain.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: laoli
 * @date: 2018-01-21 16:24
 * @Description:
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";

    /**
     * @see Converter#convert(Object)
     */
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source) || "null".equals(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                SimpleDateFormat formatter;
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(dateFormat);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new HttpMessageNotReadableException(String.format("parser %s to Date fail", source));
        }
        throw new HttpMessageNotReadableException(String.format("parser %s to Date fail", source));
    }
}
