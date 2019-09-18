package com.denn.edd.axon.query.repository;

import com.denn.edd.axon.query.entity.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
