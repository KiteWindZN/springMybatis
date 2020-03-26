package com.example.springbootdemo.utils;
import lombok.Data;

@Data
public class ResponseResult {
    public static final int FAIL = -1;
    public static final int SUCCESS = 0;
    private String msg = "操作成功";

    private int code = SUCCESS;

    private Object data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(String msg, Object data, int code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static ResponseResult success() {
        return success("操作成功");
    }

//    public static ResponseResult passApplySuccess() {
//        return success("申请信息提交成功");
//    }

    public static ResponseResult success(String msg) {
        return success(msg, null);
    }

    public static ResponseResult successData(Object data) {
        return success("操作成功", data);
    }


    public static ResponseResult success(Object data) {
        return success("操作成功", data);
    }

    public static ResponseResult mutiple(){
        return new ResponseResult("出入证已申请，请勿重新提交", null, 2);
    }

    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(msg, data, SUCCESS);
    }

    public static ResponseResult error() {
        ResponseResult resultBean = new ResponseResult();
        resultBean.setCode(FAIL);
        resultBean.setMsg("操作失败");
        return resultBean;
    }

    public static ResponseResult error(String msg) {
        ResponseResult resultBean = new ResponseResult();
        resultBean.setCode(FAIL);
        resultBean.setMsg(msg);
        return resultBean;
    }
    public static ResponseResult result(String msg, Object data,int code) {
        return new ResponseResult(msg, data, code);
    }


}
