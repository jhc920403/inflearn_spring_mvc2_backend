package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/v1/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
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

    @GetMapping("/api/v1/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/v1/response-status-ex2")
    public String responseStatusEx2() {
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
        throw new IllegalArgumentException("test");
    }

    @GetMapping("/api/v1/default-handler-ex")
    public String defaultException(@RequestParam(value = "data") Integer data) {
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
