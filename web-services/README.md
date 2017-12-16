# Kotlin Web Services 

This module uses [Ktor](http://ktor.io) and [Retrofit](http://square.github.io/retrofit/).

## Usage

Start the server with:
```bash
$ java -jar web-services/target/customer-server-jar-with-dependencies.jar
```
 
### CLI Calls

Say hello with:
```bash
$ http :8080/plain-hello
$ http :8080/html-hello
```

Query all customers with:
```bash
$ http :8080/customers
```

Query customers by id with:
```bash
$ http :8080/customers/1
$ http :8080/customers/2
$ http :8080/customers/3
```

Query customers by name with:
```bash
$ http :8080/customer_query?name=Bill
```

Add values via POST with:
```bash
$ http --form :8080/customers name='Joe Jackson' 
$ http :8080/customers
$ http --form :8080/customers name='Jill West' address='456 Sycamore Lane'
$ http :8080/customers
```

### Programatic Calls

Query all customers with:
```bash
$ java -jar target/all-customers-jar-with-dependencies.jar
```

Query customers by id with:
```bash
$ java -jar target/by-id-jar-with-dependencies.jar -i 1
$ java -jar target/by-id-jar-with-dependencies.jar -i 2
$ java -jar target/by-id-jar-with-dependencies.jar -i 3
```

Query customers by name with:
```bash
$ java -jar target/by-name-jar-with-dependencies.jar -n Bill
```

Create a new customer with:
```bash
$ java -jar target/create-customer-jar-with-dependencies.jar -n "Mike Bryant" -a "1831 Dupont St"
```

