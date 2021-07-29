SELECT company.name, count (*) AS c 
FROM company LEFT JOIN person ON company_id = company.id GROUP BY company.name
HAVING count (*) = 
(SELECT max(count) FROM 
 (SELECT count (*) AS count 
  FROM company LEFT JOIN person ON company_id = company.id 
  GROUP BY company.name) AS A);
