[![Build Status](https://travis-ci.org/maikelfenner/contas.svg?branch=master)](https://travis-ci.org/maikelfenner/contas)


# Bill API

**Heroku URL:** https://contas-backend.herokuapp.com/

### Endpoints:

 - **/bill - POST** - add new bill.

Expected POST object:
```json
{
    "name": "bill 01",
    "value": "100",
    "dueDate": "2019-07-10",
    "paymentDate": "2019-07-15"   
}
```

- **/bill - GET** - return all bills.

> More info: https://contas-backend.herokuapp.com/swagger-ui.html

---

## Frontend - Node + AngularJS

**GitHub:** https://github.com/maikelfenner/contas-frontend

**Heroku URL:** https://contas-frontend.herokuapp.com/

