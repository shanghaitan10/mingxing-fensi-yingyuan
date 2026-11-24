# 明星粉丝应援系统

基于 Spring Boot 的明星粉丝应援系统，包含前台用户端与后台管理端。

## 技术栈

- 后端：Spring Boot 2.2.2、MyBatis-Plus 2.3、Shiro 1.3.2
- 数据库：MySQL 5.7+
- 前端：Vue（管理员端 `admin`、用户端 `front`，源码与构建产物位于 `src/main/resources`）
- Java：JDK 1.8
- 构建：Maven

## 功能模块

粉丝管理、明星简介、艺人活动、票务信息、活动报名、新闻资讯、优惠券、订单、收藏、评论、系统管理等。

## 本地运行

1. 安装 JDK 1.8、Maven、MySQL 5.7+。
2. 创建数据库 `springboote4e34i32` 并导入 `db/springboote4e34i32.sql`。
3. 修改 `src/main/resources/application.yml` 中的数据库连接账号密码。
4. 启动后端：

```bash
mvn spring-boot:run
```

5. 访问：前台 `http://localhost:8080/springboote4e34i32/`，后台 `/springboote4e34i32/admin/dist/index.html`（以实际路由为准）。

## 说明

`application.yml` 中的数据库密码为本地开发默认值，部署时请改为自己的密码。
