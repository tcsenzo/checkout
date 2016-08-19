## How to setup

- Import the project as an Existing Maven Project at eclipse
- Import base.sql into your checkout database:

```bash
mysql -u root checkout < base.sql
``` 

- Copy the application.properties.sample:

```bash
cp src/main/resources/application.properties.sample src/main/resources/application.properties
```

- Set the amazon properties


## How to run

- If you have the project imported, just run the class com.senzo.qettal.checkout.CheckoutApplication
- If you don't, run 'mvn spring-boot:run' at your terminal

## How to test

 If you don't have an application to test with, use curl sending a json with the structure provided bellow:


## User

### How to create one

- Json template:

```json
{
	"name": "Leonardo",
	"email": "leo@leo.com",
	"password": "123"
}
```

Example: 

```bash
 curl -H "Content-Type:application/json" -X POST http://localhost:8082/users --data "{\"name\": \"Leonardo\", \"email\": \"leo@leo.com\", \"password\": \"123\"}"
```

Possible responses:

- 202 - User created
- 400 - Invalid or insufficient data


## Login

After you create an user, you have to log in to be able to access any other resources

## How to

Example:

```bash
curl -X POST -c /tmp/cookies.txt  http://localhost:8082/login -d email=leo@leo.com -d password=123
```

This will create a file with your cookies at /tmp/cookies.txt, you will need to send this file in each request to prove you are authenticated

**All endpoints marked with (REQUIRES LOGIN) bellow will return 401 if you are not logged in**

## Purchases

### How to create one (REQUIRES LOGIN)

- Json template:

```json
{
	"event_id": 1,
	"items": [
		{
			"quantity": 2,
			"ticket_type": "HALF" //Ignored for now
		},
		{
			"quantity": 3,
			"ticket_type": "FULL"  //Ignored for now
		}
	]
}
```

Example:

```bash
curl -b /tmp/cookies.txt -H "Content-Type:application/json" -X POST http://localhost:8082/purchases --data "{\"event_id\": 1, \"items\": [{ \"quantity\": 2, \"ticket_type\": \"HALF\"}, { \"quantity\": 3, \"ticket_type\": \"FULL\"}]}"
```

Possible responses:

- 202 - Purchase created
- 400 - Invalid or insufficient data
- 409 - Event not available

## Payments

### How to create one (REQUIRES LOGIN)

- Json template:

```json
{
	"credit_card_hash": "GMgxgzN3gg+wCGliLgKFR/fUaAPZH8sq/NJlZkF3D69xL0uUKsak4KLGDNms+6QG9Oc7PMh5J4FD53tna8Xr9bLotrVdcle9Gr+ORl/qdx3DraW8YP4k+aGiSOHD250rm4LVdkSMT0za8JAUEbINy6mpgORDsMXLwUJs4ExdwI4WDbMow8gk1p0yWx2ldVBuNZVC+PtuLWulE+zg56X0crs5IaEPfg2XucSNBQEy5GeMPZcZ/meJO4G+KfvZ0pMnxcV0Dmx2CXxi9qLRFlJrmoSFkqeqVFNZbmtQhqdAmvRGOqJX+d8nzhWepOiT3JBkSmkAgLpQeYDGu5MhgI2AXg==",
	"full_name": "Leonardo Cesar Wolter",
	"birth_date": "18/07/1994",
	"phone_area_code": "11",
	"phone": "99999999",
	"cpf": "111.111.111-11",
	"purchase_id": "1"
}
```

Example:

```bash
curl -b /tmp/cookies.txt -H "Content-Type:application/json" -X POST http://localhost:8082/payments --data "{\"credit_card_hash\":\"GMgxgzN3gg+wCGliLgKFR/fUaAPZH8sq/NJlZkF3D69xL0uUKsak4KLGDNms+6QG9Oc7PMh5J4FD53tna8Xr9bLotrVdcle9Gr+ORl/qdx3DraW8YP4k+aGiSOHD250rm4LVdkSMT0za8JAUEbINy6mpgORDsMXLwUJs4ExdwI4WDbMow8gk1p0yWx2ldVBuNZVC+PtuLWulE+zg56X0crs5IaEPfg2XucSNBQEy5GeMPZcZ/meJO4G+KfvZ0pMnxcV0Dmx2CXxi9qLRFlJrmoSFkqeqVFNZbmtQhqdAmvRGOqJX+d8nzhWepOiT3JBkSmkAgLpQeYDGu5MhgI2AXg==\",\"full_name\":\"Leonardo Cesar Wolter\",\"birth_date\":\"1994-07-18\",\"phone_area_code\":\"11\",\"phone\":\"99999999\",\"cpf\":\"111.111.111-11\",\"purchase_id\":\"1\"}" -i
```

Possible responses:

- 202 - Payment created
- 400 - Invalid or insufficient data
- 404 - Purchase not found


### How to simulate moip callbacks

Setting payment status to APPROVED:

Example

```bash
curl -X POST http://localhost:8082/payments/moip/callback -d id_transacao=<purchase_unique_id> -d status_pagamento=1
```

Possible responses:

- 200 - Ok
- 404 - Purchase or payment not found


## Tickets

### How to create one

You have to send the moip callback above to confirm your payment.
If you do this, your ticket will be generated automatically

### How to get a specific event (REQUIRES LOGIN)


Example

```
curl -b /tmp/cookies.txt -H "Content-Type:application/json" http://localhost:8082/tickets/0d63f1d98c1d0e718a058ca57add34555e15a56dadbee5c1d4eb6ada69c70035
```

The response will be a json in as the one bellow:

```json
{
    "event": {
        "name": "Evento maroto",
        "price": 22.0,
        "scheduled_date": "2017-12-03T10:15:30",
        "theater": {
            "address": {
                "city": "S\u00e3o Paulo",
                "country": "Brasil",
                "district": "Vila Olimpia",
                "number": "360",
                "state": "SP",
                "street": "Rua Olimp\u00edadas",
                "zip_code": "04551-000"
            },
            "name": "Teatro NET SP"
        }
    },
    "qrcode_url": "http://dev.qettal.tickets.s3.amazonaws.com/0d63f1d98c1d0e718a058ca57add34555e15a56dadbee5c1d4eb6ada69c70035.png",
    "user": {
        "name": "Leonardo"
    }
}

```