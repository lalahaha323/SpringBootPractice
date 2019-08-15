package com.example.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCustomRealmMd5() {
		//创建securityManager工厂，通过init配置文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("shiro-first.ini");

		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		//将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		//从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("zhangsansan", "111111");
		try{
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否通过验证: " + isAuthenticated);
	}
}
