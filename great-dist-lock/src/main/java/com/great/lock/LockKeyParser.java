package com.great.lock;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2021/9/16 09:50
 *
 * @author Y.X
 */
public class LockKeyParser {
    private static final Logger log = LoggerFactory.getLogger(LockKeyParser.class);
    private final static Map<String, Expression> cacheMap = new ConcurrentHashMap<>(32);
    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public List<String> getKeyValue(String[] originalKeys, String[] argNames, Object[] args) {
        if (originalKeys == null || originalKeys.length <= 0) {
            throw new IllegalArgumentException("Lock key is empty.");
        }

        List<String> parsedKeys = new ArrayList<>(originalKeys.length);
        EvaluationContext context = new StandardEvaluationContext();
        if (argNames != null && argNames.length > 0) {
            for (int i = 0; i < argNames.length; i++) {
                context.setVariable(argNames[i], args[i]);
            }
        }
        for (String key : originalKeys) {
            Expression expression = cacheOrParse(key.trim());
            Object value = expression.getValue(context);
            if (value == null) {
                continue;
            }
            if (value instanceof List) {
                List<?> list = (List<?>) value;
                for (Object obj : list) {
                    addParedKey(parsedKeys, obj.toString());
                }
            } else if (value.getClass().isArray()) {
                Object[] objs = (Object[]) value;
                for (Object obj : objs) {
                    addParedKey(parsedKeys, obj.toString());
                }
            } else {
                addParedKey(parsedKeys, value.toString());
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("parsed lock keys: {}", Arrays.toString(parsedKeys.toArray()));
        }

        return parsedKeys;
    }

    public Expression cacheOrParse(String key) {
        Expression expression;
        if (cacheMap.containsKey(key)) {
            expression = cacheMap.get(key);
        } else {
            expression = parser.parseExpression(key);
            cacheMap.put(key, expression);
        }
        return expression;
    }

    public List<String> keyValues(JoinPoint joinPoint, String[] originalKeys) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        if ((argNames == null || argNames.length <= 0)
                && (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0)) {
            argNames = parameterNameDiscoverer.getParameterNames(methodSignature.getMethod());
        }
        Object[] args = joinPoint.getArgs();
        return getKeyValue(originalKeys, argNames, args);
    }

    private void addParedKey(List<String> parsedKeys, String key) {
        parsedKeys.add("lock:" + key);
    }
}
