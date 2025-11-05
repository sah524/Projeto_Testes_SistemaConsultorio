# 🏥 Sistema de Consultório — Java com JUnit e Maven

Um sistema completo de gerenciamento de **consultas médicas**, desenvolvido em **Java**, com integração ao banco de dados e testes automatizados utilizando **JUnit 5**.
O projeto foi criado com fins acadêmicos e de aprimoramento técnico em **orientação a objetos**, **boas práticas de programação** e **testes de software**.

---

## 🚀 Funcionalidades Principais

O sistema permite realizar o gerenciamento de pacientes e consultas (presenciais e online), com registro direto no banco de dados.

### 🧍 Módulo Paciente

* Cadastro, listagem, atualização e exclusão de pacientes.
* Validação de CPF e telefone.
* Conexão direta com o banco via JDBC.

### 🩺 Módulo Consulta

* Agendamento de consultas **presenciais** ou **online**.
* Geração automática de link de vídeo para consultas online.
* Verificação de número de consultório (0 a 10).
* Atualização e exclusão de consultas já cadastradas.
* Exibição de todas as consultas armazenadas.

### 🔐 Login (Simulado)

* Classe `Usuario` para autenticação de acesso ao sistema (login e senha).

---

## ⚙️ Tecnologias Utilizadas

| Tecnologia              | Descrição                           |
| ----------------------- | ----------------------------------- |
| **Java 17+**            | Linguagem principal do sistema      |
| **JUnit 5**             | Testes automatizados                |
| **Maven**               | Gerenciador de dependências e build |
| **Apache NetBeans IDE** | Ambiente de desenvolvimento         |
| **MySQL / JDBC**        | Banco de dados e conexão            |

---

## 🧪 Testes Automatizados (JUnit)

| Tipo de Teste                   | Classe                                                         | Descrição                                                                               |
| ------------------------------- | -------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Unidade**                     | `UsuarioTest`                                                  | Valida criação e autenticação do usuário.                                               |
| **Unidade / Integração**        | `PacienteTest`                                                 | Testa getters e setters do paciente.                                                    |
| **Integração (Banco de Dados)** | `PacienteMenuTest`                                             | Verifica cadastro, listagem e exclusão de pacientes no banco.                           |
| **Regressão**                   | `ConsultaTest`, `ConsultaOnlineTest`, `ConsultaPresencialTest` | Garante que os métodos principais e mensagens não sejam alterados inadvertidamente.     |
| **End-to-End (E2E)**            | `ConsultaMenu`                                                 | Simula o fluxo completo do sistema: do cadastro de paciente ao agendamento da consulta. |



🧩 **Exemplo de estrutura de teste (JUnit 5)**:

```java
@Test
void testUsuario() {
    Usuario u = new Usuario("admin", "1234");
    assertEquals("admin", u.getLogin());
    assertEquals("1234", u.getSenha());
}
```

---



## 🧰 Banco de Dados

O sistema utiliza MySQL.

## 🧾 Resultados dos Testes

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Tests run: 4
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
-------------------------------------------------------
```

Todos os testes passaram com sucesso ✅
O sistema está **estável**, **funcional** e **conectado ao banco de dados** corretamente.

---

## 👩‍💻 Equipe de Desenvolvimento



**Sarah Silva Gabriel**            
**Lia Costa dos Santos**           
**Tais Barbosa dos Santos**        
**Andressa Maria Pereira de Castro** 

---

## 🔗 Repositório do Projeto

📎 [https://github.com/sah524/Projeto_Testes_SistemaConsultorio.git](https://github.com/sah524/Projeto_Testes_SistemaConsultorio.git)
