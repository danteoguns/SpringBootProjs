package com.qa.dvdRental.Model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dvd")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"rentedDate"}, allowGetters = true)
public class DvdRentalDataModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;

	@NotBlank
	private String genre;
	
	private Integer releaseYear;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserDataModel user;
	
//	@Column(nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date rentedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

//	public Date getRentedDate() {
//		return rentedDate;
//	}
//
//	public void setRentedDate(Date rentedDate) {
//		this.rentedDate = rentedDate;
//	}
}
