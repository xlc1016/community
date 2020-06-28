# community
xlc community
 # bootstrop 地址
https://v3.bootcss.com
# github download
https://git-scm.com/downloads
# github 官网
https://github.com
#gitHub   授权码登录 url
https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/

#tools 画图
https://www.visual-paradigm.com

#okHttp
https://square.github.io/okhttp/calls/
# H2 数据库
www.h2database.com
# spring boot 默认 jdbc 
https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-features-embedded-database
# spring 注解
@Configuration是spring.xml的注解版。
@ComponentScan是<context:component-scan base-package="com.coshaho.*" />标签的注解版。
@ImportResource @Import是<import resource>标签的注解版。
@PropertySource是<context:property-placeholder location="classpath:jdbc.properties"/>标签的注解版。
@Bean是<bean>标签的注解版。
@EnableTransactionManagement是tx:annotation-driven标签的注解版。
# 创建User sql
CREATE TABLE USER (
	id INT auto_increment PRIMARY KEY NOT NULL,
	accountId VARCHAR (100),
	NAME VARCHAR (50),
	token VARCHAR (36),
	gmtCreate BIGINT,
	gmtModified BIGINT,
	avatarUrl VARCHAR (100)
);



