package com.great.common.web.log;

import com.great.common.web.StatusException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Y.X
 * @since 2021/9/7
 */
@Aspect
@Component
@ConditionalOnClass(value = Aspect.class)
@ConditionalOnProperty(
        prefix = "global.web.log",
        value = "enable",
        havingValue = "true",
        matchIfMissing = true)
public class WebLogAspect implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    private final WebLogCollector<WebLogAttr> logCollector = new DefaultAspectLogCollector();

    private ApplicationContext applicationContext;

    private Map<String, WebLogListener> logListener;

    @PostConstruct
    public void init() {
        this.logListener = applicationContext.getBeansOfType(WebLogListener.class);
    }

    @Pointcut("execution(public * com.great.*.controller..*.*(..))")
    public void requestLog() {
    }

    @Around("requestLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        WebLogAttr apiLogAttr = logCollector.collect(point);
        WebLogAttrStore.store(apiLogAttr);

        logStart(apiLogAttr);
        Object result = point.proceed();
        apiLogAttr.setResultAndEndTime(result);
        logEnd(apiLogAttr);

        WebLogAttrStore.remove();

        return result;
    }

    @AfterThrowing(value = "requestLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        WebLogAttr apiLogAttr = WebLogAttrStore.getAttr();
        apiLogAttr.setException(e);

        if (e instanceof StatusException) {
            apiLogAttr.setResult(e.getMessage());
        }

        logEnd(apiLogAttr);

        WebLogAttrStore.remove();
    }

    /**
     * 操作开始的日志
     */
    private void logStart(WebLogAttr apiLogAttr) {
        for (Map.Entry<String, WebLogListener> entry : logListener.entrySet()) {
            entry.getValue().start(apiLogAttr);
        }
    }

    /**
     * 操作结束的日志
     */
    private void logEnd(WebLogAttr apiLogAttr) {
        for (Map.Entry<String, WebLogListener> entry : logListener.entrySet()) {
            entry.getValue().end(apiLogAttr);
        }
    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
