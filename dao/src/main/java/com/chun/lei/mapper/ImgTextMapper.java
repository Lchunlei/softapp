package com.chun.lei.mapper;

import com.chun.lei.entity.ImgText;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Repository
public interface ImgTextMapper {

    @Select("SELECT * FROM mv_img_text WHERE resId=#{resId} AND sortIndex=#{sortIndex}")
    ImgText getAppointPage(@Param("resId")Integer resId,@Param("sortIndex")Integer sortIndex);

}
