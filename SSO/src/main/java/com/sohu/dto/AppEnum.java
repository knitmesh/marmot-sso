/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;
/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月2日上午10:55:02
 */
public enum AppEnum {
        NOUSER(0,"没有找到用户"),
        DATAERROR(1,"添加数据异常"),
        SUCCESS(2,"批量操作成功");

        private int state;
        private String info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }


        AppEnum(int state,String s){
            this.state=state;
            this.info=s;
        }

        public static AppEnum getState(int index){
            for(AppEnum appEnum:values()){
                if(appEnum.getState()==index){
                    return appEnum;
                }
            }
            return null;
    }
}
