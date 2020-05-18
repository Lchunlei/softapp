package com.chun.lei.model;

/**
 * @Created by lcl on 2020/4/24 0024
 */
public class ApiResp<T> {
    private String respCode;
    private String respMsg;
    private T respData;

    public ApiResp() {
        this.respCode = "R000";
        this.respMsg = "操作成功！";
    }

    public ApiResp(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public void respErr(String errMsg) {
        this.respCode = "R500";
        this.respMsg = errMsg;
    }

    public void loginErr() {
        this.respCode = "R502";
        this.respMsg = "登录失败";
    }

    public void authErr() {
        this.respCode = "R503";
        this.respMsg = "验证失败，请退出重新登录！";
    }

    @Override
    public String toString() {
        return "ApiResp{" +
                "respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", respData=" + respData +
                '}';
    }

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
