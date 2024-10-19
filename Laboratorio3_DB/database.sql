--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: tbl_categorias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_categorias (
    idcategoria integer NOT NULL,
    nombrecategoria character varying(250)
);


ALTER TABLE public.tbl_categorias OWNER TO postgres;

--
-- Name: tbl_categorias_idcategoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_categorias_idcategoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_categorias_idcategoria_seq OWNER TO postgres;

--
-- Name: tbl_categorias_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_categorias_idcategoria_seq OWNED BY public.tbl_categorias.idcategoria;


--
-- Name: tbl_clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_clientes (
    idcliente integer NOT NULL,
    nombreclientes character varying(250),
    dui character varying(10),
    nit character varying(10),
    direccion text,
    telefono character varying(20)
);


ALTER TABLE public.tbl_clientes OWNER TO postgres;

--
-- Name: tbl_clientes_idcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_clientes_idcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_clientes_idcliente_seq OWNER TO postgres;

--
-- Name: tbl_clientes_idcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_clientes_idcliente_seq OWNED BY public.tbl_clientes.idcliente;


--
-- Name: tbl_detallefactura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_detallefactura (
    iddetalle integer NOT NULL,
    idfactura integer,
    idproducto integer,
    cantidadvendida integer,
    preciounitario numeric(10,2)
);


ALTER TABLE public.tbl_detallefactura OWNER TO postgres;

--
-- Name: tbl_detallefactura_iddetalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_detallefactura_iddetalle_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_detallefactura_iddetalle_seq OWNER TO postgres;

--
-- Name: tbl_detallefactura_iddetalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_detallefactura_iddetalle_seq OWNED BY public.tbl_detallefactura.iddetalle;


--
-- Name: tbl_estado_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_estado_factura (
    idestadofactura integer NOT NULL,
    descripcionestado character varying(200)
);


ALTER TABLE public.tbl_estado_factura OWNER TO postgres;

--
-- Name: tbl_estado_factura_idestadofactura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_estado_factura_idestadofactura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_estado_factura_idestadofactura_seq OWNER TO postgres;

--
-- Name: tbl_estado_factura_idestadofactura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_estado_factura_idestadofactura_seq OWNED BY public.tbl_estado_factura.idestadofactura;


--
-- Name: tbl_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_factura (
    idfactura integer NOT NULL,
    fechaemision date,
    idcliente integer,
    numerofactura integer,
    tipofactura integer,
    idestadofactura integer
);


ALTER TABLE public.tbl_factura OWNER TO postgres;

--
-- Name: tbl_factura_idfactura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_factura_idfactura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_factura_idfactura_seq OWNER TO postgres;

--
-- Name: tbl_factura_idfactura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_factura_idfactura_seq OWNED BY public.tbl_factura.idfactura;


--
-- Name: tbl_productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_productos (
    idproducto integer NOT NULL,
    descripcion text,
    existencia numeric(10,2),
    preciounitario numeric(10,2),
    fechavencimiento date,
    idcategoria integer,
    idproveedor integer
);


ALTER TABLE public.tbl_productos OWNER TO postgres;

--
-- Name: tbl_productos_idproducto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_productos_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_productos_idproducto_seq OWNER TO postgres;

--
-- Name: tbl_productos_idproducto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_productos_idproducto_seq OWNED BY public.tbl_productos.idproducto;


--
-- Name: tbl_proveedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_proveedores (
    idproveedor integer NOT NULL,
    nombreproveedor character varying(250),
    telefono character varying(20),
    correo character varying(250)
);


ALTER TABLE public.tbl_proveedores OWNER TO postgres;

--
-- Name: tbl_proveedores_idproveedor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_proveedores_idproveedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_proveedores_idproveedor_seq OWNER TO postgres;

--
-- Name: tbl_proveedores_idproveedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_proveedores_idproveedor_seq OWNED BY public.tbl_proveedores.idproveedor;


--
-- Name: tbl_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_roles (
    idroles integer NOT NULL,
    nombrerol character varying(250)
);


ALTER TABLE public.tbl_roles OWNER TO postgres;

--
-- Name: tbl_roles_idroles_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_roles_idroles_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_roles_idroles_seq OWNER TO postgres;

--
-- Name: tbl_roles_idroles_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_roles_idroles_seq OWNED BY public.tbl_roles.idroles;


--
-- Name: tbl_tipofactura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_tipofactura (
    idtipofactura integer NOT NULL,
    nombretipofactura character varying(250)
);


ALTER TABLE public.tbl_tipofactura OWNER TO postgres;

--
-- Name: tbl_tipofactura_idtipofactura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_tipofactura_idtipofactura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_tipofactura_idtipofactura_seq OWNER TO postgres;

--
-- Name: tbl_tipofactura_idtipofactura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_tipofactura_idtipofactura_seq OWNED BY public.tbl_tipofactura.idtipofactura;


--
-- Name: tbl_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tbl_usuarios (
    idusuario integer NOT NULL,
    nombreusuario character varying(50),
    claveusuario character varying(100),
    nombrecompleto character varying(250),
    correo character varying(250),
    telefono character varying(20),
    idroles integer
);


