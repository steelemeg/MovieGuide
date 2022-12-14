# Android Project 3 - MovieGuide

Submitted by: Megan Steele

MovieGuide is a movie browsing app that allows users to browse movies currently playing in theaters.

Time spent: 14 hours spent in total

## Required Features

The following **required** functionality is completed:

- [X] **Make a request to [The Movie Database API's `now_playing`](https://developers.themoviedb.org/3/movies/get-now-playing) endpoint to get a list of current movies**
- [X] **Parse through JSON data and implement a RecyclerView to display all movies**
- [X] **Use Glide to load and display movie poster images**

The following **optional** features are implemented:

- [X] Improve and customize the user interface through styling and coloring
- [X] Implement orientation responsivity
  - App should neatly arrange data in both landscape and portrait mode
- [X] Implement Glide to display placeholder graphics during loading
  - Note: this feature is difficult to capture in a GIF without throttling internet speeds.  Instead, include an additional screencap of your Glide code implementing the feature.  (<10 lines of code)

The following **additional** features are implemented:

- [X] To work around extreme API response times, the app retains movie data after the initial call
- [X] API key stored in a private file 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='movies.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/)

Screencap of Glide placeholder code: 
<img src='loadingImage.PNG' title='Placeholder implementation' width='' alt='Placeholder implementation' />

## Notes

- Extreme latency (80000ms and up) from the required API. This was only seen through Android Studio; tests in Postman were on the order of 300ms. Other APIs (such as the NY Times book stats API) did not show this issue.  
- Swapping between poster and promo images on orientation changes was challenging in the fragment structure.

## License

    Copyright 2022 Megan Steele

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.