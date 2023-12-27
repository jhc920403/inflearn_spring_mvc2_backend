package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    /*
    // ExControllerAdvice로 Exception 전역처리를 위해 이동, 특정 Controller의 Exception 처리가 필요하다면 아래 코드 주석 푸는 형태로 사용 가능
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[ExceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER_EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "Internal Error");
    }
    */

    @GetMapping("/api/v2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        log.info("[getMember] String id : {}", id);
        if ("ex".equalsIgnoreCase(id)) {
            throw new RuntimeException("잘못된 사용자입니다.");
        }

        if ("bad".equalsIgnoreCase(id)) {
            throw new IllegalArgumentException("잘못된 값");
        }

        if ("user-ex".equalsIgnoreCase(id)) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
