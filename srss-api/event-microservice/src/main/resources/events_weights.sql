select * from events_weights;

create extension if not exists "uuid-ossp";

insert into events_weights (uuid, category_evaluation_method_uuid, name, weight)
values (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Спорт')), '1 место', 3),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Спорт')), '2 место', 2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Спорт')), '3 место', 1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Спорт')), 'Участие', 0.2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Спорт')), 'Присутствие', 0.1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Творческая работа')), '1 место', 3),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Творческая работа')), '2 место', 2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Творческая работа')), '3 место', 1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Творческая работа')), 'Участие', 0.2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Творческая работа')), 'Присутствие', 0.1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'MAN_HOURS'
                               and category_uuid = (select uuid from categories where name = 'Социальная работа')), 'Часы', 0.5),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'MAN_HOURS'
                               and category_uuid = (select uuid from categories where name = 'Производственная работа')), 'Часы', 0.5),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), '1 место', 3),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), '2 место', 2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), '3 место', 1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), 'Участие', 0.2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), 'Присутствие', 0.1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'ATTENDANCE'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях')), 'Посещение', 1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), '1 место', 3),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), '2 место', 2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), '3 место', 1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), 'Участие', 0.2),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'PLACES'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), 'Присутствие', 0.1),
       (uuid_generate_v4(), (select uuid
                             from categories_evaluation_methods
                             where evaluation_method = 'ATTENDANCE'
                               and category_uuid = (select uuid from categories where name = 'Участие в мероприятиях штаба УрФУ')), 'Посещение', 1);
