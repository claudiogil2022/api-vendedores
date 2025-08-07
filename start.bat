@echo off
echo ========================================
echo     INICIANDO API DE VENDEDORES
echo ========================================
echo.

REM Verificar e configurar JDK
echo Verificando JDK...
if not defined JAVA_HOME (
    REM Buscar JDK em locais comuns
    for /d %%d in ("C:\Program Files\Java\jdk*") do (
        if exist "%%d\bin\javac.exe" (
            set "JAVA_HOME=%%d"
            set "PATH=%%d\bin;%PATH%"
            goto jdk_found
        )
    )
    
    for /d %%d in ("C:\Program Files\Eclipse Adoptium\jdk*") do (
        if exist "%%d\bin\javac.exe" (
            set "JAVA_HOME=%%d"
            set "PATH=%%d\bin;%PATH%"
            goto jdk_found
        )
    )
    
    REM Buscar JDK no diretório do usuário
    for /d %%d in ("C:\Users\%USERNAME%\Java\jdk*") do (
        if exist "%%d\bin\javac.exe" (
            set "JAVA_HOME=%%d"
            set "PATH=%%d\bin;%PATH%"
            goto jdk_found
        )
    )
    
    echo ERRO: JDK nao encontrado!
    echo Execute: install-jdk.bat para instalar automaticamente
    pause
    exit /b 1
)

:jdk_found
echo JDK configurado: %JAVA_HOME%

REM Verificar se Maven está instalado
set MAVEN_CMD=.\mvnw.cmd
set MAVEN_FOUND=false

if exist "C:\tools\apache-maven-3.9.4\bin\mvn.cmd" (
    set "PATH=%PATH%;C:\tools\apache-maven-3.9.4\bin"
    set MAVEN_CMD=mvn
    set MAVEN_FOUND=true
    echo Maven instalado encontrado!
) else (
    echo Usando Maven Wrapper...
)

echo.
echo Opcoes de execucao:
echo 1. Compilar, testar e executar (completo)
echo 2. Apenas executar (rapido)
echo 3. Apenas compilar
echo 4. Apenas testar
echo.
set /p option="Escolha uma opcao (1-4): "

if "%option%"=="1" goto full
if "%option%"=="2" goto run_only
if "%option%"=="3" goto compile_only
if "%option%"=="4" goto test_only
goto full

:compile_only
echo.
echo Compilando projeto...
call %MAVEN_CMD% clean compile -q
if errorlevel 1 (
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)
echo Compilacao concluida com sucesso!
pause
exit /b 0

:test_only
echo.
echo Executando testes...
call %MAVEN_CMD% test
if errorlevel 1 (
    echo AVISO: Alguns testes falharam!
) else (
    echo Todos os testes passaram!
)
pause
exit /b 0

:run_only
echo.
echo Iniciando aplicacao diretamente...
echo Acesse: http://localhost:8080/swagger-ui.html
echo Console H2: http://localhost:8080/h2-console
echo Documentacao: http://localhost:8080/api-docs
echo Health Check: http://localhost:8080/actuator/health
echo.
echo Pressione Ctrl+C para parar a aplicacao
echo.
call %MAVEN_CMD% spring-boot:run
exit /b 0

:full
echo.
echo === ETAPA 1: COMPILACAO ===
call %MAVEN_CMD% clean compile -q
if errorlevel 1 (
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)
echo Compilacao OK!

echo.
echo === ETAPA 2: TESTES ===
call %MAVEN_CMD% test -q
if errorlevel 1 (
    echo AVISO: Alguns testes falharam!
    echo Deseja continuar mesmo assim? (s/n)
    set /p choice=
    if /i not "%choice%"=="s" (
        pause
        exit /b 1
    )
) else (
    echo Testes OK!
)

echo.
echo === ETAPA 3: INICIANDO APLICACAO ===
echo.
echo URLs importantes:
echo - Swagger UI: http://localhost:8080/swagger-ui.html
echo - Console H2: http://localhost:8080/h2-console
echo - API Docs:   http://localhost:8080/api-docs
echo - Health:     http://localhost:8080/actuator/health
echo - Metrics:    http://localhost:8080/actuator/metrics
echo.
echo Pressione Ctrl+C para parar a aplicacao
echo ========================================
echo.

call %MAVEN_CMD% spring-boot:run
