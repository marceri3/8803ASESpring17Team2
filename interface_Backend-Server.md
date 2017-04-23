**Cloud Database to Web Backend Interface**

All objects will be represented in JSON format unless otherwise noted.

**Playlist Object -** The Playlist object contains information about a
collected group of songs.

| **Name**      | **Type** | **Description**                            |
|---------------|----------|--------------------------------------------|
| playlistID    | Int      | Unique identifier for the playlist         |
| playlistName  | String   | Human readable name for the playlist       |
| playlistOwner | Int      | The userID for the creator of the playlist |
**Playlist REST API**

| **HTTP Method** | **URL**                       | **Input**               | **Output**       | **Description**                                                                                                 |
|-----------------|-------------------------------|-------------------------|------------------|-----------------------------------------------------------------------------------------------------------------|
| GET             | /api/playlist/{playlistID}    |                         | playlist         | Returns playlist with playlist ID                                                                               |
| GET             | /api/playlist/{playlistName}  |                         | playlistID       | Returns playlistID of playlist with name string                                                                 |
| GET             | /api/playlist/{songID}        |                         | (list)playlistID | Returns list of playlist IDs of songs with names containing title                                               |
| GET             | /api/playlist/{playlistOwner} |                         | (list)playlistID | Returns list of playlistID for all playlists with playlistOwner as owner                                        |
| POST            | /api/playlist/{playlistID}    | songID                  |                  | Puts songID into an existing playlist                                                                           |
| POST            | /api/playlist/{playlistName}  | userID                  |                  | Creates a new playlist with playlistName and userID as the owner.                                               |
| PUT             | /api/playlist/{playlistID}    | songID, userID, likeVal |                  | Set the value of “like” tied to a userID for a specific songID in a playlist. (1=like, 0=neither, -1 = dislike) |
| DELETE          | /api/playlist/{playlistID}    |                         |                  | Deletes an existing playlist                                                                                    |
**Song Object -** The Song object contains information about a single song.

| **Name** | **Type** | **Description**            |
|----------|----------|----------------------------|
| SongID   | Int      | The unique id of the song  |
| Title    | String   | Name of the song           |
| Artist   | String   | Artist of the song         |
| Album    | String   | Album the song belongs ot  |
| Genre    | String   | Genre of the song          |
| URL      | String   | URL that can play the song |

**Song REST API**

| **HTTP Method** | **URL**            | **Input**                 | **Output** | **Description**                                 |
|-----------------|--------------------|---------------------------|------------|-------------------------------------------------|
| GET             | /api/song/{songId} |                           | Song       | Get the song with the songID                    |
| GET             | /api/song/{title}  |                           | (list)Song | Get songs with the title                        |
| GET             | /api/song/{artist} |                           | (list)Song | Get songs with the artist                       |
| GET             | /api/song/{genre}  |                           | (list)Song | Get songs with the genre                        |
| GET             | /api/song/{album}  |                           | (list)Song | Get songs with the album                        |
| PUT             | /api/song/{title}  | JSON of song details   | songID     | Create a new song object and returns its SongID |
| DELETE          | /api/song/{songID} |                           |            | Deletes the song object with songID             |

**User Object -** The User object contains information about an individual user.

| **Name** | **Type** | **Description**            |
|----------|----------|----------------------------|
| userID   | String      | Unique identifier for a user |
| firstName    | String   | First name of the user           |
| lastName   | String   | Last name of the user         |
| dateJoined    | Date   | Date the user joined |
| email    | String   | Email of the user         |

**User REST API**

| **HTTP Method** | **URL**            | **Input**                 | **Output** | **Description**                                 |
|-----------------|--------------------|---------------------------|------------|-------------------------------------------------|
| GET             | /api/user/{userId} |                           | User       | Get the user with the givern userID                   |
| GET             | /api/user/host/{userId}  |                           | (list)playlistID | Get the list of all playlistIDs of which the user is a host                        |
| GET             | /api/song/member/{userID} |                           | (list)playlistID | Get the list of all playlistIDs of which the user is a member                       |
| GET             | /api/song/songlike/{userID}  |                           | (list)songID | Get the list of all songIDs that the user has liked                        |
| GET             | /api/song/songdislike/{userID}  |                           | (list)songID | Get a list of all songIDs that the user has disliked                       |
| POST             | /api/user/  |  JSON of user details   | userID | Create a new user with the given user details                       |
| PUT             | /api/user/{userID}  | JSON of user details |     | Update the user details with given user JSON object |
| DELETE          | /api/user/{userID} |                           |      | Delete the user associated with a given userID             |
