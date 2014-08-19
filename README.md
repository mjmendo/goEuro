# GoEuro Challenge

## What the source does
- Accepts search string from command line as input
- Performs http get request to `http://api.goeuro.com/api/v2/position/suggest/en/` with the search string
- Receives JSON response (if any) and parses it into array of POJOs with jackson mapper
- Build csv content from pojo array
- Writes/override csv file with that content.

## How to package
Used maven to package fat jar (with dependencies). Assembly plugin seemed to be enough.

Run `mvn clean package` to start packaging all source into a executable jar.

## How to run
In a command line terminal run `java -jar ./target/GoEuroTest.jar berlin` to make a request searching for "berlin" coincidences.

## Techs used
- java 7
- maven 3
- jackson mapper
- apache httpclient
- apache commons-lang3
- junit 4

## Testing
Unit tests perform basic tests with some tricks to reach non-public methods of the service implementation class.

## Considerations
- Tried to keep simple and minimalistic source project.
- All the challenge logic is wrapped in a service oriented design, in order to be reused with other callers.
- All Exceptions are wrapped in a general-purpose custom exception class.
- Rest api calling is intented to be synchronic. 
- If m2e is being used, the error that appears at the very beginning of the built is a m2e bug (http://stackoverflow.com/a/13479631/1703896)

## Going further 
The code is intented to be small and it goes straight to the solution. But there are some enhacements 
to be done depending on lower level of specification of the project:

- If any of the protected methods in GoEuroTestServiceImpl is intented to be reused, 
it should be extracted to its own interface/implementation service classes, with unit testing.
- If logging will be a concern, slf4j is a good lib for that task.
- If separation of concerns will be addressed by creating more clases/services, dependency injection with Guice, Weld or Spring is a must.
- If asynchronic rest-api calls or several rest-api feeds are going to be used, libs like spring integration or apache camel are good choices.
- If dynamic file creation (headers and content) is going to be a need, then reflection over the Location object is a fair approach,
as well as extracting constants and mapping to .properties and descriptor files.

