<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>城市列表--${site.name}</title>
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
<fieldset class="layui-elem-field">
    <legend>城市检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline">
            <input type="text" value="" name="s_type" placeholder="请输入类型" class="layui-input search_input">
        </div>
        <div class="layui-inline">
            <input type="text" value="" name="s_label" placeholder="请输入标签名" class="layui-input search_input">
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="addUser">添加城市</a>
        </div>
    </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>

    <script type="text/html" id="longlat">
        <!-- 这里的 checked 的状态只是演示 -->
        <span class="layui-badge layui-bg-gray">{{d.longitude}},{{d.latitude}}</span>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="addDistrict">添加区域</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="display">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.display == true){ }}
        <span class="layui-badge layui-bg-green">启用</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">停用</span>
        {{#  } }}
    </script>
    <script type="text/html" id="hotCity">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.hot == true){ }}
        <span class="layui-badge layui-bg-green">热门城市</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">非热门</span>
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
<script type="text/javascript">
function layerPhoto(src, size) {
	var img = "<img src='" + src + "' />";
	layer.open({  
	    type: 1,  
	    shade: false,  
	    title: false, //不显示标题  
	    maxWidth: 1200,
		maxHeight: 900,  
	    content: img, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
	    cancel: function () {  
	        //layer.msg('图片查看结束！', { time: 5000, icon: 6 });  
	    } 
	}); 
}
</script>
<script>
    layui.use(['layer','form','table'], function() {
    
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t,changeIndex;                  //表格数据变量
        
        t = {
            elem: '#test',
            url:'${base}/admin/fang/region/city/list',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                limit:20,
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[10, 20, 30, 50 ,100]
            },
            width: $(parent.window).width()-223,
            cols: [[
                {type:'checkbox'},
                {field:'sort',	title: '序号'},
                {field:'city',    	title: '城市'},
                {field:'cityCode',  title: '城市编码'},
                {field:'province',	title: '所属省份'},
                {field:'domain',	title: '域名'},
                {field:'cityImg',   title: '封面图片'},             
                {field:'hot',   title: '热门城市', templet:'#hotCity'},             
                {field:'display',   title: '启用', templet:'#display'},             
                {field:'longitude', title: '经纬度', align: 'center', templet:'#longlat'},  
                {fixed: 'right', 	title: '操作', align: 'center',toolbar: '#barDemo'}
                           
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'addDistrict'){
                var editIndex = layer.open({
                    title : "编辑区域",
                    type : 2,
                    content : "${base}/admin/fang/region/district/list?cityId="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('添加区域', '.layui-layer-setwin .layui-layer-close', {
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
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑区域",
                    type : 2,
                    content : "${base}/admin/fang/region/city/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回字典列表', '.layui-layer-setwin .layui-layer-close', {
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
            if(obj.event === "del"){
                layer.confirm("你确定要删除该城市么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/admin/fang/region/city/deleteById",{"id":data.id},function (res){
                                if(res.success){
                                    layer.msg("删除成功",{time: 1000},function(){
                                        table.reload('test', t);
                                    });
                                }else{
                                    layer.msg(res.message);
                                }

                            });
                        }
                )
            }
            if(obj.event === "addType"){
                var addTypeIndex = layer.open({
                    title : "新增TYPE的值",
                    type : 2,
                    content : "${base}/admin/system/dict/add?type="+data.type,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回字典列表', '.layui-layer-setwin .layui-layer-close', {
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

            if(obj.event === "editType"){
                $("#changeTypeForm")[0].reset();
                $("input[name='oldType']").val(data.type);
                changeIndex = layer.open({
                    type: 1,
                    title: '编辑TYPE值',
                    closeBtn: 0,
                    area: '516px',
                    shadeClose: true,
                    content: $('#changeTypeDiv')
                });
            }
        });

        //功能按钮
        var active={
            addUser : function(){
                var addIndex = layer.open({
                    title : "添加城市",
                    type : 2,
                    content : "${base}/admin/fang/region/city/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
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

        //搜索
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