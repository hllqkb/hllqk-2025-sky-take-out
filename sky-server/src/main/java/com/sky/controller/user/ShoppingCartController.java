package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端-用户购物车相关接口")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 添加购物车
     * @param dto
     * @return
 */
    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO dto) {
        log.info("添加购物车：{}", dto);
        shoppingCartService.add(dto);
        return Result.success();
    }
    @ApiOperation("获取购物车列表")
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list() {
        log.info("获取购物车列表");
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }
}
