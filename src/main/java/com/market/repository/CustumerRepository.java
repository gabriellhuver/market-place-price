package com.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.model.Custumer;
@Repository
public interface CustumerRepository extends JpaRepository<Custumer, Integer> {

}
