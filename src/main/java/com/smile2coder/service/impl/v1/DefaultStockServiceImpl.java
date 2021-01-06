package com.smile2coder.service.impl.v1;

import com.smile2coder.model.BaseModel;
import com.smile2coder.service.GoodsService;
import com.smile2coder.service.StockService;
import com.smile2coder.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
public class DefaultStockServiceImpl implements StockService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SwitchService switchService;

    @Override
    public void init() {
        List<Integer> goodsIds = this.goodsService.selectIdsByStatus(BaseModel.STATUS_NORMAL);
        goodsIds.forEach(goodsId -> this.switchService.setSwitch(goodsId, true));
    }

    @Override
    public boolean decrStock(Integer goodsId) {
        return goodsService.decrStock(goodsId);
    }
}
