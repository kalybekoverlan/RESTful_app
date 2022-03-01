package kg.nurtelecom.simregistration.models;

import kg.nurtelecom.simregistration.enums.ResultCode;

public class ResponseMessage<T> {
    T result;
    ResultCode resultCode;
    String details;

    public ResponseMessage() {
    }

    public ResponseMessage(T result, ResultCode resultCode, String details) {
        this.result = result;
        this.resultCode = resultCode;
        this.details = details;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
