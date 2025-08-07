# 📬 Collection Postman - API Vendedores

## 📁 Arquivos Criados

- **`postman-collection.json`** - Collection completa com todos os endpoints
- **`postman-environment.json`** - Environment com variáveis configuradas

## 🚀 Como Importar no Postman

### 1. **Importar Collection**
1. Abra o Postman
2. Clique em **Import** (botão no canto superior esquerdo)
3. Selecione o arquivo `postman-collection.json`
4. Clique em **Import**

### 2. **Importar Environment**
1. No Postman, clique no ícone de **configurações** (⚙️) no canto superior direito
2. Selecione **Manage Environments**
3. Clique em **Import**
4. Selecione o arquivo `postman-environment.json`
5. Clique em **Import**

### 3. **Ativar Environment**
1. No dropdown no canto superior direito do Postman
2. Selecione **API Vendedores - Environment**

## 📋 Endpoints Incluídos

### 👥 **Vendedores**
- `POST /api/v1/vendedores` - Criar vendedor (assíncrono)
- `GET /api/v1/vendedores` - Listar todos os vendedores
- `GET /api/v1/vendedores?filialId=1` - Listar por filial
- `GET /api/v1/vendedores/{id}` - Consultar por ID
- `GET /api/v1/vendedores/matricula/{matricula}` - Consultar por matrícula
- `GET /api/v1/vendedores/processamento/{processamentoId}` - **🆕 Consultar por processamento**
- `PUT /api/v1/vendedores/{id}` - Atualizar vendedor
- `DELETE /api/v1/vendedores/{id}` - Deletar vendedor

### ⚙️ **Processamentos**
- `GET /api/v1/processamentos/{id}` - Consultar status
- `GET /api/v1/processamentos/{id}/vendedor` - **🆕 Acessar vendedor direto**

### 🏢 **Filiais**
- `GET /api/v1/filiais` - Listar filiais ativas
- `GET /api/v1/filiais/{id}` - Consultar filial por ID

### 📊 **Monitoramento**
- `GET /actuator/health` - Health check
- `GET /actuator/metrics` - Métricas
- `GET /api-docs` - Documentação OpenAPI

## 🎯 **Exemplos Pré-configurados**

### **Criar Vendedores**
- **CLT** - Funcionário com carteira assinada
- **Terceirizado** - Prestador de serviços
- **Pessoa Jurídica** - Empresa (CNPJ)

### **Casos de Erro**
- Dados inválidos (validação)
- Filial inativa
- Documento duplicado

## 🔧 **Variáveis Automáticas**

A collection possui scripts que **capturam automaticamente**:

- **`vendedorId`** - ID do vendedor criado
- **`processamentoId`** - ID do processamento assíncrono

Essas variáveis são usadas automaticamente nos endpoints que precisam de IDs.

## 📝 **Como Usar**

### 🚀 **Fluxos Novos (Recomendados):**

#### **Fluxo 1: Acesso Direto (Mais Simples)**
1. Execute **"🚀 Fluxos Completos → Fluxo 1 → 1. Criar Vendedor"**
2. Aguarde alguns segundos (processamento assíncrono)
3. Execute **"🚀 Fluxos Completos → Fluxo 1 → 2. Acessar Vendedor Direto"**
   - ✅ Se concluído: retorna dados do vendedor
   - ⏳ Se processando: retorna status (execute novamente)

#### **Fluxo 2: Com Monitoramento**
1. Execute **"🚀 Fluxos Completos → Fluxo 2 → 1. Monitorar Processamento"**
2. Repita até status = `CONCLUIDO`
3. Execute **"🚀 Fluxos Completos → Fluxo 2 → 2. Acessar Vendedor Final"**

### 📋 **Fluxo Tradicional:**
1. Execute **"Listar Filiais"** para ver filiais disponíveis
2. Execute **"Criar Vendedor CLT"** 
3. Copie o `processamentoId` da resposta
4. Execute **"Consultar Status do Processamento"**
5. Quando status = `CONCLUIDO`, use o `vendedorId` retornado
6. Execute **"Consultar Vendedor por ID"**

### 2. **Teste de Validações**
- Use os requests da pasta **"Casos de Erro"**
- Veja as mensagens de erro amigáveis

### 3. **Novos Fluxos Simplificados**
- Use a pasta **"🚀 Fluxos Completos"** 
- Scripts automáticos com logs no console
- Apenas 2 requests para todo o processo

### 4. **Monitoramento**
- Execute **"Health Check"** para verificar se a API está funcionando
- Use **"Métricas"** para dados de performance

## 🌟 **Features da Collection**

### ✅ **Testes Automáticos**
- Verifica status codes válidos
- Captura IDs automaticamente
- Logs no console do Postman

### 🔄 **Variáveis Dinâmicas**
- `{{baseUrl}}` - URL base da API
- `{{vendedorId}}` - ID capturado automaticamente
- `{{processamentoId}}` - ID de processamento capturado

### 📱 **Organizada por Funcionalidade**
- Pastas separadas por contexto
- Exemplos específicos para cada tipo
- Casos de teste de erro

## 🚦 **Pré-requisitos**

- Aplicação Spring Boot rodando em `http://localhost:8080`
- Postman instalado
- Java 17+ e Maven configurados

## 🎉 **Pronto para Usar!**

Após importar, você terá acesso completo a todos os endpoints da API de Vendedores, com exemplos práticos e testes automatizados.

---

**💡 Dica:** Execute os requests na ordem sugerida para uma experiência completa da API!
