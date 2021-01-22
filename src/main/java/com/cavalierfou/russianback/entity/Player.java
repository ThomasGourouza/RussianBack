package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "player" database table.
 * 
 */
@Entity
@Table(name="\"player\"")
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"birth_country_ref_id\"")
	private long birthCountryRefId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"birth_date\"")
	private Date birthDate;

	@Column(name="\"email\"")
	private String email;

	@Column(name="\"first_name\"")
	private String firstName;

	@Column(name="\"gender_ref_id\"")
	private long genderRefId;

	@Column(name="\"image_ref_id\"")
	private long imageRefId;

	@Column(name="\"last_name\"")
	private String lastName;

	@Column(name="\"login\"")
	private String login;

	@Column(name="\"password\"")
	private String password;

	@Column(name="\"phone\"")
	private String phone;

	@OneToMany(mappedBy="playerId")
	private List<PlayerSpokenLanguage> playerSpokenLanguages;

	public Player() {
		// default constructor
	}

	public Player(
		long id, long birthCountryRefId, Date birthDate, String email, 
		String firstName, long genderRefId, long imageRefId,
		String lastName, String login, String password, String phone) {
			this.id = id;
			this.birthCountryRefId = birthCountryRefId;
			this.birthDate = birthDate;
			this.email = email;
			this.firstName = firstName;
			this.genderRefId = genderRefId;
			this.imageRefId = imageRefId;
			this.lastName = lastName;
			this.login = login;
			this.password = password;
			this.phone = phone;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBirthCountryRefId() {
		return this.birthCountryRefId;
	}

	public void setBirthCountryRefId(long birthCountryRefId) {
		this.birthCountryRefId = birthCountryRefId;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getGenderRefId() {
		return this.genderRefId;
	}

	public void setGenderRefId(long genderRefId) {
		this.genderRefId = genderRefId;
	}

	public long getImageRefId() {
		return this.imageRefId;
	}

	public void setImageRefId(long imageRefId) {
		this.imageRefId = imageRefId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<PlayerSpokenLanguage> getPlayerSpokenLanguages() {
		return this.playerSpokenLanguages;
	}

}