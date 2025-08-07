@echo off
echo Iniciando API de Vendedores...
echo.

REM Configurar PATH do Maven se estiver instalado
if exist "C:\tools\apache-maven-3.9.4\bin\mvn.cmd" (
    set "PATH=%PATH%;C:\tools\apache-maven-3.9.4\bin"
    echo Maven encontrado em C:\tools\apache-maven-3.9.4\bin
)

echo Compilando projeto...
call mvnw.cmd clean compile -q
if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo.
echo Executando testes...
call mvnw.cmd test -q
if errorlevel 1 (
    echo Alguns testes falharam!
    echo Deseja continuar mesmo assim? (s/n)
    set /p choice=
    if /i not "%choice%"=="s" (
        pause
        exit /b 1
    )
)

echo.
echo Iniciando aplicacao...
echo Acesse: http://localhost:8080/swagger-ui.html
echo Console H2: http://localhost:8080/h2-console
echo.

call mvnw.cmd spring-boot:run
