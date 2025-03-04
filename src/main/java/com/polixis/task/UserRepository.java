package com.polixis.task;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    public List<UserEntity> findByIds(List<Long> ids) {
        return list("id IN ?1", ids);
    }
}
