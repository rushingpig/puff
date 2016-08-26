package net.blissmall.puff.core.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>,InsertUseGenerateKeysSelectiveMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
    enum DelFlag{
        /**
         *
         */
        DELETED(false),
        VALID(true);

        private boolean status;
        private DelFlag(boolean status){
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}