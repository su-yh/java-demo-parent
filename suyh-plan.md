





> mybatis plus 如果对entity 中的某个Boolean 类型的属性使用typeHandler 可以 进行存储与读取。
>
> 比如将Boolean.TRUE 存储为true，以及从数据库中读取true 遇到为Boolean.TRUE 对象，是没有问题的。
>
> 但是如果进行where 条件判断就会有问题。
>
> 我估计是LambdaQueryWrapper 是以固定的方式处理，所以我想mybatis plus 是否有提供扩展可以自定义 where 解析.
>
> 在调用 wrapper.eq(User::isActive, true); 这种的时候是否可以自定义。处理成 'where active = "true"' 或者 'where active = "false"'





