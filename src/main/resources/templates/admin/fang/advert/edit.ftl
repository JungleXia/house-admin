<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑广告参数--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel = "shortcut icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css?v=2.0" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
    <div class="user_left">
        <input class="layui-hide" name="id" value="${advert.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <input type="text" value="${advert.description}" name="description" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
	        <label class="layui-form-label">是否上架</label>
	        <div class="layui-input-block">
	            <input type="checkbox" name="expired" lay-skin="switch" value="${advert.expired}" lay-text="上架|下架" <#if (advert.expired == false)> checked="checked"</#if> >
	        </div>
	    </div>
    </div>
    <div class="user_right">
        <input type="hidden"  name="imageUrl"  value="${advert.imageUrl}" >
        <button type="button" class="layui-btn layui-btn-normal" id="test1"><i class="layui-icon"></i>更换图片</button>
        <img <#if (advert.imageUrl??)>src="${advert.imageUrl}" <#else> src="${base}/static/images/face.jpg " </#if> style="margin-top:50px;" id="imageUrl">
        <div id="imageSize" style="font-size:14px;padding:5px;"></div>
    </div>
    <div class="layui-form-item" style="margin-left: 5%;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changeAdvert">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary restuserinfo">恢复</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
	$( window ).load(function() {
	    $('#imageSize').text('width: '+ $('#imageUrl').width() +',height:' + $('#imageUrl').height());
	});


    layui.use(['form','jquery','layer','upload','laydate'],function() {
        var form = layui.form,
                $ = layui.jquery,
                upload = layui.upload,
                layer = layui.layer;

        //普通图片上传
        upload.render({
            elem: '#test1',
            url: '${base}/file/upload/',
            field: 'test',
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#imageUrl').attr('src', result); //图片链接（base64）
	    			$('#imageSize').text('width: '+ $('#imageUrl').width() +',height:' + $('#imageUrl').height());
                });
                layer.load(2, {
                    shade: [0.3, '#333']
                });
            },
            done: function (res) {
                layer.closeAll('loading');
                //如果上传失败
                if (res.success === false) {
                    return layer.msg('上传失败');
                }else{
                    layer.msg("上传成功",{time:1000},function () {
                        $("input[name='imageUrl']").val(res.data.url);
                    })
                }
            }
        });

        form.on("submit(changeAdvert)",function (data) {
            if(data.field.id == null){
                layer.msg("ID不存在");
                return false;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
                                    //判断用户是否启用
            if(undefined !== data.field.expired && null != data.field.expired){
                data.field.expired = false;
            }else{
                data.field.expired = true;
            }
            $.post("${base}/admin/advert/save",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("保存成功！",{time:1500},function(){
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

        $(".restuserinfo").on("click",function () {
            layer.confirm('确定恢复原始数据?', {icon: 3, title:'提示'}, function(index){
                window.location.reload();
                layer.close(index);
            });
        });

    });
</script>
</body>
</html>