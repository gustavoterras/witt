*** README APP WITT ***


WITT siglas de Weather interesting to travel.

Para desenvolver o aplicativo adotei o uso do padrão de projetos MVVM, como qualquer boa arquitetura, o MVVM juntamente com o uso do Android Data Binding me ajudou a separar o código em camadas para que eu possa ter um código melhor estruturado.

A aplicação possui um layout simples e intuitivo, sendo uma activity única que controla um viewpager que navega na vertical, meu preocupei em minimizar os toques para que o usuário tenha uma melhor navegabilidade.

Utilizei a library do Rertrofit para consumir o webservice REST, visando facilitar o acesso a configuração da URL BASE, adicionei uma variável no buildconfig, caso tenha problemas de redirecionamento de url, podes modificar está variável.
