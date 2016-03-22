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

#### Watermarking Examples
##### Watermark a video with text
```java
WatermarkResult watermarkResult = safestream
                                    .watermark()
                                    .create("MY VIDEO KEY", new WatermarkConfiguration()
                                                .withContent("ANY TEXT YOU'D LIKE"));
```

##### Watermark a video with BIG text
```java
WatermarkResult watermarkResult = safestream
                                    .watermark()
                                    .create("MY VIDEO KEY", new WatermarkConfiguration()
                                                .withContent("ANY TEXT YOU'D LIKE")
                                                .withFontSize(.15));
```

##### Watermark a video text in the top left corner
```java
WatermarkResult watermarkResult = safestream
                                    .watermark()
                                    .create("MY VIDEO KEY", new WatermarkConfiguration()
                                                .withContent("ANY TEXT YOU'D LIKE")
                                                .withHorizontalAlignment(WatermarkHorizontalAlignment.RIGHT)
                                                .withVerticalAlignment(WatermarkVerticalAlignment.BOTTOM)
                                                .withX(0.0f)
                                                .withY(0.0f));
```

##### Watermark a video text in the top right corner
```java
WatermarkResult watermarkResult = safestream
                                    .watermark()
                                    .create("MY VIDEO KEY", new WatermarkConfiguration()
                                                .withContent("ANY TEXT YOU'D LIKE")
                                                .withHorizontalAlignment(WatermarkHorizontalAlignment.LEFT)
                                                .withVerticalAlignment(WatermarkVerticalAlignment.BOTTOM)
                                                .withX(0.0f)
                                                .withY(1.0f));
```
#### Watermark Configuration Properties
Name | Description
------------ | -------------
content | The text string to watermark onto the video.
type | Currently <code>TEXT</code> is support
horizontalAlignment | Specifies the horizontal anchor point on the watermark. A value of LEFT will anchor the watermark on it's right most point. A value of RIGHT will anchor the watermark on it's left most pixel. A value of CENTER will anchor the watermark in it's center most pixel.
verticalAlignment | Specifies the vertical anchor point on the watermark. A value of TOP will anchor the watermark on it's top most point. A value of MIDDLE will anchor the watermark on it's middle most pixel. A value of BOTTOM will anchor the watermark in it's bottom most pixel.
x | The relative x position of the anchor. The position is relative to the width of the video. So, a video with a width of 1080 and an x value of .5 will put the anchor point at 540 pixels. The anchor position is defined by the horizontal and vertical alignment.
y | The relative y position of the anchor. The position is relative to the height of the video. So, a video with a height of 720 and an y value of .5 will put the anchor point at 360 pixels. The anchor position is defined by the horizontal and vertical alignment.
fontSize | Size of the watermark text relative to the height of the video. For example, a video with a height of 720 and a font size of .05 will produce a watermark with a text font size of 36.
fontOpacity |Values from 0 (totally transparent) to 1 (totally opaque)
fontColor | Hex value of font color (ex 0xffffff)
shadowOpacity | Opacity of the drop shadow of the watermark text. 0 (totally transparent) to 1 (totally opaque)
shadowColor | Hex value of watermark text drop shadow color (ex 0xffffff)
shadowOffsetX | Horizontal offset of the drop shadow
shadowOffsetY | Vertical offset of the drop shadow

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
