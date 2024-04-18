package com.suyh.service;

import com.suyh.mapper.ChannelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author suyh
 * @since 2024-04-17
 */
@RequiredArgsConstructor
@Slf4j
public class ChannelService {
    private final ChannelMapper channelMapper;

    @PostConstruct
    public void init() {
        log.info("ChannelService  init method.");
    }
}
