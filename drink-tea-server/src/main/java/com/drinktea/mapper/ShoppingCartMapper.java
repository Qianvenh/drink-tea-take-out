package com.drinktea.mapper;

import com.drinktea.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 新增购物车商品
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time) " +
            "values " +
            "(#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void add(ShoppingCart shoppingCart);

    /**
     * 根据动态查询条件判断商品是否已在购物车中
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> exists(ShoppingCart shoppingCart);

    /**
     * 更新购物车商品信息
     * @param shoppingCart
     */
    void update(ShoppingCart shoppingCart);
}
