# Puff
> the backend of the xfxb shop

```


_________   _...._
\        |.'      '-.               _.._     _.._
 \        .'```'.    '.           .' .._|  .' .._|
  \      |       \     \          | '      | '
   |     |        |    |_    _  __| |__  __| |__
   |      \      /    .| '  / ||__   __||__   __|
   |     |\`'-.-'   .'.' | .' |   | |      | |
   |     | '-....-'`  /  | /  |   | |      | |
  .'     '.          |   `'.  |   | |      | |
'-----------'        '   .'|  '/  | |      | |
                      `-'  `--'   |_|      |_|
```



## Description
商城后台系统
## Environments
* JDK 1.8+
* gradle (2.12+)
* MySQL 5.6+
* Redis 3.x
## Technology Stack
* 核心框架 : Spring Boot
* 安全框架 : Shiro / Spring Security [oAuth2]
* MVC : Spring MVC
* 参数校验 : Hibernate Validator
* 持久层框架 : Mybatis
* 缓存框架 : Ehcache && Redis
* 日志管理 : log4j2
* 工具类 : Apache Commons , Jackson Json , Guava
* 构建工具 : Gradle
* API : Restful
* 分布式session : spring session + redis
* 多缓存集成 + 分布式缓存 : spring cache
* 视图渲染 : thymeleaf3.0
## Quick Start
`cd puff` && `gradle bootRun`

## Contributing
#### Contributors
<zhenglin.zhu@xfxb.net>