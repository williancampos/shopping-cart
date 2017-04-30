package com.williancampos.shoppingcart.authentication;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfig {

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(final org.apache.shiro.mgt.SecurityManager manager) {
	    final ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
	    final Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
	    filterChainDefinitionMapping.put("/rest/**", "anon");
	    shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
	    shiroFilter.setSecurityManager(manager);
	    final Map<String, Filter> filters = new HashMap<String, Filter>();
	    filters.put("anon", new AnonymousFilter());
	    shiroFilter.setFilters(filters);
	    return shiroFilter;
	}
	
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager(Realm realm) {
	    final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm) ;
	    return securityManager;
	}

	@Bean
    public Realm realm() {
        final Realm realm = new AuthenticatingRealm() {
			@Override
			protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
				return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), "REALM");
			}
		};
	    return realm;
    }

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	    return new LifecycleBeanPostProcessor();
	}
}
