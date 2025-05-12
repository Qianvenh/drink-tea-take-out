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

    /**
     *
     * @param shoppingCartDTO
     */
    @Override
    @Transactional
    public void add(ShoppingCartDTO shoppingCartDTO) {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        List<ShoppingCart> existedShoppingCartList = shoppingCartMapper.exists(shoppingCart);
        // 如果新增的商品已存在购物车中
        if (existedShoppingCartList != null && !existedShoppingCartList.isEmpty()) {
            ShoppingCart existedCart = getShoppingCart(existedShoppingCartList);
            shoppingCartMapper.update(existedCart);
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

    private static ShoppingCart getShoppingCart(List<ShoppingCart> existedShoppingCartList) {
        ShoppingCart existedCart = existedShoppingCartList.get(0);
        int oldNumber = existedCart.getNumber();
//        BigDecimal oldAmount = existedCart.getAmount();
//        // 用 BigDecimal 计算 (oldAmount * (oldNumber + 1)) / oldNumber
//        BigDecimal numerator = oldAmount.multiply(BigDecimal.valueOf(oldNumber + 1));
//        BigDecimal denominator = BigDecimal.valueOf(oldNumber);
//            // 设定小数位为 2 位，舍入模式为四舍五入
//        BigDecimal newAmount = numerator.divide(denominator, 2, RoundingMode.HALF_UP);
//        existedCart.setAmount(newAmount);
        existedCart.setNumber(oldNumber + 1);
        return existedCart;
    }
}
