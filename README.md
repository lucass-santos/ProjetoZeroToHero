# ProjetoZeroToHero
Projeto clone da série de vídeo aulas para backend Java

# Product-ms
## Endpoints
- BaseURL: /products
- POST: create()
- GET: getAll()
- GET /{id}: getById()
- PUT /{id}: update()
- DELETE /{id}: delete()

## MODEL
```json
  {
    "id": 1,
    "name": "Iphone 13 Pro Max",
    "description": "Celular de última geração e mais...",
    "price": 6999.90,
    "isAvailable": true
  }
```
## Business Rules
- Só é possível a criação de produtos com preço positivo;
- Não é possívelpesquisar produtos que não estão disponíveis;
- Não é possível inserir descrições com menos de 50 caracteres;
