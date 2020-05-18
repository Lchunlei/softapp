package com.chun.lei.mapper;

import com.chun.lei.entity.UserResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@Repository
public interface UserResourceMapper {

    @Insert("INSERT INTO mv_user_resource(`uid`,`resId`,`uidRsId`,`cTime`) VALUES (#{uid}, #{resId},#{uidRsId},NOW())")
    int insertOne(UserResource userResource);

    @Select("SELECT * FROM mv_user_resource WHERE uidRsId=#{uidRsId}")
    UserResource getByUidRsId(String uidRsId);

    @Select("SELECT resId FROM mv_user_resource WHERE uid=#{uid} ORDER BY id DESC")
    List<Integer> getByUId(Integer uid);

    @Delete("DELETE FROM mv_user_resource WHERE id=#{id} AND uid=#{uid}")
    int delOneById(@Param("id") Integer id,@Param("uid") Integer uid);

    @Select("SELECT COUNT(id) FROM mv_user_resource WHERE uid=#{uid}")
    int getMineTotalNum(Integer uid);

}
