package com.chun.lei.mapper;

import com.chun.lei.entity.SysChannel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
@Repository
public interface SysChannelMapper {

    @Select("SELECT * FROM mv_channel WHERE lookState='1' ORDER BY channelSort")
    List<SysChannel> getAll();

    @Select("SELECT * FROM mv_channel WHERE lookState='1' AND id!='5' ORDER BY channelSort")
    List<SysChannel> getNotMv();

}
