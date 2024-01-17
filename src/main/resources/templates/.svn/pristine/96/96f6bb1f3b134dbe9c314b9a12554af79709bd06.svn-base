<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>链家小区列表--${site.name}</title>
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
	            <input type="text" value="" name="s_cNo" placeholder="请输入小区编号" class="layui-input search_input">
	        </div>
	        <div class="layui-inline">
	            <input type="text" value="" name="s_community" placeholder="请输入小区" class="layui-input search_input">
	        </div>
	        <div class="layui-inline">
				<select name="s_status" lay-search class="layui-select " lay-search>
				  <option value="" >全部状态</option>
				  <option value="0" >不动</option>
				  <option value="1" >涨价</option>
				  <option value="-1" >跌价</option>
				  <option value="2" selected>上新</option>
				</select>  
	        </div>
	        
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
	    	{{#  if(d.url != null && d.url != ''){ }}
	        	<a class="layui-badge layui-bg-green" target="_blank" href="{{d.url}}" title="点击跳到源网站">{{d.community}}</a>
	         {{#  } else { }}
	               {{d.title}}
	         {{#  } }}
	    </script>
	    <script type="text/html" id="dealNum">
	    	{{#  if(d.dealNum != null && d.dealNum != '' && d.dealNum > 0){ }}
					{{d.dealDay}}天内{{d.dealNum}}套
	         {{#  } else { }}
	               {{d.dealNum}}
	         {{#  } }}
	    </script>
	    <script type="text/html" id="updateTime">
	    	{{#  if(d.updateTime != null && d.updateTime != ''){ }}
				{{d.updateTime }}
	         {{#  } else { }}
	        	{{d.createTime }}
	         {{#  } }}
	    </script>
	    <script type="text/html" id="barDemo">
	    	{{#  if(d.expired == false){ }}
	        <a class="layui-btn layui-btn-xs" lay-event="edit">上架</a>
	         {{#  } else if(d.expired == true) { }}
	        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">下架</a>
	         {{#  } }}
	    </script>
	    <script type="text/html" id="showPhoto">
	    	{{#  if(d.picUrl != null){ }}
			<img src="{{d.picUrl}}" width='50' height='50' onclick="layerPhoto('{{d.picUrl}}', '')">
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
                        	    data = {};
                        	    return;
                        	} else if (treeNode.level == 1) {
                        	 	data = {s_province: treeNode.getParentNode().name, s_city:treeNode.name, s_district:'', s_block:''};
                        	} else if (treeNode.level == 2) {
                        	    data = {s_province:'', s_city:treeNode.getParentNode().name, s_district:treeNode.name, s_block:''}
                        	} else if (treeNode.level == 3) {
                        	    data = {s_province:'', s_city:treeNode.getParentNode().getParentNode().name, s_district:treeNode.getParentNode().name, s_block:treeNode.name}
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
            url:'${base}/admin/ljEsfCommunity/list',
            where: {'s_expired':'0', 's_status': 2},
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
            width: $(parent.window).width()*0.91-223,
            cols: [[
                {type:'checkbox'},
                {field:'cNo',     title: '小区编码',width:'6%'},
                {field:'community',     title: '小区',width:'10%', templet:'#showUrl'},
                {field:'unitPrice',    	title: '均价',width:'6%'},
                {field:'numbers',  title: '在售数量',width:'6%'},
                {field:'diffNum',   title: '在售变动',width:'6%'},
                {field:'diffPrice',   title: '价格变动',width:'6%'},
                {field:'buildType',    title: '建筑结构',width:'8%'},
                {field:'buildYear',    	title: '建筑年限',width:'8%'},
                {field:'dealNum',	title: '成交' ,width:'8%',templet:'#dealNum'},
                {field:'city',	title: '城市',width:'4%'},
                {field:'district',	title: '区',width:'6%'},
                {field:'block',	title: '商圈',width:'6%'},
                {field:'updateTime',	title: '更新时间' ,templet:'#updateTime'},
                
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
                layer.confirm("你确定要上架？",{btn:['是的,我确定','我再想想']},
                        function(){

                        }
                )
            }
            if(obj.event === "del"){
                layer.confirm("你确定要下架？",{btn:['是的,我确定','我再想想']},
                        function(){
      
                        }
                )
            }
            if(obj.event === "addType"){
                var addTypeIndex = layer.open({

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

            },

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