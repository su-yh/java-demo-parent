#!/bin/bash

# 所有机器实例都需要执行的脚本文件

# 安装docker

# 先删除安装过的docker
yum remove docker*

# 配置yum 源
yum install -y yum-utils
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 安装docker
# yum install -y docker-ce docker-ce-cli containerd.io
# 安装指定版本
yum install -y docker-ce-20.10.7 docker-ce-cli-20.10.7  containerd.io-1.4.6

# 启动（同时开机启动）
systemctl enable docker --now

sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://inpq4kkk.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload
sudo systemctl restart docker

