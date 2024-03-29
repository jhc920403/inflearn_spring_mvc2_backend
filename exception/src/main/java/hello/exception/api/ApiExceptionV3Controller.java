package hello.exception.api;

import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionV3Controller {

    @GetMapping("/api/v3/members/{id}")
    public ApiExceptionV2Controller.MemberDto getMember(@PathVariable("id") String id) {
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

        return new ApiExceptionV2Controller.MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
