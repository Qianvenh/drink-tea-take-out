package com.drinktea.service.impl;

import com.drinktea.constant.MessageConstant;
import com.drinktea.context.BaseContext;
import com.drinktea.dto.OrdersSubmitDTO;
import com.drinktea.entity.AddressBook;
import com.drinktea.entity.OrderDetail;
import com.drinktea.entity.Orders;
import com.drinktea.entity.ShoppingCart;
import com.drinktea.exception.AddressBookBusinessException;
import com.drinktea.exception.ShoppingCartBusinessException;
import com.drinktea.mapper.AddressBookMapper;
import com.drinktea.mapper.OrderDetailMapper;
import com.drinktea.mapper.OrderMapper;
import com.drinktea.mapper.ShoppingCartMapper;
import com.drinktea.result.Result;
import com.drinktea.service.OrderService;
import com.drinktea.vo.OrderSubmitVO;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;


    @Override
    @Transactional
    public OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO) {
        // 处理各种业务异常情况
            // 送餐地址是否合法
        Long addressBookId =  ordersSubmitDTO.getAddressBookId();
        AddressBook addressBook =  addressBookMapper.getById(addressBookId);
        if (addressBook == null)
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
            // 购物车信息是否合法
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCartQuery = ShoppingCart.builder()
                .userId(userId).build();
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.list(shoppingCartQuery);
        if (shoppingCarts == null || shoppingCarts.isEmpty())
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        // 订单表插入1条数据
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO, orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(Orders.UN_PAID);
        orders.setStatus(Orders.PENDING_PAYMENT);
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setUserId(userId);
        orderMapper.insert(orders);
        // 订单明细表插入n条数据
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (var shoppingCart : shoppingCarts) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart, orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.insertBatch(orderDetailList);
        // 删除购物车数据
        shoppingCartMapper.delete(ShoppingCart.builder()
                .userId(userId).build());
        // 返回封装的VO
        return OrderSubmitVO.builder()
                .id(orders.getId())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .orderTime(orders.getOrderTime())
                .build();
    }
}
