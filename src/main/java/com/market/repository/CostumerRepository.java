package com.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.model.Costumer;
@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

}