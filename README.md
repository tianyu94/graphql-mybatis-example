<http://localhost:8080/graphiql>

request:

```
{
  userById(id: 1) {
    id
    name
    age
    email
  }
}
```

response:

```
{
  "data": {
    "userById": {
      "id": "1",
      "name": "Jone",
      "age": 18,
      "email": "test1@example.com"
    }
  }
}
```
