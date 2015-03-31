package com.fengjx.common.system.exception;

/**
 * 自定义异常
 * 
 * @author taixu.zqq
 * @version $Id: MyException.java, v 0.1 2014年7月24日 下午6:58:39 taixu.zqq Exp $
 */
public class MyRuntimeException extends RuntimeException{

    private static final long serialVersionUID = -5710488447295073398L;
    
    public MyRuntimeException(){
    }   
    
    public MyRuntimeException(String message) {   
        super(message);           
    }
    
    public MyRuntimeException(Throwable throwable){
        super(throwable);
    }
    
    public MyRuntimeException(String message, Throwable throwable){
        super(message, throwable);
    }
}
