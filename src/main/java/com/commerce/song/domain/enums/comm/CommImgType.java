package com.commerce.song.domain.enums.comm;

public enum CommImgType {
    PRODUCT(0, "상품타입"),
    COMMUNITY(1, "커뮤니티"),
    CAMPING_INFO(2, "캠핑인포"),
    TEMP_CONF(98, "임시확정"),
    TEMP(99, "임시"),

    ;
    private Integer code;
    private String desc;

    CommImgType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
