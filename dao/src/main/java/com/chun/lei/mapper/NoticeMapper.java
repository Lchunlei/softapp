package com.chun.lei.mapper;

import com.chun.lei.entity.Notice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Created by lcl on 2020/5/13 0013
 */
@Repository
public interface NoticeMapper {

    @Select("SELECT * FROM mv_notice WHERE lookState='1' AND uid IN('0',#{uid}) ORDER BY creatTime DESC")
    List<Notice> getNotices(Integer uid);

}
