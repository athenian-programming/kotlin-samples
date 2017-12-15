# Web Services Demo

## Usage

Start the server with:
```bash
$ cd ~/git/kotlin-web-services
$ python customer_server.py
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
$ cd ~/git/web-services-demo
$ ./all_customers.py
```

Query customers by id with:
```bash
$ cd ~/git/web-services-demo
$ ./customer_by_id.py -i 1
$ ./customer_by_id.py -i 2
$ ./customer_by_id.py -i 3
```

Query customers by name with:
```bash
$ cd ~/git/web-services-demo
$ ./customer_by_name.py -n Bill
```

Create a new customer with:
```bash
$ cd ~/git/web-services-demo
$ ./create_customer.py -n "Mike Bryant" -a "1831 Dupont St"
```

