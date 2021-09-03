### Table of content

* [Introduction](../../#Introduction)
* [Configure IAM-Client](../../#configure-iam-client)
* [How to run IAM-Client service](../../#how-to-run-iam-client-service)
* [Functionalities](../../#Functionalities)

## Introduction

### IAM-Client service motivation

IAM-Client service is an IAM's client side application used to generate IAM login url using the client certificates. and its main motivation is to ease the intgration process to IAM service.

### Technologies 

* Java 8
* Spring boot
* Spring web **REST**
* Lombok
* Java key store
* Maven

## Configure IAM-Client

In order to use the service properly we need to fill some properties from [application.properties](https://github.com/yyagoub/iam-client/blob/main/src/main/resources/application.properties "iam-client/src/main/resources/application.properties") file:

#### To build IAM URL, search for each key in the specified file and fill it with the proper value.

In the properties file the client will find a group of properties start with `iam.request.url` this group will help the client build IAM request url. Some of the values already filled the client may keep it as it is.

property | key | value explain
--- | --- | ---
host | `iam.request.url.host` | check if the client are using the staging or production host.
client-id | `iam.request.url.client-id` | it is the *reference number* giving to the client .
redirect-uri | `iam.request.url.redirect-uri` | *redirect-uri is a static*, please use the submitted redirect-uri to the NIC, **without queries or extra path**.

another group will start with `jks.store` this group will help the client refereing to the key store

property | key | value explain
--- | --- | ---
path | `jks.store.path` | the full system path to the key store .
pass | `jks.store.pass` | provide the password for the key store.
store-type | `jks.store.store-type` | specify the key store type. (e.g. `JKS`)

lastly the client is going to refere to the certificates, the last group of properties will start with `certificate.client`, which will help to fetch certificate from the configured key store.

property | key | value explain
--- | --- | ---
private.alias | `certificate.client.private.alias` | refere to the private key alias that giving to the certificate once imported to the key store.
private.password | `certificate.client.private.password` | refere to the private key's password of the certificate.
public.alias | `certificate.client.public.alias` | refere to the public key alias that giving to the certificate once imported to the key store.
public.password | `certificate.client.public.password` | refere to the private key's password of the certificate *if exists*.

#### Once you fill the previous key value pairs you are ready to run the application.

In the following section you will know how to generate IAM url and how to validate it.

## How to run IAM-Client service

* [Linux remote server](https://www.youtube.com/watch?v=W2Ccvpt4C3A&ab_channel=EduonixLearningSolutions)
* TO-DO

## Functionalities

IAM-Client service exposes the following rest endpoints:

### generate iam url

The client can directly generate login url by hitting the rest endpoint:

> GET http://localhost:8088/url

and it will return back login url as string and you may use it to test.

**NOTE:** *In order to access IAM servers the client server need to be white labled in the NIC.*

### validate iam url

The client may validate login url by hitting the rest endpoint:

> POST http://localhost:8088/url 

> @RequestBody {"url":"https://iambeta.elm.sa/authservice/authorize?..."} 

and it will return back validation response with HTTP_STATUS 200 if it is valid, and with HTTP_STATUS 422 with error description if it is invalid login url.