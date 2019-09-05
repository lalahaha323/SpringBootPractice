package com.example.mall2.config;

/**
 * @author lala
 */
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfiguration {

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 密码编码
     * @return
     */
    @Bean(name = "hashCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法的名称
        credentialsMatcher.setHashAlgorithmName("MD5");
        //配置加密的次数
        credentialsMatcher.setHashIterations(2);
        //是否存储为16进制
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean(name = "myRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public MyRealm myRealm(){
        MyRealm realm = new MyRealm();
//        realm.setCachingEnabled(true);
//        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
//        realm.setAuthenticationCachingEnabled(true);
//        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
//        realm.setAuthenticationCacheName("authenticationCache");
//        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
//        realm.setAuthorizationCachingEnabled(true);
//        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
//        realm.setAuthorizationCacheName("authorizationCache");
        //配置自定义密码比较器
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager(){
        return new EhCacheManager();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        //用户授权/认证信息Cache, 采用EhCache缓存
        securityManager.setCacheManager(ehCacheManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        //配置记住我或认证通过可以访问的地址
        filterChainMap.put("/index", "user");
        filterChainMap.put("/", "user");

        /**
         * authc：该过滤器下的页面必须验证后才能访问，他是Shiro内置的一个拦截器
         * anno：它对应的过滤器里面是空的，什么都没做，可以理解为不拦截
         * authc：所有url都必须认证通过才可以访问
         * anno：所有url都可以匿名访问
         */
        //输入http://localhost:8080/myEra/tUser会跳到登录页面
//        filterChainMap.put("/tUser", "authc");

        filterChainMap.put("/permission/userInsert", "anon");
        filterChainMap.put("/error", "anon");
        filterChainMap.put("/tUser/insert","anon");
        filterChainMap.put("/gif/getGifCode","anon");
        filterChainMap.put("/**", "authc");
//        Map<String, Filter> filters = new LinkedHashMap<>();
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/login");
//        shiroFilterFactoryBean.setFilters(filters);
//
//        Map<String, String> filterChainDefinitionManger = new LinkedHashMap<>();
//        //logout直接加载logout
//        filterChainDefinitionManger.put("/logout", "logout");
//        //访问index下url需要authentication
//        filterChainDefinitionManger.put("/index", "authc, perms[read]");
//        filterChainDefinitionManger.put("/admin", "authc, perms[write]");
//        filterChainDefinitionManger.put("/**", "anon");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManger);
//
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;

    }

    /**
     * cookie对象
     * rememberMeCookie()方法是设置Cookie的生成模板，比如cookie的name，cookie的有效时间等等
     */
    @Bean("rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间30天，单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象
     * rememberMeManager()方法是生成rememberMe管理器，并且要将这个rememberMe管理器设置到securityManager中
     */
    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥，默认AES算法，密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

}
