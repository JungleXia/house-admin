<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>APP广告图片设置--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel = "shortcut icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <link rel="stylesheet" href="${base}/static/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <style>
        .detail-body{
            margin: 20px 0 0;
            color: #333;
            word-wrap: break-word;
        }
        /***
        *  ztree 图标变成黄色
        */
        .ztree .line{
            line-height: 0;
            border-top:none;
            float: none;
        }
        .ztree li span.button.ico_docu {
            background-position: -110px 0;
            margin-right: 2px;
            vertical-align: top;
        }
        
    </style>
</head>
<body class="childrenBody">
<div class="layui-col-xs1">
    <div class="grid-demo grid-demo-bg1"><ul id="treeDemo" class="ztree"></ul></div>
</div>
<div class="layui-col-xs11">
	<fieldset class="layui-elem-field">
	    <legend>检索</legend>
	    <div class="layui-field-box">
	    <form class="layui-form">
	        <div class="layui-inline">
	            <input type="text" value="" name="s_province" placeholder="请输入省" class="layui-input search_input">
	        </div>
	        <div class="layui-inline">
	            <input type="text" value="" name="s_city" placeholder="请输入市" class="layui-input search_input">
	        </div>
	        <div class="layui-inline">
	            <input type="text" value="" name="s_district" placeholder="请输入区" class="layui-input search_input">
	        </div>
	        <div class="layui-inline">
	            <input type="text" value="" name="s_block" placeholder="请输入商圈" class="layui-input search_input">
	        </div>
            <div class="layui-inline">
				<select name="s_type" lay-search class="layui-select " lay-search>
				  <option value="" selected>类型</option>
				  <option value="1" >市级</option>
				  <option value="2" >区级</option>
				  <option value="3" >商圈</option>
				</select>  
        	</div>
	        <div class="layui-inline">
	            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
	        </div>
	        <div class="layui-inline" >
	            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	        </div>
	        <div class="layui-inline">
	            <a class="layui-btn layui-btn-normal" data-type="addUser">新增</a>
	        </div>
	    </form>
	    </div>
	</fieldset>
	
	<div class="layui-form users_list">
	    <table class="layui-table" id="test" lay-filter="demo"></table>
	    <script type="text/html" id="showUrl">
	    	{{#  if(d.avatar != null){ }}
	        <a class="layui-badge layui-bg-green" target="_blank" href="{{d.url}}">{{d.title}}</a>
	        {{#  } }}
	    </script>
	    <script type="text/html" id="regions">
	        <!-- 这里的 checked 的状态只是演示 -->
	        <span class="layui-badge layui-bg-green">{{d.district}}</span>
	        <span class="layui-badge layui-bg-gray">{{d.blockName}}</span>
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
	      	<a class="layui-btn layui-btn-xs layui-bg-gray" href="{{d.url}}" target="_blank">查看</a>
	        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	    </script>
	    <script type="text/html" id="showPhoto">
	    	{{#  if(d.imageUrl != null){ }}
			<img src="{{d.imageUrl}}" width='50' height='40' onclick="layerPhoto('{{d.imageUrl}}', '')" >
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

</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

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
                setting  = {callback:{
                    onClick : function (event, treeId, treeNode) {
                        var id = treeNode.id,
                                data;
                                //console.log(treeNode)
                        if(id === undefined || id == null){
                            data = {}
                        }else{
                       		data = {}
                        	if (treeNode.level == 0) {
                        	    data = {s_province:treeNode.name, s_city:'', s_district:'', s_block:''};
                        	} else if (treeNode.level == 1) {
                        	 	data = {s_province: treeNode.getParentNode().name, s_city:treeNode.name, s_district:'', s_block:''};
                        	} else if (treeNode.level == 2) {
                        	    data = {s_province:'', s_city:treeNode.getParentNode().name, s_district:treeNode.name, s_block:''}
                        	} else if (treeNode.level == 3) {
                        	    data = {s_province:'', s_city:'', s_district:treeNode.getParentNode().name, s_block:treeNode.name}
                        	} else {
                        		alert(treeNode)
                        	}
                        }
                        t.where = data;
                        table.reload('test', t);
                        
                    }
                }},
                t,changeIndex;                  //表格数据变量
                
        
        t = {
            elem: '#test',
            url:'${base}/admin/ljEsfStatistic/list',
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
            width: $(parent.window).width()*0.9-223,
            cols: [[
                {type:'checkbox'},
                {field:'province',    title: '省'},
                {field:'city',    title: '市'},
                {field:'district',    title: '区域'},
                {field:'block',    title: '商圈'},
                {field:'type',    title: '类型'},
                {field:'numbers',     title: '数量'},
                {field:'diff',     title: '涨跌'},
                {field:'createDay',     title: '日期'},
                {fixed: 'right', align: 'center',toolbar: '#barDemo'}

            ]]
        };
        table.render(t);

        $(function() {
            var tableload = layer.msg('栏目树拼命加载中', {
                icon: 16
                ,shade: 0.01
            });
            $.post("${base}/admin/ljEsfStatistic/ztreeData",function (res) {
                layer.close(tableload);
                if(res.success){
                    var zNodes = res.data;
                    var newNode = [];
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    newNode = zTreeObj.addNodes(null,newNode);
                }else{
                    layer.msg("加载异常:{"+res.message+"}");
                }
            });
        });
        
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑",
                    type : 2,
                    content : "${base}/admin/ljEsfStatistic/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
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
        });

        //功能按钮
        var active={
            addUser : function(){
                var addIndex = layer.open({
                    title : "新增",
                    type : 2,
                    content : "${base}/admin/ljEsfStatistic/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
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