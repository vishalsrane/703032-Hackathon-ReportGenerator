--drop schema outreachdata;
--create schema outreachdata;

drop table if exists event_details;
drop table if exists event_poc;
drop table if exists event;
drop table if exists associate;



create table associate (
       id bigint not null auto_increment,
        associate_id bigint,
        business_unit varchar(255),
        contact_number varchar(255),
        designation varchar(255),
        location varchar(255),
        name varchar(255),
        role varchar(255),
        primary key (id)
);

create table event (
       id bigint not null auto_increment,
        activity_type bigint,
        base_location varchar(255),
        benificiaryname varchar(255),
        category varchar(255),
        council varchar(255),
        description varchar(255),
        event_id varchar(255),
        eventdate datetime,
        lives_impacted bigint,
        month varchar(255),
        name varchar(255),
        project varchar(255),
        status varchar(255),
        venue varchar(255),
        volunteers_count bigint,
        volunteers_hours bigint,
        volunteers_travel_hours bigint,
        primary key (id)
);

create table event_poc (
       event_id bigint not null,
        poc_id bigint not null,
        primary key (event_id, poc_id)
);

create table event_details (
       id bigint not null auto_increment,
        travel_hours bigint,
        volunteer_hours bigint,
        associate_id bigint not null,
        event_id bigint not null,
        primary key (id)
);

alter table associate 
       add constraint unique_associate_id unique (associate_id);

alter table event 
       add constraint unique_event_id unique (event_id);

alter table event_poc 
       add constraint associate_id 
       foreign key (poc_id) 
       references associate (id);

alter table event_poc 
       add constraint event_id 
       foreign key (event_id) 
       references event (id);

alter table event_details 
       add constraint associate_id_for_event_table 
       foreign key (associate_id) 
       references associate (id);

alter table event_details 
       add constraint event_id_for_event_table  
       foreign key (event_id) 
       references event (id);