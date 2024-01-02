<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>叩丁狼教育-登陆页面</title>
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#submitBtn").click(function(){
                $("#loginForm").submit();
            });
        });
    </script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form id="loginForm" method="post" action="/login">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
            <p class="submit">
                <input type="button" value="登录" id="submitBtn">
                <input type="button" value="重置">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2017 广州狼码教育科技有限公司</p>
</div>
</body>
</html>