package by.tms.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private int id;


    public BusinessException(int id) {

        this.id = id;
    }

    public BusinessException(String message, int id) {
        super(message);
        this.id = id;
    }

    public BusinessException(String message, Throwable cause, int id) {
        super(message, cause);
        this.id = id;
    }

    public BusinessException(Throwable cause, int id) {
        super(cause);
        this.id = id;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }
}
