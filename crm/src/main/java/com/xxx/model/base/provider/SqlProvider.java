package com.xxx.model.base.provider;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.Map;

public class SqlProvider {

    public String bind_before(Map<String, Object> map){
        String type = map.get("type").toString();
        StringBuffer sql = new StringBuffer("SELECT id FROM mt_user WHERE");
        if("1".equals(type)){
            sql.append(" qq_token = '"+map.get("token")+"'");
        }else if("2".equals(type)){
            sql.append(" wb_token = '"+map.get("token")+"'");
        }else if("3".equals(type)){
            sql.append(" wx_token = '"+map.get("token")+"'");
        }
        return sql.toString();
    }

    public String getTopByCatId(Map<String, Object> map){
        StringBuffer sql = new StringBuffer("SELECT id,fbt,thumb,description,inputtime FROM "+map.get("table"));
        sql.append(" WHERE posid = 1");
        if(Integer.parseInt(map.get("id").toString())!=0){
            if("1".equals(map.get("type"))){
                sql.append(" AND parentid = "+map.get("id"));
            }else{
                sql.append(" AND catid = "+map.get("id"));
            }
        }
        sql.append(" ORDER BY inputtime DESC LIMIT 0,1");
        return sql.toString();
    }

    public String getCategoryById(Map<String, Object> map){
        StringBuffer sql = new StringBuffer("SELECT catid,catname,image FROM mt_category");
        sql.append(" WHERE parentid = "+map.get("id"));
        if(!"".equals(map.get("limit"))){
            sql.append(" ORDER BY catid ASC LIMIT "+map.get("limit"));
        }
        return sql.toString();
    }

    public String getListByCatId(Map<String, Object> map){
        String type = map.get("type").toString();
        StringBuffer sql = new StringBuffer("SELECT id,title,fbt,thumb,description,inputtime FROM "+map.get("table"));
        if("1".equals(type)||"2".equals(type)){
            sql.append(" WHERE parentid = "+map.get("id"));
        }else{
            sql.append(" WHERE catid = "+map.get("id"));
        }
        if("2".equals(map.get("posids"))){
            sql.append(" AND posid = 2");
        }else if ("5".equals(map.get("posids"))){
            sql.append(" AND posid != 0");
        }
        sql.append(" AND inputtime <= "+ new Date().getTime()/1000);
        sql.append(" ORDER BY listorder DESC,inputtime DESC LIMIT "+map.get("limit"));
        return sql.toString();
    }

    public String getDetails(Map<String, Object> map){
        return new SQL() {
            {
                SELECT("a.id,a.title,a.keywords,a.username,a.copyfrom,a.inputtime,a.catid,a.pageview,a.thumbsup,b.content");
                FROM(map.get("table")+" AS a");
                LEFT_OUTER_JOIN(map.get("table")+"_data AS b on a.id=b.id");
                WHERE("a.id=" + map.get("id"));
            }
        }.toString();
    }

    public String likes(Map<String, Object> map){
        return new SQL() {
            {
                UPDATE(map.get("table").toString());
                SET("thumbsup = thumbsup+1");
                WHERE(map.get("id").toString());
            }
        }.toString();
    }

    public String select(int id){
        return new SQL() {
            {
                SELECT("id, name, email");
                FROM("demo");
                WHERE("id=" + id);
                ORDER_BY("");
            }
        }.toString();
    }

}
