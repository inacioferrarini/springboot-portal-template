# About

This document describes how to create a self-sign certificate.

1. keytool -genkeypair -alias portaltemplate -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore portaltemplate.p12 -validity 3650

2. keytool -genkeypair -alias portaltemplate -keyalg RSA -keysize 2048 -keystore portaltemplate.jks -validity 3650

3. keytool -importkeystore -srckeystore portaltemplate.jks -destkeystore portaltemplate.p12 -deststoretype pkcs12

# References

* (https://www.thomasvitale.com/https-spring-boot-ssl-certificate/) [https://www.thomasvitale.com/https-spring-boot-ssl-certificate/]
