package com.sky.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    @Transactional
    //全成功才提交事务
    @Override
    public void saveWithFlavors(DishDTO dishDTO) {
        //向数据库保存dishDTO
        //保存dishDTO的flavors
        //保存dishDTO的category
        //保存dishDTO的dishDetail
        //保存dishDTO的dishImage
        //保存dishDTO的dishSpecification
        //保存dishDTO的dishComment
        Dish dish = new Dish();
        BeanUtil.copyProperties(dishDTO, dish);
        //插入dish菜品一条
        dishMapper.insert(dish);
        //获取dish id
        Long dishId = dish.getId();
        //向口味表中插入n条口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors!= null && flavors.size() > 0){
            for(DishFlavor flavor : flavors){
                //设置口味的dishId
                flavor.setDishId(dishId);
            }
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
