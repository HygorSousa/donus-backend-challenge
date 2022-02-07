# Donus Code Challenge - Back-end
### _Feito em Java 11 + SpringBoot_

Para execução do projeto, deve ser executar o `main()` da classe `DonusBackendChallengeApplication`.

O projeto está utilizando o H2 Database em memória, afim de não necessitar a instalação de uma ferramenta. Por padrão o projeto utilizará da porta `8080`, sendo seu endereço `https://localhost:8080/`.

Os testes podem ser realizados através da ferramenta `Insomnia`, na qual pode ser importado o arquivo `donus-code-challenge.json` presente na raiz do projeto para ter acesso a todos os endpoints.

Os endpoints listados abaixo estão em formato aceitos pela aplicação.

- Lista com as contas
```bash
curl --request GET \
  --url http://localhost:8080/conta
```

- Criação de nova conta
```bash
curl --request POST \
  --url http://localhost:8080/conta \
  --header 'Content-Type: application/json' \
  --data '{
	"nomeTitular":"GUSTAVO MEDEIROS",
	"cpf":"84576215487"
}'
```

- Detalhes da Conta
```bash
curl --request GET \
  --url http://localhost:8080/conta/1
```

- Deletar Conta
```bash
curl --request GET \
  --url http://localhost:8080/conta/1
```

- Depositar
```bash
curl --request POST \
  --url http://localhost:8080/transacao/depositar \
  --header 'Content-Type: application/json' \
  --data '{
	"numeroConta":"0001",
	"valor" : 1200
}'
```

- Tranferir entre contas
```bash
curl --request POST \
  --url http://localhost:8080/transacao/transferir \
  --header 'Content-Type: application/json' \
  --data '{
	"numeroContaOrigem" : "0002",
	"numeroContaDestino": "0001",
	"valor" : 150
}'
```