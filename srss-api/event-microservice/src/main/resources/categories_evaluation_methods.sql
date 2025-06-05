select * from categories_evaluation_methods;

create extension if not exists "uuid-ossp";

insert into categories_evaluation_methods (uuid, category_uuid, evaluation_method)
values (uuid_generate_v4(), (select uuid from categories where name = 'Спорт'), 'PLACES'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Творческая работа'), 'PLACES'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Социальная работа'), 'MAN_HOURS'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Производственная работа'), 'MAN_HOURS'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Участие в мероприятиях'), 'PLACES'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Участие в мероприятиях'), 'ATTENDANCE'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ'), 'PLACES'),
       (uuid_generate_v4(), (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ'), 'ATTENDANCE');