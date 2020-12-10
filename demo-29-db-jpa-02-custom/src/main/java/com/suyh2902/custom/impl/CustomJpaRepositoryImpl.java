package com.suyh2902.custom.impl;

import com.suyh2902.custom.CustomJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

@Slf4j
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    public void someCustomMethod(T entity) {
        log.info("CustomizedUserRepositoryImpl#someCustomMethod invoked");
    }
}
