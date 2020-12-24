package com.smile2coder.service;

import com.smile2coder.dto.order.OrderDetailRespDto;
import com.smile2coder.dto.order.OrderReqDto;
import com.smile2coder.model.MOrder;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public interface OrderService {

    Integer order(OrderReqDto orderReqDto);

    int selectCountByUserIdAndGoodsId(Integer userId, Integer goodsId);

    OrderDetailRespDto detail(Integer orderId);

    MOrder selectByPrimaryKey(Integer id);

    boolean isSuccess(Integer userId, Integer goodsId);
}
