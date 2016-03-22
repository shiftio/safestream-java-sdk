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
```gradle
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
```maven
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

#### Getting Started
##### Instantiating the client
The client can be reused. You only need to create the client once for your application.
```java
SafeStreamAPI safestream = new SafeStreamAPI("MY API KEY");
```

#### Video Examples
##### Create a new video from URL
```java
Video video = safestream
                .video()
                .create(new Video()
                    .withSourceUrl("http://www.nasa.gov/downloadable/videos/occultationgraphh264fullsize.mp4"));
```

##### Create a new video with my own key
```java
Video video = safestream
                .video()
                .create(new Video()
                    .withKey("MY SPECIAL KEY")
                    .withSourceUrl("http://www.nasa.gov/downloadable/videos/occultationgraphh264fullsize.mp4"));
```

##### Create a new video with tags
```java
Video video = safestream
                .video()
                .create(new Video()
                    .withTags(Arrays.asList("sun", "ekg"))
                    .withSourceUrl("http://www.nasa.gov/downloadable/videos/occultationgraphh264fullsize.mp4"));
```
#### Video Properties
Name | Description
------------ | -------------
key | This can be an external key of any string value. If no value is given when the video is created then the key will be the source URL.
name | An optional descriptive name for a video
sourceUrl | The URL where the video source exists at the time of creating this video. Currently, http and https URLs are supported
targetBitRate | Target bit rate in kilobits. For example to create a 4Mb proxy this value would be 4000k.
allowHmacAuth | If we should use signed URLs for access tto he watermarked segments and M3U8 of this videos watermarked versions. Defaults to true.
encrypt |  If we should encrypt the watermarked segments of this video at rest. Defaults to true.
status | The ingest status of this video <code>PENDING, INGESTED</code>.  Videos can only be watermarked that are in the <code>INGESTED</code> status
