package com.chun.lei.sql;

import com.chun.lei.entity.Tip;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public class TipSql {

    public String updateSql(Tip tip) {
        return new SQL(){{
            UPDATE("mv_tip");
            if(tip.getTipNum()!=null){
                SET("tipNum=#{tipNum}");
            }
            SET("dealStatu=#{dealStatu}");
            SET("dealId=#{dealId}");
            SET("uTime=NOW()");
            WHERE("id=#{id}");
        }}.toString();
    }

}
