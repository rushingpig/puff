package net.blissmall.puff.web.interceptor;

import net.blissmall.puff.common.utils.IpUtils;
import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegionInterceptor implements HandlerInterceptor {

    @Value("${puff.cookie.maxAge}")
    private String BROWNIE_IP_URL = "http://brownie.xfxb.net/ip";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DictRegionalism userRegion = WebUtils.getSessionAttribute(request.getSession(), PuffNamedConstant.USER_REGION, DictRegionalism.class);
        //设置所属区域
        if (userRegion == null) {
            System.out.println("------------------region interceptor-----------------");
            String ip = request.getRemoteAddr();
            String info = IpUtils.getRegionInfoByIp(BROWNIE_IP_URL, ip);
            System.out.println(info);
//            需要转换成数据库对应区域名称和id
//            如不能转换则按照一定策略设置默认区域
//            IpDict ipDict = new JSONUtils().fromJson(info, IpDict.class);
            userRegion = new DictRegionalism();
            userRegion.setId(440300);
            userRegion.setName("深圳");
            request.getSession().setAttribute(PuffNamedConstant.USER_REGION, userRegion);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    class IpDict {

        String province;
        String city;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
