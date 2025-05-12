package com.drinktea.service;

import com.drinktea.dto.ShoppingCartDTO;

public interface ShoppingCartService {
    /**
     * 添加到购物车
     * @param shoppingCartDTO
     */
    void add(ShoppingCartDTO shoppingCartDTO);
}
