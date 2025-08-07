# 🔧 Correção do Problema de UPDATE com Timestamps

## ❌ **Problema Identificado**
Durante operações de PUT (atualização), o campo `created_at` estava sendo definido como `NULL`, causando erro:
```
NULL not allowed for column "CREATED_AT"
```

## 🔍 **Causa Raiz**
1. O `VendedorEntityMapper.toEntity()` não estava incluindo os campos `createdAt` e `updatedAt`
2. Durante UPDATE, uma nova entidade era criada sem preservar o `createdAt` original
3. O JPA tentava salvar a entidade com `createdAt = null`

## ✅ **Correções Implementadas**

### 1. **VendedorEntityMapper.java**
```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VendedorEntityMapper {
    @Mapping(source = "filialId", target = "filialId")
    VendedorEntity toEntity(Vendedor vendedor);
    
    @Mapping(source = "filialId", target = "filialId")
    @Mapping(target = "filial", ignore = true)
    Vendedor toDomain(VendedorEntity entity);
}
```
**Mudança**: Removidas as anotações que ignoravam `createdAt` e `updatedAt`

### 2. **VendedorRepositoryImpl.java**
```java
@Override
public Vendedor save(Vendedor vendedor) {
    var entity = mapper.toEntity(vendedor);
    
    // Se o vendedor já existe (UPDATE), preservar createdAt
    if (vendedor.getId() != null) {
        var existingEntity = jpaRepository.findById(vendedor.getId());
        if (existingEntity.isPresent()) {
            entity.setCreatedAt(existingEntity.get().getCreatedAt());
        }
    }
    
    var savedEntity = jpaRepository.save(entity);
    var domain = mapper.toDomain(savedEntity);
    
    // Carregar filial se necessário
    if (domain.getFilialId() != null) {
        domain.setFilial(filialService.findById(domain.getFilialId()).orElse(null));
    }
    
    return domain;
}
```
**Mudança**: Adicionada lógica para preservar `createdAt` durante UPDATEs

### 3. **VendedorEntityMapperImpl.java**
```java
@Override
public VendedorEntity toEntity(Vendedor vendedor) {
    // ... código gerado ...
    vendedorEntity.createdAt( vendedor.getCreatedAt() );
    vendedorEntity.updatedAt( vendedor.getUpdatedAt() );
    return vendedorEntity.build();
}
```
**Mudança**: Adicionado mapeamento dos campos de timestamp

## 🚀 **Como Recompilar (Quando Java 17+ estiver disponível)**
```bash
mvn clean compile
```

## ✅ **Resultado Esperado**
- ✅ PUT requests não falharão mais com erro de `created_at` NULL
- ✅ `createdAt` será preservado durante atualizações
- ✅ `updatedAt` será atualizado automaticamente
- ✅ Fluxo assíncrono continua funcionando normalmente

## 🧪 **Para Testar**
1. Criar um vendedor via POST
2. Tentar atualizar via PUT
3. Verificar que não há mais erro de constraint
4. Confirmar que `createdAt` permanece o mesmo e `updatedAt` é atualizado
