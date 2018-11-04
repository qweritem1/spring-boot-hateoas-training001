## Model
#### Customer Model
+ String customerId
+ String customerName
+ String companyName
+ Map orders

##### Order Model
+ String orderId
+ double price
+ int quantity


## Service

##### Customer Service
+ List<Customer> allCustomers
+ Customer getCustomerDetail(String customerId)

###### Customer Service Impl
|customerId|customer_name|company_name|
|---|---|---|
|cust001|John|Samsung|
|cust001|Mike|LG CNS|
|cust001|David|SK Planet|


##### Order Service
+ List<Order> getAllOrdersForCustomer(String customerId)
+ Order getOrderByIdForCustomer(String customerId, String orderId)

###### Order Service Impl
|customerId|customer_name|company_name|
|---|---|---|
|cust001|John|Samsung|
|cust001|Mike|LG CNS|
|cust001|David|SK Planet|

| customer_id | order_id | price | quantity |
|-------------|-----------|-------|----------|
| cust001 | order_001 | 100.0 | 10 |
| cust001 | order_002 | 200.0 | 20 |
| cust002 | order_003 | 300.0 | 30 |
| cust002 | order_004 | 400.0 | 40 |


## Controller

##### CustomerController
| url | Func_name | parameter | return |
|-------------------------|----------------------|---------------------|---------------------|
| /customers | * base * |  |  |
| /{customerId} | getCustomerById | customerId | Customer |
| /{customerId}/{orderId} | getOrderId | customerId, orderId | Order |
| /{customerId}/orders | getOrdersForCustomer | customerId | Resources<Order> |
| /customers | getAllCustomers |  | Resources<Customer> |


