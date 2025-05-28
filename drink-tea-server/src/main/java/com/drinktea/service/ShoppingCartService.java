package com.drinktea.service;

import com.drinktea.dto.ShoppingCartDTO;
import com.drinktea.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 添加到购物车
     * @param shoppingCartDTO
     */
    void add(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询用户购物车
     * @return
     */
    List<ShoppingCart> list();

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    void sub(ShoppingCartDTO shoppingCartDTO);
}
