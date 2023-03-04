package sparat.spartaclone.mainpage.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sparat.spartaclone.mainpage.enums.SortType;

@Component
public class SortTypeEnumConverter implements Converter<String, SortType> {
    @Override
    public SortType convert(String value) {
        return SortType.of(value);
    }
}