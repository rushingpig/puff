package net.blissmall.puff.service.impl.regionalism;

import com.google.common.collect.Maps;
import net.blissmall.puff.api.regionalism.RegionalismService;
import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.mapper.regionalism.DictRegionalismMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/8 23:34
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
@CacheConfig(cacheNames = {"regionalism"})
public class RegionalismServiceImpl implements RegionalismService {

    @Resource
    private DictRegionalismMapper dictRegionalismMapper;

    @Override
    @Cacheable(cacheManager = "redisCacheManager",keyGenerator = "puffKeyGenerator")
    public Map<String, String> getRegionalismNameIdMap() {
        DictRegionalism dictRegionalism = new DictRegionalism();
        dictRegionalism.setDelFlag(MyMapper.DelFlag.VALID.getStatus());
        List<DictRegionalism> list = dictRegionalismMapper.select(dictRegionalism);
        Map<String,String> maps = Maps.newConcurrentMap();
        for(DictRegionalism curr : list){
            maps.put(curr.getName(),curr.getId().toString());
        }
        return maps;
    }

    @Cacheable(cacheManager = "redisCacheManager",keyGenerator = "puffKeyGenerator")
    @Override
    public Map<String, String> getRegionalismIdNameMap() {
        DictRegionalism dictRegionalism = new DictRegionalism();
        dictRegionalism.setDelFlag(MyMapper.DelFlag.VALID.getStatus());
        List<DictRegionalism> list = dictRegionalismMapper.select(dictRegionalism);
        Map<String,String> maps = Maps.newHashMap();
        for(DictRegionalism curr : list){
            maps.put(curr.getId().toString(),curr.getName());
        }
        return maps;
    }
}
