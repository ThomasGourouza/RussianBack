DROP TABLE IF EXISTS player_game_history;
DROP TABLE IF EXISTS player_spoken_language;
DROP TABLE IF EXISTS player_game;
DROP TABLE IF EXISTS memory_russian_specific_noun_ending;
DROP TABLE IF EXISTS russian_singular_plural_noun_couple;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS russian_interrogative_word_ref;
DROP TABLE IF EXISTS russian_adjective;
DROP TABLE IF EXISTS russian_noun;
DROP TABLE IF EXISTS russian_decl_spec_ending_ref;
DROP TABLE IF EXISTS russian_noun_ending_ref;
DROP TABLE IF EXISTS russian_noun_category_ref;
DROP TABLE IF EXISTS russian_adjective_ending_ref;
DROP TABLE IF EXISTS russian_role_ref;
DROP TABLE IF EXISTS russian_gender_ref;
DROP TABLE IF EXISTS russian_case_ref;
DROP TABLE IF EXISTS russian_adjective_category_ref;
DROP TABLE IF EXISTS level_ref;
DROP TABLE IF EXISTS language_ref;
DROP TABLE IF EXISTS image_ref;
DROP TABLE IF EXISTS gender_ref;
DROP TABLE IF EXISTS birth_country_ref;
DROP TABLE IF EXISTS russian_grammatical_number_ref;
DROP TABLE IF EXISTS russian_declension_name_ref;
DROP TABLE IF EXISTS russian_decl_spec_rule_ref;
DROP TABLE IF EXISTS russian_decl_cat_type_ref;
create table russian_decl_cat_type_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50)
);
create table russian_decl_spec_rule_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(200) NOT NULL
);
create table russian_declension_name_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50)
);
create table russian_grammatical_number_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50)
);
create table birth_country_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table gender_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table image_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(500) NOT NULL,
	UNIQUE(value)
);
create table language_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table level_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	UNIQUE(value),
	UNIQUE(name)
);
create table russian_adjective_category_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table russian_case_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table russian_gender_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE (value)
);
create table russian_role_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	value VARCHAR(50) NOT NULL,
	UNIQUE(value)
);
create table russian_adjective_ending_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_adjective_category_ref_id BIGINT REFERENCES russian_adjective_category_ref (id) NOT NULL,
	russian_gender_ref_id BIGINT REFERENCES russian_gender_ref (id) NOT NULL,
	russian_case_ref_id BIGINT REFERENCES russian_case_ref (id) NOT NULL,
	value VARCHAR(50) NOT NULL
);
create table russian_noun_category_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_declension_name_ref_id BIGINT REFERENCES russian_declension_name_ref (id) NOT NULL,
	russian_gender_ref_id BIGINT REFERENCES russian_gender_ref (id) NOT NULL,
	russian_grammatical_number_ref_id BIGINT REFERENCES russian_grammatical_number_ref (id) NOT NULL,
	russian_decl_cat_type_ref_id BIGINT REFERENCES russian_decl_cat_type_ref (id) NOT NULL
);
create table russian_noun_ending_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_noun_category_ref_id BIGINT REFERENCES russian_noun_category_ref (id) NOT NULL,
	russian_case_ref_id BIGINT REFERENCES russian_case_ref (id) NOT NULL,
	value VARCHAR(50)
);
create table russian_decl_spec_ending_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_noun_ending_ref_id BIGINT REFERENCES russian_noun_ending_ref (id) NOT NULL,
	russian_decl_spec_rule_ref_id BIGINT REFERENCES russian_decl_spec_rule_ref (id) NOT NULL,
	value VARCHAR(50) NOT NULL
);
create table russian_noun (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_noun_category_ref_id BIGINT REFERENCES russian_noun_category_ref (id) NOT NULL,
	root VARCHAR(50),
	translation VARCHAR(50),
	is_animate BOOLEAN
);
create table russian_adjective (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_adjective_category_ref_id BIGINT REFERENCES russian_adjective_category_ref (id) NOT NULL,
	root VARCHAR(50) NOT NULL,
	translation VARCHAR(50) NOT NULL,
	UNIQUE(root),
	UNIQUE (translation)
);
create table russian_interrogative_word_ref (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_case_ref_id BIGINT REFERENCES russian_case_ref (id) NOT NULL,
	russian_role_ref_id BIGINT REFERENCES russian_role_ref (id) NOT NULL,
	value VARCHAR(50) NOT NULL
);
create table player (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	birth_date DATE,
	birth_country_ref_id BIGINT REFERENCES birth_country_ref (id),
	gender_ref_id BIGINT REFERENCES gender_ref (id),
	email VARCHAR(50),
	image_ref_id BIGINT REFERENCES image_ref (id) NOT NULL,
	phone VARCHAR(50),
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	UNIQUE(email),
	UNIQUE (phone),
	UNIQUE (login)
);
create table russian_singular_plural_noun_couple (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_singular_noun_id BIGINT REFERENCES russian_noun (id) NOT NULL,
	russian_plural_noun_id BIGINT REFERENCES russian_noun (id) NOT NULL,
	UNIQUE (russian_singular_noun_id),
	UNIQUE (russian_plural_noun_id)
);
create table memory_russian_specific_noun_ending (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	russian_noun_id BIGINT REFERENCES russian_noun (id) NOT NULL,
	russian_decl_spec_ending_ref_id BIGINT REFERENCES russian_decl_spec_ending_ref (id) NOT NULL
);
create table player_game (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	player_id BIGINT REFERENCES player (id) NOT NULL,
	game BIGINT NOT NULL,
	date_time DATE NOT NULL,
	score BIGINT NOT NULL
);
create table player_spoken_language (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	player_id BIGINT REFERENCES player (id) NOT NULL,
	language_ref_id BIGINT REFERENCES language_ref (id) NOT NULL,
	level_ref_id BIGINT REFERENCES level_ref (id) NOT NULL,
	certification VARCHAR(50)
);
create table player_game_history (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	item_number BIGINT NOT NULL,
	player_game_id BIGINT REFERENCES player_game (id) NOT NULL,
	russian_adjective_id BIGINT REFERENCES russian_adjective (id) NOT NULL,
	russian_noun_id BIGINT REFERENCES russian_noun (id) NOT NULL,
	russian_case_ref_id BIGINT REFERENCES russian_case_ref (id) NOT NULL,
	iscorrect BOOLEAN
);
