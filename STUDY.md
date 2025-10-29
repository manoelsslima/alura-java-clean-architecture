# Learning

# Understanding Concepts

### In Domain-Driven Design (DDD), two important concepts are Entity and Value Object.

#### Domain package
Entity: means that its identity is based on a unique identifier (CPF in this case).
JPA entities are extracted as domain entities because they represent core business concepts that have a distinct identity.
Value Object: means that its identity is based on (all) its attributes rather than a unique identifier.

#### Application package
Usecases: Application services that orchestrate the use of domain entities and value objects to perform specific business operations.
Operations like creating, listing, and updating users are implemented as use cases.
`Services` are extracted to use cases to encapsulate business logic and ensure that each operation is clearly defined.

Each use case represents a specific action that the application can perform, such as creating a new user, listing users, or updating user information.
Gateways: Interfaces that define how the application interacts with external systems or services, such as databases or APIs.

Gateways, no pacote application, são interfaces que representam dependências externas dos casos de uso (ports de saída) e
ficam sob controle da camada de aplicação, não da infraestrutura. Eles descrevem `o quê` o caso de uso precisa para
conversar com repositórios, mensagerias, serviços externos, mas sem expor `como` isso é feito pela tecnologia.

São definidos no pacote application, implementados no pacote de infraestrutura por adapters tecnológicos (JPA, HTTP, Kafka, Redis);
Referenciados pelos casos de uso, via injeção de dependência. O core conhece apenas a interface.
Modelam operaçoes em termos do domínio (entidades/VOs ou DTOs de aplicação), não tipos de frameworks ou tecnologiass

#### Infrastructure package
Persistence: interfaces that implement the gateways defined in the application layer.

Gateways implementations are placed in the infrastructure layer because they deal with technical details of data storage and retrieval, which are outside the core business logic.


domain
v
application
v
infrastructure



ports: interfaces
adapters: implementações

domain
1) entities: entidade com identificador único. É o caso, por exemplo, de uma pessoa, onde o CPF é único para cada pessoa. não é um modelo anêmico, pois contém regras para sua criação e eventuais exceções. somente java puro, getters and setters

2) value objects: sua identidade é baseada na junção de todos os seus atributos, ao invés de um único identificaodr. É o caso, por exemplo, de um endereço, que é formado pela junção da rua, número e complemento.

application
3) use cases: são as operações que o sistema deve realizar, como adicionarUsuario, listarPessoas, deletarPedido, atualizarStatus. Ela depende apenas da interface definida no pacote gateway (gateway, port, out). É o equivalente à classe de implementação da interface de serviço (ex.: UsuarioServiceImpl). Nesse pacote, cada método do service é implementado em sua própria classe. Ex.: cadastrarUsuario vir um usecase. listarUsuarios vira outra classe (use case).

4) gateway: interface que contém as assinaturas dos métodos utilizados nos casos de uso. É o equivalente à interface de service. (Ex.: UsuarioService). A implementação dos gateways do pacote application é feita no pacote infra.

infra:
5) persistence: está a interface que extends JpaRepository. É equivalente à interface do pacote repository (Ex.: UsuarioRepository)


Em um sistema que segue os princípios da clean architecture, os pacotes denominados "gateways" podem ser encontrados tanto no pacote de aplicação (application) quanto no pacote de infraestrutura (infra). Embora ambos os tipos de gateways sirvam para lidar com interações externas ao núcleo da aplicação, eles têm propósitos e responsabilidades ligeiramente diferentes.

Vamos explorar cada um deles:
Gateways no pacote de aplicação (application)

Os gateways no pacote de aplicação são responsáveis por definir interfaces abstratas ou contratos para interações externas necessárias para que os casos de uso (use cases) sejam executados. Eles representam a maneira como os casos de uso interagem com o mundo externo e são definidos em termos de operações de alto nível que o sistema precisa realizar.

Por exemplo, um gateway de repositório no pacote de aplicação pode definir uma interface RepositorioDeUsuario com métodos como listarTodos, cadastrarUsuario, excluirUsuario etc. Essa interface representa a maneira como os casos de uso interagem com o armazenamento de dados, mas não especifica a implementação concreta dessas operações.
Gateways no Pacote de Infraestrutura (infra)

