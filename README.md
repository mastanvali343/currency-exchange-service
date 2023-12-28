# currency-exchange-service
http://localhost:8000/currency-exchange/from/USD/to/INR 


http://localhost:8000/h2-console/login.jsp?jsessionid=0d61a341aef0315956706f76bd04142

insert into 
currency_exchange(id,currency_from,currency_to,conversion_multiple,port) 
values(1001,'USD','INR',80,'');

insert into 
currency_exchange(id,currency_from,currency_to,conversion_multiple,port) 
values(1002,'EUR','INR',75,'');

insert into 
currency_exchange(id,currency_from,currency_to,conversion_multiple,port) 
values(1003,'AUD','INR',78,'');

Circuit breaker
==================
1) for testing circuit breaker run the naming server and currency exchange service

2) Take the curl commands from below file and past in windows power shell
G:\workspace\ms2\currency-exchange-service\src\test\resources\api requests for circuit breaker.txt

