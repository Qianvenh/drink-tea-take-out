package com.drinktea.service.impl;

import com.drinktea.context.BaseContext;
import com.drinktea.dto.ShoppingCartDTO;
import com.drinktea.entity.Dish;
import com.drinktea.entity.Setmeal;
import com.drinktea.entity.ShoppingCart;
import com.drinktea.mapper.DishMapper;
import com.drinktea.mapper.SetmealMapper;
import com.drinktea.mapper.ShoppingCartMapper;
import com.drinktea.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    enum Operation {
        add,
        sub
    }

    /**
     *
     * @param shoppingCartDTO
     */
    @Override
    @Transactional
    public void add(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(BaseContext.getCurrentId());
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        List<ShoppingCart> existedShoppingCartList = shoppingCartMapper.list(shoppingCart);
        // 如果新增的商品已存在购物车中
        if (existedShoppingCartList != null && !existedShoppingCartList.isEmpty()) {
            shoppingCart.setNumber(existedShoppingCartList.get(0).getNumber() + 1);
            shoppingCartMapper.update(shoppingCart);
            return;
        }
        // 如果新增的商品不在购物车中
        shoppingCart.setCreateTime(LocalDateTime.now());
        if (shoppingCartDTO.getSetmealId() != null) {
            // 本次添加的商品是套餐
            Setmeal setmeal = setmealMapper.getById(shoppingCartDTO.getSetmealId());
            shoppingCart.setAmount(setmeal.getPrice());
            shoppingCart.setImage(setmeal.getImage());
            shoppingCart.setName(setmeal.getName());
        } else if (shoppingCartDTO.getDishId() != null) {
            // 否则本次添加的饮品
            Dish dish = dishMapper.getById(shoppingCartDTO.getDishId());
            shoppingCart.setAmount(dish.getPrice());
            shoppingCart.setImage(dish.getImage());
            shoppingCart.setName(dish.getName());
        }
        shoppingCartMapper.add(shoppingCart);
    }

    /**
     * 查询用户购物车
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build();
       return shoppingCartMapper.list(shoppingCart);
    }

    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(BaseContext.getCurrentId());
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        List<ShoppingCart> existedShoppingCartList = shoppingCartMapper.list(shoppingCart);
        // 如果减去的商品数量大于1
        if (existedShoppingCartList.get(0).getNumber() > 1) {
            shoppingCart.setNumber(existedShoppingCartList.get(0).getNumber() - 1);
            shoppingCartMapper.update(shoppingCart);
        }
        else // 如果减去的商品数量等于1
            shoppingCartMapper.delete(shoppingCart);
    }

    /**
     * 清空购物车
     */
    @Override
    public void cleanShoppingCart() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build();
        shoppingCartMapper.delete(shoppingCart);
    }
}
