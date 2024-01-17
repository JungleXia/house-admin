<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>APP用户--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel = "shortcut icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <style>
        .detail-body{
            margin: 20px 0 0;
            color: #333;
            word-wrap: break-word;
        }
    </style>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote layui-bg-green">
	<div id="nowTime">为了保护客户的隐私和规避风险，请通过指定电话拨打隐私号联系客户，隐私号的有效期为30天。<br/> 由于隐私号数量有限，一个经纪人最多同时使用8个隐私号，超过8个会将之前使用该隐私号的客户解绑。</div>
</blockquote>
<fieldset class="layui-elem-field">
    <legend>客户检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
    <#if (isBroker  == false)>
        <div class="layui-inline">
            <input type="text" value="" name="s_phone" placeholder="请输入客户手机" class="layui-input search_input">
        </div>
     </#if>

        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
    </div>
</fieldset>

<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
        <script type="text/html" id="showUrl">
        <a class="layui-badge layui-bg-green" target="_blank" href="{{d.url}}">查看原链接</a>
    </script>
    <script type="text/html" id="standRegion">
        <!-- 这里的 checked 的状态只是演示 -->
        <span class="layui-badge layui-bg-green">{{d.regions.district}}</span>
        <span class="layui-badge layui-bg-gray">{{d.regions.blockName}}</span>
    </script>
    <script type="text/html" id="userStatus">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.delFlag == false){ }}
        <span class="layui-badge layui-bg-green">正常</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">停用</span>
        {{#  } }}
    </script>
    <script type="text/html" id="barDemo">
        <#if (isBroker == false)>
        {{# if(d.documentaryCount > 0){ }}
        <a class="layui-btn layui-btn-green layui-btn-xs" lay-event="addDocument">查看跟单</a>
        {{# } }}
        <#else>
        <a class="layui-btn layui-btn-green layui-btn-xs" lay-event="addDocument">添加跟单</a>
        </#if>
    </script>
    <script type="text/html" id="getPrivateNumber">
    	<#if (isBroker == true)>
    	{{# if(d.privatePhone != null && d.privatePhone != '') { }}
        	{{# if(d.privateExpired == true) { }}
        		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="getPrivateNumber" title="点击重新获取隐私号">{{d.privatePhone}}已过期</a>
        	{{# } else { }}
	        	<b>{{d.privatePhone.substring(0,3)}} {{d.privatePhone.substring(3,7)}} {{d.privatePhone.substring(7,11)}}</b>
        	{{# }}}
        {{# } else { }}
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="getPrivateNumber">点击获取隐私号</a>
        {{# }}}
        <#else>
    	{{# if(d.privatePhone != null && d.privatePhone != '') { }}
        	<b>{{d.privatePhone}}</b>
        {{# } }}
        </#if>
    </script>
    <script type="text/html" id="barBind">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="addType">绑定经纪人</a>
    </script>
    <script type="text/html" id="barHistory">
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="viewHistory">客户浏览历史</a>
    </script>
    <script type="text/html" id="showPhoto">
    	{{#  if(d.avatar != null){ }}
		<img src="{{d.avatar}}" width='28' height='28' onclick="layerPhoto('{{d.avatar}}', '')" style="border-radius: 50%">
		{{#  } }}
    </script>
</div>
<div id="page"></div>

<div id="changeTypeDiv" style="display: none" class="detail-body">
    <form  id="changeTypeForm" class="layui-form" style="width: 500px">
        <div class="layui-form-item">
            <label class="layui-form-label">原TYPE值</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input layui-disabled" name="oldType" disabled lay-verify="required" placeholder="请输入原TYPE值">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新TYPE值</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="newType" lay-verify="required" placeholder="请输入新TYPE值">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changeType">立即提交</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table'], function() {
    
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t,changeIndex;                  //表格数据变量
        
        t = {
            elem: '#test',
            url:'${base}/admin/broker/user/list',
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
                {field:'customerId',  title: '客户ID'},
                {field:'downPayment',  width: '5%',  title: '首付'},
                {field:'location',    title: '地点'},
                {field:'telCity',    title: '手机归属地'},
                {field:'createDate',    title: '注册时间'},
                {field:'huxing',    title: '户型'},
                {field:'lastLogin',    title: '绑定时间'},
                {field:'brokerName',    title: '绑定经纪人'},
                {field:'brokerPhone',    title: '经纪人号码'},
                {field:'privatePhone',    title: '客户隐私号', toolbar: '#getPrivateNumber'},
                {field:'documentaryCount',    title: '跟单记录'},
                <#if (allowAdd  == true)>
                {field:'',    title: '绑定', width: '10%', toolbar: '#barBind'},
                </#if>
                {field:'',    title: '查看', width: '10%', toolbar: '#barHistory'},
                {fixed: 'right',  title: '跟单',  width: '10%', align: 'center',toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'viewHistory'){
                var editIndex = layer.open({
                    title : "查看浏览历史",
                    type : 2,
                    content : "${base}/admin/broker/user/view/list?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if (obj.event === "getPrivateNumber") {
                var loadIndex = layer.load(2, {
	                shade: [0.3, '#333']
	            });
                $.ajax({
		            type:"POST",
		            url:"${base}/admin/broker/widthUser/add",
		            dataType:"json",
		            contentType:"application/json",
		            data:JSON.stringify({userId:data.id, brokerId:"${brokerId}" }),
		            success:function(res){
		                layer.close(loadIndex);
		                if(res.success){
		                    parent.layer.msg("获取隐私号成功!",{time:1500},function(){
		                        //刷新当前页面
		                        $(".layui-laypage-btn").click();
		                    });
		                }else{
		                    layer.msg(res.message);
		                }
		            }
		        });
            }
            if(obj.event === "addType"){
                var addTypeIndex = layer.open({
                    title : "绑定经纪人",
                    type : 2,
                    content : "${base}/admin/broker/withUser/add?id=" + data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addTypeIndex);
                });
                layer.full(addTypeIndex);
            }
            if(obj.event === "addDocument"){
                var addDocumentIndex = layer.open({
                    title : "添加跟单记录",
                    type : 2,
                    content : "${base}/admin/broker/documentary/add?id=" + data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addDocumentIndex);
                });
                layer.full(addDocumentIndex);
            }
            

        });

        //功能按钮
        var active={
            addUser : function(){
                var addIndex = layer.open({
                    title : "添加字典",
                    type : 2,
                    content : "${base}/admin/system/dict/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                // 改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }
        };

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

       
        form.on("submit(searchForm)",function(data){
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

        form.on("submit(changeType)",function (data) {
            if(data.field.oldType == null || data.field.oldType === ""){
                layer.msg("原TYPE值不能为空");
                return false;
            }
            $.post("${base}/admin/system/dict/editType",data.field,function(res){
                if(!res.success){
                    layer.msg(res.message);
                }else{
                    layer.msg("修改成功",{time:1000},function () {
                        table.reload('test', t);
                    })
                }
                layer.close(changeIndex);
            });
            return false;
        });

    });
</script>
</body>
</html>