package com.suyh2902.custom;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 自定义jpa Repository
 */
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> {
    void someCustomMethod(T entity);
}
