package com.smile2coder.dao;

import com.smile2coder.model.MGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MGoods record);

    int insertSelective(MGoods record);

    MGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoods record);

    int updateByPrimaryKey(MGoods record);

    int decrStock(@Param("goodsId") Integer goodsId, @Param("count") int count);

    List<MGoods> list();

    int updateStatus(@Param("goodsId") Integer goodsId, @Param("status") Byte status);

    List<Integer> selectIdsByStatus(byte status);
}