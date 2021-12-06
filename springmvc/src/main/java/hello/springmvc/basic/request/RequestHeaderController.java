package hello.springmvc.basic.request;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
        HttpServletResponse response,
        HttpMethod httpMethod,
        Locale locale,
        @RequestHeader MultiValueMap<String, String> headerMap,
        @RequestHeader("host") String host,
        @CookieValue(value = "myCookie", required = false) String cookie) {
        
            log.info("request={}", request);
            log.info("response={}", response);
            log.info("httpMethod={}", httpMethod);
            log.info("locale={}", locale); // 언어정보
            log.info("headerMap={}", headerMap); //헤더 한번에 다 받기
            log.info("headers={}", host); // 특정 헤더 받을 때(여기선 host)
            log.info("myCookie={}", cookie);

            return "ok!";
        }

}
