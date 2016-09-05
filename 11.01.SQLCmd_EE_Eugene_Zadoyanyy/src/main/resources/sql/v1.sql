-- Sequence: public.actions_id_seq

-- DROP SEQUENCE public.actions_id_seq;

CREATE SEQUENCE public.actions_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 25
CACHE 1;
ALTER TABLE public.actions_id_seq
  OWNER TO postgres;

-- Table: public.actions

-- DROP TABLE public.actions;

CREATE TABLE public.actions
(
  id integer NOT NULL DEFAULT nextval('actions_id_seq'::regclass),
  username text,
  database text,
  action text,
  date text,
  CONSTRAINT actions_id_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.actions
  OWNER TO postgres;

-- Constraint: public.actions_id_pk

-- ALTER TABLE public.actions DROP CONSTRAINT actions_id_pk;

ALTER TABLE public.actions
  ADD CONSTRAINT actions_id_pk PRIMARY KEY(id);

