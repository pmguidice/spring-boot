package com.pg.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.info("in hello()");

        return "Hello, world";
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    public String helloId(@PathVariable int id) {
        logger.info("in helloId()");

        String idStr = "0";

        try {
            idStr = Integer.toString(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Hello, " + id;
    }
}
