package com.suyh5802.web.base.runner;

import com.suyh5802.web.base.util.JsonUtils;
import com.suyh5802.web.base.vo.TbUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author suyh
 * @since 2023-12-07
 */
@Component
@Slf4j
public class MessageSourceTestRunner implements ApplicationRunner {
    @Resource
    private MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String message = messageSource.getMessage("suyh.temp", null, LocaleContextHolder.getLocale());
        System.out.println(message);
        message = messageSource.getMessage("suyh.notExist", null, "没有配置", LocaleContextHolder.getLocale());
        System.out.println(message);

        TbUserDto dto = new TbUserDto();
        dto.setUid("107834903").setGaid("3ef61bf6-3c3f-4134-83a9-51a3bb4744be").setChannel("slm_1300004")
                .setCtime(1704973990L).setRegDate(20240111L).setStatRegDate(20240111L).setPn("aw").setHashTime("202401111720");
        String dtoJson = JsonUtils.serializable(dto);
        System.out.println("dtoJson: " + dtoJson);
    }
}
