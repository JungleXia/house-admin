<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>抖音搬家用户--${site.name}</title>
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
    <legend>检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
         <div class="layui-inline">
            <input type="text" value="" name="s_phone" placeholder="请输入手机号" class="layui-input search_input">
        </div>

        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
    </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>

    <script type="text/html" id="progress">
		<div class="layui-progress layui-progress-big" lay-showPercent="true">
		{{#  if(d.home == false && d.clickid != '' && d.clickid != null) { }}
			点击广告
		{{# } else if(d.home == true && d.ticket == false && d.freeMove == false){ }}
		  <div class="layui-progress-bar layui-bg-blue" lay-percent="25%" style="width: 25%"><span class="layui-progress-text">已提交搬家日期</span></div>
		{{#  } else if(d.ticket == true && d.viewFreeSer == false && d.viewTicket == false) { }}
   		  <div class="layui-progress-bar layui-bg-orange" lay-percent="50%" style="width: 50%"><span class="layui-progress-text">已领取50元搬家券</span></div>
         {{#  } else if(d.freeMove == true && d.viewFreeSer == false && d.viewTicket == false){ }}
		  <div class="layui-progress-bar layui-bg-orange" lay-percent="50%" style="width: 50%"><span class="layui-progress-text">已领取免费搬家券</span></div>
		 {{# } else if ((d.viewFreeSer == true) && d.viewTicket == true && d.upload == false) { }}
         <div class="layui-progress-bar layui-bg-red" lay-percent="75%" style="width: 75%"><span class="layui-progress-text">已查看两种短信链接</span></div>
		 {{# } else if ((d.viewFreeSer == true) && d.upload == false) { }}
         <div class="layui-progress-bar layui-bg-red" lay-percent="75%" style="width: 75%"><span class="layui-progress-text">已查看免费搬家短信链接</span></div>
         {{# } else if((d.viewTicket == true) && d.upload == false) { }}
         <div class="layui-progress-bar layui-bg-red" lay-percent="75%" style="width: 75%"><span class="layui-progress-text">已查看50元短信链接</span></div>
         {{# } else if(d.upload == true) { }}
          <div class="layui-progress-bar" lay-percent="100%" style="width: 100%"><span class="layui-progress-text">已上传房源</span></div>
         {{# } }}
		</div>
    </script>
    
    <script type="text/html" id="smsCode">
    	{{#if (d.smsCode == 0 && d.smsmsgs == 'OK') { }}
    		<a class="layui-badge layui-bg-green" target="_blank">成功 </a>
    	{{# } else if(d.smsCode != 0){ }}
    		<a class="layui-badge layui-bg-red" href="https://cloud.tencent.com/document/product/382/3771" target="_blank">{{d.smsCode}}</a>
    	{{# } }}
    </script>
</div>
<div id="page"></div>

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
    layui.use(['layer','form','table', 'element'], function() {
    	var element = layui.element;
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t,changeIndex;                  //表格数据变量
        
        t = {
            elem: '#test',
            url:'${base}/admin/rent/movelist',
            where: {s_online: '1', s_reco: '1'},
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
                {field:'deviceId',     title: 'DeviceId'},
                {field:'district',  title: '区域'},
                {field:'buildingId',    	title: '关联小区ID'},
                {field:'community',    	title: '小区'},
                {field:'address',    	title: '详细地址'},
                {field:'phone',    	title: '手机号'},
                {field:'moveDate',	title: '搬家日期'},
                {field:'minPrice',     title: '当前租金'},
                {field:'expPrice',    title: '预计租金'}, 
                {field:'rentType',  title: '租赁方式'},
                {field:'houseType',    	title: '户型'},
                {field:'isHome',	title: '进度', templet: '#progress', width: 300},
                {field:'clickid',	title: 'clickid'},
                {field:'createTime',	title: '访问时间'},
                {field:'updateTime',	title: '更新时间'},
                {field:'smsCode',	title: '发送短信', templet: '#smsCode'},
                
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                layer.confirm("你确定要上架？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/admin/house/set/status",{"id":data.id},function (res){
                                if(res.success){
                                    layer.msg("上架成功",{time: 1000},function(){
                                        table.reload('test', t);
                                    });
                                }else{
                                    layer.msg(res.message);
                                }

                            });
                        }
                )
            }
            if(obj.event === "del"){
                layer.confirm("你确定要下架？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/admin/house/set/status",{"id":data.id},function (res){
                                if(res.success){
                                    layer.msg("下架成功",{time: 1000},function(){
                                        //table.reload('test', t);
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
				layer.confirm('是否同步真实房源',  function(index, layero){
					$.post("${base}/admin/house/copy", null ,function (res){
                        if(res.success){
                            layer.msg("正在同步，几分钟后刷新结果",{time: 1000},function(){
                                table.reload('test', t);
                            });
                        }else{
                            layer.msg(res.message);
                        }
                    });
		        });
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