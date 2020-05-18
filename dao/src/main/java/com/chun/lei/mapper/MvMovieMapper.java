package com.chun.lei.mapper;

import com.chun.lei.entity.MvMovie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Repository
public interface MvMovieMapper {

    @Select("SELECT * FROM mv_movie WHERE resId=#{resId} AND sortIndex=#{sortIndex}")
    MvMovie getAppointMv(@Param("resId")Integer resId, @Param("sortIndex")Integer sortIndex);

}
