insert into asset(id,name, serial_number, brand, category, price, description, stock, img_url)values
    (1, 'odkurzacz', '2155612512512','Bosh', 'CATEGORY1', 200.12, 'test', 3, null),
    (2, 'telewizor',  '638126091286018', 'Samsung', 'CATEGORY2', 1200.12, 'test', 6, null);
insert into client(id, firstname, surname, email)values
    (1, 'Jan', 'Kowalski', 'test@gmail.com'),
    (2, 'Piotr', 'Nowak', 'test2@gmail.com');

insert into order_details(id,street, house_number, city, zip,country,telephone)values
    (1, 'Slowackiego', '20', 'Radzyn Podlaski', '21-300', 'Polska', '215215125');

insert into position (id,position_name,hourly_wage)values
    (1,'PACKER',43.2);

insert into position (id,position_name,hourly_wage)values
    (2,'MANAGER',63.2);

insert into employee(id, name,surname,position_id,hours_worked,is_on_payroll)values
(1,'Cezary','Kępa',1,22,true);

insert into ordert(id, client_id, order_details_id, status,employee_id )values
(1, 2 ,1 ,'NEW',1);


insert into order_assets(order_id, asset_id)values
    (1,2);
