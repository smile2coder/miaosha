package com.smile2coder.dao;

import com.smile2coder.model.MOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MOrder record);

    int insertSelective(MOrder record);

    MOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MOrder record);

    int updateByPrimaryKey(MOrder record);

    int selectCountByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    List<MOrder> list(Integer userId);
}