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
    <style>
        .detail-body{
            margin: 20px 0 0;
            color: #333;
            word-wrap: break-word;
        }
    </style>
</head>
<body class="childrenBody">
<div class="row">
    <div id="container" style="height: 500px"></div>
    <script type="text/javascript">
     	var today = new Date();
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		$.get('${base}/admin/user/pvs').done(function (res) {
	          option = {
	          	title: {
			        text: '用户统计',
			        subtext: today
			    },
			    legend: {},
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    dataset: {
			        source: [
			            res.data.product,
			            res.data.register,
			            res.data.device,
			        ]
			    },
			    xAxis: {type: 'category'},
			    yAxis: {},
			    // Declare several bar series, each will be mapped
			    // to a column of dataset.source by default.
			    series: [
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}},
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}},
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}},
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}},
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}},
			        {type: 'bar', barMinHeight: 10,itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
						}}
			    ]
			};
			
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
        });
    </script>
</div>

<div class="row" style="height: 600px">
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
    <div id="container-2" style="height: 500px"></div>
    <script type="text/javascript">
     	var today = new Date();
		var dom = document.getElementById("container-2");
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
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
</body>
</html>