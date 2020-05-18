package com.chun.lei.mapper;

import com.chun.lei.entity.GetCoin;
import com.chun.lei.entity.Holiday;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Created by lcl on 2020/5/10 0010
 */
@Repository
public interface GetCoinMapper {

    @Select("SELECT * FROM mv_get_coin WHERE useType='1'")
    GetCoin getOne();

}
