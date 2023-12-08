package com.suyh0501.controller;

import com.suyh0501.vo.DemoTestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author suyh
 * @since 2023-12-07
 */
@RestController
@RequestMapping("/i18n")
@Validated
@Slf4j
public class I18nController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String func(@Valid DemoTestDto dto) {
        return "OK";
    }
}
