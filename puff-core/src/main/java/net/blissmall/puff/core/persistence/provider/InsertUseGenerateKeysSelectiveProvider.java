package net.blissmall.puff.core.persistence.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.SpecialProvider;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/16 19:51
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class InsertUseGenerateKeysSelectiveProvider extends SpecialProvider{
    public InsertUseGenerateKeysSelectiveProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 插入，主键id，自增
     *
     * @param ms
     */
    public String insertUseGeneratedSelectiveKeys(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, true, true, true));
        sql.append(SqlHelper.insertValuesColumns(entityClass, true, true, true));
        return sql.toString();
    }
}
