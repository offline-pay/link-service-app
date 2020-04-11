package com.offlinepay.link.repository;

import com.offlinepay.link.entity.LinkValidity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkValidityRepository extends CrudRepository<LinkValidity, String> {

}