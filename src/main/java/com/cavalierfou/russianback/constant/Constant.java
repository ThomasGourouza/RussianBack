package com.cavalierfou.russianback.constant;

public enum Constant {

    NG("N/G"), 
    N("Nominative"), 
    A("Accusative"),
    G("Genitive"),
    RSPNC("russian_singular_plural_noun_couple"),
    RSPNCIS("russian_singular_plural_noun_couple_id_seq"),
    RN("russian_noun"),
    RNIS("russian_noun_id_seq"),
    S("Singular"),
    M("Masculine"),
    PGH("player_game_history"),
    PGHIS("player_game_history_id_seq"),
    RNI("russian_noun_id"),
    RA("russian_adjective"),
    RAI("russian_adjective_id"),
    RAIS("russian_adjective_id_seq"),
    P("player"),
    PI("player_id"),
    PG("player_game"),
    PGI("player_game_id"),
    PGIS("player_game_id_seq"),
    PIS("player_id_seq"),
    PSL("player_spoken_language"),
    PSLIS("player_spoken_language_id_seq"),
    MRSNE("memory_russian_specific_noun_ending"),
    MRSNEIS("memory_russian_specific_noun_ending_id_seq");

    private String value; 
  
    public String getValue() 
    { 
        return this.value; 
    } 
  
    private Constant(String value) 
    { 
        this.value = value; 
    }
}
