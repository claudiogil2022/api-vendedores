# 🚀 API Vendedores - Sistema de Gestão com Processamento Assíncrono

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue)
![H2](https://img.shields.io/badge/Database-H2-lightblue)

Sistema completo para gestão de vendedores com processamento assíncrono, validações robustas e múltiplos padrões de acesso.

## 📋 Funcionalidades

- ✅ **CRUD Completo** - Criar, consultar, atualizar e deletar vendedores
- ✅ **Processamento Assíncrono** - Criação em background com monitoramento
- ✅ **Validações Robustas** - CPF/CNPJ, unicidade, tipos de contratação
- ✅ **Múltiplos Padrões de Acesso** - Por ID, matrícula ou processamento
- ✅ **Geração Automática de Matrícula** - Sequencial com sufixos por tipo
- ✅ **Timestamps Preservados** - Manutenção correta de datas

## 🛠️ Tecnologias

- **Java 17** - LTS com recursos modernos
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistência
- **MapStruct 1.5.5** - Mapeamento objeto-objeto
- **H2 Database** - Banco em memória
- **Swagger/OpenAPI 3** - Documentação
- **JUnit 5** - Testes

## 🚀 Como Executar

```bash
# Clonar o repositório
git clone https://github.com/claudiogil2022/api-vendedores.git
cd api-vendedores

# Executar a aplicação
./mvnw spring-boot:run
```

**Acessos:**
- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console` (user: `sa`, password: vazio)

## 📖 Endpoints Principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/vendedor` | Criar vendedor (assíncrono) |
| GET | `/vendedor/{id}` | Buscar por ID |
| PUT | `/vendedor/{id}` | Atualizar vendedor |
| GET | `/vendedor` | Listar vendedores |
| DELETE | `/vendedor/{id}` | Deletar vendedor |
| GET | `/processamento/{id}` | Status do processamento |
| GET | `/processamento/{id}/vendedor` | Vendedor via processamento |

## 🔄 Fluxo Assíncrono

### 1. Criar Vendedor
```http
POST /vendedor
{
  "nome": "João Silva",
  "dataNascimento": "1990-01-01",
  "documento": "11144477735",
  "email": "joao@email.com",
  "tipoContratacao": "CLT",
  "filialId": "1"
}
```

### 2. Resposta Imediata
```json
{
  "id": "uuid-processamento",
  "status": "PENDENTE",
  "message": "Processamento iniciado"
}
```

### 3. Monitorar Status
```http
GET /processamento/{id}
```

## 🧪 Testes

```bash
# Executar testes
./mvnw test

# Com coverage
./mvnw test jacoco:report
```

## 📁 Estrutura

```
src/
├── main/java/com/desafio/
│   ├── application/     # Casos de uso e DTOs
│   ├── domain/         # Entidades e regras
│   ├── infrastructure/ # Configurações
│   └── ApiVendedoresApplication.java
├── main/resources/
│   └── application.yml
└── test/java/         # Testes
```

## 🎯 Recursos Especiais

- **Preservação de Timestamps** durante UPDATEs
- **Validação CPF/CNPJ** com algoritmos corretos
- **Geração Automática** de matrículas: `00000001-CLT`
- **Múltiplos Acessos** aos mesmos dados
- **Collection Postman** incluída com fluxos automatizados

## 🔧 Configurações

- **Pool Assíncrono**: 5-10 threads, fila de 100
- **Banco H2**: Em memória para desenvolvimento
- **Logs**: Estruturados para debugging

---

**Desenvolvido com ❤️ usando Spring Boot 3 e Java 17**
