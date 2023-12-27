package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외가 발생했습니다~~");
    }

    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400, "404 오류 발생~!!");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value(), "404 오류 발생~!!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(500);
    }
}
