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
        .row{
        	padding-top: 10px;
        }
    </style>
    <script>
    Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
}
    </script>
</head>
<body class="childrenBody">
<div class="row">
    <div id="container_s" style="height: 500px; display:inline-block;"></div>
    <div id="container_s1" style="height: 500px; display:inline-block;"></div>
    <div id="container_s2" style="height: 500px; display:inline-block;"></div>
     <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
    <div id="container_s3" style="height: 500px; display:inline-block;"></div>
    <div id="container_s4" style="height: 500px; display:inline-block;"></div>
    <div id="container_s5" style="height: 500px; display:inline-block;"></div>
    <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
    <div id="container_s6" style="height: 500px; display:inline-block;"></div>
    <div id="container_s7" style="height: 500px; display:inline-block;"></div>
    <div id="container_s8" style="height: 500px; display:inline-block;"></div>
    <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
	<div id="container_s9" style="height: 500px; display:inline-block;"></div>
    <div id="container_s10" style="height: 500px; display:inline-block;"></div>
    <div id="container_s11" style="height: 500px; display:inline-block;"></div>
    <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
	<div id="container_s12" style="height: 500px; display:inline-block;"></div>
    <div id="container_s13" style="height: 500px; display:inline-block;"></div>
    <div id="container_s14" style="height: 500px; display:inline-block;"></div>
    <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
	<div id="container_s15" style="height: 500px; display:inline-block;"></div>
    <div id="container_s16" style="height: 500px; display:inline-block;"></div>
    <div id="container_s17" style="height: 500px; display:inline-block;"></div>
    <a class="color" style="height:20px;background-color:#F2EFE6; width:100%;display:block;"></a>
