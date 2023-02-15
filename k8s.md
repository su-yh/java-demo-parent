





我本地虚拟机上安装的kubesphere，虚拟机名为：`CentOS7-Docker-kubesphere`

```txt
#####################################################
###              Welcome to KubeSphere!           ###
#####################################################

Console: http://192.168.127.143:30880
Account: admin
Password: P@88w0rd

NOTES：
  1. After you log into the console, please check the
     monitoring status of service components in
     "Cluster Management". If any service is not
     ready, please wait patiently until all components 
     are up and running.
  2. Please change the default password after login.

#####################################################
https://kubesphere.io             2023-02-13 21:44:59
#####################################################
INFO[21:45:13 CST] Installation is complete.

Please check the result using the command:

       kubectl logs -n kubesphere-system $(kubectl get pod -n kubesphere-system -l app=ks-install -o jsonpath='{.items[0].metadata.name}') -f
```

改密码：

```txt
K3bdp-2023
```

忘记密码，可以使用命令改密码：
将admin 密码改为：admin
```txt
kubectl patch users admin -p '{"spec":{"password":"admin"}}' --type='merge' && kubectl annotate users admin iam.kubesphere.io/password-encrypted-
```

