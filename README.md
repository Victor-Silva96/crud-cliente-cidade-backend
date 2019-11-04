# crud-cliente-cidade-backend
Crud realizado utilizando spring boot para clientes e cidades.

# Banco de dados utilizado

  Nessa atividade foi utilizado o banco de dados MySql, para persistência dos dados(insert,update,read e delete),
Favor configurar a porta e alterar se necessario o usuario e senha que fica no arquivo crud-cliente-cidade-backend\crud-clientes-cidades-backend\src\main\resources\application.properties, la está setada algumas configurações como user name , password e a url do datasource,
caso utilize outro banco de dados, so será necessario incluir a url do novo banco e usuario e senha utilizados.

 Também caso precise foi realizado o export do schema do banco utilizado para o desenvolvimento na pasta: /Schema/SCHEMA_CLIENTE_CIDADE_EXPORT.sql
 
 
 # Validações 
 
  Foi incrementado algumas validações como todos os campos tanto da cidade como do cliente são obrigátorios, o formato da data na parte de adicionar o cliente que tem que ser dd/MM/yyyy, o tipo de sexo aceitado pelo cliente so pode ser masculino ou feminino, e a cidade que pertence ao cadastro do cliente precisa existir na base de cidades para o cliente poder ser adicionado e a idade do cliente tem que ser acima de 0.
  
  
  # Testes unitários
  
   Foi desenvolvido testes unitarios com Junit 5, Tanto para o Service Controller de cidade como o de cliente.
