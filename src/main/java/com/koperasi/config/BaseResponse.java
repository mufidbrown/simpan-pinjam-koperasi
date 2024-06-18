package com.koperasi.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

//    private static final long serialVersionUID = -874399667315508476L;


    private boolean success;
    private String message;
    @JsonIgnoreProperties({"pageable", "sort"})
    private T data;
    private int status;
    private String color;


    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .color("#F44336")
                .message("OK")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> ok(String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .color("#F44336")
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }



    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .status(500)
                .color("#F44336")
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(null)
                .build();
    }

}
