package com.xlc.community.community.enums;

public enum NotificationTypeEnum {

    REPALY_COMMONT(1,"评论回复"),
    REPALY_QUESTION(2,"问题回复");
    private int  type;
    private String typeName;

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    NotificationTypeEnum(int type,String  typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public  static String  nameOfType(int type){

        for (NotificationTypeEnum  notificationTypeEnum : NotificationTypeEnum.values()){

            if (type == notificationTypeEnum.getType()){
                return notificationTypeEnum.getTypeName();
            }
        }

        return "";
    }
}
