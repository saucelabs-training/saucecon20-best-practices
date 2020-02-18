# Best Practices for Automated Testing

This directory contains example scripts and dependencies for the SauceCon workshop: [Best Practices for Automated Testing](https://saucecon.com/). Use these scripts to test your Sauce Labs authentication credentials, setup your automated testing environment, try out Sauce Labs features, and complete the in-class Selenium examples. Download the zip file or clone the entire directory to your local environment.

#### For Demonstration Purposes Only

The code in these scripts is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. These scripts are provided for educational and demonstration purposes only, and should not be used in production. Issues regarding these scripts should be submitted through GitHub. These scripts are maintained by the Technical Services team at Sauce Labs.

<br />

## Prerequisites

These procedures will show you to set up a Selenium environment for Java. The scripts in this repository allow you run a simple automated test to validate your Selenium environment and your [saucelabs.com](https://app.saucelabs.com/login) account credentials.
In order to complete these exercises you must complete the following prerequisite installation and configuration steps:

* Install Git
* Install IntelliJ
* Install JDK
* Install Maven
* Setup Project

Once your test environment is up and running, refer to the [exercise guides](exercise-guides/getting-started.md) to begin the exercises.

### Install Git

[Git](https://git-scm.com/doc) is a version control system that lets you check out code from a repository, 
work with that code on your own branch, and then merge that code with any changes that have been made by other developers. 
Git is an essential tool for distributed development teams, and is a critical component of the continuous 
integration/continuous development toolchain.

#### MacOSX:

1. Go to [https://git-scm.com/downloads](https://git-scm.com/downloads).
2. Under **Downloads**, click **Mac OS X**.
3. When the download completes, double-click the `.dmg` file open the installer package.
4. Double-click the installer package to begin the installation.
    > *Security Warning*
    >
    > You may see a warning message that the package can't be opened because it's not from a recognized developer. 
    If this happens, go to System Preferences > Security and Privacy Settings, and click Open Anyway.
5. Click **Continue** for the installation, and enter your local password to authorize the installation.

#### Windows:

1. Go to [https://git-scm.com/downloads](https://git-scm.com/downloads)
2. Under **Downloads**, click on **Windows**.
3. When the dialog opens asking if you want to allow the app to make changes to your device, click Yes.
4. Follow the steps in the setup wizard to complete the installation. You should accept all the default settings.
<br />

### Install IntelliJ

[IntelliJ](https://www.jetbrains.com/idea/) is an integrated development environment that incorporates several tools for developing and running Java code. You will be using IntelliJ to write and edit the sample Selenium scripts used in the exercises.  For these exercises you only need to download the free Community edition.

#### MacOSX:

1. Go to [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)
2. Click **Download**.
3. On the **Downloads** page, select **macOS**.
4. Under **Community**, click **Download**.
5. When the download completes, double-click the .dmg file open the installer package.
6. Double-click the installer package to begin the installation.
7. Drag and drop the IntelliJ icon into the **Applications** folder.

#### Windows:

1. Go to [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)
2. Click **Download**.
3. On the **Downloads** page, select **Windows**.
4. Under **Community**, click **Download**.
5. When the download completes, double-click the `.exe` file to launch the installation wizard. 
You should accept all the default settings.
<br />

### Install the JDK

The [Java SE Developer Kit](http://www.oracle.com/technetwork/java/javase/overview/index.html) lets you develop and 
deploy Java applications on desktops and servers. It is needed to compile our test code.

#### MacOSX:

1. Go to [the JDK downloads](https://www.oracle.com/technetwork/java/javase/downloads/index.html) page, locate the latest release, select the download button.
2. Under **Java SE Development Kit {version}**, select the **Accept License Agreement** radio button.
3. Click the download link for **Mac OS**.
4. When the download completes, double-click the `.dmg` file open the installer package.
Double-click the installer package to begin the installation.

#### Windows:

1. Go to [the JDK downloads](https://www.oracle.com/technetwork/java/javase/downloads/index.html) page, locate the latest release, select the download button.
2. Under **Java SE Development Kit {version}**, select the **Accept License Agreement** radio button.
3. Click the download link for **Windows x64**.
4. When the download completes, double-click the `.exe` file open the installer package.
5. Double-click the installer package to begin the installation. You should accept all the default settings.
<br />

### Install Maven

Maven is a build automation and project management tool use for managing project builds, dependencies, and documentation. It uses a project object model (pom.xml) to manage Java-based projects. With our use case, it's very useful for configuring and managing test suites.

#### MacOSX:

1. Go to Maven Apache website and [download](https://maven.apache.org/install.html) the following package: `apache-maven-<version>-bin.tar.gz`
2. Extract the archive
    ```
    $ tar -xvf apache-maven-<version>-bin.zip
    ```
3. Add the `bin` directory of the extracted directory (`apache-maven-<version>`) to the `PATH` variable:
    ```
    $ export M2=$M2_HOME/bin
    $ export PATH=$M2:$JAVA_HOME/bin:$PATH
    ```
    > WARNING!: Make sure you've set `JAVA_HOME` othewise `mvn` commands won't run

4. Check to see if maven installed correctly:
    ```
    $ mvn -version
    ```
    ```
    Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-24T11:41:47-07:00)
    Maven home: /usr/local/apache-maven-3.6.0
    Java version: 11.0.2, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home
    Default locale: en_US, platform encoding: UTF-8
    OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
    ```

#### Windows:

1. Go to Maven Apache website and [download](https://maven.apache.org/install.html) the following package: `apache-maven-<version>-bin.zip`
2. Unpack the archive using an archive tool (for example WinZip)
3. Add the unpacked distribution’s `bin` directory to your user `PATH` environment variable by:
    1. Open up the system properties (WinKey + Pause) 
    2. Select the **Advanced** tab, and the **Environment Variables** button
    3. Add/Select the **`PATH`** variable in the user variables with the value:
        ```
        C:\Program Files\apache-maven-3.6.0\bin
        ```
4. Open a new command prompt (Winkey + R then type cmd) and run `mvn -v` to verify the installation.

### Setup the Project

#### Ensure IntelliJ is Correctly Configured:

1. Import the project into IntelliJ as a **Maven Project**.
2. Click through the prompts, and confirm when it asks to **Import from Sources**
3. Open a terminal and run the following command to update any package dependencies:
    ```
    $ mvn dependency:resolve
    ```
2. Then run the following command:
    ```
    $ mvn test
    ```