package com.offlinepay.link.repository;

import com.offlinepay.link.entity.LinkValidityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkValidityRepository extends CrudRepository<LinkValidityEntity, String> {

}