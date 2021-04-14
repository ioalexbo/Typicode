# Architecture

## Presentation module
  - Contains MainActivity along with the used fragments and its ViewModels.
  - The ViewModel interacts with the Domain layer and holds the view's states.

## Domain module
  - Contains the definitions of the objects used in the presentation layer: the model, ResultState and UseCase (with its implementation).
  Also holds the repository interface used by the UseCase implementation.

## Repository module
  - Contains implementations for the interfaces defined in the layer mentioned above. Also, the module holds the networking and database implementations.
  - The logic of agregating the data from the API and database is present in the class abstract NetworkBoundResource.
  A class which extends it needs to implement methods for fetching data from the API, fetching and saving data from/to the database
  and converting the data to the models used by different layers.
  - The implementing class of interface AlbumsRepository uses an anonymous implementation of NetworkBoundResource and defines the logic of the abstract methods,
  exposing the result as a Coroutines Flow.
  - Database is implemented with Room.
  - Remote data fetching is implemented with Retrofit; Moshi is used for deserialization data.
  - Each layer uses its own models, and the conversion from one type to another is done with extension functions.

## Dependency injection module
 - DI is implemented using Dagger Hilt, which provides the functionality offered by Dagger, but in standard way.
 - Contains the AppModule and UseCaseModule. The later is later uninstalled from the UI test in order to provide a mocked UseCase.

# Future recommendations:

1. Implement swipe to refresh in AlbumsListFragment, as currently the app fetches the data only at startup
2. The API provides more info than currently implemented. For example, when tapping on an album, one can see it's photos as a detail screen.
For this scenario, a dual pane app is better suited for tablets.
3. Unit test the networking layer with MockWebServer to be better protection against networking issues.
