package com.chun.lei.mapper;

import com.chun.lei.entity.SysResource;
import com.chun.lei.entity.reqvo.ResSearch;
import com.chun.lei.sql.SysResourceSql;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
@Repository
public interface SysResourceMapper {

    @Select("SELECT * FROM mv_resource WHERE rType=#{rType} AND isPop='1' AND lookState='1' ORDER BY sortIndex DESC")
    List<SysResource> getIndexResource(Integer rType);

    @Select("SELECT * FROM mv_resource WHERE id=#{resId} AND lookState='1'")
    SysResource getById(Integer resId);

    @Select("SELECT * FROM mv_resource WHERE rTitle LIKE concat('%',#{key},'%') AND lookState='1' ORDER BY id DESC LIMIT 10")
    List<SysResource> searchKey(String key);

    @SelectProvider(type= SysResourceSql.class, method="selectSql")
    List<SysResource> searchCid(ResSearch resSearch);

    @Select("SELECT r.* FROM mv_user_resource ur LEFT JOIN mv_resource r ON ur.resId=r.id WHERE ur.uid=#{uid} ORDER BY ur.id DESC LIMIT #{pageNum},10")
    List<SysResource> getMinePage(@Param("uid") Integer uid,@Param("pageNum") Integer pageNum);


}
