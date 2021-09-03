### IAM-Client service motivation
IAM-Client service is an IAM's client side application used to generate IAM login url using the client certificates. 
and its main motivation is to ease the intgration process to IAM service.

### Technologies 
* Spring boot
* Spring web
* Lombok
* Java key store

### How to run IAM-Client service
In order to use the service properly we need to fill some properties from [application.properties](https://github.com/yyagoub/iam-client/blob/main/src/main/resources/application.properties iam-client/src/main/resources/application.properties) file:

#### To build IAM URL, search for each key in the specified file and fill it with the proper value.

property | key | value explain
--- | --- | ---
host | 'iam.request.url.host' | check if you are using the staging or production host.
client-id | 'iam.request.url.client-id' | it is the *reference number* giving to the client .
redirect-uri | 'iam.request.url.redirect-uri' | *redirect-uri is a static*, please use the submitted redirect-uri to the service provider, **without queries or extra path**.
