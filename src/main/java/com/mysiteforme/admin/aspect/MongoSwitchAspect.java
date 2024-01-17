package com.mysiteforme.admin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mysiteforme.admin.util.SpringUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;

/**
 * MongoTemplate切换， 切换连接mongodb数据库
 * @author CB
 * 
 * @dateTime 2019年4月11日下午6:40:24
 */
@Aspect
@Order(2)
@Component
public class MongoSwitchAspect {

    @Pointcut("execution(* com.zfh.app.core.service..*.*(..))||execution(* com.zfh.app.mongo.service..*.*(..))")
    public void primary(){}

    @Before("primary()")
    public void doBefore(JoinPoint joinPoint) {
    	String className = joinPoint.getTarget().getClass().getName();
    	Object targetObject  = joinPoint.getTarget();
    	if (className.indexOf("service.system") > -1) {
//        	System.err.println("---before service.system --> mongoTemplate2");
    		BaseMongoServiceImpl<?> baseMongoService = (BaseMongoServiceImpl<?>) targetObject;
    		baseMongoService.setMongoTemplate((MongoTemplate)SpringUtil.getBean("mongoTemplate2"));
    	} 
    	else if (className.indexOf("service.esf") > -1){
//        	System.err.println("---before service.esf --> mongoTemplate");
        	BaseMongoServiceImpl<?> baseMongoService = (BaseMongoServiceImpl<?>) targetObject;
    		baseMongoService.setMongoTemplate((MongoTemplate)SpringUtil.getBean("mongoTemplate"));
    	}
    }

    
    /**
     * spring aop 无法代理父类方法 的解决方法，需要制定target
     * 排除某些方法用 && !
     * @see https://my.oschina.net/u/2819035/blog/730765
     * 2019年4月11日下午5:56:58
     */
//    @Pointcut("execution(* com.zfh.app.core.dao.BaseMongoDao.*(..)) && !execution(* com.zfh.app.core.dao.BaseMongoDao.set*(..)) and target(com.zfh.app.mongo.dao..*)")
//    public void pointcutSecond(){}
//
//    @Before("pointcutSecond()")
//    public void doBefore1(JoinPoint joinPoint) {
//    	String className = joinPoint.getTarget().getClass().getName();
//    	if (className.indexOf("dao.system") > -1) {
//        	System.err.println("---before1 dao.system --> mongoTemplate2");
//        	BaseMongoServiceImpl<?> baseMongoService = (BaseMongoServiceImpl<?>)SpringUtil.getBean(joinPoint.getTarget().getClass());
//        	baseMongoService.setMongoTemplate((MongoTemplate)SpringUtil.getBean("mongoTemplate2"));
//    	} 
////    	else if (className.indexOf("dao.esf") > -1){
////        	System.err.println("---before1 dao.esf --> mongoTemplate");
////    		BaseMongoDao baseMongoDao = (BaseMongoDao)SpringUtil.getBean(joinPoint.getTarget().getClass());
////    		baseMongoDao.setMongoTemplate((MongoTemplate)SpringUtil.getBean("mongoTemplate"));
////    	}
//    }

}
