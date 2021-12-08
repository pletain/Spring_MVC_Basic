package hello.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {
    
    @RequestMapping("/request-param-v1")
    public void reuqestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String usernmae = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={} age={}", usernmae, age);

        response.getWriter().write("ok!!");

    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String meberName,
        @RequestParam("age") int memberAge) {   
            
            log.info("username={}, age={}", meberName, memberAge);
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
        @RequestParam String username,
        @RequestParam int age) {   
            
            log.info("username={}, age={}", username, age);
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
        String username,
        int age) {   
            
            log.info("username={}, age={}", username, age);
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age) {   
            
            log.info("username={}, age={}", username, age);
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") String username,
        @RequestParam(required = false, defaultValue = "-1") int age) {   
            
            log.info("username={}, age={}", username, age);
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
        @RequestParam Map<String, Object> paramMap,
        @RequestParam(required = true, defaultValue = "guest") String username,
        @RequestParam(required = false, defaultValue = "-1") int age) {   
            
            log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
            return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @Controller
    public class RequestBodyStringController {

        @PostMapping("/request-body-string-v1")
        public void requestBodyStirng(HttpServletRequest request, HttpServletResponse response) throws IOException {

            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            log.info("messageBody={}", messageBody);

            response.getWriter().write("ok!");
        }



        @PostMapping("/request-body-string-v2")
        public void requestBodyStirngV2(InputStream inputStream, Writer responseWriter) throws IOException {

            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            log.info("messageBody={}", messageBody);

            responseWriter.write("ok!");
        }

        @PostMapping("/request-body-string-v3")
        public HttpEntity<String> requestBodyStirngV3(HttpEntity<String> httpEntity) throws IOException {

            String messageBody = httpEntity.getBody();

            log.info("messageBody={}", messageBody);

            return new HttpEntity<>("ok!!");
        }

        @PostMapping("/request-body-string-v4")
        public String requestBodyStirngV4(@RequestBody String messageBody) throws IOException {

            log.info("messageBody={}", messageBody);

            return "ok!!!!";
        }
    }


}
