package com.example.ecommercewebapp.library.enums;

public enum MessageCodes {
    SUCCESS("200","general.success"),
    FAIL("500","general.fail"),
    BAD_REQUEST("400","general.badRequest"),
    NOT_FOUND("404","general.notFound"),
    UNAUTHORIZED("401","general.unauthorized"),
    ENTITY_NOT_FOUND("404","general.entityNotFound"),
    ENTITY_ALREADY_EXISTS("409","general.entityAlreadyExists"),
    FIELD_NOT_FOUND("1001","general.fieldNotFound"),
    BAD_ALGORITHM("1002","general.badAlgorithm");
    private final String code;
    private final String message;

    MessageCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
