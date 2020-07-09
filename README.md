# TransactionRegistry
 Repositorio que maneja las transacciones con la base de datos de Blockchain. La base de datos que se instaló en una máquina virtual y es la que utiliza este servicio, corresponde a [BigchainDB](https://github.com/bigchaindb/bigchaindb#the-basics), que es una iniciativa de código abierto para el manejo de transacciones utilizando Blockchain.

Este servicio provee los siguientes endpoint:

```
Para generación de par de llaves de firmado:
http://host:8080/keygen
```

```
Para creación de transacciones (POST):
http://host:8080/transaction/create
```

```
Para actualización de transacciones - metadata (POST):
http://host:8080/transaction/update
```

```
Obtener transacciones asociadas a etiqueta RFID (GET):
http://host:8080/transaction/rfidTransact/{rfidTag}
```

## Build With

* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SpringBoot](https://spring.io/projects/spring-boot) - Para la construcción de la aplicación
* [SpringBoot initializr](https://start.spring.io/) - Para generación del artefacto

## License

This project is licensed under the Apache License 2.0 License
