

```properties

# 在spring boot 项目中，可以在启动参数里面指定项目的语言环境、所在区域、以及时区
# 可参考，initDefault() 方法：private volatile static Locale defaultLocale = initDefault();
# 但是要注意这里面好几个initDefault() 方法，断点都 点不到，不知道 它是怎么调用的。
# 以下指定语言环境为日语，参考：Locale.JAPAN
-Duser.language=ja
# 以下指定所在位置(区域)为日本，参考：Locale.JAPAN
-Duser.region=JP

# 这个可以参考：java.util.TimeZone.setDefaultZone()
# 以下指定时区使用中国上海时区，参考：ZoneId
-Duser.timezone=Asia/Shanghai

还可以使用环境变量控制
export TZ=Asia/Shanghai

```


