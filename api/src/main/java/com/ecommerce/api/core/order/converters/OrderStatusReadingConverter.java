package com.ecommerce.api.core.order.converters;

import com.ecommerce.api.core.order.models.OrderStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class OrderStatusReadingConverter implements Converter<String, OrderStatus> {
    @Override
    public OrderStatus convert(String status) {
        return OrderStatus.fromValue(status);
    }
}
