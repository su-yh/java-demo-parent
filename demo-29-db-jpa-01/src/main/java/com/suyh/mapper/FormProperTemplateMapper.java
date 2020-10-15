package com.suyh.mapper;

import com.suyh.entity.FormPropertyTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormProperTemplateMapper extends JpaRepository<FormPropertyTemplateEntity, Long> {
}
