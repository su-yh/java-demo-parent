package com.suyh.es3202.service;

import com.suyh.dto.PageInfoDto;
import com.suyh.es3202.entity.KibanaEcommerceEntity;
import com.suyh.es3202.repository.KibanaEcommerceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KibanaEcommerceService {

    @Resource
    private KibanaEcommerceRepository kibanaEcommerceRepository;

    public List<KibanaEcommerceEntity> getPageEntityList(
            KibanaEcommerceEntity processFormEsDo, PageInfoDto pageInfo) {
        PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, pageInfo.getPageSize());
        Page<KibanaEcommerceEntity> pageResult = kibanaEcommerceRepository.search(processFormEsDo, pageRequest);
        pageInfo.setTotalRows((int) pageResult.getTotalElements());
        return pageResult.getContent();
    }
}
