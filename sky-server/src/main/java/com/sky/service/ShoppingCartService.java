package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface ShoppingCartService {
    /**
     *  添加购物车
     * @param dto
     */
    void add(ShoppingCartDTO dto);
}
