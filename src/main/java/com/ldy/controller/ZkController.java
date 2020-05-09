package com.ldy.controller;

import com.ldy.util.ConfigCenterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/zk")
public class ZkController {
    private static Logger logger = LoggerFactory.getLogger(ZkController.class);

    @Autowired
    private ConfigCenterUtil configCenterUtil;

    @RequestMapping("/getData")
    @ResponseBody
    public String getContent(String key) throws Exception {
        logger.info("{} -> getContent key is [{}]", this.getClass().getSimpleName(), key);
        return configCenterUtil.getContentFromConfigCenter(key);
    }

//    @Autowired
//    private ZkUtil zkUtil;
//
//    @Value("${name}")
//    private String name;
//
//    @RequestMapping("/getData")
//    @ResponseBody
//    public String getData(String path) {
//        logger.info("{} -> getData name is [{}]", this.getClass().getSimpleName(), name);
//        return zkUtil.getData(path);
//    }
//
//    @RequestMapping("/getChild")
//    @ResponseBody
//    public List<String> getChild(String path) {
//        return zkUtil.getChild(path);
//    }
//
//    @RequestMapping("/createNode")
//    @ResponseBody
//    public String createNode(String path, String data) {
//        return zkUtil.createNodeP(path, data.getBytes(), true);
//    }
}
