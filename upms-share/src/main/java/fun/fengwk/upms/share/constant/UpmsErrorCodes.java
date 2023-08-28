package fun.fengwk.upms.share.constant;

import fun.fengwk.convention4j.api.code.ConventionErrorCode;
import fun.fengwk.convention4j.api.code.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fengwk
 */
@Getter
@AllArgsConstructor
public enum UpmsErrorCodes implements ConventionErrorCode {

    DUPLICATE_USER_SYSTEM_CODE(1001, HttpStatus.BAD_REQUEST),
    USER_SYSTEM_NOT_FOUND(1002, HttpStatus.BAD_REQUEST),
    USER_SYSTEM_TYPE_NOT_FOUND(1003, HttpStatus.BAD_REQUEST),
    INVALID_USER_SYSTEM_CODE(1004, HttpStatus.BAD_REQUEST),
    CREATE_USER_SYSTEM_FAILED(1005, HttpStatus.INTERNAL_SERVER_ERROR),
    UPDATE_USER_SYSTEM_FAILED(1006, HttpStatus.INTERNAL_SERVER_ERROR),
    REMOVE_USER_SYSTEM_FAILED(1007, HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE_USERNAME(1008, HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL(1009, HttpStatus.BAD_REQUEST),
    DUPLICATE_MOBILE(1010, HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1011, HttpStatus.BAD_REQUEST),
    CREATE_USER_FAILED(1012, HttpStatus.INTERNAL_SERVER_ERROR),
    UPDATE_USER_FAILED(1013, HttpStatus.INTERNAL_SERVER_ERROR),
    REMOVE_USER_FAILED(1014, HttpStatus.INTERNAL_SERVER_ERROR),
    APP_NOT_FOUND(1015, HttpStatus.BAD_REQUEST),
    APP_UNSUPPORTED_VALID_CODE_MODE(1016, HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final int domainCode;
    private final HttpStatus httpStatus;

    @Override
    public String getDomain() {
        return "UPMS";
    }

    @Override
    public String getMessage() {
        return name();
    }

}
