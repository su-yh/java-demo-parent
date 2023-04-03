



进入阿里云官网: https://account.aliyun.com/login/login.htm?oauth_callback=https%3A%2F%2Fecs.console.aliyun.com%2Fserver%2Fregion%2Fcn-wulanchabu%3Fpage%3D1%26pageSize%3D20%26__refreshToken%3D1678715487959



找到`容器镜像服务`  选择`个人版`  进入`命名空间`  创建一个命名空间  并将其设置为公开

![](jpg\docker-image.jpg)



进入到`访问凭证` 可以看到登录命令

```bash
docker login --username=su_787******@sina.com registry.cn-qingdao.aliyuncs.com
```

```bash
#把本地镜像，改名，成符合阿里云名字规范的镜像。
# tag 镜像改名
# docker tag [ImageId] <阿里云地址>/<名称空间>/镜像名:[镜像版本号]
docker tag [ImageId] registry.cn-qingdao.aliyuncs.com/k8s-suyh/镜像名:[镜像版本号]
## docker tag 461955fe1e57 registry.cn-hangzhou.aliyuncs.com/lfy_ruoyi/ruoyi-visual-monitor:v1

# docker push <阿里云地址>/<名称空间>/k8s-suyh/镜像名:[镜像版本号]
docker push registry.cn-qingdao.aliyuncs.com/k8s-suyh/镜像名:[镜像版本号]
## docker push registry.cn-hangzhou.aliyuncs.com/lfy_ruoyi/ruoyi-visual-monitor:v1
```



```bash
# 制作镜像
docker build -t demo-swagger:v1.0 -f Dockerfile .
# 查看镜像
docker images
```



推送镜像

```bash
[root@k8s-master ~]# docker images demo-swagger
REPOSITORY     TAG       IMAGE ID       CREATED         SIZE
demo-swagger   v1.0      f88511517d68   2 minutes ago   554MB
[root@k8s-master ~]# docker tag f88511517d68 registry.cn-qingdao.aliyuncs.com/k8s-suyh/demo-swagger-tag:v1.0
[root@k8s-master ~]# docker push registry.cn-qingdao.aliyuncs.com/k8s-suyh/demo-swagger-tag:v1.0
```

这样在阿里云镜像仓库就可以看到我们推送上去的镜像信息了



拉取镜像

```bash
docker pull registry.cn-qingdao.aliyuncs.com/k8s-suyh/demo-swagger-tag:[镜像版本号]
```



