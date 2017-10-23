Software Engineering (C-T-303-HUGB), Week 11 Fall Semester 2017

# Functional testing
Functional testing, User Acceptance testing, (Black Box testing, End-to-end testing and Full stack testing) are often synonyms, even though their focus is a bit different. These tests often give great value and are able to catch regression bugs that would not be caught by other types of tests. They are however not suited for testing everything and should be used sparingly.

## Selenium WebDriver
[Selenium](http://docs.seleniumhq.org/) is a tool for functional testing of web applications and pages. It uses a running browser to simulate what actual users would do.

For today's exercise we'll be focusing on getting Selenium running on your laptops.

### Starting points
- [Selenium 3.0.1 JavaDoc](http://seleniumhq.github.io/selenium/docs/api/java/index.html)
- [Selenium 3.0.1 helpful commands](http://www.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations)

### How Selenium works?
Selenium as a library is available for a number of Programming languages. Java being the most commonly used, but Python, Javascript, and C# are also popular. Selenium tests use a WebDriver to send commands to a running Browser simulating a user interacting with the Browser.

#### WebDriver
To run Selenium tests, a WebDriver is needed. For today's exercise we recommend [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/). You'll need to have Chrome accessible from your command prompt (Git Bash, Shell, or whatever you use). See the [Getting Started pages](https://sites.google.com/a/chromium.org/chromedriver/getting-started).

## Objectives
The objective is that the students know what functional testing is about and what kinds of tools are needed to automate such testing. In particular, students should be familiar with Selenium and to be able to create simple, automated, Web UI tests with this tool.

## Assignment
### First Part - WebDriver Setup
1. Depending on your OS, installing the WebDriver will be different. Look for installation instructions for your OS and your choice of WebDriver:
    - For running tests in Chrome install Chromedriver (_recommended_)
    - For running tests in Firefox install Geckodriver
2. Add your installed WebDriver to path
    - To test if you have done this, you can call: `geckodriver` or `chromedriver` in a new terminal/command prompt window. The WedDriver of your choice should start up (just close it, we won't run it there).
3. Add your selected Browser to path
    - To test if you have done this, you can call: `Firefox` or `Chrome` in a new terminal/command prompt Window and the browser should start up (just close it again).

### Second part - Running Selenium tests
1. Clone the latest [CalcWeb](https://github.com/arnlaugsson/CalcWeb) repo.
    - `git clone https://github.com/arnlaugsson/CalcWeb`
2. Make sure everything builds, `./gradlew check` or `gradle check`.
3. Now run the Selenium tests: `./gradlew selenium`

Did it work?

### Third part - Writing Selenium tests
In the repo we have given you a boiler plate. There are a few items of interest.

1. The build.gradle file now contains a new task (selenium), a new [sourceSet](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html) (also called selenium, main and test are default), dependencies for that task, and definitions for the task on where the classes will be and what the classpath is.
2. Under `src/` we now have a new folder, selenium (this is the new sourceSet). Since these test don't live under `src/test/` Gradle will not run them automatically (which is what we want anyway).
3. In the `src/selenium/java/is/ru/hugb/calcWeb/` we have two Java files.
    - SeleniumTestWrapper.java contains a helper class that handles WebDriver logic.
    - TestCalcWeb.java is the actual Selenium test class that extends the helper class.

There are two test cases that have been created, one simply asserts the title of the page is what is expected, and the second one is left for you to implement.

```java
@Test
public void testSimpleAdd() throws Exception {
  driver.get(baseUrl);
  /* Remove Thread.sleep... */
  Thread.sleep(2000);
  WebElement input = driver.findElement(By.id("number"));
  input.sendKeys("1");
  /* Remove Thread.sleep... */
  Thread.sleep(2000);
  /* ... finish test! */
}
```    

#### Steps
1. Find the WebElement that you should enter text into:
    - `WebElement input = driver.findElementById(By.id("number"));`
    - For this you'll need to import some items from `org.openqa.selenium`.
    - Once you have the element you can use `sendKeys()` to fill it with content.
    - Do the same for the last name.
2. Submitting forms can be done by pressing a button, and in most cases also by pressing Enter.
    - `input.sendKeys(Keys.RETURN)`
    - Or using `submit()` on the form or any element in the form.
    - Again some import is needed.
3. For asserting a text is found on the page, find the element and use `getText()` to get the text in the element. Use that for comparison. You should use assert from junit or Hamcrest matchers library.
4. Create a new test that asserts that an error is raised on an non-allowed number string.

### And beyond
In the boilerplate we have given, it's impossible to run the Selenium tests as a part of the TravisCI cycle. The reason for this is simply that Firefox and Chrome are Desktop apps, and TravisCI servers are lacking a Desktop environment.

There are several options to go about this though. For Travis CI specifically they have a [article about this](https://docs.travis-ci.com/user/gui-and-headless-browsers/). A commonly used solution is to use Xvfb (X virtual frame buffer) that creates a Desktop environment and allows a GUI application to be run on a server with a Desktop environment. Another common solution is to use an external service (like SauceLabs, Testbot or similar). And a third very exciting way to implement this is to use a [PhantomJS](http://phantomjs.org/).

PhantomJS doesn't need a Desktop environment and could be easily run on TravisCI and on other servers. It's also fairly quick.
