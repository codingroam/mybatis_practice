package com.company.mapper;

import com.company.po.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * FROM gu_order WHERE id = #{id}")
    Order findOrderById(int id);

    @Select("SELECT * FROM gu_order")
    List<Order> findAll();
}