</div>
<div class="row">
	<div id="container_s18" style="height: 500px; display:inline-block;"></div>
	<div id="container_s19" style="height: 500px; display:inline-block;"></div>
    <script type="text/javascript">
    	var width = $('.childrenBody').width();
    	$("#container_s").css("width", (width * 0.28) + "px");
    	$("#container_s1").css("width", (width * 0.35) + "px");
    	$("#container_s2").css("width", (width * 0.35) + "px");
    	$("#container_s3").css("width", (width * 0.28) + "px");
    	$("#container_s4").css("width", (width * 0.35) + "px");
    	$("#container_s5").css("width", (width * 0.35) + "px");
    	$("#container_s6").css("width", (width * 0.28) + "px");
    	$("#container_s7").css("width", (width * 0.35) + "px");
    	$("#container_s8").css("width", (width * 0.35) + "px");
    	$("#container_s9").css("width", (width * 0.28) + "px");
    	$("#container_s10").css("width", (width * 0.35) + "px");
    	$("#container_s11").css("width", (width * 0.35) + "px");
    	$("#container_s12").css("width", (width * 0.28) + "px");
    	$("#container_s13").css("width", (width * 0.35) + "px");
    	$("#container_s14").css("width", (width * 0.35) + "px");
    	$("#container_s15").css("width", (width * 0.28) + "px");
    	$("#container_s16").css("width", (width * 0.35) + "px");
    	$("#container_s17").css("width", (width * 0.35) + "px");
    	$("#container_s18").css("width", (width * 0.35) + "px");
    	$("#container_s19").css("width", (width * 0.35) + "px");
    	
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s = document.getElementById("container_s");
		var myChart_s = echarts.init(dom_s);
		var app = {};
		var option_s = null;
		$.get('${base}/admin/statistics/register').done(function (res) {
			option_s = {
			    title : {
			        text: '注册比例',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '注册比例',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s && typeof option_s === "object") {
			    myChart_s.setOption(option_s, true);
			}
        });
    </script>
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s1 = document.getElementById("container_s1");
		var myChart_s1 = echarts.init(dom_s1);
		var app = {};
		option_s1 = null;
		$.get('${base}/admin/statistics/warrant/unregister').done(function (res) {
			option_s1 = {
			    title : {
			        text: '未注册用户获取设备比例',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '未注册用户获取设备比例',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s1 && typeof option_s1 === "object") {
			    myChart_s1.setOption(option_s1, true);
			}
        });
    </script>
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s2 = document.getElementById("container_s2");
		var myChart_s2 = echarts.init(dom_s2);
		var app = {};
		option_s2 = null;
		$.get('${base}/admin/statistics/warrant/register').done(function (res) {
			option_s2 = {
			    title : {
			        text: '已注册用户获取设备比例',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '已注册用户获取设备比例',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s2 && typeof option_s2 === "object") {
			    myChart_s2.setOption(option_s2, true);
			}
        });
    </script>
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s3 = document.getElementById("container_s3");
		var myChart_s3 = echarts.init(dom_s3);
		var app = {};
		option_s3 = null;
		$.get('${base}/admin/statistics/nxfp/all').done(function (res) {
			option_s3 = {
			    title : {
			        text: '全部用户刷新首页下一批次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '全部用户刷新首页下一批次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s3 && typeof option_s3 === "object") {
			    myChart_s3.setOption(option_s3, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s4 = document.getElementById("container_s4");
		var myChart_s4 = echarts.init(dom_s4);
		var app = {};
		option_s4 = null;
		$.get('${base}/admin/statistics/nxfp/unregister').done(function (res) {
			option_s4 = {
			    title : {
			        text: '未注册用户刷新首页下一批次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '未注册用户刷新首页下一批次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s4 && typeof option_s4 === "object") {
			    myChart_s4.setOption(option_s4, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s5 = document.getElementById("container_s5");
		var myChart_s5 = echarts.init(dom_s5);
		var app = {};
		option_s5 = null;
		$.get('${base}/admin/statistics/nxfp/register').done(function (res) {
			option_s5 = {
			    title : {
			        text: '已注册用户刷新首页下一批次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '已注册用户刷新首页下一批次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s5 && typeof option_s5 === "object") {
			    myChart_s5.setOption(option_s5, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s6 = document.getElementById("container_s6");
		var myChart_s6 = echarts.init(dom_s6);
		var app = {};
		option_s6 = null;
		$.get('${base}/admin/statistics/view/all').done(function (res) {
			option_s6 = {
			    title : {
			        text: '所有用户查看详情页次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '所有用户查看详情页次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s6 && typeof option_s6 === "object") {
			    myChart_s6.setOption(option_s6, true);
			}
        });
    </script>
    
        
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s7 = document.getElementById("container_s7");
		var myChart_s7 = echarts.init(dom_s7);
		var app = {};
		option_s7 = null;
		$.get('${base}/admin/statistics/view/unregister').done(function (res) {
			option_s7 = {
			    title : {
			        text: '未注册用户查看详情页次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '未注册用户查看详情页次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s7 && typeof option_s7 === "object") {
			    myChart_s7.setOption(option_s7, true);
			}
        });
    </script>
    
        
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s8 = document.getElementById("container_s8");
		var myChart_s8 = echarts.init(dom_s8);
		var app = {};
		option_s8 = null;
		$.get('${base}/admin/statistics/view/register').done(function (res) {
			option_s8 = {
			    title : {
			        text: '已注册用户查看详情页次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '已注册用户查看详情页次数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s8 && typeof option_s8 === "object") {
			    myChart_s8.setOption(option_s8, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s9 = document.getElementById("container_s9");
		var myChart_s9 = echarts.init(dom_s9);
		var app = {};
		option_s9 = null;
		$.get('${base}/admin/statistics/modify/register').done(function (res) {
			option_s9 = {
			    title : {
			        text: '已注册用户修改首付及条件',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '已注册用户修改首付及条件',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s9 && typeof option_s9 === "object") {
			    myChart_s9.setOption(option_s9, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s10 = document.getElementById("container_s10");
		var myChart_s10 = echarts.init(dom_s10);
		var app = {};
		option_s10 = null;
		$.get('${base}/admin/statistics/shoufu/register').done(function (res) {
			option_s10 = {
			    title : {
			        text: '已注册用户首付分布',
			        subtext: '平均数：' + res.data.average_media[0] + '\n 中位数：' + res.data.average_media[1],
			        subtextStyle: {
			        	color: '#4cb4e7',
			        	fontSize: '16px'
			        },
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data
			    },
			    series : [
			        {
			            name: '已注册用户首付分布',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s10 && typeof option_s10 === "object") {
			    myChart_s10.setOption(option_s10, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s11 = document.getElementById("container_s11");
		var myChart_s11 = echarts.init(dom_s11);
		var app = {};
		option_s11 = null;
		$.get('${base}/admin/statistics/hujixg/register').done(function (res) {
			option_s11 = {
			    title : {
			        text: '已注册用户户籍及限购',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户户籍及限购',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s11 && typeof option_s11 === "object") {
			    myChart_s11.setOption(option_s11, true);
			}
        });
    </script>
    
   <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s12 = document.getElementById("container_s12");
		var myChart_s12 = echarts.init(dom_s12);
		var app = {};
		option_s12 = null;
		$.get('${base}/admin/statistics/office/register').done(function (res) {
			option_s12 = {
			    title : {
			        text: '已注册用户设置办公地点',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户设置办公地点',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s12 && typeof option_s12 === "object") {
			    myChart_s12.setOption(option_s12, true);
			}
        });
    </script>
    
   <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s13 = document.getElementById("container_s13");
		var myChart_s13 = echarts.init(dom_s13);
		var app = {};
		option_s13 = null;
		$.get('${base}/admin/statistics/school/register').done(function (res) {
			option_s13 = {
			    title : {
			        text: '已注册用户设置学位',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户设置学位',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s13 && typeof option_s13 === "object") {
			    myChart_s13.setOption(option_s13, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s14 = document.getElementById("container_s14");
		var myChart_s14 = echarts.init(dom_s14);
		var app = {};
		option_s14 = null;
		$.get('${base}/admin/statistics/xinoresf/register').done(function (res) {
			option_s14 = {
			    title : {
			        text: '已注册用户新房二手房选择',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户新房二手房选择',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s14 && typeof option_s14 === "object") {
			    myChart_s14.setOption(option_s14, true);
			}
        });
    </script>
    
        <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s15 = document.getElementById("container_s15");
		var myChart_s15 = echarts.init(dom_s15);
		var app = {};
		option_s15 = null;
		$.get('${base}/admin/statistics/huxing/register').done(function (res) {
			option_s15 = {
			    title : {
			        text: '已注册用户户型选择',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户户型选择',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s15 && typeof option_s15 === "object") {
			    myChart_s15.setOption(option_s15, true);
			}
        });
    </script>
    
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s16 = document.getElementById("container_s16");
		var myChart_s16 = echarts.init(dom_s16);
		var app = {};
		option_s16 = null;
		$.get('${base}/admin/statistics/useType/register').done(function (res) {
			option_s16 = {
			    title : {
			        text: '已注册用户房源用途选择',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户房源用途选择',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s16 && typeof option_s16 === "object") {
			    myChart_s16.setOption(option_s16, true);
			}
        });
    </script>
        
    <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s17 = document.getElementById("container_s17");
		var myChart_s17 = echarts.init(dom_s17);
		var app = {};
		option_s17 = null;
		$.get('${base}/admin/statistics/useAxb/register').done(function (res) {
			option_s17 = {
			    title : {
			        text: '已注册用户加密电话点击次数',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '已注册用户加密电话',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s17 && typeof option_s17 === "object") {
			    myChart_s17.setOption(option_s17, true);
			}
        });
    </script>
    
        <script type="text/javascript">
     	var today = new Date().format("yyyy-MM-dd");;
		var dom_s18 = document.getElementById("container_s18");
		var myChart_s18 = echarts.init(dom_s18);
		var app = {};
		option_s18 = null;
		$.get('${base}/admin/statistics/device/client').done(function (res) {
			option_s18 = {
			    title : {
			        text: '当日新增设备统计',
			        subtext: today,
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '当日新增设备统计',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s18 && typeof option_s18 === "object") {
			    myChart_s18.setOption(option_s18, true);
			}
        });
    </script>
    
        
        <script type="text/javascript">
		var dom_s19 = document.getElementById("container_s19");
		var myChart_s19 = echarts.init(dom_s19);
		var app = {};
		option_s19 = null;
		$.get('${base}/admin/statistics/device/clientlast').done(function (res) {
			option_s19 = {
			    title : {
			        text: '昨日新增设备统计',
			        subtext: '',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)",
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: res.data.legend_data,
			    },
			    series : [
			        {
			            name: '昨日新增设备统计',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: res.data.series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                },
			                normal: {
				                label:{  
				                	show:true,  
				                	formatter:'{b} : {c} ({d}%)'  
				            	}
				            }
			            }
			        }
			    ]
			};
			
			if (option_s19 && typeof option_s19 === "object") {
			    myChart_s19.setOption(option_s19, true);
			}
        });
    </script>
    
</div> 
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
</body>
</html>