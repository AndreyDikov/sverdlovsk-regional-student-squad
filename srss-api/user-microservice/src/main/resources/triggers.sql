create or replace function update_number_users_on_add_user()
    returns trigger as $$
begin
    -- Увеличиваем количество пользователей на 1 в таблице squads
    update squads
    set number_users = number_users + 1
    where uuid = new.squad_uuid;
    return new;
end;
$$ LANGUAGE plpgsql;

create trigger trigger_add_user_to_squad
    after insert on additional_users_data
    for each row
execute function update_number_users_on_add_user();


create or replace function update_number_users_on_remove_user()
    returns trigger as $$
begin
    -- Уменьшаем количество пользователей на 1 в таблице squads
    update squads
    set number_users = number_users - 1
    where uuid = old.squad_uuid;
    return old;
end;
$$ language plpgsql;

create trigger trigger_remove_user_from_squad
    after delete on additional_users_data
    for each row
execute function update_number_users_on_remove_user();


create or replace function initialize_number_users_on_insert_squad()
    returns trigger as $$
begin
    -- Инициализируем поле number_users в 0 при добавлении новой записи в таблицу squads
    new.number_users := 0;
    return new;
end;
$$ language plpgsql;

create trigger trigger_initialize_number_users_on_insert_squad
    before insert on squads
    for each row
execute function initialize_number_users_on_insert_squad();
