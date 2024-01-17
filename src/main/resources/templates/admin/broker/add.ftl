<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户添加--${site.name}</title>
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
		    width: 100px;
		    font-weight: 400;
		    text-align: right;
		}
		.layui-input-block {
		    margin-left: 130px;
		    min-height: 36px;
		}

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="loginName" lay-verify="required" placeholder="请输入登录名">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="nickName" placeholder="请输入昵称">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="tel" maxlength="11" lay-verify="required|phone" placeholder="请输入手机">
            </div>
        </div>
     </div> 
     <div class="layui-form-item">
        <div class="layui-inline">
	        <label class="layui-form-label">新密码</label>
	        <div class="layui-input-block">
	            <input type="password" name="password" placeholder="请输入新密码" lay-verify="required|password" id="oldPwd" class="layui-input pwd">
	        </div>
	    </div>
	    <div class="layui-inline">
	        <label class="layui-form-label">再次确认密码</label>
	        <div class="layui-input-block">
	            <input type="password" name="confirmPwd" placeholder="请再次确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
	        </div>
	    </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-block role-box">
            <div class="jq-role-inline">
                <fieldset class="layui-elem-field">
                    <legend>选择角色</legend>
                    <div class="layui-field-box">
                        <#if roleList??>
                            <#if (roleList?size > 0)>
                                <#list roleList as role>
                                    <input type="checkbox" name="roles"  value="${role.id}" title="${role.name}" lay-filter="role" checked="checked"/>
                                </#list>
                            </#if>
                        </#if>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">负责城市</label>
        <div class="layui-input-block role-box">
            <div class="jq-role-inline">
                <fieldset class="layui-elem-field">
                    <legend>选择城市</legend>
                    <div class="layui-field-box">
                    	<input type="checkbox" name="remarks"  value="深圳" title="深圳" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="广州" title="广州" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="东莞" title="东莞" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="惠州" title="惠州" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="北京" title="北京" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="上海" title="上海" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="杭州" title="杭州" lay-filter="remarks" />  
                    	<input type="checkbox" name="remarks"  value="重庆" title="重庆" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="成都" title="成都" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="武汉" title="武汉" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="西安" title="西安" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="南京" title="南京" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="苏州" title="苏州" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="长沙" title="长沙" lay-filter="remarks" />      
                    	<input type="checkbox" name="remarks"  value="合肥" title="合肥" lay-filter="remarks" />
                    	<input type="checkbox" name="remarks"  value="宁波" title="宁波" lay-filter="remarks" />
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
    
    <div class="layui-form-item">
        <label class="layui-form-label">限查看客户日期</label>
        <div class="layui-inline">
            <input type="text" value="" name="limitDate" placeholder="限查看客户日期" id="createDate" class="layui-input">
        </div>
    </div>
    
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="delFlag" lay-skin="switch"  lay-filter="delFlag"  lay-text="启用|停用" checked>
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
    layui.use(['form','jquery','layer', 'laydate'],function(){
    
		var laydate = layui.laydate;
		
		laydate.render({
		   elem: '#createDate', // 指定元素
		   type:'date'
		});
	          
       var form = layui.form,
           $    = layui.jquery,
           layer = layui.layer,
           delFlage = false;    //默认启用用户
           
        //添加验证规则
        form.verify({
        	phone : function(value, item) {
				if(!(/^1[345789]\d{9}$/.test(value))){ 
				    return "手机号格式错误"     
				}
        	},
            password : function(value, item){
                if(value.length < 6){
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd : function(value, item){
                if(!new RegExp($("#oldPwd").val()).test(value)){
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        });

        form.on("submit(addUser)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            delete data.field["roles"];
            delete data.field["confirmPwd"]
            var selectRole = [];
            $('input[name="roles"]:checked').each(function(){
                selectRole.push({"id":$(this).val()});
            });
            console.log(JSON.stringify(selectRole))
            data.field.roleLists = selectRole;
            //给角色添加城市
            delete data.field["remarks"];
            var remarks = [];
            $('input[name="remarks"]:checked').each(function(){
                remarks.push($(this).val());
            });
            if (remarks != null && remarks.length > 0) {
                data.field.remarks = remarks.join(",");
            } else {
            	data.field.remarks = '';
            	layer.msg("请选择至少一个城市");
				layer.close(loadIndex);
				return false;
            }
            //判断用户是否启用
            if(undefined !== data.field.delFlag && null != data.field.delFlag){
                data.field.delFlag = false;
            }else{
                data.field.delFlag = true;
            }

            var laeryIndex = layer.confirm('添加此经纪人后将自动绑定【' + data.field.remarks + "】地区的客户隐私号",  function(index, layero){
	            $.ajax({
	                type:"POST",
	                url:"${base}/admin/broker/add",
	                dataType:"json",
	                contentType:"application/json",
	                data:JSON.stringify(data.field),
	                success:function(res){
	                    layer.close(loadIndex);
	                    if(res.success){
	                        parent.layer.msg("经纪人添加成功!",{time:1500},function(){
	                            //刷新父页面
	                            parent.location.reload();
	                        });
	                    }else{
	                        parent.layer.msg(res.message);
	                    }
	                }
	            });
	        });
			layer.close(loadIndex);
            return false;
        });

    });
</script>
</body>
</html>