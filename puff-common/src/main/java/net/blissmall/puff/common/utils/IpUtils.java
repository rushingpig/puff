package net.blissmall.puff.common.utils;

import net.blissmall.puff.common.utils.http.RestClient;

public class IpUtils {

    public static String getRegionInfoByIp(String url, String ip)
    {
        url += "?ip=" + ip;
        String resource = RestClient.get(url);
        return resource;
    }

}