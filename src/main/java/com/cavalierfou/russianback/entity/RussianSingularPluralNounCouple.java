package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "russian_singular_plural_noun_couple" database table.
 * 
 */
@Entity
@Table(name="\"russian_singular_plural_noun_couple\"")
@NamedQuery(name="RussianSingularPluralNounCouple.findAll", query="SELECT r FROM RussianSingularPluralNounCouple r")
public class RussianSingularPluralNounCouple implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_plural_noun_id\"")
	private long russianPluralNounId;

	@Column(name="\"russian_singular_noun_id\"")
	private long russianSingularNounId;

	public RussianSingularPluralNounCouple() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianPluralNounId() {
		return this.russianPluralNounId;
	}

	public void setRussianPluralNounId(long russianPluralNounId) {
		this.russianPluralNounId = russianPluralNounId;
	}

	public long getRussianSingularNounId() {
		return this.russianSingularNounId;
	}

	public void setRussianSingularNounId(long russianSingularNounId) {
		this.russianSingularNounId = russianSingularNounId;
	}

}