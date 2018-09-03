package com.qa.dvdRental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.dvdRental.Model.DvdRentalDataModel;

@Repository
public interface DvdRentalRepository extends JpaRepository<DvdRentalDataModel, Long> {

}
