📝 Descrição do Projeto
API RESTful para gerenciamento de tarefas (To-Do List) desenvolvida com Spring Boot, seguindo as melhores práticas de desenvolvimento Java.

🚀 Funcionalidades
✅ Criação, leitura, atualização e exclusão de tarefas

🔍 Filtragem por status, prioridade e intervalo de datas

🏷️ Categorização por prioridade (BAIXA, MÉDIA, ALTA)

📅 Data de criação automática

🔄 Atualização parcial de tarefas

🛠️ Tecnologias Utilizadas
Java 17

Spring Boot

Spring Data JPA

H2 Database (em memória para desenvolvimento)

Lombok (para redução de boilerplate code)

MapStruct (para mapeamento DTO-Entity)

Validation API (para validação de dados)

📋 Estrutura do Projeto
src/
├── main/
│   ├── java/
│   │   └── com/To_Do_List/
│   │       ├── config/       # Configurações da aplicação
│   │       ├── controller/   # Endpoints da API
│   │       ├── dto/          # Objetos de transferência de dados
│   │       ├── exception/    # Tratamento de exceções
│   │       ├── model/        # Entidades JPA
│   │       ├── repository/   # Interfaces de repositório
│   │       ├── service/      # Lógica de negócio
│   │       └── DemoApplication.java
│   └── resources/
│       ├── application.yml   # Configurações da aplicação
│       └── data.sql         # Dados iniciais (opcional)
└── test/                    # Testes automatizados

🔌 Endpoints da API
Método	Endpoint	Descrição
POST	/tasks	Cria uma nova tarefa
GET	/tasks	Lista todas as tarefas
GET	/tasks/{id}	Obtém uma tarefa específica
PUT	/tasks/{id}	Atualiza uma tarefa
DELETE	/tasks/{id}	Remove uma tarefa
GET	/tasks/filter	Filtra tarefas por critérios

🏗️ Como Executar o Projeto
Pré-requisitos
JDK 17+
Maven 3.8+
(Opcional) Docker para banco de dados externo

Passo a Passo
1-Clone o repositório:
git clone https://github.com/JoelSilva2002/to-do-list-api.git
2-Navegue até o diretório do projeto:
cd to-do-list-api
3-Execute a aplicação:
mvn spring-boot:run
Acesse a API em:
http://localhost:8080

🧪 Testando a API
Com HTTPie (recomendado)

# Criar tarefa
http POST :8080/tasks titulo="Estudar Spring" descricao="Estudar Spring Boot" prioridade="ALTA"

# Listar tarefas
http GET :8080/tasks

# Atualizar tarefa
http PUT :8080/tasks/1 status="EM_ANDAMENTO"

# Deletar tarefa
http DELETE :8080/tasks/1

🧠 Diagrama de Classes
classDiagram

    class Tasks {
        +Long id
        +String titulo
        +String descricao
        +LocalDateTime dataCriacao
        +Status status
        +Prioridade prioridade
    }
    
    class TaskDTO {
        +Long id
        +String titulo
        +String descricao
        +Prioridade prioridade
    }
    
    class TaskService {
        +criarTarefa(TaskDTO)
        +listarTarefas()
        +buscarTarefaPorId(Long)
        +atualizarTarefa(Long, TaskDTO)
        +deletarTarefa(Long)
    }
    
    class TaskController {
        +criarTarefa(TaskDTO)
        +listarTarefas()
        +buscarTarefa(Long)
        +atualizarTarefa(Long, TaskDTO)
        +deletarTarefa(Long)
    }
    
    class Repositorio {
        +findByStatus(Status)
        +findByPrioridade(Prioridade)
        +findByDataCriacaoBetween(LocalDateTime, LocalDateTime)
    }
    
    Tasks "1" -- "*" TaskDTO : Mapeia para
    TaskService ..> Repositorio : Usa
    TaskController ..> TaskService : Usa
    
