package com.chun.lei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Repository
public interface UserSearchMapper {

    @Insert("INSERT INTO mv_user_search(`uid`, `keyWord`, `cTime`) VALUES (#{uid},#{keyWord},NOW())")
    int insertOne(@Param("uid")Integer uid,@Param("keyWord")String keyWord);

}
