package com.mysiteforme.admin.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.BlogArticle;
import com.mysiteforme.admin.entity.BlogChannel;
import com.mysiteforme.admin.entity.BlogComment;
import com.mysiteforme.admin.entity.Dict;
import com.mysiteforme.admin.entity.Log;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.QuartzTask;
import com.mysiteforme.admin.entity.QuartzTaskLog;
import com.mysiteforme.admin.entity.Rescource;
import com.mysiteforme.admin.entity.Role;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.util.Constants;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.util.VerifyCodeUtil;
import com.xiaoleilu.hutool.http.HttpUtil;

@Controller
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Value("${server.port}")
	private String port;
	
	@Autowired
	private Environment env;
	  
	@GetMapping("login")
	public String login(HttpServletRequest request) {
		LOGGER.debug("跳到这边的路径为:"+request.getRequestURI());
		Subject s = SecurityUtils.getSubject();
		LOGGER.debug("是否记住登录--->"+s.isRemembered()+"<-----是否有权限登录----->"+s.isAuthenticated()+"<----");
		if(s.isAuthenticated()){
			return "redirect:index";
		}else {
			return "login";
		}
	}
	
	@PostMapping("login/main")
	@ResponseBody
	@SysLog("用户登录")
	public RestResponse loginMain(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		if(session == null){
			return RestResponse.failure("session超时");
		}
		if (env.getProperty("spring.profiles.active").equals("dev")) {
			code = (String)session.getAttribute(Constants.VALIDATE_CODE);
		}
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return RestResponse.failure("用户名或者密码不能为空");
		}
		if(StringUtils.isBlank(rememberMe)){
			return RestResponse.failure("记住我不能为空");
		}
		if(StringUtils.isBlank(code)){
			return  RestResponse.failure("验证码不能为空");
		}
		Map<String,Object> map = Maps.newHashMap();
		String error = null;

		String trueCode =  (String)session.getAttribute(Constants.VALIDATE_CODE);
		if(StringUtils.isBlank(trueCode)){
			return RestResponse.failure("验证码超时");
		}
		if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
			error = "验证码错误";
		}else {
			/*就是代表当前的用户。*/
			Subject user = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
			try {
				user.login(token);
				if (user.isAuthenticated()) {
					map.put("url","index");
				}
			}catch (IncorrectCredentialsException e) {
				error = "登录密码错误.";
			} catch (ExcessiveAttemptsException e) {
				error = "登录失败次数过多";
			} catch (LockedAccountException e) {
				error = "帐号已被锁定.";
			} catch (DisabledAccountException e) {
				error = "帐号已被禁用.";
			} catch (ExpiredCredentialsException e) {
				error = "帐号已过期.";
			} catch (UnknownAccountException e) {
				error = "帐号不存在或已被禁用";
			} catch (UnauthorizedException e) {
				error = "您没有得到相应的授权！";
			}
		}
		if(StringUtils.isBlank(error)){
			return RestResponse.success("登录成功").setData(map);
		}else{
			return RestResponse.failure(error);
		}
	}
	
	@GetMapping("index")
	public String showView(Model model){
		Subject s = SecurityUtils.getSubject();
		return s.isAuthenticated() ? "index" : "login";
	}


	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@GetMapping("/genCaptcha")
	public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
		//将验证码放到HttpSession里面
		request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
		LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		//设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
		//写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	public static void main(String args[]){
		LOGGER.info("result的值为"+HttpUtil.get("http://localhost:8080/static/admin/quartzTask/list"));
	}

	@GetMapping("main")
	public String main(Model model){
		showStatistics(model);
		User curUser = getCurrentUser();
		User newUser = userService.findUserById(curUser.getId());
		Set<Role> roleSet = newUser.getRoleLists();
		List<Role> list = new ArrayList(roleSet);
		Role role = list.get(0);
		if (role.getId() == 4 && role.getName().equals("门店负责人")) {
			return "redirect:/admin/broker/list";
		} else if (role.getId() == 5 && role.getName().equals("经纪人")){
			return "redirect:/admin/broker/userlist";
		}
		return "main";
	}

	private void showStatistics(Model model){
		User user = userService.findUserById(MySysUser.id());
		Set<Menu> menus = user.getMenus();
		java.util.List<Menu> showMenus = Lists.newArrayList();
//		if(menus != null && menus.size()>0){
//			for (Menu menu : menus){
//				if(StringUtils.isNotBlank(menu.getHref())){
//					StringBuilder mys = new StringBuilder("http://localhost");
//					if("80".equals(port)){
//						mys.append("/static");
//					}else{
//						mys.append(":").append(port).append("/static");
//					}
//					mys.append(menu.getHref());
//					String result= HttpUtil.get(mys.toString());
//					if(StringUtils.isNotBlank(result)){
//						menu.setDataCount(Integer.valueOf(result));
//						showMenus.add(menu);
//					}
//				}
//			}
//		}
		showMenus.sort(new MenuComparator());
		model.addAttribute("userMenu",showMenus);
	}

	/**
	 *  空地址请求
	 * @return
	 */
	@GetMapping(value = "")
	public String index() {
		LOGGER.info("这事空地址在请求路径");
		Subject s = SecurityUtils.getSubject();
		return s.isAuthenticated() ? "redirect:index" : "login";
	}

	@GetMapping("systemLogout")
	@SysLog("退出系统")
	public String logOut(){
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	/**
	 * 统计用户
	 * @return
	 */
	@GetMapping("/static/admin/system/user/list")
	@ResponseBody
	public Integer getUserStatistics(){
		return userService.selectCount(new EntityWrapper<User>().eq("del_flag",false));
	}

	/**
	 * 统计角色
	 * @return
	 */
	@GetMapping("/static/admin/system/role/list")
	@ResponseBody
	public Integer getRoleStatistics(){
		return roleService.selectCount(new EntityWrapper<Role>().eq("del_flag",false));
	}

	/**
	 * 统计菜单
	 * @return
	 */
	@GetMapping("/static/admin/system/menu/list")
	@ResponseBody
	public Integer getMenuStatistics(){
		return menuService.selectCount(new EntityWrapper<Menu>().eq("del_flag",false));
	}

	/**
	 * 统计资源
	 * @return
	 */
	@GetMapping("/static/admin/system/rescource/list")
	@ResponseBody
	public Integer getRescourceStatistics(){
		return rescourceService.selectCount(new EntityWrapper<Rescource>().eq("del_flag",false));
	}

	/**
	 * 统计日志
	 * @return
	 */
	@GetMapping("/static/admin/system/log/list")
	@ResponseBody
	public Integer getLogStatistics(){
		return logService.selectCount(new EntityWrapper<Log>().eq("del_flag",false));
	}

	/**
	 * 统计站点
	 * @return
	 */
	@GetMapping("/static/admin/system/site/show")
	@ResponseBody
	public Integer getSiteStatistics(){
		return 15;
	}

	/**
	 * 统计数据库
	 * @return
	 */
	@GetMapping("/static/admin/system/table/list")
	@ResponseBody
	public Integer getTableStatistics(){
		return tableService.getTableCount();
	}

	/***
	 * 统计字典
	 * @return
	 */
	@GetMapping("/static/admin/system/dict/list")
	@ResponseBody
	public Integer getDictStatistics(){
		return dictService.selectCount(new EntityWrapper<Dict>().eq("del_flag",false));
	}

	/**
	 * 统计博客评论
	 * @return
	 */
	@GetMapping("/static/admin/blogComment/list")
	@ResponseBody
	public Integer getCommentStatistics(){
		return blogCommentService.selectCount(new EntityWrapper<BlogComment>().eq("del_flag",false));
	}

	/**
	 * 统计博客文章
	 * @return
	 */
	@GetMapping("/static/admin/blogArticle/list")
	@ResponseBody
	public Integer getArticleStatistics(){
		return blogArticleService.selectCount(new EntityWrapper<BlogArticle>().eq("del_flag",false));
	}

	/**
	 * 统计博客栏目
	 * @return
	 */
	@GetMapping("/static/admin/blogChannel/list")
	@ResponseBody
	public Integer getChannelStatistics(){
		return blogChannelService.selectCount(new EntityWrapper<BlogChannel>().eq("del_flag",false));
	}

	@GetMapping("/static/admin/blogTags/list")
	@ResponseBody
	public int getTagsStatistics(){
		return blogTagsService.listAll().size();
	}

	/**
	 * 统计定时任务数量
	 * @return
	 */
	@GetMapping("/static/admin/quartzTask/list")
	@ResponseBody
	public Integer getQuartzTaskStatistics(){
		int str = quartzTaskService.selectCount(new EntityWrapper<QuartzTask>().eq("del_flag",false));
		return str;
	}

	/**
	 * 统计定时任务日志数量
	 * @return
	 */
	@GetMapping("/static/admin/quartzTaskLog/list")
	@ResponseBody
	public Integer getQuartzTaskLogStatistics(){
		return quartzTaskLogService.selectCount(new EntityWrapper<QuartzTaskLog>().eq("del_flag",false));
	}

}

class MenuComparator implements Comparator<Menu>{

	@Override
	public int compare(Menu o1, Menu o2) {
		if(o1.getSort()>o2.getSort()){
			return -1;
		}else {
			return 0;
		}

	}
}