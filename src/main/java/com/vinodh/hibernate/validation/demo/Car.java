package com.vinodh.hibernate.validation.demo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * 
 *  // @formatter:off
 *  @Target({FIELD, METHOD, PARAMETER, TYPE etc}) 
 * 		There are four types of bean constraints: 
 * 				1. Field constraints:
 *						a) Constraints can be applied to fields of any access type (public, private etc.).
 *   					b) Constraints on static fields are not supported.
 * 				2. Property constraints (@Getter Method)
 *						a) The validation engine accesses the state via the property accessor method.
 * 				3. Container element constraints
 * 						a) List, Map, Set etc...
 * 				4. Class constraints
 *   
 
 *   
 *  //@formatter:on
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Data
@AllArgsConstructor
@Builder
public class Car {

	// Sample Field Level Constraints
	@NotNull
	private String manufacturer;
	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;
	@Min(2)
	private int seatCount;

	// Sample Property Level
	@Getter(AccessLevel.NONE)
	private String mfgCountry;
	@Getter(AccessLevel.NONE)
	private boolean isGood;

	@NotNull
	public String getMfgCountry() {
		return mfgCountry;
	}

	@AssertTrue
	public boolean isGood() {
		return isGood;
	}

	// Container Level constraints. Can be used for Custom Constraint
	@Builder.Default
	private Set<@NotNull @Size(min = 2, max = 10) String> emails = Collections.emptySet();
	@Builder.Default
	private List<@NotNull @Size(min = 2, max = 10) String> description = Collections.emptyList();
	@Builder.Default
	private Map<@Length(min = 2) String, @Length(max = 5) String> marks = Collections.emptyMap();

	// Built in Bean Validation Constraints

	@DecimalMin(value = "2")
	@DecimalMax(value = "5", inclusive = false)
	private BigDecimal bigDecimal;

	@Digits(integer = 5, fraction = 3)
	private int days;

	@Email
	@Pattern(regexp = "/^[a-zA-Z0-9]+$/")
	private String email;

	@Future
	private Date deathDate;

	@Past
	private Date birthDate;

	@FutureOrPresent
	private Date marriageDate;

	@NotBlank // Trailing spaces will be trimmed
	private String userName;

	@Negative
	private Integer temperature;

	@Size(min = 2, max = 100)
	private List<String> friends;

	
}
