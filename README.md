# pessoa-cadastro                
💻 Sobre o projeto
Permite cadastrar pessoas e adicionar endereços a ela.


⚙️ Funcionalidades                                        
Pessoa
  * Cadastrar novas pessoas;
  * Buscar lista de pessoas cadastradas;
  * Buscar lista de endereços de uma pessoa;
  * Buscar pessoa cadastrada pelo Id;
  * Alterar pessoa pelo Id;
  * Deletar pessoa pelo Id;

Endereço
  * Cadastrar um novo endereço para uma pessoa;
  * Colocar um endereço como principal; 
  * Deletar um endereço através do id;



🛠 Tecnologias e padrão utilizadas
* Arquitetura padrão MVC;
* Spring Data;
* Java 11;
* Maven;
* Docker;
* Flyway;
* Postgres;
* Lombok;
* Swagger;


🧭 Rodando a aplicação
Clonar o repositório do projeto;

Importar o projeto na IDE de sua preferência;


Dentro do diretório do projeto, buildar com o Maven mvn clean install.                         
O projeto baixará as dependências necessárias e buildará com sucesso.                               
Caso não complete com sucesso, verifique o log do build para encontrar possíveis erros.                     
Configurar o DB Postgres localmente com o Docker Com o terminal dentro do diretório do projeto,                    
executar o docker-compose abaixo docker-compose up. 
Conectar-se ao DB com o manager de sua preferência. 
O usuário e senha do DB podem ser consultados no arquivo application.yml                                            
Documentação da API A documentação da API é feita através do swagger,                             
e quando a aplicação estiver rodando em ambiente local você pode acessá-la pelo link :
http://localhost:8080/swagger-ui.html#                              
                                 
💡 Regras de negócio implementadas
Endereço
* Apenas um endereço principal é permitido por pessoa;
