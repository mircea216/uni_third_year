package com.company.bst;

public class FIP {
    private final Integer code;
    private final Integer positionInTs;

    public FIP(Integer code, Integer positionInTs) {
        this.code = code;
        this.positionInTs = positionInTs;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getPositionInTs() {
        return positionInTs;
    }
}
