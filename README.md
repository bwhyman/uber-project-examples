# uber-project-examples

### Introduction

基于springboot/spring-cloud-gateway/spring-cloud-alibaba-nacos/docker等开发环境/生产环境流程演示。  

- nacos: 2.3.2  
- spring-cloud-starter-alibaba-nacos-discovery: 2023.0.1.0  
- spring-cloud-starter-gateway: 2023.0.1

必须显式引入loadbalancer依赖，否则基于nacos服务名称的路由无效。Consul则默认引入。

配置包含nacos注册地址，因此必须先运行nacos否则无法完启动。

### Online Tutorials

[视频演示网址。视频主要讲解开发过程，代码已过时，涉及的具体版本代码以本示例为准。](https://mooc1-1.chaoxing.com/nodedetailcontroller/visitnodedetail?courseId=208931964&knowledgeId=394488338)

### Nacos

Nacos定义为一个IDC内部应用组件，并非面向公网环境的产品，建议在内部隔离网络环境中部署，强烈不建议部署在公共网络环境。   
移除健康监控，简化服务构建。  
微服务项目以application.name注册nacos服务名称。  

### user-service

`/api/users`。接收用户登录数据验证密码返回用户数据。

### order-service

`/api/orders`。返回10个order，且从header注入uid数据。

### uber-gateway

spring-cloud-gateway，默认基于webflux异步非阻塞实现  
`/api/order/**`路由到`lb://order-service/api/`  
`/api/user/**`路由到`lb://user-service/api/`  

与单微服务相似  
`/api/login`，登录由gateway处理，调用user-service微服务验证获取用户信息。成功则签发token置于header返回前端。  
LoginGlobalFilter全局拦截器过滤拦截路径验证token，解析出uid/role数据置于header。因为是微服务之间的调用，
不能像单微服务使用request对象。