ALTER TABLE public.tbl_usuarios OWNER TO postgres;

--
-- Name: tbl_usuarios_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tbl_usuarios_idusuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_usuarios_idusuario_seq OWNER TO postgres;

--
-- Name: tbl_usuarios_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tbl_usuarios_idusuario_seq OWNED BY public.tbl_usuarios.idusuario;


--
-- Name: tbl_categorias idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_categorias ALTER COLUMN idcategoria SET DEFAULT nextval('public.tbl_categorias_idcategoria_seq'::regclass);


--
-- Name: tbl_clientes idcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_clientes ALTER COLUMN idcliente SET DEFAULT nextval('public.tbl_clientes_idcliente_seq'::regclass);


--
-- Name: tbl_detallefactura iddetalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_detallefactura ALTER COLUMN iddetalle SET DEFAULT nextval('public.tbl_detallefactura_iddetalle_seq'::regclass);


--
-- Name: tbl_estado_factura idestadofactura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_estado_factura ALTER COLUMN idestadofactura SET DEFAULT nextval('public.tbl_estado_factura_idestadofactura_seq'::regclass);


--
-- Name: tbl_factura idfactura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_factura ALTER COLUMN idfactura SET DEFAULT nextval('public.tbl_factura_idfactura_seq'::regclass);


--
-- Name: tbl_productos idproducto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_productos ALTER COLUMN idproducto SET DEFAULT nextval('public.tbl_productos_idproducto_seq'::regclass);


--
-- Name: tbl_proveedores idproveedor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_proveedores ALTER COLUMN idproveedor SET DEFAULT nextval('public.tbl_proveedores_idproveedor_seq'::regclass);


--
-- Name: tbl_roles idroles; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_roles ALTER COLUMN idroles SET DEFAULT nextval('public.tbl_roles_idroles_seq'::regclass);


--
-- Name: tbl_tipofactura idtipofactura; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_tipofactura ALTER COLUMN idtipofactura SET DEFAULT nextval('public.tbl_tipofactura_idtipofactura_seq'::regclass);


--
-- Name: tbl_usuarios idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_usuarios ALTER COLUMN idusuario SET DEFAULT nextval('public.tbl_usuarios_idusuario_seq'::regclass);


--
-- Data for Name: tbl_categorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_categorias (idcategoria, nombrecategoria) FROM stdin;
1	Accesorios de computadoras
3	audifonos gamer
4	Unidades de almacenamiento
5	Tarjetas de video
11	Teclados gamer
10	Mouse
\.


--
-- Data for Name: tbl_clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_clientes (idcliente, nombreclientes, dui, nit, direccion, telefono) FROM stdin;
1	Jeferson Alexis De La Cruz Ventura	212121	001010001	SRL	7777-7777
\.


--
-- Data for Name: tbl_detallefactura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_detallefactura (iddetalle, idfactura, idproducto, cantidadvendida, preciounitario) FROM stdin;
\.


--
-- Data for Name: tbl_estado_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_estado_factura (idestadofactura, descripcionestado) FROM stdin;
1	Compra
\.


--
-- Data for Name: tbl_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_factura (idfactura, fechaemision, idcliente, numerofactura, tipofactura, idestadofactura) FROM stdin;
\.


--
-- Data for Name: tbl_productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_productos (idproducto, descripcion, existencia, preciounitario, fechavencimiento, idcategoria, idproveedor) FROM stdin;
1	Monitor 24 pulgadas	1.00	155.96	2024-12-31	1	1
2	Audifonos mesii	1.00	155.96	2024-12-31	3	1
3	SSD 250GB	40.00	45.25	2026-12-31	4	2
4	SSD 500GB	40.00	95.25	2026-12-31	4	2
5	SSD 1TB	40.00	175.23	2026-12-31	4	2
6	SSD 2TB	40.00	250.00	2026-12-31	4	2
7	NVIDIA RTX 3090	10.00	800.80	2026-12-31	5	3
8	NVIDIA RTX 4060	10.00	400.80	2026-12-31	5	3
9	NVIDIA RTX 4090 OC EDITION	31.00	2279.72	2026-12-31	5	3
10	AMD RX6600XT	31.00	350.00	2026-12-31	5	4
11	AMD RX7800XT	31.00	550.95	2026-12-31	5	4
12	AMD RX7900XT OC EDITION GDDR6 	31.00	849.99	2026-12-31	5	4
\.


--
-- Data for Name: tbl_proveedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_proveedores (idproveedor, nombreproveedor, telefono, correo) FROM stdin;
1	DELL	7777-2223	dell@gmail.com
2	Samsung	1111-2222	smg@gmail.com
3	Nvidia	1111-4444	nvidia@gmail.com
4	AMD	1222-4444	amd@gmail.com
5	Corsair	2222-2323	corsair@outlook.com
7	Xiaomi	4444-3333	xiaomi@hotmail.com
\.


--
-- Data for Name: tbl_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_roles (idroles, nombrerol) FROM stdin;
1	Support
2	Tank
\.


