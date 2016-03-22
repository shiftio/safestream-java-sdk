# SafeStream Java SDK

```java
// Create a new SafeStream video
SafeStreamAPI safestream = new SafeStreamAPI("MY API KEY");
Video video = safestream
            .video()
            .create(new Video()
                .withSourceUrl("http://www.nasa.gov/downloadable/videos/occultationgraphh264fullsize.mp4"));
```

#### Overview
The SafeStream java SDK provides an interface for watermarking video through SafeStream. It is both a wrapper around and abstraction of the [SafeStream REST API](http://docs.safestream.com). The SDK provides a fluent interface for creating and manages videos in SafeStream as well as watermarking videos.

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