Os gateways no pacote de infraestrutura são responsáveis por implementar os contratos definidos pelos gateways no pacote de aplicação. Eles fornecem implementações concretas para as operações definidas nas interfaces de gateway, utilizando tecnologias específicas de infraestrutura, como acesso a bancos de dados, comunicação de rede etc.

Continuando com o exemplo anterior, no pacote de infraestrutura, poderíamos ter uma implementação concreta de RepositorioDeUsuario, usando JDBC, JPA, Hibernate ou qualquer outro framework de persistência. Essa implementação específica é responsável por traduzir as operações de alto nível definidas na interface de gateway em chamadas concretas para o banco de dados, como fizemos com a classe RepositorioDeUsuarioJpa.

Em resumo, os gateways no pacote de aplicação definem contratos abstratos para interações externas, enquanto os gateways no pacote de infraestrutura fornecem implementações concretas desses contratos usando tecnologias específicas de infraestrutura. Essa separação de responsabilidades e níveis de abstração permite que a lógica de negócios da aplicação permaneça independente de detalhes de implementação e facilite a substituição ou modificação das tecnologias de infraestrutura no futuro.

# Clean Architecture - Estrutura de Pacotes

Em um sistema que segue os princípios da Clean Architecture, a estrutura de pacotes é organizada em camadas distintas,
cada uma com responsabilidades específicas. A seguir, descrevo a estrutura típica de pacotes para um sistema simples,
dividido em três camadas principais: domain, application e infrastructure. As camadas só enxergam as camadas internas a
elas, seguindo o modelo de cebola.

## Domain
Começamos pelo **domain**, parte principal do sistema, onde estão as entidades (classes Java) e os value objects.

Ex.: Pessoa (entity) e Endereço (value object).

## Application
Em seguida, passamos para a camada de **application**, onde definimos os casos de uso (use cases), no pacote
**usecases**, que definem a lógica de negócios (regra de negócio) utilizando as entidades e value objects do domain.
Não há preocupação com detalhes técnicos aqui, apenas com as regras de negócio.

Ex.: CadastrarPessoaUseCase, ListarPessoasUseCase.

Também na camada de application, definimos os gateways (interfaces) no pacote **gateways**, que descrevem as operações
necessárias para interagir com sistemas externos, como repositórios de dados. Obs.: Esses gateways são implementados na
camada de infraestrutura.

## Infrastructure
Finalmente, chegamos à camada de infraestrutura (**infrastructure**), no pacote **infra**, onde implementamos os
gateways, no pacote infra/persistence, definidos na camada de application (application/gateway), lidando com detalhes
técnicos como persistência de dados. Essa é a camada que implementa as interfaces das tecnologias específicas, como
JPA, JDBC, Spring Boot JPA, etc. Também criamos a entidade JPA aqui, no pacote infra/persistence, que mapeia a entidade
do domain para a tabela do banco de dados, como PessoaEntity. No pacote infra/gateway, implementamos um mapper, para
converter entre a entidade do domain (Pessoa) e a entidade JPA (PessoaEntity).

Ex.: PessoaRepositoryJpa implementa PessoaRepository (gateway).

O núcleo nunca tem contato com as camadas externas

No controller da camada de infraestrutura, você deve chamar os casos de uso (use cases) definidos na camada de
aplicação (application). Os casos de uso encapsulam a lógica de negócios e são responsáveis por orquestrar as
operações necessárias para atender às solicitações do usuário.

## Config
Nesse pacote, adicionamos as classes de configuração da implementação, como configuração do banco de dados, injeção
de dependências, etc., tudo que não pôde ser aproveitado do framework utilizado (Spring Boot, por exemplo).

pacotebase
|-- domain
|   |-- entities
|   |   `-- Pessoa.java
|   `-- valueobjects
|       `-- Endereco.java
|-- application
|   |-- usecases
|   |   |-- CadastrarPessoaUseCase.java
|   |   `-- ListarPessoasUseCase.java
|   `-- gateways
|       `-- PessoaRepository.java
`-- infrastructure
|   `-- persistence
|   |   |-- PessoaEntity.java
|   |   `-- PessoaRepositoryJpa.java
|   `-- gateway
|   |   `-- PessoaMapper.java
|   |-- controller
|       `-- PessoaController.java
`-- config
    `-- UsuarioConfig.java
