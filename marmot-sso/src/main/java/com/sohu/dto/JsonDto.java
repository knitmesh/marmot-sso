/**
* @Title: JsonDto.java
* @Package com.sohu.dto
* @Description: (用一句话描述该文件做什么)
* @author wenbozhang
* @date 2016年1月28日 下午3:25:43
* @version V1.0
*/
/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;
/**
 * @ClassName: JsonDto
 * @Description: 用于前后台传输json的工具类
 * @author wenbozhang
 * @date 2016年1月28日 下午3:25:43
 *
 */
public class JsonDto<T> {
    private  boolean isSuccess;
    private String info;
    private T data;

    public void setIsSuccess(boolean isSuccess){
        this.isSuccess=isSuccess;
    }
    public boolean getIsSuccess(){
        return isSuccess;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public JsonDto(boolean isSuccess, String info) {
        super();
        this.isSuccess = isSuccess;
        this.info = info;
    }

    public JsonDto(boolean isSuccess, T data) {
        super();
        this.isSuccess = isSuccess;
        this.data = data;
    }

}
