# safestream-java-sdk
SafeStream SDK for Java

#### Installation

##### Gradle
```
repositories {
    mavenCentral()
    maven {
        url "s3://repo.safestream.com/releases"
        credentials(AwsCredentials) {
            accessKey AWS_ACCESS_KEY
            secretKey AWS_SECRET_KEY
        }
    }
}

dependencies {
    compile (
            'com.safestream:sdk:1.0.0'
    )
}
```

##### Maven
```
  <repository>
      <id>repo.safestream.com-releases</id>
      <name>SafeStream Repository</name>
      <url>s3://repo.safestream.com/releases</url>
      <releases>
          <enabled>false</enabled>
          <updatePolicy>never</updatePolicy>
          <checksumPolicy>fail</checksumPolicy>
      </releases>
      <snapshots>
          <enabled>true</enabled>
          <updatePolicy>never</updatePolicy>
          <checksumPolicy>fail</checksumPolicy>
      </snapshots>
  </repository>
  
  ...
  
  <dependency>
      <groupId>com.safestream</groupId>
      <artifactId>sdk</artifactId>
      <version>1.0.0</version>
  </dependency>
```
