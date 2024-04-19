PGDMP                         |            turizmacente    13.14    13.14 1               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17163    turizmacente    DATABASE     j   CREATE DATABASE turizmacente WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE turizmacente;
                postgres    false            �            1259    17186    features    TABLE     �   CREATE TABLE public.features (
    feature_id bigint NOT NULL,
    feature text NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL
);
    DROP TABLE public.features;
       public         heap    postgres    false            �            1259    17184    hotel_features_features_id_seq    SEQUENCE     �   ALTER TABLE public.features ALTER COLUMN feature_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_features_features_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            �            1259    17176    otel    TABLE     %  CREATE TABLE public.otel (
    otel_id bigint NOT NULL,
    name text NOT NULL,
    address text NOT NULL,
    email text NOT NULL,
    phone text NOT NULL,
    star text NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    city text NOT NULL,
    region text NOT NULL
);
    DROP TABLE public.otel;
       public         heap    postgres    false            �            1259    17174    hotel_otel_id_seq    SEQUENCE     �   ALTER TABLE public.otel ALTER COLUMN otel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �            1259    17216    otel_feature    TABLE     z   CREATE TABLE public.otel_feature (
    id bigint NOT NULL,
    feature_id bigint NOT NULL,
    otel_id bigint NOT NULL
);
     DROP TABLE public.otel_feature;
       public         heap    postgres    false            �            1259    17214    otel_feature_id_seq    SEQUENCE     �   ALTER TABLE public.otel_feature ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_feature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209            �            1259    17206    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id bigint NOT NULL,
    pension_name text NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    17204    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    17223    pension_type    TABLE     z   CREATE TABLE public.pension_type (
    id bigint NOT NULL,
    pension_id bigint NOT NULL,
    otel_id bigint NOT NULL
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    17221    pension_type_id_seq    SEQUENCE     �   ALTER TABLE public.pension_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    211            �            1259    17230    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    start_season date NOT NULL,
    end_season date NOT NULL,
    otel_id bigint NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17228    period_period_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.period_period_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    213            �            1259    17252    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    room_id integer NOT NULL,
    customer_fullname text NOT NULL,
    customer_email text NOT NULL,
    customer_phone text NOT NULL,
    customer_identity text NOT NULL,
    total_adultcount integer NOT NULL,
    total_childcount integer NOT NULL,
    total_price double precision NOT NULL,
    status text NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    in_date date NOT NULL,
    out_date date NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17250    rezervation_rezervation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.rezervation_rezervation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    17237    room    TABLE     N  CREATE TABLE public.room (
    room_id bigint NOT NULL,
    otel_id bigint NOT NULL,
    pension_id bigint NOT NULL,
    season_id bigint NOT NULL,
    type text NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean DEFAULT false NOT NULL,
    minibar boolean DEFAULT false NOT NULL,
    game_console boolean DEFAULT false NOT NULL,
    cash_box boolean DEFAULT false NOT NULL,
    projection boolean DEFAULT false NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17235    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    17166    user    TABLE       CREATE TABLE public."user" (
    id bigint NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL,
    mobile_phone text NOT NULL,
    password text NOT NULL,
    role text NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17164    user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            �          0    17186    features 
   TABLE DATA           O   COPY public.features (feature_id, feature, created_at, updated_at) FROM stdin;
    public          postgres    false    205   �:       �          0    17176    otel 
   TABLE DATA           p   COPY public.otel (otel_id, name, address, email, phone, star, created_at, updated_at, city, region) FROM stdin;
    public          postgres    false    203   #;       �          0    17216    otel_feature 
   TABLE DATA           ?   COPY public.otel_feature (id, feature_id, otel_id) FROM stdin;
    public          postgres    false    209   �<       �          0    17206    pension 
   TABLE DATA           S   COPY public.pension (pension_id, pension_name, created_at, updated_at) FROM stdin;
    public          postgres    false    207   N=       �          0    17223    pension_type 
   TABLE DATA           ?   COPY public.pension_type (id, pension_id, otel_id) FROM stdin;
    public          postgres    false    211   �=                 0    17252    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, customer_fullname, customer_email, customer_phone, customer_identity, total_adultcount, total_childcount, total_price, status, created_at, updated_at, in_date, out_date) FROM stdin;
    public          postgres    false    217   S>                  0    17237    room 
   TABLE DATA           �   COPY public.room (room_id, otel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    215   �>       �          0    17230    season 
   TABLE DATA           N   COPY public.season (season_id, start_season, end_season, otel_id) FROM stdin;
    public          postgres    false    213   O@       �          0    17166    user 
   TABLE DATA           x   COPY public."user" (id, first_name, last_name, email, mobile_phone, password, role, created_at, updated_at) FROM stdin;
    public          postgres    false    201   �@       	           0    0    hotel_features_features_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.hotel_features_features_id_seq', 9, true);
          public          postgres    false    204            
           0    0    hotel_otel_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.hotel_otel_id_seq', 5, true);
          public          postgres    false    202                       0    0    otel_feature_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.otel_feature_id_seq', 35, true);
          public          postgres    false    208                       0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 8, true);
          public          postgres    false    206                       0    0    pension_type_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.pension_type_id_seq', 21, true);
          public          postgres    false    210                       0    0    period_period_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.period_period_id_seq', 13, true);
          public          postgres    false    212                       0    0    rezervation_rezervation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.rezervation_rezervation_id_seq', 6, true);
          public          postgres    false    216                       0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 22, true);
          public          postgres    false    214                       0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 13, true);
          public          postgres    false    200            b           2606    17193    features hotel_features_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.features
    ADD CONSTRAINT hotel_features_pkey PRIMARY KEY (feature_id);
 F   ALTER TABLE ONLY public.features DROP CONSTRAINT hotel_features_pkey;
       public            postgres    false    205            `           2606    17183    otel hotel_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.otel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (otel_id);
 9   ALTER TABLE ONLY public.otel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    203            f           2606    17220    otel_feature otel_feature_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.otel_feature
    ADD CONSTRAINT otel_feature_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.otel_feature DROP CONSTRAINT otel_feature_pkey;
       public            postgres    false    209            d           2606    17213    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    207            h           2606    17227    pension_type pension_type_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    211            j           2606    17234    season period_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT period_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT period_pkey;
       public            postgres    false    213            n           2606    17259    reservation rezervation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT rezervation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT rezervation_pkey;
       public            postgres    false    217            l           2606    17249    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    215            ^           2606    17173    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    201            �   �   x�3�<<'�(��8�J��$� �(�����D��D����e��2<�-�2���{�rS<�J�Jq�2�t�,�K-.VpN�+I-¡̌�#�$5G�9?/93�(=�:s�� Gr����F&
�)�
��Ee�Ÿ\���� �YA�      �   �  x����n�0���)��*�H��Ҥ[+e٤i�I�8�k�0F289�&�X^�\v�x�>�ɚ*MX�����`d�EҔ�6;�(ڧ��je$�y�iF�=����BXb�3�5��U[��sWiS��G.|��e�� `�$/��)��,%�O�]�EB���2j�a.���N���i�e�V�8��F�`&½��0�<��>+���<�0���nȹn��f�?A�[6����l!`���1�P�4��+��-y݃�oX�.�Q
�	}���<.����w|�^�m��Oy��nc�ʊ�ЖvM��*I�l�N���Q��!=�L7)��C)� {�a����s�ėw8���I��[��J���$EK�pgw��
Ud��[��z�ڌ��6�<X���zy[�oJ��U&z��z�pb+�c������#�w�������1      �   R   x����0��RLd~v�K��#��	��
�y(��+ͯb��;�SY$K(��,ak� ^�m��	�אP�m�cf?Sq�      �   �   x�3��H-::?�R�%1#3�����D��D����e�韒����Q��Srd#U���9%E�
@i������W�Y���C�)gdbё�ՙq'��&�*D&�$f�Pd�阓�����X�yx��[iN�BrQjJf	1z\\\ qI5      �   V   x����0���0� !Iw��sTy` �R�T��IN5ɨ�����<|�/�r�����[�&"hL��.6p(��ek������?�j         �   x���1�0��+\��h��B�)����E
ޒ�xXHD��͎�&�v�i��c)~.��J��0��>�ݔ	�z9٠�2[�2by �>�����Ƕ����@��w|����j���S��A��E��1
���ƘviJ�          R  x���MJ1�וS�	$���+�.�NCQ��3�1<�x/_%Stz�	4tx_^�^ʐ%O#��Ӳ��;������J�L�d���iZ��*�b+���'��	�g�� X��@n��(/�E�{��el�8YV�ꋀF�Z�7
�/^4g'[S�P�����5<��z�٥�բ�@�:�e�i�/3c��[mx'\˷R��&���#�|z�nK馒����=�}/��_زX.]�O`̶�oQc�\KMx���{�$^��]�U#��(n]�3�J�{n�Γɱ����La�.n�yp0=�C�冕92D_�e��l�1���Y/�vz =��Լ�)�� ��.      �   K   x�u���@Dѳ���e�^�1�xrC~���c��oͲ�c��+K�r
��~�[��Bu
�
L�
���j$X      �   �   x���M
�0�ד��d&?�� h�u3�hk!m��i���Rn|�y�[|B��C�7\3�t��Y�|;��5����d�E���6��fUI҉�	RT*X����}�Xv��sf��W۲Xm*���rG���s�{o�q�j����JR!�!6G�     