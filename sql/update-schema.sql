alter table PETITION_PREVIEW add column ADDED timestamp(3) with time zone;

update PETITION_PREVIEW set ADDED = current_timestamp;

alter table PETITION_PREVIEW alter column ADDED set not null;
