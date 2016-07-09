package org.sdust.searchEngine.util;


/**
 * Created by zzy on 2016/4/25.
 */
public class ResponseMessageFactory {
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "1";

    public static ResponseMessage success(){
        return success(StatusMessage.MSG_SUCCESS,null);
    }
    public static ResponseMessage success(String msg){
        return success(msg,null);
    }

    public static ResponseMessage successData(Object data){
       return success(StatusMessage.MSG_SUCCESS,data);
    }

    public static ResponseMessage success(String msg,Object data){
        return new ResponseMessage(msg,SUCCESS_CODE,data);
    }

    public static ResponseMessage error(){
        return error(StatusMessage.MSG_ERROR,null);
    }

    public static ResponseMessage error(String msg){
        return error(msg,null);
    }

    public static ResponseMessage error(String msg,Object data){
        return new ResponseMessage(msg,ERROR_CODE,data);
    }



}
