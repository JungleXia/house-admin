<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户修改--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
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
    <input class="layui-hide" name="id" value="${localuser.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="loginName" lay-verify="required" placeholder="请输入登录名" value="${localuser.loginName}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="nickName" placeholder="请输入昵称" value="${localuser.nickName}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="tel" lay-verify="phone" maxlength="11" placeholder="请输入手机号" value="${localuser.tel}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
	        <label class="layui-form-label">新密码</label>
	        <div class="layui-input-block">
	            <input type="password" name="password" placeholder="不修改密码可以不填" lay-verify="password" id="oldPwd" class="layui-input pwd">
	        </div>
	    </div>
	    <div class="layui-inline">
	        <label class="layui-form-label">再次确认密码</label>
	        <div class="layui-input-block">
	            <input type="password" name="confirmPwd" placeholder="不修改密码可以不填" lay-verify="confirmPwd" class="layui-input pwd">
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
                                <input type="checkbox" name="roles"  value="${role.id}" title="${role.name}" lay-filter="role" <#if roleIds?contains(role.id?string)>checked</#if> />
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
                    	<input type="checkbox" name="remarks"  value="深圳" title="深圳" lay-filter="remarks" <#if (localuser.remarks?indexOf("深圳") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="广州" title="广州" lay-filter="remarks" <#if (localuser.remarks?indexOf("广州") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="东莞" title="东莞" lay-filter="remarks" <#if (localuser.remarks?indexOf("东莞") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="惠州" title="惠州" lay-filter="remarks" <#if (localuser.remarks?indexOf("惠州") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="北京" title="北京" lay-filter="remarks" <#if (localuser.remarks?indexOf("北京") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="上海" title="上海" lay-filter="remarks" <#if (localuser.remarks?indexOf("上海") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="杭州" title="杭州" lay-filter="remarks" <#if (localuser.remarks?indexOf("杭州") > -1)>checked</#if> />  
                    	<input type="checkbox" name="remarks"  value="重庆" title="重庆" lay-filter="remarks" <#if (localuser.remarks?indexOf("重庆") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="成都" title="成都" lay-filter="remarks" <#if (localuser.remarks?indexOf("成都") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="武汉" title="武汉" lay-filter="remarks" <#if (localuser.remarks?indexOf("武汉") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="西安" title="西安" lay-filter="remarks" <#if (localuser.remarks?indexOf("西安") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="南京" title="南京" lay-filter="remarks" <#if (localuser.remarks?indexOf("南京") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="苏州" title="苏州" lay-filter="remarks" <#if (localuser.remarks?indexOf("苏州") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="长沙" title="长沙" lay-filter="remarks" <#if (localuser.remarks?indexOf("长沙") > -1)>checked</#if> />      
                    	<input type="checkbox" name="remarks"  value="合肥" title="合肥" lay-filter="remarks" <#if (localuser.remarks?indexOf("合肥") > -1)>checked</#if> />
                    	<input type="checkbox" name="remarks"  value="宁波" title="宁波" lay-filter="remarks" <#if (localuser.remarks?indexOf("宁波") > -1)>checked</#if> />
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">限查看客户日期</label>
        <div class="layui-inline layui-input-block">
            <input type="text" value="${broker.limitDate}" name="limitDate" placeholder="限查看客户日期" id="createDate" class="layui-input">
        </div>
    </div>
    
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="delFlag" lay-skin="switch" value="true"  lay-text="启用|停用" <#if (localuser.delFlag  == false)>checked=""</#if> >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">我要修改</button>
            <button class="layui-btn"   class="layui-btn layui-btn-primary">我不改了</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    var index = parent.layer.getFrameIndex(window.name); //当前窗口索引
    layui.use(['form','jquery','layer', 'laydate'],function(){
    
    	var laydate = layui.laydate;
		
		laydate.render({
		   elem: '#createDate', // 指定元素
		   type:'date'
		});
		
        var form = layui.form,
                $    = layui.jquery,
                layer = layui.layer,
                delFlage = ${localuser.delFlag?string};

        //添加验证规则
        form.verify({
            phone : function(value, item) {
				if(!(/^1[345789]\d{9}$/.test(value))){ 
				    return "手机号格式错误"     
				}
        	},
            password : function(value, item){
                if(value != null && value != '' && value.length < 6){
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd : function(value, item){
            	if ($("#oldPwd").val() != null && $("#oldPwd").val() !='') {
            	    if(!new RegExp($("#oldPwd").val()).test(value)){
	                    return "两次输入密码不一致，请重新输入！";
	                }
            	}
            }
        });
        
        form.on("submit(addUser)",function(data){
            if(data.field.id == null){
                layer.msg("用户ID不存在");
                return false;
            }
            //给角色赋值
            delete data.field["roles"];
            delete data.field["confirmPwd"]
            var selectRole = [];
            $('input[name="roles"]:checked').each(function(){
                selectRole.push({"id":$(this).val()});
            });
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
            }
            //判断用户是否启用
            if(undefined !== data.field.delFlag && null != data.field.delFlag){
                data.field.delFlag = false;
            }else{
                data.field.delFlag = true;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.ajax({
                type:"POST",
                url:"${base}/admin/broker/edit",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("用户编辑成功！",{time:1500},function(){
                            parent.location.reload();
                        });
                    }else{
                        layer.msg(res.message);
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>