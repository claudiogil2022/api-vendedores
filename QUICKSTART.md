# 🚀 COMO EXECUTAR A API DE VENDEDORES

## ⚡ Execução Rápida

### Opção 1: Automática (Recomendada)
```bash
# Execute na pasta do projeto:
start.bat
```

### Opção 2: Scripts de Instalação
```bash
# 1. Instalar JDK (se necessário)
install-jdk.bat

# 2. Instalar Maven (se necessário) 
install-maven.bat

# 3. Verificar configuração
setup-jdk.bat

# 4. Executar aplicação
start.bat
```

### Opção 3: Script Original
```bash
run.bat
```

## 🎯 Opções do start.bat

1. **Completo**: Compila, testa e executa
2. **Rápido**: Apenas executa (sem compilar/testar)
3. **Compilar**: Apenas compila o projeto
4. **Testar**: Apenas executa os testes

## 🌐 URLs da Aplicação

Após iniciar, acesse:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs  
- **H2 Console**: http://localhost:8080/h2-console
- **Health Check**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/metrics

## 🔧 Configuração H2 Console

- **JDBC URL**: `jdbc:h2:mem:vendedores`
- **Username**: `sa`
- **Password**: (deixe vazio)

## 📋 Pré-requisitos

- ✅ **JDK 17+** (será instalado automaticamente se necessário)
- ✅ **Maven** (wrapper incluído, ou instalação automática)
- ✅ **Windows** (scripts .bat)

## 🐛 Solução de Problemas

### "JDK não encontrado"
```bash
install-jdk.bat
```

### "Maven não encontrado"  
```bash
install-maven.bat
```

### "Porta 8080 ocupada"
- Pare outros serviços ou altere a porta em `application.properties`

### "Falha na compilação"
- Execute como Administrador
- Verifique se JDK está instalado (não apenas JRE)

## 🧪 Teste Rápido da API

```bash
# Criar vendedor
curl -X POST http://localhost:8080/api/v1/vendedores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "documento": "11144477735",
    "email": "joao@email.com", 
    "tipoContratacao": "CLT",
    "filialId": "1"
  }'

# Listar vendedores
curl http://localhost:8080/api/v1/vendedores
```

## 📞 Parar a Aplicação

- Pressione `Ctrl + C` no terminal
- Ou feche a janela do terminal
