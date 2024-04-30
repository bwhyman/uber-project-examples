# uber-project-examples

### Introduction
基于springboot/spring-cloud-gateway/spring-cloud-alibaba-nacos/docker等的开发环境/生产环境流程演示。  
- nacos: 2.3.2  
- spring-cloud-starter-alibaba-nacos-discovery: 2023.0.1.0  
- spring-cloud-starter-gateway: 2023.0.1

必须显式引入loadbalancer依赖，否则基于nacos服务名称的路由无效。Consul默认引入。

### Online Tutorials
[视频演示网址。视频主要讲解开发过程，代码已过时，涉及的具体版本代码以本示例为准。](https://mooc1-1.chaoxing.com/nodedetailcontroller/visitnodedetail?courseId=208931964&knowledgeId=394488338)

### Nacos
Nacos定义为一个IDC内部应用组件，并非面向公网环境的产品，建议在内部隔离网络环境中部署，强烈不建议部署在公共网络环境。   
移除健康监控，简化服务构建。
项目以application.name注册nacos服务名称。  

### uber-order-service
`/api/orders`

### uber-gateway
将`/order-api/`路由到`/uber-order-service/api/`  

spring-cloud-gateway，默认基于webflux异步非阻塞实现。  

