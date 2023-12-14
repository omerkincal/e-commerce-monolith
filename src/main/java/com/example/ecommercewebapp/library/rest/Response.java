package com.example.ecommercewebapp.library.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T>{

    private T data;
    private MetaResponse meta;

    public Response(MetaResponse meta) {
        this.meta = meta;
    }

    public Response(T data) {
        this.data = data;
        this.meta = MetaResponse.success();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("data: ");
        sb.append(data != null ? data.toString() : "null");
        sb.append(", meta: ");
        sb.append(meta != null ? meta.toString() : "null");
        return sb.toString();
    }

}
