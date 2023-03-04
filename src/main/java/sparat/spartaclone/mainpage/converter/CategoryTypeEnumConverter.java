package sparat.spartaclone.mainpage.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sparat.spartaclone.mainpage.enums.Category;
import sparat.spartaclone.mainpage.enums.SortType;

@Component
public class CategoryTypeEnumConverter implements Converter<String, Category> {
    @Override
    public Category convert(String value) {
        return Category.of(value);
    }
}