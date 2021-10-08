# htec-qa-template

## Description
This is a project for test automation. It contains code base for e2e(ui) and api automation. It makes easy collaboration and usage of functionalities from api in e2e tests. 
<br/>There are different types of tests:
* api functional (positive, negative, security)
* api response schema validation
* e2e (end-to-end tests from ui)
* performance (although it is JS, purpose here is to have code base, but also for data preparation to be able to use api-tests)

Modules structure could be based on services/controllers from swagger. Packages could be named based on that.

##Flow and requirements

Preferable checkpoints in process of writing tests:
* Written test case in tool you use for that purpose on project
* Created Task in jira (or similar tool) with set component **TestAutomation**
* Clone/open this project, create branch and implement described test case
* Create PR, answer on comments (if there are any). Once code is improved and comments are resolved PR will be approved and ready for Merge

Merging strategies for merging code from **your branch** to **main** branch:
* *squash and merge* option. Edit message to describe all changes in one line
* *Create a merge commit* option

<br/>**NOTE:** If you are using squash and merge and plan to continue working on the same branch, merge test branch with the latest changes to your working branch, otherwise create new branch from **main** branch (after you pull the latest changes).
Always create new branch from **main** branch

If you have test and staging branches, it is preferable to have test and staging branches that are going to track changes and state of
those environments and at the same time to enable you to be in sync with those envs. 


##Some tips:
* Use one class for Request model if it is the same in add, edit cases
* Use one class for Response model if it is the same for get, add, update. It not the same, than create separate AddEntityResponse, EditEntityResponse, etc.)
* Don't set default values in Request and Response models directly - Use constructors to set what is needed
* Don't set primitive types for variables - use classes, like String, Integer, Float, Double, etc

  *Explanation*: if we set default values for request or response models, than in cases when we not need them, or if purpose is to not define them, we will still have predefined values. In request that means that those fields will be sent to the service, in response that means that default values will be part of expected. That can lead us to missing bugs. We should always control data we are sending in request and data we are preparing as expected.
  The same is with primitive types - if we set primitive type that means that we already set default value. And we will miss nulls which are bugs or are intentional for those fields.
  
##Running setup:
In order to run tests locally you should have properties files
* to api-tests/src/test/resources/ 
* to e2e/src/main/resources/

Note that all those files are git ignored and should stay as secret

For example here is how to run api functional tests from maven: *mvn -Denvironment=test clean test -PFunctional -pl api-tests -am*
* *-Denvironment* - provide environment on which you want to execute tests <br/>
* *-P* - provide profile which you want to trigger based on module you mentioned (for example I want to run Functional profile from api-tests module, that is going to trigger desired testng xml file) <br/>
* *-pl* - set module from which you want to run tests

###More details at the following link - [Test Automation basics, WoW and framework structure](https://docs.google.com/presentation/d/1-qz0arOc5ZWvIco65uemlk8p-Q9oKqUYZThNg9XW0bU/edit#slide=id.ge8ed61ca74_0_78)
