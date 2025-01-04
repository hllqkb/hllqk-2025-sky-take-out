package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入菜品口味
     * @param list
     */
    void insertBatch(List<DishFlavor> list);
    /**
     * 根据菜品id删除菜品口味
     * @param id
     */

    void deleteByDishId(Long id);

    /**
     * 根据菜品id获取菜品口味
     * @param id
     * @return
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id = #{id}")
    List<DishFlavor> getByDishId(Long id);
}
