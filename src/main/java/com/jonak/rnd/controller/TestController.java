package com.jonak.rnd.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
Fahim created at 11/12/2020
*/
@RestController
@RequestMapping("/api")
@Log4j2
public class TestController {


    @RequestMapping(path = "/getData", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasAnyScope('read')")
    public ResponseEntity<String> geData(HttpServletRequest request) {

        return ResponseEntity.ok("Hello");
    }

    @RequestMapping(path = "/setData", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN') and #oauth2.hasAnyScope('write')")
    public ResponseEntity<String> seData(HttpServletRequest request) {

        return ResponseEntity.ok("Data set");
    }
}
