package com.great.common.web.log;

/**
 * 处理日志接口
 * Created on 2021/9/8 16:57
 *
 * @author Y.X
 */
public interface WebLogListener {

    void start(final WebLogAttr logAttr);

    void end(final WebLogAttr logAttr);
}
