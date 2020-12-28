package com.smile2coder.controller;

import com.github.pagehelper.PageInfo;
import com.smile2coder.annotation.Limiter;
import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.dto.order.OrderDetailRespDto;
import com.smile2coder.dto.order.OrderReqDto;
import com.smile2coder.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zxt
 * @date 12/24/20
 * @desc
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("秒杀接口")
    @PostMapping("")
    @Limiter(6)
    public ReturnData order(@Valid @RequestBody OrderReqDto orderReqDto, BindingResult result) {
        Integer orderId = this.orderService.order(orderReqDto);
        return ReturnDataUtil.success(orderId);
    }

    @ApiOperation("查询是否秒杀成功接口")
    @GetMapping("/isSuccess")
    public ReturnData isSuccess(@RequestParam(value = "goodsId") String goodsId) {
        boolean success = this.orderService.isSuccess(Integer.valueOf(goodsId));
        return ReturnDataUtil.success(success);
    }

    @ApiOperation("订单详情")
    @GetMapping("/detail/{orderId}")
    public ReturnData<OrderDetailRespDto> detail(@PathVariable(value = "orderId") String orderId) {
        OrderDetailRespDto orderDetailRespDto = this.orderService.detail(Integer.valueOf(orderId));
        return ReturnDataUtil.success(orderDetailRespDto);
    }

    @ApiOperation("订单列表")
    @GetMapping("")
    public ReturnData page(@RequestParam(value = "page", defaultValue = "1") String page,
                           @RequestParam(value = "limit", defaultValue = "10") String limit) {
        PageInfo pageInfo = this.orderService.page(Integer.valueOf(page), Integer.valueOf(limit));
        return ReturnDataUtil.page(pageInfo);
    }
}
