--В одном запросе получить
--имена всех person, которые не состоят в компании с id = 5;
--название компании для каждого человека.
select person.name, company.name from person LEFT JOIN company on company_id = company.id where company_id != 5;

--Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
select company.name, count (*) from person LEFT JOIN company on company_id = company.id group by company.name
order by count (*) desc LIMIT 1;

