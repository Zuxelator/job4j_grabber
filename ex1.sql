select company.name, count (*) from company LEFT JOIN person on company_id = company.id group by company.name
order by count (*) desc LIMIT 1;