package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(ShoppingCartDTO dto) {
        ShoppingCart cart = new ShoppingCart();
        BeanUtils.copyProperties(dto, cart);
        Long userId = BaseContext.getCurrentId();
        cart.setUserId(userId);
        List<ShoppingCart> list = shoppingCartMapper.list(cart);
        if (list != null && list.size() > 0) {
            //存在购物车记录
            //更新数量
            ShoppingCart sc = list.get(0);
            sc.setNumber(sc.getNumber() + 1);//数量加1
            shoppingCartMapper.updateNumberById(sc);

        }else{
            //创建一个新的购物车
            Long DisId = dto.getDishId();
            if(DisId!= null) {
                //添加菜品到购物车
                Dish dish = dishMapper.getById(DisId);
                cart.setName(dish.getName());
                cart.setImage(dish.getImage());
                cart.setAmount(dish.getPrice());
            }
            else{
                    //添加套餐到购物车
                    Long SetId = dto.getSetmealId();
                    Setmeal setmeal = setmealMapper.getById(SetId);
                    cart.setName(setmeal.getName());
                    cart.setImage(setmeal.getImage());
                    cart.setAmount(setmeal.getPrice());
                }
            cart.setNumber(1);
            cart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(cart);
            }
    }

    @Override
    public List<ShoppingCart> showShoppingCart()
    {
        //微信小程序获取用户id
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart=ShoppingCart.builder().userId(userId).build();
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    @Override
    public void clean() {
        //微信小程序获取用户id
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart=ShoppingCart.builder().userId(userId).build();
        shoppingCartMapper.delete(shoppingCart);
    }
}
