create or replace function increment_event_users_counter()
    returns trigger as $$
begin
    update events
    set number_users = number_users + 1
    where uuid = new.event_uuid;
    return new;
end;
$$ language plpgsql;

create trigger increment_event_users_trigger
    after insert on users_events
    for each row
execute function increment_event_users_counter();


create or replace function decrement_event_users_counter()
    returns trigger as $$
begin
    update events
    set number_users = number_users - 1
    where uuid = old.event_uuid;
    return old;
end;
$$ language plpgsql;

create trigger decrement_event_users_trigger
    after delete on users_events
    for each row
execute function decrement_event_users_counter();


CREATE OR REPLACE FUNCTION set_number_users_default()
    RETURNS trigger AS $$
BEGIN
    IF NEW.number_users IS NULL THEN
        NEW.number_users := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_events
    BEFORE INSERT ON events
    FOR EACH ROW
EXECUTE FUNCTION set_number_users_default();

