package com.drinktea.service;

import com.drinktea.dto.OrdersSubmitDTO;
import com.drinktea.vo.OrderSubmitVO;

public interface OrderService {
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
}
