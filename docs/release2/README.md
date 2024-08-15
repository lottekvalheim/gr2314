# CoutureRental release 2

In this release we extended the project by adding a module. As well as understanding their functionality and requirements. We foucused on emphasizing clean commits, which lead to adopting a structured approach for commit messages.

Furthermore, we valued feedback and incorporated it into our work. Additionally, we successfully implemented JSON and Jackson, integrating them seamlessly into our project. Eclipse Che was also implemented to enhance the development environment.

## Requirements

### Modularization

We have expanded the modularization in our project. Previously we had two, which was ui and core. In this release we added another one, presistence, which encapsulates file storage using JSON and Jackson.

All the modules are communicating, as you can see in our architecture documentation (see below).

### Code quality

All modules contains up to several tests that checks the logic of our code. Furthermore, the coverage rate of all the tests are checked using Jacoco. Additionaly, we have implemented CheckStyle to make sure that our code adheres to a coding standard. Lastly, we implemented SpotBug which helped us spot and clean the code for bugs.

### Eclipse Che

Our project is now integrated with Eclipse Che for compatibility. Eclipse Che makes sure that other developers can code, compile, and test their software directly on the web, which eliminates the need for setting up a traditional local development setup.
However, the application will get errors in the ui module in terms of launching the app, because there is no display for the application in Eclipse Che. In other words, the test in ui will fail, as well as the application will not run. 

### Architecture

This architecture is documented in the PlantUML diagram, offering a clear view of the application's components.

Below is a representation of our package architecture, which is split into three different testable modlues: core, ui, and persistence.

#### The current architecture

![CoutureRental Architecture](/CoutureRental/pictures/packageDiagram.png)

## Reflection

### Feedback from release 1

In this release, we focused on the feedback we received from Release 1. We received a lot of positive feedback, but took the suggested improvements into consideration while working on this release. For example, we have now divided the user history into several smaller ones.

### Changes from last release

#### Commit messages

We focused on changing the format of our commit messages to be more like the industry standard. Additionaly we have implemented "co-authored by: " in our commit messages, however we shortened it to c-a-b: [name].

Although we have not followed this perfectly for the whole release, we are now aware of the new format and will follow this for the rest of the project.

#### File storage

In release 1 we used the .txt file to store the status of the dresses, but in this release we had to implement JSON and Jackson. This was pretty easy for us to manage as we didn't have to change much of the logic to fit the new file storage. On the other hand, understanding how to get Jackson to communicate with the other layers was a bit challenging. This resulted in us having to research and learn more about how module-info files work. As well as how to use "export" and "requires" correctly.

We also had difficulties with making some of the tests, as we did not want to modify the existing JSON file.


#### Module

We did not experiencing difficulties in expanding our architecture. As we had implemented two modules to our project in the previous release, as well as a parent and connecting child poms to the modules.

We were happy to already have an architecture that matched the requirements of this release, as this led to fewer complications.

#### Git

As an improvement from the previous release we have gained a lot more confidence and knowledge about Git workflow, eventhough we still have more to improve.

## Summary

In this release we have learn a lot about architecture, code quality, and JSON. We have mostly worked on the file storage and improving our existing code, however we are excited to expand the functionality of the application in the next release.
