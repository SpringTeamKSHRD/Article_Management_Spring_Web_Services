/*
Navicat PGSQL Data Transfer

Source Server         : postgres
Source Server Version : 90303
Source Host           : localhost:5432
Source Database       : articledb
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90303
File Encoding         : 65001

Date: 2015-11-12 09:16:18
*/


-- ----------------------------
-- Sequence structure for tbarticle_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "tbarticle_id_seq" CASCADE;
CREATE SEQUENCE "tbarticle_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;
SELECT setval('"public"."tbarticle_id_seq"', 14, true);

-- ----------------------------
-- Sequence structure for tbuser_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "tbuser_id_seq" CASCADE;
CREATE SEQUENCE "tbuser_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 11
 CACHE 1;
SELECT setval('"public"."tbuser_id_seq"', 11, true);

-- ----------------------------
-- Table structure for tbarticle
-- ----------------------------
DROP TABLE IF EXISTS "tbarticle" CASCADE;
CREATE TABLE "tbarticle" (
"id" int8 DEFAULT nextval('tbarticle_id_seq'::regclass) NOT NULL,
"title" varchar COLLATE "default" NOT NULL,
"userid" int4 NOT NULL,
"content" varchar COLLATE "default",
"publish_date" date,
"enable" bool NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbarticle
-- ----------------------------
BEGIN;
INSERT INTO "tbarticle" VALUES ('1', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('2', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('3', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('4', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('5', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('6', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('7', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('8', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('9', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('10', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('11', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('12', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('13', 'sdf', '1', 'sdf', '2010-02-01', 't');
INSERT INTO "tbarticle" VALUES ('14', 'sdf', '1', 'sdf', '2010-02-01', 't');
COMMIT;

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS "tbuser" CASCADE;
CREATE TABLE "tbuser" (
"id" int4 DEFAULT nextval('tbuser_id_seq'::regclass) NOT NULL,
"username" varchar COLLATE "default" NOT NULL,
"password" varchar COLLATE "default" NOT NULL,
"role" varchar COLLATE "default" NOT NULL,
"enable" bool NOT NULL,
"email" varchar COLLATE "default",
"address" varchar COLLATE "default",
"phone" varchar COLLATE "default",
"name" varchar COLLATE "default",
"gender" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbuser
-- ----------------------------
BEGIN;
INSERT INTO "tbuser" VALUES ('1', 'lit', '1234', 'admin', 't', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('2', 'derp', '1234', 'admin', 't', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('3', 'derp', '1234', 'author', 'f', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('4', 'derp', '1234', 'admin', 'f', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('5', 'derp', '1234', 'author', 't', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('6', 'derp', '1234', 'admin', 't', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('7', 'derp', '1234', 'author', 't', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('8', 'derp', '1234', 'admin', 'f', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('9', 'derp', '1234', 'author', 'f', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('10', 'derp', '1234', 'admin', 'f', null, null, null, null, null);
INSERT INTO "tbuser" VALUES ('11', 'derp', '1234', 'author', 't', null, null, null, null, null);
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "tbarticle_id_seq" OWNED BY "tbarticle"."id";
ALTER SEQUENCE "tbuser_id_seq" OWNED BY "tbuser"."id";

-- ----------------------------
-- Primary Key structure for table tbarticle
-- ----------------------------
ALTER TABLE "tbarticle" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tbuser
-- ----------------------------
ALTER TABLE "tbuser" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "tbarticle"
-- ----------------------------
ALTER TABLE "tbarticle" ADD FOREIGN KEY ("userid") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
