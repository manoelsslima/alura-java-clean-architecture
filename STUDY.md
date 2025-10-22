# Learning

# Understanding Concepts

### In Domain-Driven Design (DDD), two important concepts are Entity and Value Object.

#### Domain package
Entity: means that its identity is based on a unique identifier (CPF in this case).
JPA entities are extracted as domain entities because they represent core business concepts that have a distinct identity.
Value Object: means that its identity is based on (all) its attributes rather than a unique identifier.

#### Application package
Usecases: Application services that orchestrate the use of domain entities and value objects to perform specific business operations.
Services are extracted to use cases to encapsulate business logic and ensure that each operation is clearly defined.


Each use case represents a specific action that the application can perform, such as creating a new user, listing users, or updating user information.

Gateways: Interfaces that define how the application interacts with external systems or services, such as databases or APIs.