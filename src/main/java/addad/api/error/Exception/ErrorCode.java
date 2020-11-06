package addad.api.error.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_AUTH_CODE(400,"Invalid Auth Code"),
    EXPIRED_AUTH_CODE(400,"Expired Auth Code"),
    INVALID_AUTH_EMAIL(400,"Invalid Auth Email"),
    INVALID_SEARCH_TYPE(400,"Invalid Search Type"),
    INVALID_PASSWORD(400, "Invalid password"),
    INVALID_TARGET(400,"Invalid Target"),
    INVALID_TOKEN(401,"Invalid Token"),
    EXPIRED_TOKEN(401,"Expired Token"),
    USER_NOT_LEADER(401, "User Not Leader"),
    PERMISSION_DENIED_EXCEPTION(401,"Permission Denied"),
    TEAM_NOT_FOUND(404,"Team Not Found"),
    USER_NOT_FOUND(404,"User Not Found"),
    MEMBER_NOT_FOUND(404,"Member Not Found"),
    TARGET_NOT_FOUND(404,"Target Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    MESSAGE_NOT_FOUND(404, "Message Not Found"),
    APPLICATION_NOT_FOUND(404,"Application Not Found"),
    TEAM_LEADER_NOT_FOUND(404, "Team Leader Not Found"),
    IMAGE_NOT_FOUND(404, "Image Not Found"),
    NUMBER_DUPLICATION(409,"Number Duplication"),
    USER_ALREADY_EVALUATION_EXCEPTION(409, "User Already Evaluation"),
    USER_ALREADY_EXISTS_EXCEPTION(409,"User Already Exists"),
    USER_ALREADY_INCLUDE_EXCEPTION(409,"User Already Include"),
    MEMBER_ALREADY_INCLUDE_EXCEPTION(409, "Member Already Include"),
    TEAM_ALREADY_EXISTS_EXCEPTION(409, "Team Already Exists");

    private final int status;

    private final String message;

}
