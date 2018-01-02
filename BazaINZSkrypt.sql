-- Generated by Oracle SQL Developer Data Modeler 4.1.5.907
--   at:        2017-12-17 13:55:15 CET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g




DROP TABLE Answer CASCADE;

DROP TABLE Criteria CASCADE;

DROP TABLE Criteria_Ratio CASCADE;

DROP TABLE Question CASCADE;

DROP TABLE Questionare CASCADE;

DROP TABLE Service CASCADE;

CREATE TABLE Answer
  ( ID_ANSWER INTEGER NOT NULL , Value INTEGER
  ) ;
ALTER TABLE Answer ADD CONSTRAINT Answer_PK PRIMARY KEY ( ID_ANSWER ) ;

CREATE TABLE Criteria
  (
    ID_CRITERIA          INTEGER NOT NULL ,
    Name                 VARCHAR (50) ,
    Criteria_Ratio_ID_CR INTEGER NOT NULL
  ) ;
ALTER TABLE Criteria ADD CONSTRAINT Criteria_PK PRIMARY KEY ( ID_CRITERIA ) ;


CREATE TABLE Criteria_Ratio
  (
    ID_CR          INTEGER NOT NULL ,
    Criteria_Value REAL
  ) ;
ALTER TABLE Criteria_Ratio ADD CONSTRAINT Criteria_Ratio_PK PRIMARY KEY ( ID_CR ) ;


CREATE TABLE Question
  (
    ID_QUESTION                  INTEGER NOT NULL ,
    Description                  VARCHAR (400) ,
    Criteria_ID_CRITERIA         INTEGER NOT NULL ,
    Questionare_ID_QUESTIONNAIRE INTEGER NOT NULL ,
    Answer_ID_ANSWER             INTEGER
  ) ;
ALTER TABLE Question ADD CONSTRAINT Question_PK PRIMARY KEY ( ID_QUESTION, Criteria_ID_CRITERIA ) ;


CREATE TABLE Questionare
  (
    ID_QUESTIONNAIRE INTEGER NOT NULL ,
    Name             VARCHAR (100),
    Service_ID_SERVICE INTEGER NOT NULL
  ) ;
ALTER TABLE Questionare ADD CONSTRAINT Questionare_PK PRIMARY KEY ( ID_QUESTIONNAIRE ) ;


CREATE TABLE Service
  (
    ID_SERVICE                   INTEGER NOT NULL ,
    Name                         VARCHAR (50) ,
    Quality_Score                REAL
  ) ;
ALTER TABLE Service ADD CONSTRAINT Service_PK PRIMARY KEY ( ID_SERVICE ) ;


ALTER TABLE Criteria ADD CONSTRAINT Criteria_Criteria_Ratio_FK FOREIGN KEY ( Criteria_Ratio_ID_CR ) REFERENCES Criteria_Ratio ( ID_CR ) ;

ALTER TABLE Question ADD CONSTRAINT Question_Answer_FK FOREIGN KEY ( Answer_ID_ANSWER ) REFERENCES Answer ( ID_ANSWER ) ;

ALTER TABLE Question ADD CONSTRAINT Question_Criteria_FK FOREIGN KEY ( Criteria_ID_CRITERIA ) REFERENCES Criteria ( ID_CRITERIA ) ;

ALTER TABLE Question ADD CONSTRAINT Question_Questionare_FK FOREIGN KEY ( Questionare_ID_QUESTIONNAIRE ) REFERENCES Questionare ( ID_QUESTIONNAIRE ) ;

ALTER TABLE Questionare ADD CONSTRAINT Questionare_Service_FK FOREIGN KEY ( Service_ID_SERVICE ) REFERENCES Service ( ID_SERVICE ) ;

-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             11
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

INSERT INTO public.criteria_ratio(
            id_cr, criteria_value)
    VALUES (1, 0.10);
INSERT INTO public.criteria_ratio(
            id_cr, criteria_value)
    VALUES (2, 0.20);
INSERT INTO public.criteria_ratio(
            id_cr, criteria_value)
    VALUES (3, 0.20);
INSERT INTO public.criteria_ratio(
            id_cr, criteria_value)
    VALUES (4, 0.40);
INSERT INTO public.criteria_ratio(
            id_cr, criteria_value)
    VALUES (5, 0.10);

INSERT INTO public.criteria(
            id_criteria, name, criteria_ratio_id_cr)
    VALUES (1, 'Solidno??', 1);
INSERT INTO public.criteria(
            id_criteria, name, criteria_ratio_id_cr)
    VALUES (2, 'Zdolno?? reagowania', 2);
INSERT INTO public.criteria(
            id_criteria, name, criteria_ratio_id_cr)
    VALUES (3, 'Empatia', 3);
INSERT INTO public.criteria(
            id_criteria, name, criteria_ratio_id_cr)
    VALUES (4, 'Pewno??', 4);
INSERT INTO public.criteria(
            id_criteria, name, criteria_ratio_id_cr)
    VALUES (5, 'Materialno??', 5);


INSERT INTO public.service(
            id_service, name,quality_score)
    VALUES (1, 'Us?uga 1', 0);
INSERT INTO public.service(
            id_service, name, quality_score)
    VALUES (2, 'Us?uga 2', 0);

INSERT INTO public.service(
            id_service, name, quality_score)
    VALUES (3, 'Us?uga wytwarzania oprogramowania', 0);


INSERT INTO public.questionare(
            id_questionnaire, name, service_id_service)
    VALUES (1, 'Kwestionaiursz 1', 1);
