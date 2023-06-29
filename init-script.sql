CREATE TABLE IF NOT EXISTS Genre (
    id integer PRIMARY KEY,
    genre varchar
);

CREATE TABLE IF NOT EXISTS Movie (
    id integer PRIMARY KEY,
    release_date date,
    title varchar,
    overview varchar,
    popularity integer,
    vote_count integer,
    vote_average integer,
    original_language varchar,
    poster_url varchar
);

CREATE TABLE IF NOT EXISTS movie_genre (
    movie_id integer,
    genre_id integer,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES Movie (id),
    FOREIGN KEY (genre_id) REFERENCES Genre (id)
);


INSERT INTO Genre (id, genre)
VALUES
  (1, 'Action'),
  (2, 'Adventure'),
  (3, 'Comedy'),
  (4, 'Drama'),
  (5, 'Fantasy'),
  (6, 'Horror'),
  (7, 'Mystery'),
  (8, 'Romance'),
  (9, 'Sci-Fi'),
  (10, 'Thriller'),
  (11, 'Animation'),
  (12, 'Crime'),
  (13, 'Family'),
  (14, 'History'),
  (15, 'Music'),
  (16, 'Sport'),
  (17, 'War'),
  (18, 'Biography'),
  (19, 'Documentary'),
  (20, 'Musical'),
  (21, 'Western'),
  (22, 'Film Noir'),
  (23, 'Superhero'),
  (24, 'Spy'),
  (25, 'Psychological');


INSERT INTO Movie (id, release_date, title, overview, popularity, vote_count, vote_average, original_language, poster_url)
VALUES
  (1, '2021-01-01', 'Movie 1', 'Overview 1', 80, 1000, 8, 'English', 'https://example.com/poster1.jpg'),
  (2, '2021-02-01', 'Movie 2', 'Overview 2', 70, 1500, 7, 'English', 'https://example.com/poster2.jpg'),
  -- Add more movie entries here
  (100, '2022-12-31', 'Movie 100', 'Overview 100', 90, 2000, 9, 'English', 'https://example.com/poster100.jpg');

INSERT INTO movie_genre (movie_id, genre_id)
VALUES
  (1, 1),
  (2, 2),
  (100, 24);
