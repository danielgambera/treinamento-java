# Treinamento Java - Indra
Treinamento Java - Indra

# Para rodar a aplicação da avaliação:

1 - o nome do projeto java é AppContato

2 - instalar banco de dados (usando o Docker)

 - baixar imagem:
docker pull postgres 

 - criar volume:
docker volume create postgres_data_itau

 - iniciar e rodar container:
docker run -d --name postgres_itau -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -v postgres_data_itau:/var/lib/postgresql/data -p 5432:5432 postgres:latest

conectar no banco de dados, usuario admin e senha admin e criar o banco de dados itau

3 - no arquivo Insomnia.json estão os scripts do Imsomnia

4 - para swagger, o endereco é http://localhost:8080/swagger-ui/index.html
    

