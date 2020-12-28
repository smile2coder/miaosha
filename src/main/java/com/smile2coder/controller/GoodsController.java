package com.smile2coder.controller;

import com.github.pagehelper.PageInfo;
import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.model.MGoods;
import com.smile2coder.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxt
 * @date 12/25/20
 * @desc
 */
@RestController
@RequestMapping("/goods")
@Api(tags = "商品相关接口")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取秒杀商品列表")
    @GetMapping("")
    public ReturnData list(@RequestParam(value = "page", defaultValue = "1") String page,
                           @RequestParam(value = "limit", defaultValue = "10") String limit) {
        PageInfo<MGoods> pageinfo = this.goodsService.list(Integer.valueOf(page), Integer.valueOf(limit));
        return ReturnDataUtil.page(pageinfo);
    }
}
