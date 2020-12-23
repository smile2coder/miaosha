package com.smile2coder.dao;

import com.smile2coder.model.MGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MGoods record);

    int insertSelective(MGoods record);

    MGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoods record);

    int updateByPrimaryKey(MGoods record);

    int decrStock(@Param("goodsId") Integer goodsId, @Param("count") int count);
}