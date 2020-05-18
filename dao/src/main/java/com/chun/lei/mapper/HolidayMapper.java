package com.chun.lei.mapper;

import com.chun.lei.entity.Holiday;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Created by lcl on 2020/5/8 0008
 */
@Repository
public interface HolidayMapper {

    @Select("SELECT * FROM mv_holiday WHERE dateStr=#{today}")
    Holiday getDay(String today);

}
