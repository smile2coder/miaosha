package com.smile2coder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile2coder.dao.MGoodsMapper;
import com.smile2coder.dto.goods.GoodsStockDto;
import com.smile2coder.model.MGoods;
import com.smile2coder.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxt
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private MGoodsMapper goodsMapper;

    @Override
    @Cacheable(cacheNames = "cache_GoodsServiceImpl_selectByGoodsId_", key = "#goodsId", sync = true)
    public MGoods selectByGoodsId(Integer goodsId) {
        return this.goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public boolean decrStock(Integer goodsId) {
        int decrStock = this.goodsMapper.decrStock(goodsId, 1);
        return decrStock > 0;
    }

    @Override
    public PageInfo<MGoods> list(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<MGoods> list = this.goodsMapper.list();
        return PageInfo.of(list);
    }

    @Override
    public int updateStatus(Integer goodsId, Byte status) {
        return this.goodsMapper.updateStatus(goodsId, status);
    }

    @Override
    public List<Integer> selectIdsByStatus(byte status) {
        return this.goodsMapper.selectIdsByStatus(status);
    }

    @Override
    public List<GoodsStockDto> selectStockByStatus(byte status) {
        return this.goodsMapper.selectStockByStatus(status);
    }
}
