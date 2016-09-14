package net.blissmall.puff.api.regionalism;

import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/8 21:57
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public interface RegionalismService {

    Map<String,String> getRegionalismIdNameMap();

    Map<String,String> getRegionalismNameIdMap();

    void initRegionalism();
}
