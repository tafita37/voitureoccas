CREATE TABLE admins(
   idadmins SERIAL,
   nomadmin VARCHAR(50) ,
   prenomadmin VARCHAR(50) ,
   mail VARCHAR(50) ,
   nee DATE,
   pwd VARCHAR(50) ,
   PRIMARY KEY(idadmins),
   UNIQUE(mail)
);


CREATE TABLE users(
   iduser SERIAL,
   nomuser VARCHAR(50) ,
   prenomuser VARCHAR(50) ,
   mail VARCHAR(50) ,
   nee DATE,
   pwd VARCHAR(50) ,
   PRIMARY KEY(iduser),
   UNIQUE(mail)
);

CREATE TABLE marque(
   idmarque SERIAL,
   nommarque VARCHAR(50) ,
   PRIMARY KEY(idmarque)
);
CREATE TABLE carburant(
   idcarburant SERIAL,
   nomcarburant VARCHAR(50) ,
   PRIMARY KEY(idcarburant)
);
CREATE TABLE models(
   idmodel SERIAL,
   nommodel VARCHAR(50) ,
   idmarque int REFERENCES marque (idmarque),
   transmission int REFERENCES transmission(idtransmission),
   anneefab int check(anneefab>0),
   vitesse DOUBLE PRECISION check (vitesse>0),
   idcarburant int REFERENCES carburant (idcarburant),
   PRIMARY KEY(idmodel)
);

CREATE TABLE categorie(
   idcategorie SERIAL,
   nomcategorie VARCHAR(50) ,
   PRIMARY KEY(idcategorie)
);
create table transmission(
   idtransmission SERIAL primary key,
   nomtransmission VARCHAR unique
);
CREATE TABLE modelcategorie(
   idmodelcategorie SERIAL PRIMARY KEY,
   idmodel INTEGER,
   idcategorie INTEGER,
   FOREIGN KEY(idmodel) REFERENCES models(idmodel),
   FOREIGN KEY(idcategorie) REFERENCES categorie(idcategorie)
);

CREATE TABLE voitureinfo(
   idvoitureinfo SERIAL,
   nomvoiture VARCHAR(50) ,
   nombreplace INTEGER,
   kilometrage DOUBLE PRECISION,
   iduser INTEGER NOT NULL,
   idmodel INTEGER NOT NULL,
   PRIMARY KEY(idvoitureinfo),
   FOREIGN KEY(iduser) REFERENCES users(iduser),
   FOREIGN KEY(idmodel) REFERENCES models(idmodel)
);

CREATE TABLE lieu(
   idlieu SERIAL,
   nomlieu VARCHAR(50) ,
   PRIMARY KEY(idlieu)
);

----0 dispo 
---select * from tokenuser where iduser='' and etats=0;-->throw new ExceptionCar("session plus valide");
CREATE TABLE tokenuser(
   idtokenuser SERIAL,
   token TEXT,
   datedebut TIMESTAMP,
   dateexp TIMESTAMP,
   etats INTEGER,
   iduser INTEGER NOT NULL,
   PRIMARY KEY(idtokenuser),
   FOREIGN KEY(iduser) REFERENCES users(iduser)
);

CREATE TABLE soldeuser(
   idsoldeuser SERIAL,
   solde DOUBLE PRECISION,
   dateupdate TIMESTAMP,
   iduser INTEGER NOT NULL,
   PRIMARY KEY(idsoldeuser),
   UNIQUE(iduser),
   FOREIGN KEY(iduser) REFERENCES users(iduser)
);

CREATE TABLE soldesite(
   Idsoldesite SERIAL,
   solde DOUBLE PRECISION,
   dateupdate TIMESTAMP,
   PRIMARY KEY(Idsoldesite)
);

CREATE TABLE valeurcredit(
   idvaleurcredit SERIAL,
   valeur DOUBLE PRECISION,
   PRIMARY KEY(idvaleurcredit),
   UNIQUE(valeur)
);


CREATE TABLE motif(
   idmotif SERIAL,
   nommotif VARCHAR(50) ,
   codemotif VARCHAR(30),
   PRIMARY KEY(idmotif),
   UNIQUE(codemotif)
);

CREATE TABLE annonce(
   idannonce SERIAL,
   prixvente DOUBLE PRECISION,
   descriptions VARCHAR(200) ,
   statusvente INTEGER,
   etat INTEGER,
   idlieu INTEGER NOT NULL,
   idvoitureinfo INTEGER NOT NULL,
   dateannonce TIMESTAMP,
   PRIMARY KEY(idannonce),
   UNIQUE(idvoitureinfo),
   FOREIGN KEY(idlieu) REFERENCES lieu(idlieu),
   FOREIGN KEY(idvoitureinfo) REFERENCES voitureinfo(idvoitureinfo)
);
---statusvente : 0 : vendu /10 : non vendu
---etat : 0:encour demande / 10 :accepter / 20: refuser
CREATE TABLE annoncephoto(
   idannoncephoto SERIAL,
   photo TEXT ,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncephoto),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);
