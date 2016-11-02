<%--
  Created by IntelliJ IDEA.
  User: SunXianping
  Date: 2016/8/30 0030
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    li{list-style:none;width:100%;height:104px;font-family: 'Microsoft YaHei';}
    .linePanel {float:left;width:5%;}
    .line{background-color:rgb(204, 204, 204);width:5px;height:40px;}
    .point{width:14px;height:14px;border-radius:10px;background-color:rgb(204, 204, 204);margin:5px -4px;}
    .date{height:67px;}
    .date .text{font-size:20px;color:rgb(204, 204, 204);margin-left:-50px;width:100px;text-align:center;}
    .half{height:20px;}
    .contentPanel{width:95%;height:40px;float:right;}
    .content{position:relative;background-color:rgb(204, 204, 204);width:90%;height:70px;margin-top:25px;margin-left:10%;border-radius:10px;}
    .triangle-left{position:absolute;left:-16px;top:20px;width:0px;height: 0;border-top: 8px solid transparent;border-right: 16px solid rgb(204, 204, 204);border-bottom: 8px solid transparent;}
    .content .text{font-size:16px;color:#fff;padding:5px;font-weight:bold;word-spacing: 2px;}
</style>

<body>
<ul>
    <li>
        <div class="linePanel">
            <div class="line"></div>
            <div class="point"></div>
            <div class="line"></div>
        </div>
        <div class="contentPanel">
            <div class="content">
                <div class="triangle-left"></div>
                <div class="text">20:34 快件离开 武汉中转部已发</div>
            </div>
        </div>
    </li>
    <li>
        <div class="linePanel">
            <div class="line"></div>
            <div class="point"></div>
            <div class="line"></div>
        </div>
        <div class="contentPanel">
            <div class="content">
                <div class="triangle-left"></div>
                <div class="text">20:31 快件到达武汉中转部 上一站是 温州中转部</div>
            </div>
        </div>
    </li>
    <li class="date">
        <div class="linePanel">
            <div class="line half"></div>
            <div class="text">6-21</div>
            <div class="line half"></div>
        </div>
    </li>
    <li>
        <div class="linePanel">
            <div class="line"></div>
            <div class="point"></div>
            <div class="line"></div>
        </div>
        <div class="contentPanel">
            <div class="content">
                <div class="triangle-left"></div>
                <div class="text">21:57 快件离开 温州中转部 已发往 武汉中转部</div>
            </div>
        </div>
    </li>
    <li>
        <div class="linePanel">
            <div class="line"></div>
            <div class="point"></div>
            <div class="line"></div>
        </div>
        <div class="contentPanel">
            <div class="content">
                <div class="triangle-left"></div>
                <div class="text">20:06 快件到达温州中转部 上一站是 上海</div>
            </div>
        </div>
    </li>
</ul>
</body>
</body>
</html>
