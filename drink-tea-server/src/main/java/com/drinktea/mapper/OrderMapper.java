package com.drinktea.mapper;

import com.drinktea.entity.OrderDetail;
import com.drinktea.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);
}
