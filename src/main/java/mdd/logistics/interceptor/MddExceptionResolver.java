package mdd.logistics.interceptor;

import mdd.logistics.system.constant.CodeEnums;
import mdd.logistics.system.exception.MddException;
import mdd.logistics.system.response.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MddExceptionResolver {
    private  final Logger logger = LoggerFactory.getLogger(ThreadLocal.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVo defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.error("系统异常"+  "_" + ex.getMessage(), ex);
        MddException mddExp = null;

        if (ex instanceof MddException) {
            mddExp = (MddException) ex;
        } else {
            mddExp = new MddException(CodeEnums.SYSTEM_ERROR);
        }
        ResponseVo rv =  new ResponseVo(mddExp.getCode(),mddExp.getMsg());
        return rv;
    }

}
