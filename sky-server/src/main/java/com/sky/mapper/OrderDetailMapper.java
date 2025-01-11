package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * #author hllqkb
 * #class 物联网2401
 * #student_number 6020242411
 */

@Mapper
public interface OrderDetailMapper {
//    @Select("select * from order_detail where order_id=#{orderId}")
//    List<OrderDetail> getByOrderId(Integer orderId);

    //批量插入订单明细文件
    void insertBatch(List<OrderDetail> orderDetailList);


    //根据订单id获取订单明细
    @Select("select * from order_detail where order_id=#{orderId}")
    List<OrderDetail> getByOrderId(Long orderId);
}
