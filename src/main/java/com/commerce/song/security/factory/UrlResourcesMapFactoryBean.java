package com.commerce.song.security.factory;

import com.commerce.song.service.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * DB로부터 자원을 가지고 와서 그 자원과 권한정보를 매핑된 데이터를 가지고 있음
 */
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
    private SecurityResourceService securityResourceService;
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {

        if(resourceMap == null) {
            init();
        }
        return resourceMap;
    }

    private void init() {
        resourceMap = securityResourceService.getResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }
    // 싱글톤 적용. 메모리에 하나만.
    @Override
    public boolean isSingleton() {
        return true;
    }
}
