1. 在worker node中能访问到pod 网络吗？
2. 在worker node 中能访问到service 网络吗？

    service 的网络类型分为ClusterIP 和NodePort

    ClusterIP 是只能集群访问，也就是在worker node 中或者说是在k8s 集群中可以直接访问。

    NodePort 是能被外部任意网络可达的地方访问到。 
3. 其他
