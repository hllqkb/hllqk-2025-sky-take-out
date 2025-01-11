package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    @Scheduled(cron = "0 * * * * ? ")//每分钟执行一次
    public void processTimeoutOrder(){
            log.info("开始处理超时订单");
            //查询待支付订单
            LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
            List<Orders> timeoutOrders = orderMapper.updateOrderStatus(Orders.PENDING_PAYMENT,time);
            if(timeoutOrders!= null && timeoutOrders.size() > 0){
                for(Orders order : timeoutOrders){
                    order.setStatus(Orders.CANCELLED);
                    order.setCancelReason("订单超时未支付，系统自动取消订单");
                    order.setCancelTime(LocalDateTime.now());
                    orderMapper.update(order);
                }
            }
            log.info("处理超时订单结束");
    }
    @Scheduled(cron = "0 0 1 * * ? ")//每天凌晨1点执行一次
    public void processOrder(){
        log.info("开始处理一直派送中的订单");
        LocalDateTime time = LocalDateTime.now().plusHours(-1);
        //查询待发货订单
        List<Orders> pendingOrders = orderMapper.updateOrderStatus(Orders.DELIVERY_IN_PROGRESS,time);
        if(pendingOrders!= null && pendingOrders.size() > 0){
            for(Orders order : pendingOrders){
                order.setStatus(Orders.CONFIRMED);
                order.setDeliveryTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        log.info("处理一直派送中的订单结束");
        }
    }
}
