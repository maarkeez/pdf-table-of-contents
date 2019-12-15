# PDF Table of contents

Allows to create a table of content for an existing PDF document.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

```
git clone https://github.com/maarkeez/pdf-table-of-contents.git
mvn clean install
```

You can also download a previous build from the [GitHub project's packages](https://github.com/maarkeez/pdf-table-of-contents/packages)

### Prerequisites

Just choose your favorite Java IDE

### Example of use

Given the following folder structure

```
├── input.pdf
├── application.yml
├── pdf-table-of-contents-VERSION.jar
```
 - **Note**: you can find an `application.yml`  sample [here](https://github.com/maarkeez/pdf-table-of-contents/blob/master/src/main/resources/application.yml)


Running the following command
```
java -jar pdf-table-of-contents-VERSION.jar
```


Will generate the `output.pdf` file with the defined table of content as bookmarks.


## Running the tests

To run the automated tests for this system you can run the following command
```
mvn clean verify
```

## Deployment

Update the [pom.xml](https://github.com/maarkeez/pdf-table-of-contents/blob/master/pom.xml) version to trigger a new build with the GitHub CI/CD workflow

## Built With

* [Java](https://docs.oracle.com/javase/8/docs/api/)
* [Maven](https://maven.apache.org/) - Dependency Management
* [iText 7](https://itextpdf.com/en/products/itext-7) - PDF Library
