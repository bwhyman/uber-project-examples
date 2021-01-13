# uber-project-examples
### Introduction

基于springboot/spring-cloud-gateway/spring-cloud-alibaba-nacos/docker等的开发环境/生产环境流程演示

视频演示网址
https://mooc1-1.chaoxing.com/nodedetailcontroller/visitnodedetail?courseId=208931964&knowledgeId=394488338


### update 2021.01.13
上传nacos容器配置  
nacos应该是为多项目提供注册发现服务，不必每个项目单启1个nacos容器管理。
因此，取消在nacos容器中创建项目网络，修改微服务容器的启动配置，直接传入宿主IP给nacos注册地址。
项目的多服务容器，依然声明使用相同的网络。
