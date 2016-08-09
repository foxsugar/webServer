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
  </head>
  <body>

  <div>
    <button>向页面发送 HTTP GET 请求，然后获得返回的结果</button>
  </div>


  <script>
    $(document).ready(function(){
      $("button").click(function(){
        $.get("/webServer/hello",function(data,status){
          alert("数据：" + data + "\n状态：" + status);
        });
      });
    });

  </script>
  </body>
</html>
