package com.chun.lei.sql;

import com.chun.lei.entity.Tip;
import com.chun.lei.entity.reqvo.ResSearch;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Created by lcl on 2020/5/7 0007
 */
public class SysResourceSql {

    public String selectSql(ResSearch resSearch) {
        return new SQL(){{
            SELECT("*");
            FROM("mv_resource");
            if(resSearch.getKey()!=null&&!"".equals(resSearch.getKey())){
                WHERE("rTitle LIKE concat('%',#{key},'%')");
            }
            if(!resSearch.getCid().equals(0)){
                WHERE("rType=#{cid}");
            }
            WHERE("lookState='1'");
        }}.toString()+" ORDER BY id DESC LIMIT "+((resSearch.getPageNum()-1)*10)+",10";
    }

}
