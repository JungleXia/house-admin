<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/main.css" media="all" />
</head>
<body class="childrenBody">
<div class="panel_box row">
    <#if (userMenu?size>0)>
        <#list userMenu as items>
            <div class="panel col <#if (!items_has_next)>max_panel</#if> ">
                <a href="javascript:" data-url="${base}${items.href}">
                    <div class="panel_icon" <#if (items.bgColor != "")>style="background-color: ${items.bgColor}"<#else>style="background-color: #54ade8"</#if>>
                        <i class="layui-icon" data-icon="${items.icon}">${items.icon}</i>
                    </div>
                    <div class="panel_word newMessage">
                        <span>${items.dataCount}</span>
                        <cite>${items.name}</cite>
                    </div>
                </a>
            </div>
        </#list>
    </#if>
</div>
<div class="row">
	<fieldset class="layui-elem-field">
		<div class="layui-form-item layui-field-box">
			<label class="layui-form-label">选择时间</label>
			<div class="layui-input-block">
				<select name="s_initDay" lay-search class="layui-select " lay-search id="select_initDay" onchange="initDay()">
				  <option value="">两周</option>
				  <option value="7">一周</option>
				  <option value="30">30天内</option>
				  <option value="90">60天内</option>
				  <option value="90">90天内</option>
				</select> 
			</div>
		</div>
	</fieldset>

    <div id="container" style="height: 500px"></div>
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min2.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts-gl.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/ecStat.min.js"></script>
    <script type="text/javascript">
    var today = new Date();
		var dom = document.getElementById("container");
		var myChart2 = echarts.init(dom);
		var app = {};
		option2 = null;
		
		var base_url = '${base}/admin/user/devices'
		function initDay() {
			var url = base_url;
			var s_initDay = $("#select_initDay").val();
			if (s_initDay != null && s_initDay != '') {
				url = url + "?s_initDay=" + s_initDay
			}
			getData(url);
		}
		
		getData(base_url);
		
		function getData(url) {
				$.get(url).done(function (res) {
	         option2 = {
			    title: {
			        text: '统计图'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['注册人数','活跃设备','新增设备','广告激活'],
			    },
			    color: ['#DE0000','#17CC10','#795DB3','#F2991D'],
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: res.data.xAxis
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'注册人数',
			            type:'line',
			            data: res.data.register,
			            itemStyle : { normal: {label : {show: true}}}
			        },
			        {
			            name:'活跃设备',
			            type:'line',
			            data: res.data.device,
			            itemStyle : { normal: {label : {show: true}}}
			        },
			        {
			            name:'新增设备',
			            type:'line',
			            data: res.data.activate,
			            itemStyle : { normal: {label : {show: true}}}
			        },
			        {
			            name:'广告激活',
			            type:'line',
			            data: res.data.advertActive,
			            itemStyle : { normal: {label : {show: true}}},
			        }
			    ]
			};
			
			if (option2 && typeof option2 === "object") {
			    myChart2.setOption(option2, true);
			}
        });
		}
		
    </script>
</div>
<div class="row">
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">更新日志</blockquote>
        <div class="layui-elem-quote layui-quote-nm">
            <@ar channelid="7">
                <#if (result?size>0)>
                    <#list result as item>
                        ${item.content}
                    </#list>
                </#if>
            </@ar>
        </div>
    </div>
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">系统基本参数</blockquote>
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col>
            </colgroup>
            <tbody>
            <tr>
                <td>网站名称</td>
                <td class="version">${site.name}</td>
            </tr>
            <tr>
                <td>开发作者</td>
                <td class="author">${site.author}</td>
            </tr>
            <tr>
                <td>当前版本</td>
                <td class="homePage">${site.version}</td>
            </tr>
            <tr>
                <td>服务器环境</td>
                <td class="server">${site.server}</td>
            </tr>
            <tr>
                <td>数据库版本</td>
                <td class="dataBase">${site.database}</td>
            </tr>
            <tr>
                <td>最大上传限制</td>
                <td class="maxUpload">${site.maxUpload}</td>
            </tr>
            <tr>
                <td>当前用户角色</td>
                <td class="userRights">
                    <#if (currentUser.roleLists?? && currentUser.roleLists?size>0)>
                        <#list currentUser.roleLists as items>
                            <span class="layui-badge layui-bg-green">${items.name}</span>
                        </#list>
                    </#if>
                </td>
            </tr>
            </tbody>
        </table>
        <blockquote class="layui-elem-quote title">最新文章<i class="iconfont icon-new1"></i></blockquote>
        <table class="layui-table" lay-skin="line">
            <colgroup>
                <col>
                <col width="110">
            </colgroup>
            <tbody class="hot_news">
                <@myindex limit = "5">
                    <#if (result?size>0)>
                        <#list result as items>
                            <tr><td align="left">${items.title}</td><td>${items.publistTime?string("yyyy-MM-dd")}</td></tr>
                        </#list>
                    </#if>
                </@myindex>
                <#--<@ar channelId = "5">-->
                    <#--<#assign articleList = result["articleList"]>-->
                    <#--<#if (articleList?size>0)>-->
                        <#--<#list articleList as items>-->
                                <#--<tr><td align="left">${items.title}</td><td>${items.publistTime?string("yyyy-MM-dd")}</td></tr>-->
                        <#--</#list>-->
                    <#--</#if>-->
                <#--</@ar>-->
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['layer','jquery','form'],function(){
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $(".panel a").on("click",function(){
            window.parent.addTab($(this));
        });
    });
</script>
</body>
</html>