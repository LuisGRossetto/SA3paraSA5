
# Título do projeto

Esse projeto foi feito para mostrar a implementação de um projeto utilizando o modelo MVC com Servlet, JSP e SGBD MySQL Server, na aplicação de um sistema de gerenciamento de clientes, podendo editar, listar e excluir registros.

## 🚀 Motivação

Ampliar o aprendizado sobre a implimentação e utilização do modelo MVC com Servlet, JSP e SGBD MySQL Server

### 📋 Status de compilação

O projeto pode acontecer bug se não tiver sido feito as seguintes orienteções:
O mysql tem que ter o schema jdbc_servlet, com o seguinte codigo:
 
CREATE DATABASE `jdbc_servlet`;
USE `jdbc_servlet`;
create table `cliente` (
	matricula INT(10) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	modalidade VARCHAR(200),
	PRIMARY KEY (matricula)
)

Assim como no clienteDAO.java, tem que ser colocado o nome de usuario e a senha do mysql nas linhas 25 e 26


### 🔧 Tecnologia/framework utilizados

Modelo MVC, linguagem Java no back-end (Servlet, JavaBean, DAO), tecnologias no front-end (HTML5, CSS ou Bootstrap, JSP, JSTL), WildFly como servidor de aplicação, persistência de dados utilizando o SGBD MySQL Server.

## ⚙️ Instalação

JAVA instalado;
Eclipse IDE;
WindFLY no Eclipse para servir de server;
MySQL Workbench


## 🖇️ Contribuição

Esse projeto foi possivel ser realizado graças aos ensinamentos passado pelos docentes do SENAI SC

## ✒️ Autores

* **Luis Gustavo Velasque Rossetto** - aluno de desenvolvimento de sistemas do SENAI SC
* **Edinilson da Silva Vida** - docente de desenvolvimento de sistemas do SENAI SC

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE.md](https://github.com/usuario/projeto/licenca) para detalhes.
