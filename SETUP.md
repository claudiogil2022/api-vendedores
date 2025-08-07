# INSTRUÇÕES DE SETUP - API de Vendedores

## Pré-requisitos

### 1. Java 17 ou superior
Verifique se o Java está instalado:
```bash
java -version
```

Se não estiver instalado, baixe de: https://adoptium.net/

### 2. Git (opcional)
Para clonar o repositório

### 3. IDE (opcional mas recomendado)
- IntelliJ IDEA
- Eclipse
- VS Code com extensão Java

## Instalação e Execução

### Opção 1: Execução Rápida (Windows)
```bash
# 1. Navegue até a pasta do projeto
cd api-vendedores

# 2. Execute o script
run.bat
```

### Opção 2: Execução Manual

```bash
# 1. Compile o projeto
mvnw.cmd clean compile

# 2. Execute os testes (opcional)
mvnw.cmd test

# 3. Execute a aplicação
mvnw.cmd spring-boot:run
```

### Opção 3: Para sistemas Unix/Linux/Mac

```bash
# Torne o wrapper executável
chmod +x mvnw

# Compile e execute
./mvnw clean compile
./mvnw spring-boot:run
```

## Verificação da Instalação

1. **Aplicação rodando**
   - Console deve mostrar: "Started ApiVendedoresApplication"
   - URL: http://localhost:8080

2. **Swagger/OpenAPI**
   - Acesse: http://localhost:8080/swagger-ui.html
   - Deve exibir a documentação da API

3. **Database Console**
   - Acesse: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:vendedores`
   - Username: `sa`
   - Password: (vazio)

4. **Health Check**
   - Acesse: http://localhost:8080/actuator/health
   - Deve retornar: `{"status":"UP"}`

## Teste da API

### 1. Criar um vendedor
```bash
curl -X POST http://localhost:8080/api/v1/vendedores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "dataNascimento": "1990-05-15",
    "documento": "11144477735",
    "email": "joao.silva@email.com",
    "tipoContratacao": "CLT",
    "filialId": "1"
  }'
```

### 2. Consultar processamento
Use o ID retornado na etapa anterior:
```bash
curl http://localhost:8080/api/v1/processamentos/{ID_DO_PROCESSAMENTO}
```

### 3. Listar vendedores
```bash
curl http://localhost:8080/api/v1/vendedores
```

## Estrutura de Pastas

```
api-vendedores/
├── src/main/java/com/desafio/
│   ├── domain/              # Entidades e regras de negócio
│   ├── application/         # Casos de uso e DTOs
│   └── infrastructure/      # Controllers, repos, configs
├── src/test/java/           # Testes
├── pom.xml                  # Configuração Maven
├── README.md                # Documentação principal
├── run.bat                  # Script de execução (Windows)
└── mvnw.cmd                 # Maven Wrapper (Windows)
```

## Problemas Comuns

### Java não encontrado
```
JAVA_HOME not found in your environment
```
**Solução**: Instale Java 17+ e configure JAVA_HOME

### Porta 8080 ocupada
```
Port 8080 was already in use
```
**Solução**: 
- Pare outros serviços na porta 8080
- Ou altere a porta em `application.properties`: `server.port=8081`

### Erro de compilação
```
mvnw.cmd clean compile
```
**Solução**: Verifique se todos os arquivos foram baixados corretamente

## Próximos Passos

1. **Explorar a API**: Use o Swagger UI para testar todos os endpoints
2. **Ver os testes**: Execute `mvnw.cmd test` para rodar a suíte de testes
3. **Monitoramento**: Acesse `/actuator/metrics` para métricas da aplicação
4. **Logs**: Observe os logs no console para acompanhar o processamento

## Suporte

Em caso de problemas:
1. Verifique os logs da aplicação
2. Consulte a documentação no README.md
3. Verifique os pré-requisitos de sistema
