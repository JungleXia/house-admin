<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${city.city}区域添加--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- 页面描述 -->
    <meta name="description" content="${site.description}"/>
    <!-- 页面关键词 -->
    <meta name="keywords" content="${site.keywords}"/>
    <!-- 网页作者 -->
    <meta name="author" content="${site.author}"/>
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }
		.layui-form-label {
		    float: left;
		    display: block;
		    padding: 9px 15px;
		    width: 120px;
		    font-weight: 400;
		    text-align: right;
		}
		.layui-input-block {
		    margin-left: 150px;
		    min-height: 36px;
		}
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:50%;">

    <div class="layui-form-item">
        <label class="layui-form-label">城市</label>
        <div class="layui-input-block">
            <input type="text" disabled="" class="layui-input layui-disabled" name="city" value="${city.city}" lay-verify="required" placeholder="请输入城市">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">行政区</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="district" lay-verify="required" placeholder="请输入行政区">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">区域编码（可选）</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="districtCode"  placeholder="请输入区域编码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">中心经度（可选）</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="longitude"  placeholder="请输入经度（高德）">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">中心纬度（可选）</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="latitude" placeholder="请输入纬度（高德）">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">序号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sort" lay-verify="required" placeholder="请输入值0,1,2,3,4,5,6,7,8,9...">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="display" lay-skin="switch" value="true"  lay-text="启用|停用" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
            $.post("${base}/admin/fang/region/district/add",data.field,function(res){
               layer.close(loadIndex);
               if(res.success){
                   parent.layer.msg("区域添加成功!",{time:1000},function(){
                       //刷新父页面
                       parent.location.reload();
                   });
               }else{
                   layer.msg(res.message);
               }
            });
            return false;
        });

    });
</script>
</body>
</html>