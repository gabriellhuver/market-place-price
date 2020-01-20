package com.market.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Custumer extends PersistentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	private String email;
	@NotNull
	private String password;
}
