package com.lvhong.web.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.lvhong.web.service.JedisService;
import com.lvhong.web.util.RedisCache;

@Aspect
@Component
@EnableAspectJAutoProxy
public class RedisCacheAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private JedisService jedisService;
	
	@Pointcut("execution(public * com.lvhong.web.service..*.*(..))")
    public void webAspect(){
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //得到类名、方法名和参数
        String redisResult = "";
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        //根据类名，方法名和参数生成key
        String key = genKey(className,methodName,args);
        logger.info("生成的key[{}]",key);
        //得到被代理的方法
        Signature signature = pjp.getSignature();
        if(!(signature instanceof MethodSignature)){
            throw  new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
        //得到被代理的方法上的注解
        Object result = null;
        if(method.getAnnotation(RedisCache.class) != null) {        	
        	Class modelType = method.getAnnotation(RedisCache.class).type();
        	int cacheTime = method.getAnnotation(RedisCache.class).cacheTime();
        	if(!jedisService.exists(key)) {
        		logger.info("缓存未命中");
        		//缓存不存在，则调用原方法，并将结果放入缓存中
        		result = pjp.proceed(args);
        		redisResult = JSON.toJSONString(result);
        		jedisService.set(key,redisResult,cacheTime);
        	} else{
        		//缓存命中
        		logger.info("缓存命中");
        		redisResult = jedisService.get(key);
        		//得到被代理方法的返回值类型
        		Class returnType = method.getReturnType();
        		result = JSON.parseObject(redisResult,returnType);
        	}
        }else {
        	result = pjp.proceed(args);
        }
        return result;
    }
	
	private String genKey(String className, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder("SpringBoot:");
        sb.append(className);
        sb.append("_");
        sb.append(methodName);
        sb.append("_");
        for (Object object: args) {
            logger.info("obj:"+object);
            if(object!=null) {
                sb.append(object+"");
                sb.append("_");
            }
        }
        return sb.toString();
    }
	
}
