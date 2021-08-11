# azure-demo-app

Application uses Azure Storage for storing images and Azure Database for PostgreSQL.
It runs at https://azure-demo-app13.azurewebsites.net/

2 endpoints are available:

POST /api/upload  consumes variables: "file": {actual file, max size 5MB}, "picture" : {"name": "name you gonna use for downloading file"} ;


GET /api/download/{name} returns image