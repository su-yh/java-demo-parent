package cn.wolfcode.shiro;

import cn.wolfcode.shiro.realm.UserRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.security.krb5.Realm;

import java.util.List;

@SpringBootApplication
public class BootApplication {
	/*
    创建安全管理器对象,关联自定义Realm
     */
	@Bean
	public DefaultWebSecurityManager securityManager(UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	/**
	 * 定义拦截的规则
	 * @return
	 */
	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition(){
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		//浏览器标签页的图标放行.
		chainDefinition.addPathDefinition("/**/favicon.ico","anon");
		//静态资源放行
		chainDefinition.addPathDefinition("/js/**","anon");
		chainDefinition.addPathDefinition("/html/**","anon");
		chainDefinition.addPathDefinition("/css/**","anon");
		//剩下的资源都需要登录才能访问
		chainDefinition.addPathDefinition("/**","authc");
		return chainDefinition;
	}
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
