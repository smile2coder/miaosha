package com.smile2coder.service;

import com.smile2coder.dto.order.OrderReqDto;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public interface OrderService {

    boolean order(OrderReqDto orderReqDto);
}
