PGDMP                         {            tienda    15.3    15.3 +    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    33140    tienda    DATABASE     {   CREATE DATABASE tienda WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Ecuador.1252';
    DROP DATABASE tienda;
                postgres    false            �            1259    33142    cliente    TABLE     �   CREATE TABLE public.cliente (
    id bigint NOT NULL,
    email character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    33141    cliente_id_seq    SEQUENCE     w   CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    215            *           0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    214            �            1259    33151    detalle_pedido    TABLE     �   CREATE TABLE public.detalle_pedido (
    id bigint NOT NULL,
    cantidad integer NOT NULL,
    precio_unitario numeric(38,2),
    pedido_id bigint,
    producto_id bigint
);
 "   DROP TABLE public.detalle_pedido;
       public         heap    postgres    false            �            1259    33150    detalle_pedido_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.detalle_pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.detalle_pedido_id_seq;
       public          postgres    false    217            +           0    0    detalle_pedido_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.detalle_pedido_id_seq OWNED BY public.detalle_pedido.id;
          public          postgres    false    216            �            1259    33158    pedido    TABLE     �   CREATE TABLE public.pedido (
    id bigint NOT NULL,
    estado character varying(255),
    fecha timestamp(6) without time zone,
    cliente_id bigint
);
    DROP TABLE public.pedido;
       public         heap    postgres    false            �            1259    33157    pedido_id_seq    SEQUENCE     v   CREATE SEQUENCE public.pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.pedido_id_seq;
       public          postgres    false    219            ,           0    0    pedido_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.pedido_id_seq OWNED BY public.pedido.id;
          public          postgres    false    218            �            1259    33165    producto    TABLE     �   CREATE TABLE public.producto (
    id bigint NOT NULL,
    nombre character varying(255),
    precio numeric(38,2),
    stock integer NOT NULL,
    tienda_id bigint
);
    DROP TABLE public.producto;
       public         heap    postgres    false            �            1259    33164    producto_id_seq    SEQUENCE     x   CREATE SEQUENCE public.producto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.producto_id_seq;
       public          postgres    false    221            -           0    0    producto_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.producto_id_seq OWNED BY public.producto.id;
          public          postgres    false    220            �            1259    33172    tienda    TABLE     �   CREATE TABLE public.tienda (
    id bigint NOT NULL,
    direccion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.tienda;
       public         heap    postgres    false            �            1259    33171    tienda_id_seq    SEQUENCE     v   CREATE SEQUENCE public.tienda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.tienda_id_seq;
       public          postgres    false    223            .           0    0    tienda_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.tienda_id_seq OWNED BY public.tienda.id;
          public          postgres    false    222            y           2604    33145 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            z           2604    33154    detalle_pedido id    DEFAULT     v   ALTER TABLE ONLY public.detalle_pedido ALTER COLUMN id SET DEFAULT nextval('public.detalle_pedido_id_seq'::regclass);
 @   ALTER TABLE public.detalle_pedido ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            {           2604    33161 	   pedido id    DEFAULT     f   ALTER TABLE ONLY public.pedido ALTER COLUMN id SET DEFAULT nextval('public.pedido_id_seq'::regclass);
 8   ALTER TABLE public.pedido ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            |           2604    33168    producto id    DEFAULT     j   ALTER TABLE ONLY public.producto ALTER COLUMN id SET DEFAULT nextval('public.producto_id_seq'::regclass);
 :   ALTER TABLE public.producto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            }           2604    33175 	   tienda id    DEFAULT     f   ALTER TABLE ONLY public.tienda ALTER COLUMN id SET DEFAULT nextval('public.tienda_id_seq'::regclass);
 8   ALTER TABLE public.tienda ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223                      0    33142    cliente 
   TABLE DATA           >   COPY public.cliente (id, email, nombre, telefono) FROM stdin;
    public          postgres    false    215   �.                 0    33151    detalle_pedido 
   TABLE DATA           _   COPY public.detalle_pedido (id, cantidad, precio_unitario, pedido_id, producto_id) FROM stdin;
    public          postgres    false    217   /                 0    33158    pedido 
   TABLE DATA           ?   COPY public.pedido (id, estado, fecha, cliente_id) FROM stdin;
    public          postgres    false    219   K/       !          0    33165    producto 
   TABLE DATA           H   COPY public.producto (id, nombre, precio, stock, tienda_id) FROM stdin;
    public          postgres    false    221   �/       #          0    33172    tienda 
   TABLE DATA           A   COPY public.tienda (id, direccion, nombre, telefono) FROM stdin;
    public          postgres    false    223   0       /           0    0    cliente_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cliente_id_seq', 3, true);
          public          postgres    false    214            0           0    0    detalle_pedido_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.detalle_pedido_id_seq', 4, true);
          public          postgres    false    216            1           0    0    pedido_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.pedido_id_seq', 2, true);
          public          postgres    false    218            2           0    0    producto_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.producto_id_seq', 6, true);
          public          postgres    false    220            3           0    0    tienda_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tienda_id_seq', 5, true);
          public          postgres    false    222                       2606    33149    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    215            �           2606    33156 "   detalle_pedido detalle_pedido_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.detalle_pedido DROP CONSTRAINT detalle_pedido_pkey;
       public            postgres    false    217            �           2606    33163    pedido pedido_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pkey;
       public            postgres    false    219            �           2606    33170    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public            postgres    false    221            �           2606    33179    tienda tienda_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.tienda
    ADD CONSTRAINT tienda_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.tienda DROP CONSTRAINT tienda_pkey;
       public            postgres    false    223            �           2606    33185 *   detalle_pedido fk2yc3nts8mdyqf6dw6ndosk67a    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fk2yc3nts8mdyqf6dw6ndosk67a FOREIGN KEY (producto_id) REFERENCES public.producto(id);
 T   ALTER TABLE ONLY public.detalle_pedido DROP CONSTRAINT fk2yc3nts8mdyqf6dw6ndosk67a;
       public          postgres    false    3205    217    221            �           2606    33190 "   pedido fk30s8j2ktpay6of18lbyqn3632    FK CONSTRAINT     �   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);
 L   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fk30s8j2ktpay6of18lbyqn3632;
       public          postgres    false    3199    219    215            �           2606    33199 $   producto fkae8kdx8rpj6mbstkb3snu0e8d    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fkae8kdx8rpj6mbstkb3snu0e8d FOREIGN KEY (tienda_id) REFERENCES public.tienda(id);
 N   ALTER TABLE ONLY public.producto DROP CONSTRAINT fkae8kdx8rpj6mbstkb3snu0e8d;
       public          postgres    false    3207    221    223            �           2606    33180 *   detalle_pedido fkgqvba9e7dildyw45u0usdj1k2    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fkgqvba9e7dildyw45u0usdj1k2 FOREIGN KEY (pedido_id) REFERENCES public.pedido(id);
 T   ALTER TABLE ONLY public.detalle_pedido DROP CONSTRAINT fkgqvba9e7dildyw45u0usdj1k2;
       public          postgres    false    217    3203    219               J   x�3�L�/*J�wH�H�-�I�K�����)��S�H�%r����[Xps�e�����!+����� ��_            x�3�4���4�4�2�`�W� 9|�         ,   x�3���4202�5��54Q0��24�22�370�4����� },Q      !   �   x�=�1
1E�S�	B&k\,��b@���lRl�k����7"X���gp���ߍd{ΏvoI��Hbe�X�؀�=8�a�$YH�#U�"qw��a��$)tʾ��%<C�������"WJ������wS��U�"�      #   T   x�3�t�,JMN���SHIU�IT�L�KIT0�2B\�C�\C#cS3sK.\��Q5#k2¥)$��D���A����� �k.s     