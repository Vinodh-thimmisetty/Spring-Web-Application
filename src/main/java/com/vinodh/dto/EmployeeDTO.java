package com.vinodh.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {
	
	public interface Group1 {
		// When Save is clicked. Validate all the constraints
	}

	public interface Group2 {
		// When Continue without fixing errors clicks, validate only notnull valid
		// fields
	}
	

	@NotNull(groups=Group1.class)
	@Size(min = 5, max = 25,groups= {Group1.class,Group2.class})
	@Pattern(regexp="[a-zA-Z0-9]{7,}",groups= {Group1.class,Group2.class})
	private String empName;
	@NotNull(groups=Group1.class)
	@Size(min = 2, max = 25,groups= {Group1.class,Group2.class})
	@Email(groups= {Group1.class,Group2.class})
	private String emailId;
	@NotNull(groups=Group1.class)
	private long phoneNum;
	@NotNull(groups=Group1.class)
	private double weight;
	@NotNull(groups=Group1.class)
	@DateTimeFormat(iso=ISO.DATE)
	private Date dateOfBirth;

}