INSERT INTO public.questionare(
            id_questionnaire, name, service_id_service)
    VALUES (2, 'Kwestionaiursz 2', 2);

INSERT INTO public.questionare(
            id_questionnaire, name, service_id_service)
    VALUES (3, 'Kwestionaiursz dotycz?cy ?wiadczenia us?ug przez firm? tworz?c? oprogramowanie 1', 3);
INSERT INTO public.questionare(
            id_questionnaire, name, service_id_service)
    VALUES (4, 'Kwestionaiursz dotycz?cy ?wiadczenia us?ug przez firm? tworz?c? oprogramowanie 2', 3);


INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (1, 'Pytanie 1', 1, 1, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (2, 'Pytanie 2', 2, 1, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (3, 'Pytanie 3', 3, 1, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (4, 'Pytanie 4', 4, 1, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (5, 'Pytanie 5', 5, 1, 
            null);

INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (6, 'Oprogramowanie powinno spe?nia? za?o?enia Specyfikacji Wymaga?', 1, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (7, 'Oprogramowanie powinno by? pozbawione wszelakich b??d�w i luk', 1, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (8, 'Us?ugodawca nie powinien ukrywa? b??d�w i wad oprogramowania', 1, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (9, 'Oprogramowanie powinno zosta? wykonane w miar? mo?liwo?ci w najnowszych dost?pnych technologiach', 1, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (10, 'Oprogramowanie powinno zosta? wykonane do za?o?onego w harmonogramie terminu', 1, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (11, 'Us?ugodawca powinien reagowa? na sugestie klienta w kwesti realizacji us?ugi', 2, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (12, 'Us?guodawca powinien pilnowa? dotrzymywania terminu oddania oprogramowania', 2, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (13, 'Us?ugodawca powinien s?u?y? pomoc? klientowi w czasie realizowania us?ugi', 2, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (14, 'Us?ugodawca powinien reagowa? na sygna?y niezadowolenia ze strony klienta', 2, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (15, 'Us?ugodawca powinien mie? odpowiednie podej?cie do klienta', 3, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (16, 'Us?ugodawca powinien rozumie? potrzeby i oczekiwania klienta', 3, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (17, 'Us?ugodawca powinien wczuwa? si? w podej?cie klienta', 3, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (18, 'Us?ugodawca powinien idywidualnie podchodzi? do klienta', 3, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (19, 'Programi?ci powinni mie? odpowiednie kwalifikacje', 4, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (20, 'Programi?ci powinni mie? odpowiedni? wiedz? merytoryczn? w zakresie wykonywanej pracy', 4, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (21, 'Pracownicy powinni odpowiednio zachowywa? si? w obecno?ci klienta', 4, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (22, 'Pracownicy powinni fachowo podchodzi? do sprawy realizacji projektu', 4, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (23, 'Biuro powinno by? reprezentacyjne', 5, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (24, 'Pracownicy powinni wygl?da? schludnie', 5, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (25, 'Materia?y reklamowe firmy powinny przykuwa? oko', 5, 3, null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (26, 'Zachowanie oraz prezencja pracownik�w powinny kszta?towa? wizrerunek firmy', 5, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (27, 'Firma powinna by? podzielona na odpowiednie dzia?y', 5, 3, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (28, 'Oprogramowanie spe?nia za?o?enia Specyfikacji Wymaga?', 1, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (29, 'Oprogramowanie pozbawione jest wszelakich b??d�w i luk', 1, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (30, 'Us?ugodawca nie ukry? ?adnych b??d�w i wad oprogramowania', 1, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (31, 'Oprogramowanie zosta?o wykonane w miar? mo?liwo?ci w najnowszych dost?pnych technologiach', 1, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (32, 'Oprogramowanie zosta?o wykonane do za?o?onego w harmonogramie terminu', 1, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (33, 'Us?ugodawca reagowa? na sugestie klienta w kwesti realizacji us?ugi', 2, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (34, 'Us?guodawca pilnowa? dotrzymywania terminu oddania oprogramowania', 2, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (35, 'Us?ugodawca s?u?y? pomoc? klientowi w czasie realizowania us?ugi', 2, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (36, 'Us?ugodawca reagowa? na sygna?y niezadowolenia ze strony klienta', 2, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (37, 'Us?ugodawca mia? odpowiednie podej?cie do klienta', 3, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (38, 'Us?ugodawca zrozumia? potrzeby i oczekiwania klienta', 3, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (39, 'Us?ugodawca wczu? si? w podej?cie klienta', 3, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (40, 'Us?ugodawca idywidualnie podchodszed? do klienta', 3, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (41, 'Programi?ci maj? odpowiednie kwalifikacje', 4, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (42, 'Programi?ci maj? odpowiedni? wiedz? merytoryczn? w zakresie wykonywanej pracy', 4, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (43, 'Pracownicy odpowiednio zachowuj? si? w obecno?ci klienta', 4, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (44, 'Pracownicy fachowo podchodz? do sprawy realizacji projektu', 4, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (45, 'Biuro jest reprezentacyjne', 5, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (46, 'Pracownicy wygl?daj? schludnie', 5, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (47, 'Materia?y reklamowe firmy przykuwaj? oko', 5, 4, null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (48, 'Zachowanie oraz prezencja pracownik�w kszta?tuj? pozytywny wizrerunek firmy', 5, 4, 
            null);
INSERT INTO public.question(
            id_question, description, criteria_id_criteria, questionare_id_questionnaire, 
            answer_id_answer)
    VALUES (49, 'Firma jest podzielona na odpowiednie dzia?y', 5, 4, null);

