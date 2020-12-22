package com.smile2coder.service.impl;

import com.smile2coder.dao.MOrderMapper;
import com.smile2coder.dto.order.OrderReqDto;
import com.smile2coder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MOrderMapper orderMapper;

    @Override
    public boolean order(OrderReqDto orderReqDto) {
        return false;
    }
}
