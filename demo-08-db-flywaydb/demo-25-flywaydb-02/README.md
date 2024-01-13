





报错：`select command denied to user for table 'user_variables_by_thread'`

解决：

```sql
-- 为对应的帐户添加相关的权限
GRANT SELECT ON performance_schema.user_variables_by_thread TO 'supply_suyh'@'%';
flush privileges;
```

