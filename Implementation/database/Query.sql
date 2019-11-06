CREATE TABLE "role" (
  "id" SERIAL PRIMARY KEY,
  "description" varchar
);

CREATE TABLE "user" (
  "id" SERIAL PRIMARY KEY,
  "email" varchar,
  "password" varchar,
  "activate" boolean,
  "role" int
);

CREATE TABLE "teacher" (
  "id" int,
  "institution" varchar,
  "first_name" varchar,
  "last_name" varchar,
  "date_birth" datetime,
  "gender" int,
  "bonus" int,
  "defaultTemplate" int,
  "status" boolean,
  "alerts" int
);

CREATE TABLE "admin" (
  "id" int,
  "fullname" varchar,
  "phone" varchar,
  "address" varchar
);

CREATE TABLE "exam_teacher" (
  "id_teacher" int,
  "id_exam" int
);

CREATE TABLE "exam" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar,
  "creation_Date" datetime
);

CREATE TABLE "topics" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "idTopicDad" int
);

CREATE TABLE "problem" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar,
  "body" varchar,
  "image" varchar,
  "score" double,
  "qualifiers" int
);

CREATE TABLE "problem_topic" (
  "idproblem" int,
  "idtopic" int
);

CREATE TABLE "problem_submitted" (
  "id_user" int,
  "id_problem" int,
  "dateCreated" datetime
);

CREATE TABLE "exam_problem" (
  "id_problem" int,
  "id_exam" int,
  "score" int
);

CREATE TABLE "solution_problem" (
  "id" SERIAL PRIMARY KEY,
  "id_problem" int UNIQUE NOT NULL,
  "description" varchar,
  "image" varchar,
  "alternatives" varchar
);

CREATE TABLE "audit" (
  "id" SERIAL PRIMARY KEY,
  "id_admin" int,
  "id_teacher" varchar,
  "date_action" datetime,
  "action" varchar,
  "comments" varchar
);

CREATE TABLE "comments" (
  "id_problem" int,
  "id_user_comment" int,
  "description" varchar
);

CREATE TABLE "suggests" (
  "id_problem" int,
  "id_user_suggest" int,
  "description" varchar
);

ALTER TABLE "user" ADD FOREIGN KEY ("role") REFERENCES "role" ("id");

ALTER TABLE "teacher" ADD FOREIGN KEY ("id") REFERENCES "user" ("id");

ALTER TABLE "admin" ADD FOREIGN KEY ("id") REFERENCES "user" ("id");

ALTER TABLE "exam_teacher" ADD FOREIGN KEY ("id_teacher") REFERENCES "teacher" ("id");

ALTER TABLE "exam_teacher" ADD FOREIGN KEY ("id_exam") REFERENCES "exam" ("id");

ALTER TABLE "topics" ADD FOREIGN KEY ("idTopicDad") REFERENCES "topics" ("id");

ALTER TABLE "problem_topic" ADD FOREIGN KEY ("idproblem") REFERENCES "problem" ("id");

ALTER TABLE "problem_topic" ADD FOREIGN KEY ("idtopic") REFERENCES "topics" ("id");

ALTER TABLE "problem_submitted" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id");

ALTER TABLE "problem_submitted" ADD FOREIGN KEY ("id_problem") REFERENCES "problem" ("id");

ALTER TABLE "exam_problem" ADD FOREIGN KEY ("id_problem") REFERENCES "problem" ("id");

ALTER TABLE "exam_problem" ADD FOREIGN KEY ("id_exam") REFERENCES "exam" ("id");

ALTER TABLE "solution_problem" ADD FOREIGN KEY ("id_problem") REFERENCES "problem" ("id");

ALTER TABLE "audit" ADD FOREIGN KEY ("id_admin") REFERENCES "admin" ("id");

ALTER TABLE "audit" ADD FOREIGN KEY ("id_teacher") REFERENCES "teacher" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("id_problem") REFERENCES "problem" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("id_user_comment") REFERENCES "user" ("id");

ALTER TABLE "suggests" ADD FOREIGN KEY ("id_problem") REFERENCES "problem" ("id");

ALTER TABLE "suggests" ADD FOREIGN KEY ("id_user_suggest") REFERENCES "user" ("id");
