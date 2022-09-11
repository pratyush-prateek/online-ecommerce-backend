package com.ecommerce.api.core.order.converters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import java.util.Arrays;

@Configuration
public class Converters {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OrderStatusReadingConverter(),
                new OrderStatusWritingConverter()
        ));
    }
}
