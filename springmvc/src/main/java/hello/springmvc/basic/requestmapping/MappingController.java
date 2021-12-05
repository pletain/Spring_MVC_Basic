package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(MappingController.class);

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("HelloBasic");
        return "ok";
    }
    
}
