# atipera

keytool -genkeypair -alias dev-cert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore "src\main\resources\keystore\dev-keystore.p12" -validity 365 -dname "CN=localhost" -storepass changeit -keypass changeit