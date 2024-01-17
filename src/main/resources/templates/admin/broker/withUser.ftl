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
<form class="layui-form" style="width:80%;">
  <div class="layui-form" lay-filter="layuiadmin-form-order" id="layuiadmin-form-order" style="padding: 20px 30px 0 0;">

    <div class="layui-form-item">
      <label class="layui-form-label">客戶ID</label>
      <div class="layui-input-inline">
        <input type="text" lay-verify="required" value="${customer.customerId}"  autocomplete="off" class="layui-input">
        <input type="text" name="userId" lay-verify="required" value="${customer.id}"  autocomplete="off" class="layui-input" style="display:none">
      </div>
    </div>
    

    <div class="layui-form-item">
      <label class="layui-form-label">选择经纪人</label>
      <div class="layui-input-inline" style="width:250px">
        <select name="brokerId">
        	<option value="0"> --- 下拉选择 --- </option>
			<#if brokerList??>
	            <#if (brokerList?size > 0)>
	                <#list brokerList as broker>
							<option value="${broker.id}">${broker.name} - ${broker.phone} - 已代理${broker.bindNum}个</option>
	                 </#list>
	            </#if>
	       	</#if>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">确定</button>

        </div>
    </div>

  </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
  <script>
    layui.use(['form','jquery','layer'],function(){
       var form = layui.form,
           $    = layui.jquery,
           layer = layui.layer;

        form.on("submit(addUser)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });

			if (data.field.brokerId == 0) {
				layer.msg("请添加经纪人");
				layer.close(loadIndex);
				return false;
			}
            $.ajax({
                type:"POST",
                url:"${base}/admin/broker/widthUser/add",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("经纪人绑定成功!",{time:1500},function(){
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