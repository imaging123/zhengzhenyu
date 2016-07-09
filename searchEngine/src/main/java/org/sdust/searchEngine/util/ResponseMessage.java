package org.sdust.searchEngine.util;

/**
 * Created by zzy on 2016/4/18.
 */
public class ResponseMessage {

    private String msg;//响应信息
    private String code;//类型代码
    private Object data;//响应数据


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

//    public void setCode(String code) {
//        this.code = code;
//    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseMessage(){}

    public ResponseMessage(String msg, String code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public boolean hasError(){
        return code == "1";
    }

    public static void main(String[] args) {
    }

}
