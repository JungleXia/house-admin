<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 工单管理 iframe 框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
</head>
<body>
<#if curBroker??>
<form class="layui-form" style="width:80%;" >
  <div class="layui-form" lay-filter="layuiadmin-form-order" id="layuiadmin-form-order" style="padding: 20px 30px 0 0;">

    <div class="layui-form-item">
      <label class="layui-form-label">客戶ID</label>
      <div class="layui-input-inline">
        <input type="text" lay-verify="required" value="${customer.customerId}" disabled autocomplete="off" class="layui-input">
        <input type="text" name="userId" lay-verify="required" value="${customer.id}"  autocomplete="off" class="layui-input" style="display:none">
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label">专属经纪人</label>
      <div class="layui-input-inline">
        <input type="text" name="brokerName" lay-verify="required" value="${curBroker.name}" disabled autocomplete="off" class="layui-input">
        <input type="text" name="brokerId" lay-verify="required" value="${curBroker.id}"  autocomplete="off" class="layui-input" style="display:none">
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label">记录描述</label>
      <div class="layui-input-inline">
        <textarea name="description" lay-verify="required" autocomplete="off" class="layui-textarea" style="width:400px;" maxlength="250">
        </textarea>
      </div>
    </div>
    

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">确定</button>
        </div>
    </div>

  </div>
</form>
</#if>

<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
  <script>
    layui.use(['form','jquery','layer', 'table'],function(){
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t,changeIndex;                  //表格数据变量
           
        t = {
            elem: '#test',
            url:'${base}/admin/broker/documentary/list?s_userId=${customer.id}' ,
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                limit: 20,
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[3,10, 20, 30]
            },
            width: $(parent.window).width()-223,
            cols: [[
                {type:'checkbox'},
                {field:'brokerName',    title: '经纪人'},
                {field:'brokerPhone',    title: '经纪人手机'},
                {field:'privatePhone',    title: '客户隐私号'},
                {field:'createTime',    title: '时间'},
                {field:'description',    title: '跟单记录'}
            ]]
        };
        table.render(t);
        

        form.on("submit(addUser)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });

            $.ajax({
                type:"POST",
                url:"${base}/admin/broker/documentary/add",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("添加成功!",{time:1500},function(){
                            //刷新父页面
                            parent.location.reload();
                        });
                    }else{
                        layer.msg(res.message);
                    }
                }
            });
            return false;
        });

    });
  </script>
</body>
</html>