**A TestNG + Rest Assured testing challenge for the testing automation academy training.**

**Simple Install and Run:**

On one terminal window:

`npm install SimpleRestApi/`

`node SimpleRestApi/index.js`

On another terminal:

`servicetraining/mvn install`

`servicetraining/mvn test`

**Detailed Install and Run:**

This project consists of two executable applications:

1. A simple local rest API containing get, post, put, and delete methods, in order to test the main four CRUD
   operations, create, read, update and delete operations.
2. A maven java project containing the actual testing of these methods, using the testng and rest assured libraries.

Installation and Running Instructions:

In order to run the applications and see the actual testing, we need to verify that certain applications are installed:
NodeJs and NPM

`node -v`

`npm -v`

If this isn't the case, they can be found for your respective OS here:

Node and NPM: https://nodejs.org/en/download/

Once the node isntallation is verified, the Rest API can be installed and ran using the following commands:

`npm install SimpleRestApi/`

`node SimpleRestApi/index.js`

*All listed commands must be ran from the root folder of this project.*

With this the local rest api will start to run in the current terminal window.

For the next step maven must be installed. If this isn't the case please refer to the official apache installation aid for
your respective OS: https://maven.apache.org/install.html

Once maven is installed, run the following commands in another terminal while the local rest api is still running:

`servicetraining/mvn install`

`servicetraining/mvn test`

