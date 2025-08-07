# Correção para o Erro do PUT Endpoint

## 🚨 Problema Identificado

O endpoint PUT estava retornando erro devido ao MapStruct tentar definir campos obrigatórios como `NULL` durante a atualização.

**Erro original**: `NULL not allowed for column "CREATED_AT"`

## ✅ Solução Aplicada

Foi corrigido o arquivo `VendedorMapper.java` para ignorar corretamente os campos que não devem ser atualizados:

```java
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Mapping(target = "id", ignore = true)
@Mapping(target = "matricula", ignore = true)
@Mapping(target = "filial", ignore = true)
@Mapping(target = "filialId", ignore = true)      // ← ADICIONADO
@Mapping(target = "createdAt", ignore = true)
@Mapping(target = "updatedAt", ignore = true)
void updateFromRequest(AtualizarVendedorRequest request, @MappingTarget Vendedor vendedor);
```

## 📋 Para Aplicar a Correção

### Opção 1: Recompilar via IDE
1. Abra o projeto na sua IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Faça um "Clean and Build" do projeto
3. Execute a aplicação

### Opção 2: Compilar via linha de comando
```bash
# Se você tiver o Maven configurado corretamente:
mvn clean compile spring-boot:run

# Ou usar o wrapper:
./mvnw clean compile spring-boot:run
```

## 🧪 Teste da Correção

Após recompilar, teste o PUT endpoint com:

```json
PUT http://localhost:8080/api/vendedores/1
Content-Type: application/json

{
    "nome": "João Silva Updated",
    "email": "joao.updated@email.com"
}
```

**Resultado esperado**: Status 200 com os dados atualizados, preservando `created_at` original.

## 📊 Endpoints Funcionais

Todos esses endpoints devem estar funcionando após a correção:

- ✅ GET `/api/vendedores` - Listar vendedores
- ✅ GET `/api/vendedores/{id}` - Buscar por ID
- ✅ POST `/api/vendedores` - Criar vendedor (async)
- ✅ PUT `/api/vendedores/{id}` - Atualizar vendedor (CORRIGIDO)
- ✅ DELETE `/api/vendedores/{id}` - Remover vendedor

## 🔧 Coleção do Postman

A coleção completa com 30+ endpoints está disponível em:
- `api-vendedores-postman-collection.json`
- `api-vendedores-postman-environment.json`

Importe no Postman para testar todos os endpoints automaticamente.
