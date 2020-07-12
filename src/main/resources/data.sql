insert into asset(id,name, brand, category, price, description, stock, img_url)values
    (1, 'odkurzacz', 'Bosh', 'CATEGORY1', 200.12, 'test', 3, null),
    (2, 'telewizor', 'Samsung', 'CATEGORY2', 1200.12, 'test', 6, null);
insert into client(id, firstname, surname, email)values
    (1, 'Jan', 'Kowalski', 'test@gmail.com'),
    (2, 'Piotr', 'Nowak', 'test2@gmail.com');

insert into order_details(id,street, house_number, city, zip,country,telephone)values
    (1, 'Slowackiego', '20', 'Radzyn Podlaski', '21-300', 'Polska', '215215125');

    insert into ordert(id, client_id, order_details_id, status )values
(1, 2 ,1 ,'NEW');



insert into order_assets(order_id, asset_id)values
    (1,2);

