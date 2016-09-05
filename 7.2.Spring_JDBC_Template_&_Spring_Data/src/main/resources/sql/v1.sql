-- Table: public.students

-- DROP TABLE public.students;

CREATE TABLE public.students
(
  id integer NOT NULL DEFAULT nextval('students_id_seq'::regclass),
  name text NOT NULL,
  age integer,
  CONSTRAINT students_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.students
  OWNER TO postgres;