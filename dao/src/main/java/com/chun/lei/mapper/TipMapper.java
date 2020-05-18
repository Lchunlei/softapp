package com.chun.lei.mapper;

import com.chun.lei.entity.Tip;
import com.chun.lei.sql.TipSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@Repository
public interface TipMapper {
    @Select("SELECT * FROM mv_tip WHERE dealStatu=0")
    List<Tip> getWillDealTips();

    @Select("SELECT * FROM mv_tip WHERE id=#{id}")
    Tip getTipById(Integer id);

    @Select("SELECT * FROM mv_tip WHERE keyNo=#{keyNo}")
    Tip getTipBykeyNo(String keyNo);

    @Insert("INSERT INTO mv_tip(`uid`,`keyNo`,`cTime`) VALUES (#{uid},#{keyNo},NOW())")
    int insertOne(Tip tip);

    @UpdateProvider(type= TipSql.class, method="updateSql")
    int updateStatu(Tip tip);

    @Select("SELECT COUNT(id) FROM mv_tip WHERE uid=#{uid} AND cTime>#{sTime}")
    Integer getUserTodayFalseTip(@Param("uid")Integer uid,@Param("sTime")String sTime);

    @Select("SELECT * FROM mv_tip WHERE uid=#{uid} ORDER BY cTime DESC LIMIT 10")
    List<Tip> getMyTips(Integer uid);


}
