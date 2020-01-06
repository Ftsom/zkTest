package com.ldy.controller;

import com.ldy.util.ZkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zk")
public class ZkController {
    private static Logger logger = LoggerFactory.getLogger(ZkController.class);
    @Autowired
    private ZkUtil zkUtil;

    @Value("${name}")
    private String name;

    @RequestMapping("/getData")
    @ResponseBody
    public String getData(String path) {
        logger.info("{} -> getData name is {}", this.getClass().getSimpleName(), name);
        return zkUtil.getData(path);
    }

    @RequestMapping("/getChild")
    @ResponseBody
    public List<String> getChild(String path) {
        return zkUtil.getChild(path);
    }
}
