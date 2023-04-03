#传入构建参数
docker build --no-cache --build-arg param="11 22 33" msg="aa bb cc" -t demo:test -f Dockerfile4 .

docker build --no-cache -t demo-swagger:v1 -f Dockerfile .


#进入容器控制台
docker exec -it mydemo1 /bin/sh

# 移除游离镜像，出现提示后输入y 就可以了
docker image prune

docker create [OPTIONS] IMAGE [COMMAND] [ARG...]
docker create [设置项] 镜像名 [启动] [启动参数...]
docker create redis: 按照redis:latest镜像启动一个容器

# 示例：
docker create demo-swagger:v1 --name=demo-swagger-v1

# 创建之后需要启动
[root@iZ0jl918c8fwg523zctb8tZ java-demo-springboot]# docker ps -a 
CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS                        PORTS     NAMES
2bb4fb961cf4   demo-swagger:v1   "sh -c 'java -Djava.…"   12 seconds ago   Created                                 sweet_nobel
[root@iZ0jl918c8fwg523zctb8tZ java-demo-springboot]# 

# 启动
docker start 2bb4fb961cf4


docker kill  是强制kill -9（直接拔电源）；
docker stop   可以允许优雅停机(当前正在运行中的程序处理完所有事情后再停止)
