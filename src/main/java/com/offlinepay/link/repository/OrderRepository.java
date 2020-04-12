package com.offlinepay.link.repository;

import com.offlinepay.link.entity.LinkOrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<LinkOrderEntity, Long> {

}
