INSERT INTO tb_clientepf (`id_cliente`,`nome`,`cpf`)VALUES (1, "Adm", "123.456.789-00");
INSERT INTO tb_usuario VALUES (1, "admin", "$2a$10$WB2Kq8ELCz2qFynlpjINpuGcmC9Lb7OfyemLLluIII3aYKeHKctRq", 1);
UPDATE tb_clientepf SET `id_usuario` = '1' WHERE (`id_cliente` = '1');
INSERT INTO `banco_vendas`.`tb_role` (`id_role`, `nome`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `banco_vendas`.`tb_role` (`id_role`, `nome`) VALUES ('2', 'ROLE_EDITOR');
insert into tb_usuarios_roles values (1,1);