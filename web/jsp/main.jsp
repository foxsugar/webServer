<%--
  Created by IntelliJ IDEA.
  User: SunXianping
  Date: 2016/8/25 0025
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="base.jsp"/>
</head>
<body>
<%--<%@ include file="head.jsp"%>--%>




<div class="container bs-docs-container">
    <div class="row">
        <div class="col-md-3">
            <div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm" role="complementary">
                <ul class="nav bs-docs-sidenav">
                    <li>
                        <a href="#overview">概览</a>
                        <ul class="nav">
                            <a href="listUserNew.jsp" target="main">每日编报</a>
                            <li><a href="listUser.jsp" target="main">用户列表</a></li>
                            <li><a href="head.jsp" target="main">关键词匹配</a></li>

                        </ul>
                    </li>
                </ul>
            </div>
        </div>

        <div class="col-md-9" role="main"  >

            <div class="bs-docs-section">
                <h1 id="overview" class="page-header">概览</h1>
                <iframe src=""  scroling="yes"  width="100%" height="300%" name="main" frameborder="0">
                </iframe>
                <div class="highlight"></div>
            </div>
        </div>


    </div>
</div>
<%--<%@ include file="foot.jsp"%>--%>
</body>

</html>
