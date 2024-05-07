package com.spring.excel.repository;

import com.spring.excel.entity.Datas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Datas,Integer> {
}
