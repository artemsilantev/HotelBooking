package eu.senla.hotelBooking.configs.converters;

import eu.senla.hotelBooking.model.enums.SortKey;
import org.springframework.core.convert.converter.Converter;

public class StringToSortKeyConverter implements Converter<String, SortKey> {

    @Override
    public SortKey convert(String source) {
        return SortKey.valueOf(source.toUpperCase());
    }

}
