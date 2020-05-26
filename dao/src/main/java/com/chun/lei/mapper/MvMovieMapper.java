package com.chun.lei.mapper;

import com.chun.lei.entity.MvMovie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Repository
public interface MvMovieMapper {

    @Select("SELECT * FROM mv_movie WHERE resId=#{resId} AND sortIndex=#{sortIndex}")
    MvMovie getAppointMv(@Param("resId")Integer resId, @Param("sortIndex")Integer sortIndex);

    @Update("UPDATE mv_movie SET err='0',uTime=NOW() WHERE resId=#{resId} AND sortIndex=#{sortIndex}")
    int saveErr(@Param("resId")Integer resId, @Param("sortIndex")Integer sortIndex);

    @Select("SELECT * FROM mv_movie WHERE err='1'")
    List<MvMovie> getAllMvs();

    @Update("UPDATE mv_movie SET err='0',uTime=NOW() WHERE id=#{id}")
    int saveErrById(@Param("id")Integer id);

}
