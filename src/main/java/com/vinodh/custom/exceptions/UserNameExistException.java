package com.vinodh.custom.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserNameExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNameExistException(String message) {
		super(message);
	}

}
