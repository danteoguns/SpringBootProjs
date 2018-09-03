package com.qa.dvdRental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.dvdRental.Model.UserDataModel;

@Repository
public interface UserRepository extends JpaRepository<UserDataModel, Long> {

}
