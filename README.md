# xm-assignment
#### Testing project assignment on mobile and web platforms
The project consists of two modules - Web-Testing and Mobile-Testing\
Each module has its own TestNG xml file for running.\
The files found in _src/test/test-suite/_ respectively to each module.

### Mobile project

### Prerequisites:
* Java 17 and up
* A running AVD
* Yummly app .apk file

#### What was created?

The mobile module consists the infrastructure for starting the Appium server and connect to the AVD that's running. It will install the app from the path
specified in the _APK_APP_PATH_ found in Constants class.\
The Yummly apk file was used locally, but unfortunately could not be pushed to the repository due to its size. Download link is in the resources section.

### Running the project
1. Run your local machine AVD
2. In the _Mobile-Testing/src/main/java/manager/Constants.java_ file, update the following from your AVD data:
* *DEVICE_NAME*
* *AVD_NAME*

3. Copy the Yummly .apk file to the location: _Mobile-Testing/src/test/resources/app/_\
4. Run the TestNG xml file: _Mobile-Testing/src/test/test-suite/MobileTests.xml_

***
### Web project

### Prerequisites:
* Java 17 and up

#### What was created?
This web responsive test can run on top of three different browsers (chrome, firefox and edge) specified from the BrowserTypes class.
It runs the flow, as requested, using three different screen resolutions by using TestNG *@Factory* annotation:
1. it fetches the running machine max resolution and instantiate the
   *maxWidth* and *maxHeight* variables.
2. (1024, 768)
3. (800, 600)

The test runs three times using different resolution for each test run.
Different dummy assertions are made along the way.

### Running the project
1. Setup your desired browser type in the *BaseTestsWeb.beforeClass#_WebDriverFactory(String browserType)_*
2. Run the TestNG xml file: _Web-Testing/src/test/test-suite/WebTests.xml_

***

#### Resources
* Yummly app .apk file download [link](https://apkpure.net/yummly-recipes-cooking-tools/com.yummly.android/downloading)    
This will download the '.xapk' file. You'll need to extract it first.\
Then pull the 'com.yummly.android.apk' file from the extracted folder 
and place it in the path _'src/test/resources/app/'_

* Java 17 download [link](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Android Studio download [link](https://developer.android.com/studio)

GOOD LUCK TO ALL 😊 
