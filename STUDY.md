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