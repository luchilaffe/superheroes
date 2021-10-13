# API for superheroes with JWT Security

## First of all, to use it you should add a user with:
* POST /users 
{
    "user": "aUser",
    "pass": "aPassword"
}

## It implements JWT Security, so, you ask one with:
* POST /users/authenticate
{
    "user": "aUser",
    "pass": "aPassword"
}

This will give you a token that you must use to perform your request

## This API for Super Heroes implements: 

* GET /superheroes
* GET /superheroes/search?name={text}

* GET /superheroes/{id}
* DELETE /superheroes/{id}
* PUT /superheroes/{id}
{
    "id": {id},
    "name": "aNewName"
}
