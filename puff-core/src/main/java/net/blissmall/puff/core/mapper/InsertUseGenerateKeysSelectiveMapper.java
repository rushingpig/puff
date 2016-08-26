package net.blissmall.puff.core.mapper;

import net.blissmall.puff.core.persistence.provider.InsertUseGenerateKeysSelectiveProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/16 19:50
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public interface InsertUseGenerateKeysSelectiveMapper<T> {

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = InsertUseGenerateKeysSelectiveProvider.class, method = "dynamicSQL")
    int insertUseGeneratedSelectiveKeys(T record);
}
