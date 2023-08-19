INSERT INTO file_manager_folder(name,parent,created_On) VALUES ('teste1',0, NOW());
INSERT INTO file_manager_folder(name, parent,created_On) VALUES ('teste2',0, NOW());

INSERT INTO file_manager(folder, file_name, real_name,file_size,file_type,created_On) VALUES (1, 'ababab1', 'Cortador de pizza',135.25,'stl',NOW());
INSERT INTO file_manager(folder, file_name, real_name,file_size,file_type,created_On) VALUES (1, 'ababab2', 'prato de pizaa',1380.72,'stl',NOW());
INSERT INTO file_manager(folder, file_name, real_name,file_size,file_type,created_On) VALUES (2, 'cbcbcb1', 'Casquinha doce',145698.23,'gcode',NOW());
INSERT INTO file_manager(folder, file_name, real_name,file_size,file_type,created_On) VALUES (2, 'cbcbcb2', 'Casquinha salgada',1,'gcode',NOW());