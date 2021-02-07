package com.cavalierfou.russianback.customentity;

import java.util.Date;
import java.util.List;

public class PlayerCustom {
    
    private long id;

	private String birthCountry;

	private Date birthDate;

	private String email;

	private String firstName;

	private String gender;

	private String imageUrl;

	private String lastName;

	private String login;

	private String phone;

	private List<PlayerSpokenLanguageCustom> playerSpokenLanguages;

	public PlayerCustom() {
		// default constructor
    }

    public PlayerCustom(long id, String birthCountry, Date birthDate, String email, String firstName, String gender,
            String imageUrl, String lastName, String login, String password, String phone,
            List<PlayerSpokenLanguageCustom> playerSpokenLanguages) {
        this.id = id;
        this.birthCountry = birthCountry;
        this.birthDate = birthDate;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.lastName = lastName;
        this.login = login;
        this.phone = phone;
        this.playerSpokenLanguages = playerSpokenLanguages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<PlayerSpokenLanguageCustom> getPlayerSpokenLanguages() {
        return playerSpokenLanguages;
    }

    public void setPlayerSpokenLanguages(List<PlayerSpokenLanguageCustom> playerSpokenLanguages) {
        this.playerSpokenLanguages = playerSpokenLanguages;
    }
    
}
