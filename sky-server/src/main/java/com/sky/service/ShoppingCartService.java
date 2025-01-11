package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     *  添加购物车
     * @param dto
     */
    void add(ShoppingCartDTO dto);//添加购物车

    List<ShoppingCart> showShoppingCart();//查看购物车
}
