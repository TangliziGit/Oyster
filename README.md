# SimpleBlog  

This is a simple blog implemented with Spring Boot, Spring Data JPA, Thymeleaf and Flexmark.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development.

### Prerequisites

jdk 1.8 or later

### How to run

```
mvn clean package -Dmaven.test.skip=true
java -jar blog-0.0.1-SNAPSHOT.jar --server.port=80
```
Then you can check your blog on 80 port.

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
    - [ ] search functions  
        *provide multiple search functions on title, content and etc., for articles and comments*
    - [ ] more theme  
        *maybe use hexo to generate theme htmls.*
- background management
    - [ ] markdown edit  
        *real time*
- RESTful API
- docker supports



