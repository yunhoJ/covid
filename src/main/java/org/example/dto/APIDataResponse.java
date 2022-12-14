package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.constant.ErrorCode;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse<T> extends APIErrorResponse {

    private final T data;

    private APIDataResponse( T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> APIDataResponse<T> of(T data)
    {
        return new APIDataResponse<T>(data);
    }
    public static <T> APIDataResponse<T> empty(){
        return new APIDataResponse<>(null);
    }
}
