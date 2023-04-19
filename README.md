# README

> 本项目是基于 Springboot3.0 的二手书销售平台.

## 运行环境需求

- springboot 3.0
- springcloud gateway 4.0.4
- mysql 8.0+
- java 17+

## 项目技术介绍

本项目使用 `druid` 作为数据库连接池，本项目使用 `yfSql` 作为 `orm` 工具而不是 `mybatis`。
`yfSql` 是一个轻量级的 `orm` 框架，只需要准备好 `bean` 和 `sql` 即可使用 `java` 操作数据库。

本项目使用 `vue3` 作为前端框架。

本项目使用 springcloud gateway 作为网关进行请求转发，因此在 springboot 里不再需要配置跨域请求.

- 前端 `url`: http://localhost:8080/vue
- 后端 `url`: http://localhost:8080/api