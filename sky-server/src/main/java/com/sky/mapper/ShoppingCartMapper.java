package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> list(ShoppingCart cart);

    /**
     * 根据id更新购物车
     *
     * @param sc
     */
    @Update("UPDATE shopping_cart SET number = #{number} WHERE id = #{id}")
    void updateNumberById(ShoppingCart sc);

    /**
     * 批量插入购物车
     *
     * @param cart
     */
    @Insert("INSERT INTO shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            "VALUES (#{name}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{image}, #{createTime})")
    void insert(ShoppingCart cart);

    @Delete("DELETE FROM shopping_cart WHERE id = #{id}")
    void delete(ShoppingCart shoppingCart);
}
