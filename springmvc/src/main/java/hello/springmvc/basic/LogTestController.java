package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

// return값이 HTTP 메시지 바디에 바로 입력된다.
// @Slf4j
@RestController
public class LogTestController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        //로그 출력 레벨을 info로 설정해도 문자 더하기 연산이 발생한다.(사용하질 않을 것에 대한 무의미한 연산)
        log.debug("String concat log="  + name);
        return "ok";
    }
}
