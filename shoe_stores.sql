--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brands; Type: TABLE; Schema: public; Owner: iliak; Tablespace: 
--

CREATE TABLE brands (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE brands OWNER TO iliak;

--
-- Name: brands_id_seq; Type: SEQUENCE; Schema: public; Owner: iliak
--

CREATE SEQUENCE brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brands_id_seq OWNER TO iliak;

--
-- Name: brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iliak
--

ALTER SEQUENCE brands_id_seq OWNED BY brands.id;


--
-- Name: stores; Type: TABLE; Schema: public; Owner: iliak; Tablespace: 
--

CREATE TABLE stores (
    id integer NOT NULL,
    name character varying,
    address character varying,
    phone_number character varying
);


ALTER TABLE stores OWNER TO iliak;

--
-- Name: stores_brands; Type: TABLE; Schema: public; Owner: iliak; Tablespace: 
--

CREATE TABLE stores_brands (
    id integer NOT NULL,
    store_id integer,
    brand_id integer
);


ALTER TABLE stores_brands OWNER TO iliak;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE; Schema: public; Owner: iliak
--

CREATE SEQUENCE stores_brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_brands_id_seq OWNER TO iliak;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iliak
--

ALTER SEQUENCE stores_brands_id_seq OWNED BY stores_brands.id;


--
-- Name: stores_id_seq; Type: SEQUENCE; Schema: public; Owner: iliak
--

CREATE SEQUENCE stores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_id_seq OWNER TO iliak;

--
-- Name: stores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iliak
--

ALTER SEQUENCE stores_id_seq OWNED BY stores.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: iliak
--

ALTER TABLE ONLY brands ALTER COLUMN id SET DEFAULT nextval('brands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: iliak
--

ALTER TABLE ONLY stores ALTER COLUMN id SET DEFAULT nextval('stores_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: iliak
--

ALTER TABLE ONLY stores_brands ALTER COLUMN id SET DEFAULT nextval('stores_brands_id_seq'::regclass);


--
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: iliak
--

COPY brands (id, name) FROM stdin;
6	Senchio avari
5	Nikes
1	Abibas
\.


--
-- Name: brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: iliak
--

SELECT pg_catalog.setval('brands_id_seq', 12, true);


--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: iliak
--

COPY stores (id, name, address, phone_number) FROM stdin;
10	CHICCO	617 Broadway Blvd. Seatle	(000) 123-4455
11	Happy Baby	27 Shevchenko St. Poltava	(066) 462-8699
\.


--
-- Data for Name: stores_brands; Type: TABLE DATA; Schema: public; Owner: iliak
--

COPY stores_brands (id, store_id, brand_id) FROM stdin;
8	10	1
9	11	6
10	11	5
11	11	1
\.


--
-- Name: stores_brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: iliak
--

SELECT pg_catalog.setval('stores_brands_id_seq', 11, true);


--
-- Name: stores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: iliak
--

SELECT pg_catalog.setval('stores_id_seq', 11, true);


--
-- Name: brands_pkey; Type: CONSTRAINT; Schema: public; Owner: iliak; Tablespace: 
--

ALTER TABLE ONLY brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- Name: stores_brands_pkey; Type: CONSTRAINT; Schema: public; Owner: iliak; Tablespace: 
--

ALTER TABLE ONLY stores_brands
    ADD CONSTRAINT stores_brands_pkey PRIMARY KEY (id);


--
-- Name: stores_pkey; Type: CONSTRAINT; Schema: public; Owner: iliak; Tablespace: 
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);


--
-- Name: stores_brands_brand_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: iliak
--

ALTER TABLE ONLY stores_brands
    ADD CONSTRAINT stores_brands_brand_id_fkey FOREIGN KEY (brand_id) REFERENCES brands(id);


--
-- Name: stores_brands_store_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: iliak
--

ALTER TABLE ONLY stores_brands
    ADD CONSTRAINT stores_brands_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

