package eu.senla.hotelBooking.configs;

import eu.senla.hotelBooking.configs.converters.StringToFilterKeyConverter;
import eu.senla.hotelBooking.configs.converters.StringToRoomStatusConverter;
import eu.senla.hotelBooking.configs.converters.StringToSortKeyConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSortKeyConverter());
        registry.addConverter(new StringToRoomStatusConverter());
        registry.addConverter(new StringToFilterKeyConverter());
    }

}
