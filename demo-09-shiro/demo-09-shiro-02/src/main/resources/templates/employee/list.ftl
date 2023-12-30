<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
</head>
<body>
<center>
    <h1>员工列表</h1>
    <a href="/employee?cmd=input">新增</a>
    <br>
    <table border="1">
        <tbody>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>邮箱</th>
            <th>年龄</th>
            <th></th>
        </tr>
        <tr>
            <td>1</td>
            <td>tom</td>
            <td>tom@520it.com</td>
            <td>20</td>
            <td>
                <a href="/employee?cmd=input&id=1">编辑</a>
                <a href="/employee?cmd=delete">删除</a>
            </td>
        </tr>
        </tbody>
    </table>
</center>
</body>
</html>