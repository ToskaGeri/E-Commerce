package com.ecommerce.Mapper;

import com.ecommerce.Dto.DeliveryOrder;
import com.ecommerce.Models.OrderDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper extends BaseEntityMapper<DeliveryOrder, OrderDetails>{
}
