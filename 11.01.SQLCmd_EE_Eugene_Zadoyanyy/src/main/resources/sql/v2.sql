-- Sequence: public.database_connection_seq

-- DROP SEQUENCE public.database_connection_seq;

CREATE SEQUENCE public.database_connection_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE public.database_connection_seq
  OWNER TO postgres;

-- Table: public.database_connection

-- DROP TABLE public.database_connection;

CREATE TABLE public.database_connection
(
  id integer NOT NULL DEFAULT nextval('database_connection_seq'::regclass),
  username text,
  database text,
  CONSTRAINT database_connection_id_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.database_connection
  OWNER TO postgres;


-- Constraint: public.database_connection_id_pk

-- ALTER TABLE public.database_connection DROP CONSTRAINT database_connection_id_pk;

ALTER TABLE public.database_connection
  ADD CONSTRAINT database_connection_id_pk PRIMARY KEY(id);

-- Column: database_connection

-- ALTER TABLE public.actions DROP COLUMN database_connection;

ALTER TABLE public.actions ADD COLUMN database_connection_id integer;


-- Foreign Key: public.actions_database_connection_fk

-- ALTER TABLE public.actions DROP CONSTRAINT actions_database_connection_fk;

ALTER TABLE public.actions
ADD CONSTRAINT action_database_connection_fk FOREIGN KEY (database_connection_id) REFERENCES public.database_connection (id)
ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_action_database_connection_fk
ON public.actions(database_connection_id);


-- Data migration

INSERT INTO database_connection (username, database) SELECT DISTINCT username, database FROM actions;

UPDATE actions SET database_connection_id=subquery.id
FROM (SELECT id, username, database FROM database_connection) AS subquery
WHERE actions.username=subquery.username AND actions.database=subquery.database;

-- drop unused columns

ALTER TABLE actions DROP COLUMN username;
ALTER TABLE actions DROP COLUMN database;

