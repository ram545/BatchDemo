package com.example.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobException extends RuntimeException{

    private String errorNo;

    private String errorDescription;

    JobException(String errorNo, String errorDescription){
        super(errorDescription);
    }
}
