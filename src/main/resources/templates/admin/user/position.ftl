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
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min2.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts-gl.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/ecStat.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/china.js"></script>
    <script type="text/javascript" src="${base}/static/js/world.js"></script>
    <script type="text/javascript" src="${base}/static/layui/layui.js"></script>
	<script type="text/javascript" src="${base}/static/js/tools.js"></script>
	<script>
		var get_position_url = "http://admin.crowdhousing.cn/admin/user/positions";
	</script>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>客户检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline">
            <input type="text" value="" name="s_clientKey" placeholder="请输入MAC" id="clientKey" class="layui-input search_input">
        </div>
        <div class="layui-inline">
			<select name="s_register" lay-search class="layui-select " lay-search id="select_register">
			  <option value="">全部用户</option>
			  <option value="1">注册</option>
			  <option value="0">未注册</option>
			</select>  
        </div>
        <div class="layui-inline">
            <input type="text" value="" name="s_createDate" placeholder="按日期查询" id="createDate" class="layui-input search_input">
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
    </form>
    </div>
</fieldset>

<head>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PointSimplifier/examples/star.html -->
    <base href="//webapi.amap.com/ui/1.0/ui/misc/PointSimplifier/examples/" />
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>闪烁效果</title>
    <style>
    html,
    body,
    #container {
        width: 100%;
        height: 100%;
        margin: 0px;
    }

    #loadingTip {
        position: absolute;
        z-index: 9999;
        top: 0;
        right: 0;
        padding: 3px 10px;
        background: red;
        color: #fff;
        font-size: 13px;
    }
    </style>
</head>

    <div id="container"></div>
    <script type="text/javascript" src='//webapi.amap.com/maps?v=1.4.15&key=c4a7a95eef75debff0dbb98db47e1eed'></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <script type="text/javascript">
    //创建地图
    var map = new AMap.Map('container', {
        zoom: 10
    });

    //just some colors
	  var colors = [
	        '#0cc2f2',
	        '#4fd2b1',
	        '#90e36f',
	        '#ffe700',
	        '#ff9e00',
	        '#ff6700',
	        '#ff1800'
	    ];

    AMapUI.load(['ui/misc/PointSimplifier', 'lib/$'], function(PointSimplifier, $) {

        if (!PointSimplifier.supportCanvas) {
            alert('当前环境不支持 Canvas！');
            return;
        }

        var pointSimplifierIns = new PointSimplifier({
            zIndex: 115,
            autoSetFitView: false,
            map: map, //所属的地图实例

            getPosition: function(item) {
                if (!item) {
                    return null;
                }

                var parts = item.split(',');

                return [parseFloat(parts[0]), parseFloat(parts[1])];
            },
            getHoverTitle: function(dataItem, idx) {
                return '序号: ' + idx;
            },
            //使用GroupStyleRender
            renderConstructor: PointSimplifier.Render.Canvas.GroupStyleRender,
            renderOptions: {
                eventSupport: false, //禁止事件,对性能更友好
                //点的样式
                pointStyle: {
                    fillStyle: null,
                    width: 5,
                    height: 5
                },
                topNAreaStyle: null,
                getGroupId: function(item, idx) {

                    //随机返回一个组ID
                    return Math.ceil(colors.length * Math.random());
                },
                groupStyleOptions: function(gid) {

                    //随机设置大小
                    var radius = 2 + 10 * Math.random();

                    return {

                        pointStyle: radius > 0 ? {
                            content: function(ctx, x, y, width, height) {

                                var p = {
                                    x: x + width / 2,
                                    y: y + height / 2,
                                    radius: radius
                                };

                                ctx.beginPath();
                                var gradient = ctx.createRadialGradient(p.x, p.y, 0, p.x, p.y, p.radius);
                                gradient.addColorStop(0, "rgba(222, 8, 0,0.8)");
                                gradient.addColorStop(1, "rgba(222, 8, 0,0.1)");
                                ctx.fillStyle = gradient;
                                ctx.arc(p.x, p.y, p.radius, Math.PI * 2, false);
                                ctx.fill();

                            },

                            //fillStyle: colors[gid % colors.length],
                            width: radius * 2,
                            height: radius * 2
                        } : null,
                        pointHardcoreStyle: {
                            width: radius * 2 + 3,
                            height: radius * 2 + 3
                        }
                    };
                }

            }
        });


        var pointSimplifierIns_2 = new PointSimplifier({
            zIndex: 115,
            autoSetFitView: false,
            map: map, //所属的地图实例

            getPosition: function(item) {
                if (!item) {
                    return null;
                }

                var parts = item.split(',');

                return [parseFloat(parts[0]), parseFloat(parts[1])];
            },
            getHoverTitle: function(dataItem, idx) {
                return '序号: ' + idx;
            },
            //使用GroupStyleRender
            renderConstructor: PointSimplifier.Render.Canvas.GroupStyleRender,
            renderOptions: {
                eventSupport: false, //禁止事件,对性能更友好
                //点的样式
                pointStyle: {
                    fillStyle: null,
                    width: 5,
                    height: 5
                },
                topNAreaStyle: null,
                getGroupId: function(item, idx) {

                    //随机返回一个组ID
                    return Math.ceil(colors.length * Math.random());
                },
                groupStyleOptions: function(gid) {

                    //随机设置大小
                    var radius = 2 + 10 * Math.random();

                    return {

                        pointStyle: radius > 0 ? {
                            content: function(ctx, x, y, width, height) {

                                var p = {
                                    x: x + width / 2,
                                    y: y + height / 2,
                                    radius: radius
                                };

                                ctx.beginPath();
                                var gradient = ctx.createRadialGradient(p.x, p.y, 0, p.x, p.y, p.radius);
                                gradient.addColorStop(0, "rgba(87, 25, 212,0.8)");
                                gradient.addColorStop(1, "rgba(87, 25, 212,0.1)");
                                ctx.fillStyle = gradient;
                                ctx.arc(p.x, p.y, p.radius, Math.PI * 2, false);
                                ctx.fill();

                            },

                            //fillStyle: colors[gid % colors.length],
                            width: radius * 2,
                            height: radius * 2
                        } : null,
                        pointHardcoreStyle: {
                            width: radius * 2 + 3,
                            height: radius * 2 + 3
                        }
                    };
                }

            }
        });

        //重复render
        setInterval(function() {
            pointSimplifierIns.render();
            pointSimplifierIns_2.render();
        }, 1000)


        $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
        $.get(get_position_url, function(csv) {

            var data = csv.data;
            pointSimplifierIns.setData(data.positions);
            pointSimplifierIns_2.setData(data.offices);

            $('#loadingTip').remove();
        });
        
        layui.use(['layer','form','laydate'], function() {
        	$ = layui.jquery,
    		form = layui.form,
    		laydate = layui.laydate;

	          laydate.render({
	            elem: '#createDate', // 指定元素
	            type:'date'
	          });
          
            //搜索
	        form.on("submit(searchForm)",function(data){
		        $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
		        $.get(get_position_url + "?s_register=" + $("#select_register").val() + "&s_clientKey=" + $("#clientKey").val() + "&s_createDate=" + $("#createDate").val(), function(csv) {
		
		            var data = csv.data;
		            pointSimplifierIns.setData(data.positions);
		            pointSimplifierIns_2.setData(data.offices);
		
		            $('#loadingTip').remove();
		        });
	        });
        });
    });

    </script>

</body>
</html>