package ua.com.juja.sqlcmd;

/**
 * Created by Serzh on 9/4/16.
 */
public class Temp {


    /*

    CREATE TABLE student (id_pk SERIAL NOT NULL PRIMARY KEY,
name varchar(225) NOT NULL,
age varchar(225) NOT NULL,
id int NOT NULL)


    - Database: sqlcmd_log

-- DROP DATABASE sqlcmd_log;

CREATE DATABASE sqlcmd_log
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;
*/


//    CREATE TABLE user_actions (id SERIAL NOT NULL PRIMARY KEY, user_name varchar(225) NOT NULL UNIQUE,
    /*
    CREATE TABLE user_actions (id SERIAL NOT NULL PRIMARY KEY, user_name varchar(225) NOT NULL,
    db_name varchar(225) NOT NULL, action varchar(225) NOT NULL)*/

    /*-- Table: public.user_actions

-- DROP TABLE public.user_actions;

CREATE TABLE public.user_actions
(
  id integer NOT NULL DEFAULT nextval('user_actions_id_seq'::regclass),
  user_name character varying(225) NOT NULL,
  db_name character varying(225) NOT NULL,
  action character varying(225) NOT NULL,
  CONSTRAINT user_actions_pkey PRIMARY KEY (id),
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_actions
  OWNER TO postgres;*/
}
