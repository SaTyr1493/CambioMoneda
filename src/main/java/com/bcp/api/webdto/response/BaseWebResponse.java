package com.bcp.api.webdto.response;

import com.bcp.api.exception.ErrorCode;

public class BaseWebResponse<T> {
    private ErrorCode errorCode;
    private T data;

    public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static BaseWebResponse successNoData() {
        return new BaseWebResponse();
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
        BaseWebResponse<T> obj = new BaseWebResponse<>();
        obj.setData(data);
        return obj;
    }

    public static BaseWebResponse error(ErrorCode errorCode) {
        BaseWebResponse obj = new BaseWebResponse<>();
        obj.setErrorCode(errorCode);
        return obj;
    }
}
