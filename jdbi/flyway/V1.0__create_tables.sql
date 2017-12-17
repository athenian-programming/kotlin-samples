CREATE TABLE users (
  user_id  SERIAL PRIMARY KEY,
  created  TIMESTAMPTZ DEFAULT NOW(),
  username TEXT NOT NULL,
  first    TEXT NOT NULL,
  last     TEXT NOT NULL,
  password TEXT NOT NULL,
  email    TEXT NOT NULL
);
