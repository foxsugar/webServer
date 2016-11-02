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
    <title>用户列表</title>

    <jsp:include page="base.jsp"/>
    <!-- DataTables -->
    <script src="../admin/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="../admin/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="../admin/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="../admin/plugins/fastclick/fastclick.js"></script>

    <%--<script src="../Editor-1.5.6/js/dataTables.editor.js"></script>--%>
    <%--<script src="../Editor-1.5.6/js/editor.jqueryui.js"></script>--%>
    <%--<script src="../Editor-1.5.6/js/editor.bootstrap.js"></script>--%>
    <%--<script src="../Editor-1.5.6/js/editor.foundation.js"></script>--%>

    <%--<link rel="stylesheet" href="../Editor-1.5.6/css/dataTables.editor.css">--%>
    <%--<link rel="stylesheet" href="../Editor-1.5.6/css/editor.foundation.css">--%>
    <%--<link rel="stylesheet" href="../Editor-1.5.6/css/editor.bootstrap.css">--%>
    <%--<link rel="stylesheet" href="../Editor-1.5.6/css/editor.dataTables.css">--%>
    <%--<link rel="stylesheet" href="../Editor-1.5.6/css/editor.jqueryui.css">--%>


</head>
<body>
<div class="box">

    <div class="box-header">
        <h3 class="box-title">人员列表</h3>
    </div>

    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_edit" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>

    <div class="box-body">
        <table id="datatable" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>用户</th>
                <th>密码</th>
                <th>操作</th>
            </tr>
            <thead>
        </table>
    </div>
</div>

</body>

<script>

    function initTable() {
        $("#datatable").dataTable({
            dom: "Bfrtip",
            "processing": true,
            "serverSide": true,

            "paging": true,
            "lengthChange": false,
            "searching": false,
//            "ordering": true,
            "info": true,
            "autoWidth": false,

            "ajax": "/webServer/listUser",
            "columns": [
                {"data": "id", "bSortable": false},
                {"data": "name"},
                {"data": "passwd"}
            ],
            "columnDefs": [
                {
                    "targets": [3],
                    "data": "id",
                    "render": function (data, type, full) {
                        return "<a href='/update?id=" + data + "'>Update</a>";
                    }
                },
                {
                    "targets": [3],
                    "data": "id",
                    "render": function (data, type, full) {
                        return "<a href='/update?id=" + data + "'>Update1</a>";
                    }
                }
            ],
            "select": true,
//            "buttons": [
//                {extend: "create", editor: editor},
//                {extend: "edit", editor: editor},
//                {extend: "remove", editor: editor}
//            ]
        });
    }


    function table_bootstarp() {
        $("#datatable").bootstrapTable({
            url: '/webServer/listUser',


            method: 'get',      //请求方式（*）
            toolbar: '#toolbar',    //工具按钮用哪个容器
            striped: true,      //是否显示行间隔色
            cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,     //是否显示分页（*）
            sortable: false,      //是否启用排序
            sortOrder: "asc",     //排序方式
//            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,      //初始化加载第一页，默认第一页
            pageSize: 10,      //每页的记录行数（*）
            pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
            search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,     //是否显示所有的列
            showRefresh: true,     //是否显示刷新按钮
            minimumCountColumns: 2,    //最少允许的列数
            clickToSelect: true,    //是否启用点击选中行
            height: 550,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",      //每一行的唯一标识，一般为主键列
            showToggle: true,     //是否显示详细视图和列表视图的切换按钮
            cardView: false,     //是否显示详细视图
            detailView: false,     //是否显示父子表
//            height: getHeight(),


            columns: [{
                field: 'id',
                title: 'Item ID'
            }, {
                field: 'name',
                title: 'Item Name',
                editable: true
            }, {
                field: 'passwd',
                title: 'Item Price',
                editable: true
            }]
        });

    }


    $(document).ready(function () {
                initTable();







            }
    );

</script>

</html>
