package com.chun.lei.mapper;

import com.chun.lei.entity.SysBanner;
import com.chun.lei.entity.SysChannel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
@Repository
public interface SysBannerMapper {

    @Select("SELECT * FROM mv_banner WHERE lookState='1'AND useType=#{userType} ORDER BY sortIndex")
    List<SysBanner> getAllByType(Integer userType);

}
