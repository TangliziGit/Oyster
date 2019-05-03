# Oyster  

This is a simple blog for oysters, based on spring-boot, spring-mvc, spring-data-jpa and thymeleaf.

这是一个简单个人博客，采用多模块构建，主要技术栈为spring-boot, spring-mvc, spring-data-jpa和thymeleaf

## Getting Started

### Prerequisites

jdk 1.8 or later

### Run

```
mvn clean package -Dmaven.test.skip=true
java -jar blog-0.0.1-SNAPSHOT.jar --server.port=80
```

## Built With

- Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Flexmark  
    a markdown parser

## TODO List

- front pages
    - [x] search functions  
        *provide multiple search functions on title and content for articles*
    - [ ] more theme  
        *maybe use hexo to generate theme htmls.*
- background management
    - [ ] markdown edit  
        *real time*
- RESTful API
- docker supports



