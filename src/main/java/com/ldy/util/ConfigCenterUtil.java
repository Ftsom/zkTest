package com.ldy.util;

import com.jd.pop.configcenter.client.ConfigCenterClient;
import com.jd.pop.configcenter.client.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by liudongyang8 on 2020/5/9
 */
@Component
public class ConfigCenterUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfigCenterUtil.class);

    @Autowired
    private ConfigCenterClient configCenterClient;

    public String getContentFromConfigCenter(String configKey) throws Exception {
        if (StringUtils.isBlank(configKey))
            throw new Exception("ConfigCenterUtil-->getContentFromConfigCenter 请求的参数为空 configKey:" + configKey);
        String configContent = configCenterClient.get(configKey);
        logger.info("ConfigCenterUtil-->getContentFromConfigCenter get configKey from configCenter is:" + configContent);
        if (StringUtils.isBlank(configContent))
            throw new Exception("ConfigCenterUtil-->getContentFromConfigCenter 获取配置中心数据异常 configKey:" + configKey);
        return configContent;
    }
}
