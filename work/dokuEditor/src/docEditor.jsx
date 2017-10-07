import "bootstrap/dist/css/bootstrap.css!";

import React    from "react";
import ReactDOM from "react-dom";

import {RDFTable} from "./components/components.js";

const data = [
  {data_s: "Movie", data_p: "actor", data_t: "Person", data_d: "An actor, e.g. in tv, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip. Supersedes actors."},
  {data_s: "Movie", data_p: "countryOfOrigin", data_t: "Country", data_d: "The country of the principal offices of the production company or individual responsible for the movie or program."},
  {data_s: "Movie", data_p: "director", data_t: "Person", data_d: "A director of e.g. tv, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip. Supersedes directors."},
  {data_s: "Movie", data_p: "duration", data_t: "Duration", data_d: "The duration of the item (movie, audio recording, event, etc.) in ISO 8601 date format."},
  {data_s: "Movie", data_p: "musicBy", data_t: "MusicGroup  or Person", data_d: "The composer of the soundtrack."},
  {data_s: "Movie", data_p: "productionCompany", data_t: "Organization", data_d: "The production company or studio responsible for the item e.g. series, video game, episode etc."},
  {data_s: "Movie", data_p: "subtitleLanguage", data_t: "Language  or Text", data_d: "Languages in which subtitles/captions are available, in IETF BCP 47 standard format."},
  {data_s: "Movie", data_p: "trailer", data_t: "VideoObject ", data_d: "The trailer of a movie or tv/radio series, season, episode, etc."},
  {data_s: "Movie", data_p: "", data_t: "", data_d: ""},
  {data_s: "Movie", data_p: "", data_t: "", data_d: ""},
  {data_s: "Movie", data_p: "", data_t: "", data_d: ""}
];

// ReactDOM.render(
//     <RDFTable entries={data} />,
//     document.getElementById("container")
// );