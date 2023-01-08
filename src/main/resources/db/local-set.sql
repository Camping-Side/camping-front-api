create user 'scott'@'%' identified by '1234';
create user 'scott'@'localhost' identified by '1234';

grant select, insert, update, delete on camping.* to 'scott'@'localhost';
grant all privileges on camping.* to 'scott'@'localhost';

flush privileges ;