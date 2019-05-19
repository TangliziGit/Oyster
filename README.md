# Oyster  

This is a simple blog for oysters, built with Spring Boot, Spring Data JPA and Thymeleaf.

这是一个简单个人博客，采用多模块构建，主要运用spring-boot, spring-data-jpa and thymeleaf.

## 如何使用

### 环境需求

- jdk 1.8 (or later)

### 部署方法

直接运行jar，正经部署还没怎么考虑

### 运行方法

```
git clone https://github.com/TangliziGit/Oyster oyster
cd oyster
mvn clean install
java -jar oyster-runner/target/oyster-runner-0.0.1-SNAPSHOT.jar
```

## 开发细节

### 架构

待补

### 使用的框架

- Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf

## TODO List

- 公共模块
    - [x] AbstractQuery查询  
        *通过注解封装一部分JPA动态查询功能，提供方便使用的多重查询*
- 前台页面模块
    - [x] 灵活的文章查找  
        *支持文章标题和内容的多重模糊查询*
    - [ ] 更多主题  
        *可能尝试调用hexo解析hexo主题模板*
- 后台管理模块
    - [ ] markdown支持插入图片
    - [ ] 实时编辑markdown  
- RESTful API模块
    - [x] RESTful API规范
        *遵守状态码，安全与幂等等规范*
    - [x] 对提交评论和文章点击量的限制  
        *包括提交内容判误、提交频率、一段时间同ip不增加点击量、跨域提交*
- docker支持

