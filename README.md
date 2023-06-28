# Bookshelf Showcase App

This repository contains a simple app developed as part of the engineering skills showcase. The app displays a list of books and provides a detailed screen for each book. It utilizes the public API provided by Gutendex to fetch the required data.

## Project Expectations

- Integration of Retrofit for network communication.
- Utilization of Flows and Coroutines for asynchronous operations.
- Inclusion of unit tests for ensuring code quality and reliability.

### Nice-to-Have

- Adoption of clean architecture principles to promote separation of concerns and maintainability.
- Integration of a Dependency Injection Framework for improved code organization and modularity.
- Use of Compose for the user interface to create a modern and reactive UI.
- Implementation of a caching mechanism using Room for offline support and improved performance.
- Modularization of the project to enhance scalability and reusability.

## Getting Started

To run the project locally and explore its functionalities, follow the instructions below.

### Prerequisites

- Android Studio (version Android Studio Dolphin | 2021.3.1 Patch 1 or higher)
- Android SDK
- Kotlin (version 1.8.0 or higher)

### Installation

1. Clone the repository:

   ```
   git clone https://github.com/tarcisiofl/bookshelf-gutendex.git
   ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or physical device.

## Features

The Bookshelf Showcase app provides the following features:

1. Book Listing (First screen): Displays a list of books with their titles and authors.

2. Detailed View (Second screen): Shows additional details about a selected book, including:

   - Book title
   - Author's name
   - Author's birth year and death year (if available)
   - One book subject (selected from the API's list)

## UI Design

The UI design of the app is inspired by the [Audiobooks by Booksbury](https://www.figma.com/file/GdwKWQBColEHQ0UUKaRuSv/Audiobooks-by-Booksbury-(Community)?type=design&node-id=4-513&mode=design&t=5QYT3D9LsogGHnSe-0) design in Figma. The design provides a visual reference for creating the app's user interface and can be accessed using the provided link.

## License

The Bookshelf Showcase app is open source and released under the [MIT License](LICENSE). Feel free to modify and distribute the code as per the terms of the license.

## Acknowledgements

- [Gutendex API](https://gutendex.com/) - The public API used for fetching book data.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java used for network communication.
- [Room](https://developer.android.com/training/data-storage/room) - A persistence library used for implementing the caching mechanism.
- [Koin](https://insert-koin.io/) - A lightweight dependency injection framework used for managing dependencies.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - A modern UI toolkit used for building reactive and declarative user interfaces.

## Contact

For any inquiries or feedback, please contact the project maintainers:

- Tarcisio Lima - tarcisiofonsecalima@gmail.com

We appreciate your interest in the Bookshelf Showcase app and look forward to seeing your implementation. Happy coding!
