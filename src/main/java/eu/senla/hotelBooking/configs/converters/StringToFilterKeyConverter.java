package eu.senla.hotelBooking.configs.converters;

import eu.senla.hotelBooking.model.enums.FilterKey;
import org.springframework.core.convert.converter.Converter;

public class StringToFilterKeyConverter implements Converter<String, FilterKey> {

    @Override
    public FilterKey convert(String source) {
        return FilterKey.valueOf(source.toUpperCase());
    }

}
