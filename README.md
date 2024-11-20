Sistema de Autenticação e Gerenciamento de Usuários
Este projeto é um sistema de autenticação e gerenciamento de usuários desenvolvido em Spring Boot com um banco de dados MySQL . 
O sistema permite que os administradores e usuários possam realizar ações como registro, login, visualização, atualização e exclusão de usuários.

Funcionalidades
Cadastro de Usuário : Permite o registro de novos usuários. O sistema verifica se o e-mail já está cadastrado para evitar duplicidade.
Autenticação (Login) : Os usuários podem autenticar o fornecimento de seu e-mail e senha.
Gerenciamento de Usuários : O administrador pode listar todos os usuários, buscar um usuário específico por e-mail, atualizar dados de um usuário 
(como nome, e-mail e senha), e excluir usuários.
Validação de Dados : O sistema realiza validações, como garantir que um e-mail não seja duplicado e que o login seja realizado com credenciais válidas.

Endpoints Disponíveis
POST /users/cadastrar : Cadastro de um novo usuário.
POST /users/entrar : Autenticação do usuário.
GET /users/listar : Lista de todos os usuários.
GET /users/email/{email} : Busca um usuário por e-mail.
DELETE /users/deletar/{id} : Exclui um usuário pelo ID.
PUT /users/atualizar/{id} : Atualiza os dados de um usuário pelo ID.

Tecnologias Utilizadas
Spring Boot : Framework para desenvolvimento de aplicações Java.
JPA/Hibernate : ORM para interação com o banco de dados.
MySQL : Banco de dados relacional para armazenamento de dados dos usuários.


