package com.suyh0103;

import java.util.List;

public interface CustomerService {
    void findOneById(long id);

    void findOneByName(long name);

    void findOneByNameRegex(String regexName);

    void findAllByIds(List<Integer> ids);

    void findAllByName(String name);

    void update(Customer customer);
}
