package net.blissmall.puff.service.impl.user;

import com.google.common.collect.Maps;
import net.blissmall.puff.api.regionalism.RegionalismService;
import net.blissmall.puff.api.user.UserLogService;
import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.domain.user.LogsUserLogin;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.user.LogsUserLoginMapper;
import net.blissmall.puff.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 用户日志服务类
 * @Author : zhuzhenglin
 * @Date : 16/8/19 01:15
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
public class UserLogServiceImpl extends BaseService implements UserLogService {

    @Value("${puff.ip.host}")
    private String ipHost;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RegionalismService regionalismService;

    @Resource
    private LogsUserLoginMapper logsUserLoginMapper;

    @Override
    @Async
    public Future<Integer> recordLoginLog(LoginVo loginVo, String uuid){
        Map<String,String> queryParams = Maps.newHashMap();
        queryParams.put("ip",loginVo.getIp());
        URI uri = WebUtils.getRestQueryURI(ipHost,queryParams);
        Map<String,String> region = restTemplate.getForObject(uri, Map.class);
        String provinceName = region.get("province");
        String cityName = region.get("city");
        LogsUserLogin logsUserLogin = new LogsUserLogin();
        logsUserLogin.setUuid(uuid);
        logsUserLogin.setAuthId(loginVo.getUsername());
        logsUserLogin.setLoginTime(new Date());
        logsUserLogin.setProvinceId(Integer.valueOf(regionalismService.getRegionalismNameIdMap().get(provinceName)));
        logsUserLogin.setCityId(Integer.valueOf(regionalismService.getRegionalismNameIdMap().get(cityName)));
        Integer result = logsUserLoginMapper.insertUseGeneratedSelectiveKeys(logsUserLogin);
        return new AsyncResult<>(result);
    }
}
