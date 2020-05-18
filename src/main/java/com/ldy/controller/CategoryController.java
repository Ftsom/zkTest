package com.ldy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create by liudongyang8 on 2020/5/18
 */
@Controller
@RequestMapping("/ct")
public class CategoryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getCts")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from market_category";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }
        }
        return list;
    }
}
