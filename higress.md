

https://higress.io/zh-cn/blog/30-line-wasm.html


https://github.com/alibaba/higress


https://higress.io/zh-cn/docs/user/wasm-go.html



```yaml
apiVersion: extensions.higress.io/v1alpha1
kind: WasmPlugin
metadata:
  name: mock-response
  namespace: higress-system
spec:
  pluginConfig:
    content: "hello higress 2023/2/14\n"
  url: oci://higress-registry.cn-hangzhou.cr.aliyuncs.com/plugins/demo:1.0.0
```

1.go语言版本需要1.18以上，参考文档https://higress.io/zh-cn/docs/user/wasm-go.html。

2.tinygo版本推荐文档的0.25.0，参考上述文档。

3.镜像推送 docker push 操作，外网镜像仓库无法成功，docker pull操作不受影响。(考虑自建仓库或者内网仓库。即使没有推送上仓库，下发配置 资源中用阿里云的仓库url能访问成功。)

4.下发配置 （文章在mesh部署模式下写的，默认安装模式在非mesh部署）wasmplugin的资源aipversion应改成extentions.higress.io/v1alpha1,同时去掉selector。

