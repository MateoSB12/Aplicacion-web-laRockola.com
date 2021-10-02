create database `mi_rockola`;
use mi_rockola;

create table genero(
id_gen varchar(10) NOT NULL,
nombre_gen varchar(15) NOT NULL,
constraint gen_pk primary key(id_gen));

create table nacionalidad(
id_nac varchar(10) NOT NULL,
nombre_nac varchar(15) NOT NULL,
constraint nacionalidad_pk primary key(id_nac));
  
create table rol(
id_rol varchar(10) NOT NULL,
nombre_rol varchar(15) NOT NULL,
constraint rol_pk primary key(id_rol));
  
create table artista(
id_art varchar(10) NOT NULL,
nombre_art varchar(25) NOT NULL,
id_nac varchar(15),
constraint artista_pk primary key(id_art),
constraint artista_id_nac_fk foreign key(id_nac) references nacionalidad(id_nac));

create table album(
id_alb varchar(10) NOT NULL,
nombre_alb varchar(50) NOT NULL,
id_art varchar(10),
id_gen varchar(10) NOT NULL,
constraint album_pk primary key(id_alb, id_gen),
constraint album_id_art_fk foreign key(id_art) references artista(id_art),
constraint album_id_gen_fk foreign key(id_gen) references genero(id_gen));

create table cancion(
id_can varchar(10) NOT NULL,
nombre_can varchar(50) NOT NULL,
id_alb varchar(10) NOT NULL,
duracion_can varchar(5) NOT NULL,
constraint cancion_pk primary key(id_can),
constraint artista_id_alb_fk foreign key(id_alb) references album(id_alb));

create table usuario(
id_usu varchar(10) NOT NULL,
nombre_usu varchar(50) NOT NULL,
correo_usu varchar(50) NOT NULL,
clave_usu varchar(20) NOT NULL,
id_rol varchar(10) NOT NULL,
constraint usuario_pk primary key(id_usu),
constraint artista_id_rol_fk foreign key(id_rol) references rol(id_rol));

create table lista_reproduccion(
id_list_rep varchar(10) NOT NULL,
nombre_list_rep varchar(50) NOT NULL,
id_usu varchar(10) NOT NULL,
constraint lista_reproduccion_pk primary key(id_list_rep),
constraint lista_reproduccion_id_usu_fk foreign key(id_usu) references usuario(id_usu));

create table lista_cancion(
id_can varchar(10) NOT NULL,
id_list_rep varchar(10) NOT NULL,
constraint lista_cancion_pk primary key(id_can,id_list_rep),
constraint lista_cancion_id_can_fk foreign key(id_can) references cancion(id_can),
constraint lista_cancion_id_list_rep_fk foreign key(id_list_rep) references lista_reproduccion(id_list_rep));