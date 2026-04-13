package com.kaizen.security.common.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kaizen.security.common.dto.ApiResponse;
public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK.value(), message, data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.CREATED.value(), message, data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse<Void>> successMessage(String message) {
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), message, null);
        return ResponseEntity.ok(response);
    }


    public static ResponseEntity<ApiResponse<String>> error(String message) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}