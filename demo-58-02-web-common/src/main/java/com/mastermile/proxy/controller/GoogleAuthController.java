package com.mastermile.proxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyh
 * @since 2023-11-26
 */
@RestController
@RequestMapping("/google/auth")
@Slf4j
@Validated
public class GoogleAuthController {
}
