# uber-project-examples

### Introduction
基于springboot/spring-cloud-gateway/spring-cloud-alibaba-nacos/docker等的开发环境/生产环境流程演示。

### Nacos
Nacos定义为一个IDC内部应用组件，并非面向公网环境的产品，建议在内部隔离网络环境中部署，强烈不建议部署在公共网络环境。  
docker image: nacos/nacos-server:v2.3.0。  
移除健康监控，简化服务构建。
项目以application.name注册nacos服务名称。  

### uber-order-service
order微服务中，spring-cloud-starter-alibaba-nacos-discovery:2022.0.0版本仅支持到springboot:3.0.X，不支持3.2.X。  

### uber-gateway
必须显式引入spring-cloud-starter-loadbalancer依赖，否则基于nacos服务名称的路由无效。  
spring-cloud-starter-gateway:2023.0.0必须对应springboot 3.2.X。
spring-cloud-gateway，默认基于webflux异步非阻塞实现，


### Online Tutorials
[视频演示网址。视频主要讲解开发过程，代码已过时，涉及的具体版本代码以本示例为准。](https://mooc1-1.chaoxing.com/nodedetailcontroller/visitnodedetail?courseId=208931964&knowledgeId=394488338)
