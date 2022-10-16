# WeatherApp ☁🌧🌨🌩☀🌤🌪⛈🌥🌦🌫

WeatherApp is an application that brings you daily weather conditions by city. Weather information of your current location is obtained automatically. You can find out by searching the weather information of the city you want. Weather information received via location is cached. Openweathermap is used as API.

<br/>

You can enter your API key in the location specified in the Constants.kt file in the utilites folder.

<p align="left" width="100%">
  <img src="https://user-images.githubusercontent.com/73544434/177120956-60d42d2d-f05e-4fee-8a24-4d1deab164d1.PNG"/>
</p>

## Libraries 📚


* [Navigation](https://developer.android.com/guide/navigation)

* [Retrofit](https://square.github.io/retrofit)

* [Location](https://developer.android.com/training/location)

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

* [Room](https://developer.android.com/jetpack/androidx/releases/room)

## Project Structure 🏗

```
...
kotlinweatherapp
│
└───data
|   |
|   └───local
|   |   |
|   |   └───db
|   |   |     |
|   |   |     └───dao
|   |   |     |   |   WeatherDao.kt
|   |   |     |
|   |   |     └───entity
|   |   |     |   |   WeatherDataModel.kt
|   |   |     |
|   |   |     |   CacheMapper.kt
|   |   |     |   WeatherDatabase.kt
|   |      
|   └───model
|   |   |   WeatherModel.kt
|   |
|   └───remote
|   |   |
|   |   └───request
|   |   |   |   NetworkMapper.kt
|   |   |   |   StatusCode.kt
|   |   |   |   WeatherRepository.kt
|   |   |
|   |   └───response
|   |   |   |   WeatherEntity.kt
|   |
|   └───service
|   |   |
|   |   └───weatherservice
|   |   |   |   WeatherApi.kt
|   |   
|   |   LocationData.kt
|
└───di
|   |
|   └───modules
|   |   |   LocationModule.kt
|   |   |   RepositoryModule.kt
|   |   |   RetrofitModule.kt
|   |   |   RoomModule.kt
|
└───ui
|   |
|   └───location
|   |   |   GetLocationFragment.kt
|   |   |   GetLocationViewModel.kt
|   |
|   └───main
|   |   |   MainActivity.kt
|   |
|   └───search
|   |   |   SearchCityFragment.kt
|   |   |   SearchCityViewModel.kt
|   |
|   └───weather
|   |   |   WeatherFragment.kt
|   |   |   WeatherViewModel.kt
|
└───utils
|   |
|   └───mapper
|   |   |   EntityMapper.kt
|   |
|   |    Constant.kt
|   |    Status.kt
|
|   WeatherApplication.kt

```

## Images from app 🖼

### GIF
![](https://media.giphy.com/media/NqlfBzlJeb6ROVajyq/giphy.gif)
<p float="left" />

### Main screen

<p align="left" width="100%">
<img src="https://user-images.githubusercontent.com/73544434/176713827-a7cb5bba-480e-4b8f-b586-832ecc1b7cda.png" width="240" height="480"/>
</p>

### Search city screen

<p align="left" width="100%">
<img src="https://user-images.githubusercontent.com/73544434/170821019-f28d343d-d0ea-4655-8b6a-b7bdcdf12107.png" width="240" height="480"/>
</p>

### Error screens

<p align="left" width="100%">

<img src="https://user-images.githubusercontent.com/73544434/176713816-b16d1d7f-d0da-4024-8f04-f6058f16d7d0.png" width="240" height="480"/>
<img src="https://user-images.githubusercontent.com/73544434/176713820-2a3490c0-b438-4e5b-bec7-6cd287058e6c.png" width="240" height="480"/>
<img src="https://user-images.githubusercontent.com/73544434/176713834-791822c3-98a7-4f3b-a8b5-e54b7ad5e320.png" width="240" height="480"/>
</p>



