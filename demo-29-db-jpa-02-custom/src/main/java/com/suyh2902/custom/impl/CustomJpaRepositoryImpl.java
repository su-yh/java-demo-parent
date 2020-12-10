package com.suyh2902.custom.impl;

import com.suyh2902.custom.CustomJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;

@Slf4j
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager entityManager;

    public CustomJpaRepositoryImpl(
            JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public void someCustomMethod(T entity) {
        log.info("CustomizedUserRepositoryImpl#someCustomMethod invoked");
    }

    // TODO: 这里似乎就这样就可以了。
    public <S extends T> int insert(S entity) {
        // 直接调用持久化就可以了
        entityManager.persist(entity);
        return 1;
    }

    // TODO: 期望实现按需更新，类似mybatis 一样，只更新非null的字段。
    public <S extends T> int update(S entity) {
        // 这里就有得处理了，要怎么做更新呢？
        entityManager.merge(entity);
        return 1;
    }

    // TODO: 还未能明白怎么使用。
    public void func() {
        //创建CriteriaBuilder安全查询工厂，CriteriaBuilder是一个工厂对象,安全查询的开始.用于构建JPA安全查询.
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 创建一个查询
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityInformation.getJavaType());
        // 创建一个更新
        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(entityInformation.getJavaType());
        //
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(entityInformation.getJavaType());
    }

    @Override
    public <S extends T> S save(S entity) {
        log.info("重写的save 方法");
        throw new RuntimeException("请不要使用JPA的原生save 方法，改用自定义的插入/更新方法");
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        log.info("重写的saveAll 方法");
        throw new RuntimeException("请不要使用JPA的原生saveAll 方法，改用自定义的插入/更新方法");
    }
}
