**Closed** -> Não tem problemas, operações ocorrem normalmente.
Todas as requisições passam e são executadas.


**Open** -> Ops, algo deu erro e as requests não são mais executadas para 
o servidor!
Alta taxas de erro!
Evita causar ainda mais stress para o serviço


**Half-Open** -> Acontece depois de um certo periodo estando OPEN,
ele começa a "liberar" algumas requests para o server.
Se tiver sucesso -> CLOSED
Se falhar -> OPEN
