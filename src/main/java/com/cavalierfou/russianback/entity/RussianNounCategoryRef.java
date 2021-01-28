package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_noun_category_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_noun_category_ref\"")
@NamedQuery(name="RussianNounCategoryRef.findAll", query="SELECT r FROM RussianNounCategoryRef r")
public class RussianNounCategoryRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_decl_cat_type_ref_id\"")
	private long russianDeclCatTypeRefId;

	@Column(name="\"russian_declension_name_ref_id\"")
	private long russianDeclensionNameRefId;

	@Column(name="\"russian_gender_ref_id\"")
	private long russianGenderRefId;

	@Column(name="\"russian_grammatical_number_ref_id\"")
	private long russianGrammaticalNumberRefId;

	public RussianNounCategoryRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianDeclCatTypeRefId() {
		return this.russianDeclCatTypeRefId;
	}

	public void setRussianDeclCatTypeRefId(long russianDeclCatTypeRefId) {
		this.russianDeclCatTypeRefId = russianDeclCatTypeRefId;
	}

	public long getRussianDeclensionNameRefId() {
		return this.russianDeclensionNameRefId;
	}

	public void setRussianDeclensionNameRefId(long russianDeclensionNameRefId) {
		this.russianDeclensionNameRefId = russianDeclensionNameRefId;
	}

	public long getRussianGenderRefId() {
		return this.russianGenderRefId;
	}

	public void setRussianGenderRefId(long russianGenderRefId) {
		this.russianGenderRefId = russianGenderRefId;
	}

	public long getRussianGrammaticalNumberRefId() {
		return this.russianGrammaticalNumberRefId;
	}

	public void setRussianGrammaticalNumberRefId(long russianGrammaticalNumberRefId) {
		this.russianGrammaticalNumberRefId = russianGrammaticalNumberRefId;
	}

}