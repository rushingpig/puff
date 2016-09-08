package net.blissmall.puff.service.mapper.category;

import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.domain.category.BussProductCategory;
import net.blissmall.puff.vo.category.BussProductCategoryVo;
import org.apache.ibatis.annotations.Param;

public interface BussProductCategoryMapper extends MyMapper<BussProductCategory> {

    BussProductCategoryVo getCategoryWithSort(@Param("bussProductCategory") BussProductCategory bussProductCategory, @Param("regionalismId") int regionalismId);

}