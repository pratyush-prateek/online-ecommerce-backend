package com.ecommerce.api.core.order.converters;

import com.ecommerce.api.core.order.models.OrderStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class OrderStatusWritingConverter implements Converter<OrderStatus, String> {
    @Override
    public String convert(OrderStatus status) {
        return status.getValue();
    }
}
