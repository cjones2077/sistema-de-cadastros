DROP TABLE IF EXISTS agendamento;
DROP TABLE IF EXISTS covid;
DROP TABLE IF EXISTS h1n1;
DROP TABLE IF EXISTS sarampo;
DROP TABLE IF EXISTS campanhas;
DROP TABLE IF EXISTS pacientes;

CREATE TABLE campanhas(
    Sequencial INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NumeroDoses VARCHAR(2) NOT NULL,
    Tipo VARCHAR(15) NOT NULL);

CREATE TABLE covid(
    Marca VARCHAR(15) NOT NULL,
    Reforço BOOLEAN NOT NULL,
    CampanhaID INT NOT NULL,
    FOREIGN KEY (CampanhaID) REFERENCES campanhas(Sequencial));

CREATE TABLE h1n1(
    Cepas INT NOT NULL,
    Emergencial BOOLEAN NOT NULL,
    CampanhaID INT NOT NULL,
    FOREIGN KEY (CampanhaID) REFERENCES campanhas(Sequencial));

CREATE TABLE sarampo(
    Intervalo INT NOT NULL,
    Adjuvante BOOLEAN NOT NULL,
    CampanhaID INT NOT NULL,
    FOREIGN KEY (CampanhaID) REFERENCES campanhas(Sequencial));

CREATE TABLE pacientes(
    Cpf VARCHAR(14) NOT NULL PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    FaixaEt VARCHAR(15) NOT NULL,
    Comorbidade INT NOT NULL,
    Sexo VARCHAR(1) NOT NULL);

CREATE TABLE agendamento(
    Sequencial INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Acompanhante VARCHAR(50), 
    PossuiAcom BOOLEAN NOT NULL,
    PacienteCPF VARCHAR(14) NOT NULL,
    CampanhaID INT NOT NULL,
    Dia INT, Mes INT, Ano INT, 
    Status_ INT NOT NULL,
    FOREIGN KEY (CampanhaID) REFERENCES campanhas(Sequencial),
    FOREIGN KEY (PacienteCPF) REFERENCES pacientes(Cpf));
