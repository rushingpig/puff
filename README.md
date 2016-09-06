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
## Quick Start
### 未安装gradle（推荐）
> 此举也可以保证所有人员使用的gradle版本一致，避免了因为版本问题造成的冲突.在执行命令前，会根据gradle的配置文件下载指定版本

* **windows** 

```bash
cd `pwd` && exec ./bin/gradlew.bat bootRun
```
* **Linux | Unix**

```bash
cd `pwd` && ./bin/gradlew bootRun
```
### 已安装gradle
```bash
gradle bootRun
```

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

## Contributing

#### Contributors
<zhenglin.zhu@xfxb.net>