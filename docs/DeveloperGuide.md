---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of HelloFile. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `untag t/tag123`.

![Architecture Sequence Diagram](images/ArchitectureSequenceDiagram.png)

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TagListPanel`, `ThemeWindow` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Tag>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Data Structure: Tag

### Data Structure: Label

### Adding of Tags: TagCommand

[TagCommand](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/TagCommand.java) 
adds a new `Tag` to `AddressBook` if the tag's `TagName` is not a duplicate and the tag's `FileAddress`
is pointing to a valid file.

TagCommand checks if the file is present using `java.io.File.exists()`.

### Opening of Tags: OpenCommand

[OpenCommand](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/OpenCommand.java)
searches the list of Tags stored in `AddressBook` and opens the file located at the tag's `FileAddress`
if the file is present. `CommandException` is thrown if tag is not present or if the file cannot be found.

We implemented OpenCommand using `java.awt.Desktop`,
which supports various desktop capabilities such as open. This ensures that our application can operation across
most java-supported platforms.

However, one draw back of using `java.awt.Desktop` is that the platform that HelloFile operates on must
support `Desktop`. This means that our application will never work on a headless environment. 
You can check whether the environment supports `Desktop` using the provided method `java.awt.Desktop.isDesktopSupported()`.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Tech savvy NUS Computer Science Student
* has a need to manage a significant number of files
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: CS students can manage/access their files by typing
                       and using a simple GUI. Help CS students to see file relations easily.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                     | I want to …​                                       | So that I can…​                                        |
| -------- | ---------------------------------------------------------------| ------------------------------------------------------| ----------------------------------------------------------|
| `* * *`  | Student with lots of file                                      | tag my files with a easy to remember tag              | get file path                                             |
| `* * *`  | First time user                                                | use a help command                                    | start to remember how to use the command                  |
| `* * *`  | Student who prefers to type                                    | use typing to interact with my file system            | use keyboard as much as possible                          |
| `* * *`  | Student who is familiar with command line applications         | name my files                                         | access the file easily next time                          |
| `* *`    | CS student with a lot of project                               | hide private contact details                          | minimize chance of someone else seeing them by accident   |
| `*`      | Forgetful user who always forget where his files are located   | tag frequently used files with a easy to remember tag | locate my files easily                                    |
| `*`      | Intermediate user                                              | delete file                                           | not be distracted by it.                                  |
| `*`      | Developer                                                      | open files with a quick command                       | focus on coding and not look to find my files             |


### Use cases

(For all use cases below, the **System** is the `HelloFile` and the **Actor** is the `user`, unless specified otherwise)

<br />


**Use case ID: UC01**

**Use case: Create fileInfo**

**MSS**

1. User requests to tag a certain file using the absolute file path.
2. HelloFile recognises the file path to be valid.
3. HelloFile creates the tag and display the fileInfo.

    Use case ends.

**Extensions**

* 2a. HelloFile detects the file path is invalid.

    * 2a1. HelloFile prompts the user that the file path is wrong.

        Use case resumes from step 1.

* 2b. HelloFile detects no tag input.

    * 2b1. HelloFile prompts the user for a tag.

        Use case resumes from step 1.

* 2c. HelloFile detects a duplicate tag name.

    * 2c1. HelloFile prompts the user for another tag name.

        Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

    * *a1. HelloFile prompts the user that it needs the read and write permission.

        Use case resumes.

<br />

**User case ID: UC02**

**Use Case: Access file using tag**

**MSS**


1. User requests to open a file using a tag.
2. HelloFile recognises the tag to be valid.
3. HelloFile opens the specified file using the default application.

    Use case ends.

**Extension**

* 2a. HelloFile detects that the tag is invalid.

	* 2a1. HelloFile prompts the user that the tag is wrong.

        Use case resumes from step 1.

* 3a. HelloFile detects that the target file does not exist.

	* 3a1. HelloFile prompts that the target file does not exist.

        Use case resumes from step 1.

* 3b. HelloFile detects no default application to open the file.

	* 3b1. HelloFile prompts the user to choose an application to open the file.

	    Use case resumes form step 1.

* 3c. HelloFile encounters an exception when opening the file.

	* 3c1. HelloFile shows the error message.

	    Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

	* *a1. HelloFile prompts the user that it needs the read and write permission.

	    Use case resumes.

<br />

**UseCase ID: UC03**

**Use Case: Rename tag**

**MSS**

1. User requests to rename the tag of a tagged file.
2. HelloFile replaces the tag of the file with the new tag.

    Use case ends.

**Extensions**

* 2a. HelloFile failed to recognize the tag.

	* 2a1. HelloFile prompts that tag does not exist.

    	Use case resumes from step 1

* 2b. HelloFile failed to recognize the new tag input.

    * 2b1. HelloFile prompts that the new tag is invalid.

        Use case resumes from step 1. 

* 2c. HelloFile detects a duplicate tag name.

	* 2c1. HelloFile prompts the user for another tag name. 

        Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

	* *a1. HelloFile prompts the user that it needs the read and write permission.

	    Use case resumes.


<br />

**UseCase ID: UC04**

**Use Case: Untag a file**

**MSS**

1. User requests to untag a file.
2. HelloFile recognises that the tag exist.
3. HelloFile removes the file from access.

    Use case ends.

**Extensions**

* 2a. HelloFile fails to recognize the tag.

	* 2a1. HelloFile shows the error message.

	    Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

	* *a1. HelloFile prompts the user that it needs the read and write permission.
		
        Use case resumes.
        
        
<br />

**UseCase ID: UC05**

**Use Case: Show a tagged file’s path**

**MSS**

1. User requests the check the path of a tagged file.
2. HelloFile recognises that the tag exist.
3. HelloFile shows the path of the file.

    Use case ends.

**Extensions**

* 2a. HelloFile failed to recognize the tag.

	* 2a1. HelloFile prompts the user that the tag is wrong.

        Use case resumes from step 1.

* 3a. HelloFile detects that the target file does not exist.

	* 3a1. HelloFile prompts that the target file does not exist.

        Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

	* *a1. HelloFile prompts the user that it needs the read and write permission.

	    Use case resumes.

<br />

**UseCase ID: UC06**

**Use Case: Access the parent folder of a tagged file**

**MSS**

1. User request to open parent folder of a tagged file.
2. HelloFile recognises the tag exist.
3. HelloFile open the parent folder with the tag.

    Use case ends.

**Extension**

* 2a. HelloFile detects that the tag is invalid.

	* 2a1. HelloFile prompts the user that the tag is wrong.

        Use case resumes from step 1.

* 3a. HelloFile detects that the target file does not exist.

	* 3a1. HelloFile prompts that the target file does not exist.

	    Use case resumes from step 1.

* 3b. HelloFile detects no default application to open the file.

	* 3b1. HelloFile prompts the user to choose an application to open the file.

        Use case resumes form step 1.

* 3c. HelloFile encounters an exception when opening the file.

	* 3c1. HelloFile shows the error message.

		Use case resumes from step 1.

* *a. HelloFile has no permission to read or write to the file system.

	* *a1. HelloFile prompts the user that it needs the read and write permission.

        Use case resumes.

<br />

**UseCase ID: UC07**

**Use Case: Show the user help**

**MSS**

1. User requests to see the user help.
2. HelloFile shows the user help.

    Use case ends.

**Extensions**


* *a. User input the wrong command.

	* *a1. HelloFile shows the error message and direct the user to enter the user help command.

	    Use case ends.
<br />

*{More to be added}*

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 tags without sluggishness longer than 5 seconds.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The source code should be open source.
5. The application should be usable by a tech-savvy NUS CS student who has never used a similar file management system before.
6. The user interface should be simple and optimized for CLI power users.
7. The product is offered as a free application.
8. 99% of the functions are bug free.
9. The code base should be well documented and populated with ample assertions.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Tag**: A string that will be mapped to a file path
* **UI**: User interface
* **CLI**: Command line interface
* **GUI**: Graphical user interface
* **Relative File Path**: A path that is relative to a current directory, it is combined with another file path to access a file.
* **Absolute File Path**: The complete details required to locate the file or folder, starting with the root element.
