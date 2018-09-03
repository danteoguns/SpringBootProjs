package com.qa.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Database.Model.SpringBootDataModel;

@Repository
public interface SpringBootRepository extends JpaRepository<SpringBootDataModel, Long> {

}
