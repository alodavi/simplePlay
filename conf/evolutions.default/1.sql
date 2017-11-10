# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "URL_RECORDS" ("ID" SERIAL NOT NULL PRIMARY KEY, "URL" VARCHAR(254) NOT NULL,"NEW_URL" VARCHAR(254) NOT NULL);

# --- !Downs

drop table "URL_RECORDS";