--
-- Data for Name: tbl_tipofactura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_tipofactura (idtipofactura, nombretipofactura) FROM stdin;
2	Spotify
\.


--
-- Data for Name: tbl_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tbl_usuarios (idusuario, nombreusuario, claveusuario, nombrecompleto, correo, telefono, idroles) FROM stdin;
2	fdfdsfd	dfdsfdsfsdfs	dsadsadsa	dsadasdsad	dsadadsadasd	1
4	ssssss		ssss	ssss	4141	1
\.


--
-- Name: tbl_categorias_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_categorias_idcategoria_seq', 11, true);


--
-- Name: tbl_clientes_idcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_clientes_idcliente_seq', 2, true);


--
-- Name: tbl_detallefactura_iddetalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_detallefactura_iddetalle_seq', 1, false);


--
-- Name: tbl_estado_factura_idestadofactura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_estado_factura_idestadofactura_seq', 2, true);


--
-- Name: tbl_factura_idfactura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_factura_idfactura_seq', 1, false);


--
-- Name: tbl_productos_idproducto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_productos_idproducto_seq', 12, true);


--
-- Name: tbl_proveedores_idproveedor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_proveedores_idproveedor_seq', 8, true);


--
-- Name: tbl_roles_idroles_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_roles_idroles_seq', 3, true);


--
-- Name: tbl_tipofactura_idtipofactura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_tipofactura_idtipofactura_seq', 2, true);


--
-- Name: tbl_usuarios_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_usuarios_idusuario_seq', 4, true);


--
-- Name: tbl_categorias tbl_categorias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_categorias
    ADD CONSTRAINT tbl_categorias_pkey PRIMARY KEY (idcategoria);


--
-- Name: tbl_clientes tbl_clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_clientes
    ADD CONSTRAINT tbl_clientes_pkey PRIMARY KEY (idcliente);


--
-- Name: tbl_detallefactura tbl_detallefactura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_detallefactura
    ADD CONSTRAINT tbl_detallefactura_pkey PRIMARY KEY (iddetalle);


--
-- Name: tbl_estado_factura tbl_estado_factura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_estado_factura
    ADD CONSTRAINT tbl_estado_factura_pkey PRIMARY KEY (idestadofactura);


--
-- Name: tbl_factura tbl_factura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_factura
    ADD CONSTRAINT tbl_factura_pkey PRIMARY KEY (idfactura);


--
-- Name: tbl_productos tbl_productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_productos
    ADD CONSTRAINT tbl_productos_pkey PRIMARY KEY (idproducto);


--
-- Name: tbl_proveedores tbl_proveedores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_proveedores
    ADD CONSTRAINT tbl_proveedores_pkey PRIMARY KEY (idproveedor);


--
-- Name: tbl_roles tbl_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_roles
    ADD CONSTRAINT tbl_roles_pkey PRIMARY KEY (idroles);


--
-- Name: tbl_tipofactura tbl_tipofactura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_tipofactura
    ADD CONSTRAINT tbl_tipofactura_pkey PRIMARY KEY (idtipofactura);


--
-- Name: tbl_usuarios tbl_usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_usuarios
    ADD CONSTRAINT tbl_usuarios_pkey PRIMARY KEY (idusuario);


--
-- Name: tbl_productos fk_proveedor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_productos
    ADD CONSTRAINT fk_proveedor FOREIGN KEY (idproveedor) REFERENCES public.tbl_proveedores(idproveedor);


--
-- Name: tbl_detallefactura tbl_detallefactura_idfactura_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_detallefactura
    ADD CONSTRAINT tbl_detallefactura_idfactura_fkey FOREIGN KEY (idfactura) REFERENCES public.tbl_factura(idfactura);


--
-- Name: tbl_detallefactura tbl_detallefactura_idproducto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_detallefactura
    ADD CONSTRAINT tbl_detallefactura_idproducto_fkey FOREIGN KEY (idproducto) REFERENCES public.tbl_productos(idproducto);


--
-- Name: tbl_factura tbl_factura_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_factura
    ADD CONSTRAINT tbl_factura_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES public.tbl_clientes(idcliente);


--
-- Name: tbl_factura tbl_factura_idestadofactura_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_factura
    ADD CONSTRAINT tbl_factura_idestadofactura_fkey FOREIGN KEY (idestadofactura) REFERENCES public.tbl_estado_factura(idestadofactura);


--
-- Name: tbl_factura tbl_factura_tipofactura_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_factura
    ADD CONSTRAINT tbl_factura_tipofactura_fkey FOREIGN KEY (tipofactura) REFERENCES public.tbl_tipofactura(idtipofactura);


--
-- Name: tbl_productos tbl_productos_idcategoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_productos
    ADD CONSTRAINT tbl_productos_idcategoria_fkey FOREIGN KEY (idcategoria) REFERENCES public.tbl_categorias(idcategoria);


--
-- Name: tbl_usuarios tbl_usuarios_idroles_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tbl_usuarios
    ADD CONSTRAINT tbl_usuarios_idroles_fkey FOREIGN KEY (idroles) REFERENCES public.tbl_roles(idroles);


--
-- PostgreSQL database dump complete
--

