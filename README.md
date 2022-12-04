# ProshoreTest

## Project info:
Java version : Jdk 1.8
Spring boot version: 2.7.0
Junit5 version: 5.8.2
Database: h2

## Project Setup:
### install jdk 1.8
### install maven
### git clone git@github.com:sarojpurbey324/proshoreTask.git
## Go to project directory then,
### Run -> mvn install
### Run -> mvn spring-boot:run

## Apis:
GET http://localhost:8090/battery?startPostalCode=15000&endPostalCode=50000

POST http://localhost:8090/battery
    with request body
```yaml
[
  {
    "name": "Cannington",
    "postcode": "6107",
    "capacity": 13500
  },
  {
    "name": "Midland",
    "postcode": "6057",
    "capacity": 50500
  },
  {
    "name": "Hay Street",
    "postcode": "6000",
    "capacity": 23500
  },
  {
    "name": "Mount Adams",
    "postcode": "6525",
    "capacity": 12000
  },
  {
    "name": "Koolan Island",
    "postcode": "6733",
    "capacity": 10000
  },
  {
    "name": "Armadale",
    "postcode": "6992",
    "capacity": 25000
  },
  {
    "name": "Lesmurdie",
    "postcode": "6076",
    "capacity": 13500
  },
  {
    "name": "Kalamunda",
    "postcode": "6076",
    "capacity": 13500
  },
  {
    "name": "Carmel",
    "postcode": "6076",
    "capacity": 36000
  },
  {
    "name": "Bentley",
    "postcode": "6102",
    "capacity": 85000
  },
  {
    "name": "Akunda Bay",
    "postcode": "2084",
    "capacity": 13500
  },
  {
    "name": "Werrington County",
    "postcode": "2747",
    "capacity": 13500
  },
  {
    "name": "Bagot",
    "postcode": "0820",
    "capacity": 27000
  },
  {
    "name": "Yirrkala",
    "postcode": "0880",
    "capacity": 13500
  },
  {
    "name": "University of Melbourne",
    "postcode": "3010",
    "capacity": 85000
  },
  {
    "name": "Norfolk Island",
    "postcode": "2899",
    "capacity": 13500
  },
  {
    "name": "Ootha",
    "postcode": "2875",
    "capacity": 13500
  },
  {
    "name": "Kent Town",
    "postcode": "5067",
    "capacity": 13500
  },
  {
    "name": "Northgate Mc",
    "postcode": "9464",
    "capacity": 13500
  },
  {
    "name": "Gold Coast Mc",
    "postcode": "9729",
    "capacity": 50000
  }
]