CREATE TABLE vendu(
   idvendu SERIAL,
   datevente DATE,
   datemodifstatus DATE,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idvendu),
   UNIQUE(idannonce),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE annoncevalidation(
   idannoncevalidation SERIAL,
   datevalide DATE,
   idadmins INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncevalidation),
   UNIQUE(idannonce),
   FOREIGN KEY(idadmins) REFERENCES admins(idadmins),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);



CREATE TABLE annoncefavoris(
   idannoncefavoris SERIAL,
   iduser INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncefavoris),
   FOREIGN KEY(iduser) REFERENCES users(iduser),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE annoncerefus(
   idannoncerefus SERIAL,
   daterefus DATE,
   idadmins INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncerefus),
   UNIQUE(idannonce),
   FOREIGN KEY(idadmins) REFERENCES admins(idadmins),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE debitersoldeuser(
   iddebit SERIAL,
   montantd DOUBLE PRECISION ,
   dated TIMESTAMP,
   idmotif INTEGER NOT NULL,
   idsoldeuser INTEGER NOT NULL,
   PRIMARY KEY(iddebit),
   FOREIGN KEY(idmotif) REFERENCES motif(idmotif),
   FOREIGN KEY(idsoldeuser) REFERENCES soldeuser(idsoldeuser)
);


CREATE TABLE creditersoldesite(
   idcredit SERIAL,
   montantc DOUBLE PRECISION,
   datec TIMESTAMP,
   iddebit INTEGER NOT NULL,
   idmotif INTEGER NOT NULL,
   Idsoldesite INTEGER NOT NULL,
   PRIMARY KEY(idcredit),
   UNIQUE(iddebit),
   FOREIGN KEY(iddebit) REFERENCES debitersoldeuser(iddebit),
   FOREIGN KEY(idmotif) REFERENCES motif(idmotif),
   FOREIGN KEY(Idsoldesite) REFERENCES soldesite(Idsoldesite)
);

--etats:0 disponible / etats:1 plus disponible
CREATE TABLE codecredit(
   idcodecredit SERIAL,
   code INTEGER,
   etats INTEGER,
   idvaleurcredit INTEGER NOT NULL,
   PRIMARY KEY(idcodecredit),
   UNIQUE(code),
   FOREIGN KEY(idvaleurcredit) REFERENCES valeurcredit(idvaleurcredit)
);

CREATE TABLE creditersoldeuser(
   idcredit SERIAL,
   montantc DOUBLE PRECISION,
   datec TIMESTAMP,
   idcodecredit INTEGER NOT NULL,
   idsoldeuser INTEGER NOT NULL,
   PRIMARY KEY(idcredit),
   FOREIGN KEY(idcodecredit) REFERENCES codecredit(idcodecredit),
   FOREIGN KEY(idsoldeuser) REFERENCES soldeuser(idsoldeuser)
);

CREATE TABLE categorievoiture(
   idcategorievoiture SERIAL,
   idcategorie INTEGER,
   idvoitureinfo INTEGER,
   PRIMARY KEY(idcategorievoiture),
   FOREIGN KEY(idcategorie) REFERENCES categorie(idcategorie),
   FOREIGN KEY(idvoitureinfo) REFERENCES voitureinfo(idvoitureinfo)
); 
--------coderege: C000 -->commission
CREATE TABLE regletaux(
   idregletaux SERIAL,
   coderegle VARCHAR(50) ,
   nomregle VARCHAR(50) ,
   tauxpourcent float,
   PRIMARY KEY(idregletaux)
);
---------------------------NON RELATIONNEL
CREATE TABLE messages(
   idmessages SERIAL,
   nomsend VARCHAR(50) ,
   prenomsend VARCHAR(50) ,
   nomreceive VARCHAR(50) ,
   prenomreceive VARCHAR(50),
   contenu TEXT,
   typemessage INTEGER,
   datehmsg TIMESTAMP,
   idusersend INTEGER NOT NULL,
   iduserreceive INTEGER NOT NULL
);

select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v 
left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= :iduser) 
where ad_v.iduser!= :iduser and ad_v.statusvente=10 and ad_v.etat=10 
order by ad_v.idannonce ASC,ad_v.idcategorie ASC,ad_v.idannoncephoto ASC,ad_v.dateannonce ASC;

select * from modelcategorie as mc 
join models as m on m.idmodel=mc.idmodel
join categorie as c on c.idcategorie=mc.idcategorie order by m.idmodel;