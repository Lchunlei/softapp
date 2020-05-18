package com.chun.lei.mapper;

import com.chun.lei.entity.CoinAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@Repository
public interface CoinAccountMapper {

    @Insert("INSERT INTO mv_coin_account(`uid`, `getType`, `getMsg`, `coinNum`, `cTime`)VALUES (#{uid},#{getType},#{getMsg},#{coinNum},NOW())")
    int insertOne(CoinAccount coinAccount);

    @Select("SELECT * FROM mv_coin_account WHERE uid=#{uid} ORDER BY cTime DESC LIMIT 10")
    List<CoinAccount> getMine(Integer uid);

}
