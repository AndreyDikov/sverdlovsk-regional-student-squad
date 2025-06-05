alter table additional_users_data
add constraint fk_squad_uuid
foreign key (squad_uuid) references squads(uuid);
