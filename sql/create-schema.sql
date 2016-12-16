create table PETITION_PREVIEW (
  ID integer not null,
  URL varchar(255) not null,
  TITLE varchar(255) not null,
  IS_LOCKED boolean not null,
  VOICES integer not null,
  JURISDICTION varchar(50) not null,
  primary key(ID),
  constraint UK_PETITION_PREVIEW_ID unique(ID),
  constraint UK_PETITION_PREVIEW_URL unique(URL)
);
