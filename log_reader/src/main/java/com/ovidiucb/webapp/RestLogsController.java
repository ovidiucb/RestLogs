package com.ovidiucb.webapp;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ovidiucb
 */
@RestController
public class RestLogsController implements ErrorController {

    @RequestMapping(value="/error")
    public @ResponseBody String getError() {
        return "Some error";
    }

    @Override
    public String getErrorPath() {
        return "Error";
    }
}
