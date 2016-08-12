package net.blissmall.puff.core.shiro;

import net.blissmall.puff.common.utils.EncodeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @Author : pigo
 * @Date : 16/4/16 下午11:18
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Component
@Lazy(false)
public class IdGenUtils implements SessionIdGenerator {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 基于Base62编码的SecureRandom随机生成bytes.
     */
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return EncodeUtils.encodeBase62(randomBytes);
    }

    @Override
    public Serializable generateId(Session session) {
        return IdGenUtils.uuid();
    }

    public static void main(String[] args) {
        System.out.println(IdGenUtils.uuid());
        System.out.println(IdGenUtils.uuid().length());
        for (int i = 0; i < 1000; i++) {
            System.out.println(IdGenUtils.randomLong() + "  " + IdGenUtils.randomBase62(5));
        }
    }

}
