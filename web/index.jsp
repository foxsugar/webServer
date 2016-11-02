<%--
  Created by IntelliJ IDEA.
  User: SunXianping
  Date: 2016/7/28 0028
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script src="js/jquery-3.1.0.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>

  <div>

    <form action="/webServer/hello" method="get">
      名：
      <input type="text" name="firstname" id="firstname">
      <br />
      姓：
      <input type="text" name="lastname">
      <input type="submit" value="送出">
    </form>
    <button id="button1">向页面发送 HTTP GET 请求，然后获得返回的结果</button>
    <button id="button2">test</button>
  </div>


  <script>

    $(document).ready(function(){
      $("#button1").click(function(){
        var d = {};
        d.name =  $("input[name='firstname']").val();
        d.name = $("#firstname").val();
        console.log(d);
//        $("input[name='firstname']")
//        $("[name='firstname']");
        $.get("/webServer/hello",d,function(data,status){
//          alert("数据：" + data + "\n状态：" + status);
          console.log(data)
        });
      });
    });

  </script>
  </body>
</html>
