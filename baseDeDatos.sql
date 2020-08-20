--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: detalleproductos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.detalleproductos (
    idfactura integer NOT NULL,
    idproducto integer NOT NULL,
    totallinea double precision NOT NULL
);


--
-- Name: facturas; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.facturas (
    idfactura integer NOT NULL,
    cedulausuario integer NOT NULL,
    invoicedate timestamp without time zone NOT NULL,
    total double precision NOT NULL
);


--
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    idproducts integer NOT NULL,
    name character varying(45) NOT NULL,
    quantity double precision NOT NULL,
    price double precision NOT NULL,
    description character varying(60) NOT NULL,
    datepurchase timestamp without time zone NOT NULL
);


--
-- Name: tarjetas; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tarjetas (
    numerotarjeta integer NOT NULL,
    tipo character varying(45) NOT NULL,
    fechaexpiracion character varying(5) NOT NULL,
    cvv character varying(3) NOT NULL,
    cedulausuario integer NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    cedula integer NOT NULL,
    name character varying(45) NOT NULL,
    lastname character varying(45) NOT NULL,
    phone character varying(9),
    tipopago character varying(45),
    contrasena character varying(16) NOT NULL,
    email character varying(45) NOT NULL
);


--
-- Data for Name: detalleproductos; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.detalleproductos (idfactura, idproducto, totallinea) FROM stdin;
101	1001	180000
101	1002	45000
102	1001	180000
102	1002	45000
103	1004	2500
103	1001	1000
103	1004	6875
104	1003	225
104	1002	450
105	1003	225
105	1002	450
106	1003	90
106	1002	300
107	1002	300
107	1003	90
108	1003	225
108	1004	3750
109	1005	3250
109	1004	2500
\.


--
-- Data for Name: facturas; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.facturas (idfactura, cedulausuario, invoicedate, total) FROM stdin;
101	1234567891	2020-08-16 13:14:54.36262	225000
102	1234567892	2020-08-16 13:16:32.688256	225000
103	1234567891	2020-08-18 17:27:33.507107	10375
104	1234567891	2020-08-19 21:45:41.177974	675
105	1234567891	2020-08-19 21:46:53.93286	675
106	1234567891	2020-08-19 22:02:03.572724	390
107	1234567891	2020-08-19 22:20:30.517578	390
108	1234567891	2020-08-19 22:47:57.93657	3975
109	1234567891	2020-08-20 00:55:37.935621	5750
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.products (idproducts, name, quantity, price, description, datepurchase) FROM stdin;
1001	Arroz	900	200	Arroz Pelon	2020-08-16 13:09:16.641039
1002	Cereal	290	150	Cereal dulce	2020-08-16 13:10:22.036535
1003	Leche	27	45	Leche Descremada	2020-08-16 21:16:14.107791
1005	Frijoles	145	650	Frijoles Rojos	2020-08-19 23:24:32.58649
1004	Papas	115	1250	Papa amarilla	2020-08-17 21:20:09.077555
\.


--
-- Data for Name: tarjetas; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.tarjetas (numerotarjeta, tipo, fechaexpiracion, cvv, cedulausuario) FROM stdin;
1234567890	Debito	09/23	231	1234567891
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (cedula, name, lastname, phone, tipopago, contrasena, email) FROM stdin;
1234567892	Mark	Brown	61025856	Credito	Mark111	mark@example.com
1234567891	Sebas	Zumbado	60024846	Debito	Sebas111	sebas@example.com
\.


--
-- Name: facturas facturas_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT facturas_pkey PRIMARY KEY (idfactura);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (idproducts);


--
-- Name: tarjetas tarjetas_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarjetas
    ADD CONSTRAINT tarjetas_pkey PRIMARY KEY (numerotarjeta);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (cedula);


--
-- Name: facturas fk_cedula; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT fk_cedula FOREIGN KEY (cedulausuario) REFERENCES public.users(cedula);


--
-- Name: tarjetas fk_cedulausuario; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarjetas
    ADD CONSTRAINT fk_cedulausuario FOREIGN KEY (cedulausuario) REFERENCES public.users(cedula);


--
-- Name: detalleproductos fk_factura; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalleproductos
    ADD CONSTRAINT fk_factura FOREIGN KEY (idfactura) REFERENCES public.facturas(idfactura);


--
-- Name: detalleproductos fk_producto; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalleproductos
    ADD CONSTRAINT fk_producto FOREIGN KEY (idproducto) REFERENCES public.products(idproducts);


--
-- PostgreSQL database dump complete
--

