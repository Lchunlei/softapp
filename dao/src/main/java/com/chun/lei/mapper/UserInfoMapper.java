package com.chun.lei.mapper;

import com.chun.lei.entity.UserInfo;
import com.chun.lei.sql.UserSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created by lcl on 2020/4/24 0024
 */
@Repository
public interface UserInfoMapper {
    @Insert("INSERT INTO mv_user(`wxOpenId`,`loginTime`,`cTime`) VALUES (#{openId}, NOW(), NOW())")
    int insertOne(String openId);

    @Select("SELECT last_insert_id()")
    int selectMaxSeq();

    @Select("SELECT * FROM mv_user")
    List<UserInfo> getUsers();

    @Select("SELECT nickName,headUrl,cTime FROM mv_user WHERE inviteId=#{uid} ORDER BY cTime DESC LIMIT 20")
    List<UserInfo> getMyInviteUsers(Integer uid);

    @Select("SELECT * FROM mv_user WHERE wxOpenId=#{openId}")
    UserInfo getUserByOpenId(String openId);

    @Select("SELECT * FROM mv_user WHERE uid=#{uid}")
    UserInfo getUserById(Integer uid);

    @Update("UPDATE mv_user SET seeCoinTotal=seeCoinTotal+#{add},seeCoinNow=seeCoinNow+#{add},signIn=#{signIn} WHERE uid=#{uid}")
    int sigIn(@Param("uid")Integer uid,@Param("add")Integer add,@Param("signIn")String signIn);

    @UpdateProvider(type= UserSql.class, method="updateSql")
    int updateBath(UserInfo userInfo);

    @Update("UPDATE mv_user SET loginTime=NOW() WHERE uid=#{uid}")
    int updateLoginTime(int uid);

    @Update("UPDATE mv_user SET myCode=#{myCode} WHERE uid=#{uid}")
    int addMyCode(@Param("myCode")String myCode,@Param("uid")int uid);

    @Update("UPDATE mv_user SET seeCoinTotal=seeCoinTotal+#{add},seeCoinNow=seeCoinNow+#{add} WHERE uid=#{uid}")
    int addCoin(@Param("uid")Integer uid,@Param("add")Integer add);

    @Update("UPDATE mv_user SET inviteId=#{inviteId} WHERE uid=#{uid}")
    int addInviteId(@Param("uid")Integer uid,@Param("inviteId")Integer inviteId);

    @Update("UPDATE mv_user SET seeCoinNow=seeCoinNow-#{minus} WHERE uid=#{uid}")
    int minusCoin(@Param("uid")Integer uid,@Param("minus")Integer minus);


}
