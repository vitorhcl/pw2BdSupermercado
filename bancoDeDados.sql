-- Rodar tudo na ordem pfv <3

create database supermercado;

use supermercado;

use master; drop database supermercado;

create table Cliente (
cod_clie char (5) primary key,
cpf_clie char (11)
);

create table Produto(
cod_bar char (13) primary key,
nome_prod varchar (15) not null,
preco_venda numeric (10,2) not null,
preco_custo numeric (10,3) not null,
estoque numeric (5) not null,
categoria varchar (20) not null
);

create table Form_pag (
cod_pag numeric (1) primary key,
nome_pag varchar (12) not null
);

create table Atendente (
cpf_func char (11) primary key,
salr_func numeric (7,2) not null,
nome_aten varchar (12) not null
);

create table Pedido (
num_pedido numeric (6) primary key,
cod_clie char (5) references Cliente not null,
cod_pag numeric (1) references Form_pag not null,
cpf_func char (11) references Atendente not null
);

create table nf (
num_nf numeric (4) primary key,
total_nf numeric (10,2)  not null,
qtd_total numeric (3) not null,
cod_bar char (13) not null,
data_emi date not null,
);

create table Fornecedor (
cnpj_forn char (14) primary key,
nome_forn varchar (10) not null
);

create table Ped_prod (
qtd_ped numeric (3) not null,
cod_bar char (13) references Produto not null,
num_pedido numeric (6) references Pedido not null
);

create table Forn_prod (
cnpj_forn char (14) references Fornecedor not null,
cod_bar char (13) references Produto not null
);

insert into Cliente values (1, '53665812420');
insert into Cliente values (2, '85236974105');
insert into Cliente values (3, '78945612335');
insert into Cliente values (4, '36985214740');
insert into Cliente values (5, '95175315308');

insert into Produto values ('1234567891253', 'Arroz', 7.50, 5.00, 35, 'Grãos');
insert into Produto values ('5236987410325', 'Sabonete', 2.50, 1.00, 49, 'Higiene');
insert into Produto values ('7412365894563', 'Biscoito', 1.50, 0.50, 70, 'Doces');
insert into Produto values ('1574896325874', 'Pão', 4.00, 2.50, 80, 'Pães');
insert into Produto values ('7896541230158', 'Candida', 8.50, 6.50, 15, 'Produto de Limpeza');

insert into Form_pag values ('0', 'Débito');
insert into Form_pag values ('1', 'Crédito');
insert into Form_pag values ('2', 'Dinheiro');
insert into Form_pag values ('3', 'VR');
insert into Form_pag values ('4', 'Pix');

insert into Atendente values ('42536898540', 2500, 'Valeria');
insert into Atendente values ('78956412305', 4500, 'Roberto');
insert into Atendente values ('74598632148', 1500, 'Pedro');
insert into Atendente values ('24585632149', 4800, 'Paula');
insert into Atendente values ('22352651342', 5500, 'Paulo');

insert into Fornecedor values ('53698745632145', 'Camil');
insert into Fornecedor values ('15698745632145', 'Namorado');
insert into Fornecedor values ('15478965231023', 'Dove');
insert into Fornecedor values ('98745632102369', 'Panco');
insert into Fornecedor values ('74589632145897', 'Adria');

insert into Forn_prod values ('53698745632145', '1234567891253');
insert into Forn_prod values ('15698745632145', '5236987410325');
insert into Forn_prod values ('15478965231023', '7412365894563');
insert into Forn_prod values ('98745632102369', '1574896325874');
insert into Forn_prod values ('74589632145897', '7896541230158');

insert into Pedido values ('569874','1', '0', '42536898540'); 
insert into Pedido values ('458785','2', '1', '78956412305'); 
insert into Pedido values ('265894','3', '2', '74598632148'); 
insert into Pedido values ('987561','4', '3', '24585632149'); 
insert into Pedido values ('789654','5', '4', '22352651342');
insert into Pedido values ('190768','5', '4', '22352651342');

insert into nf values ('5698', 20.00, 2, '1234567891253', '20100612'); 
insert into nf values ('4589', 45.50, 4, '5236987410325', '21/jan/2011');
insert into nf values ('7598', 80.00, 6, '7412365894563', '20170819');
insert into nf values ('1589', 55.47, 7, '1574896325874', '14/mar/2015');
insert into nf values ('7412', 95.50, 15, '7896541230158', '15/jul/2014');
insert into nf values ('6584', 32.20, 78, '7896541230158', '14/set/2019');

insert into Ped_prod values (458, '1234567891253', '569874');
insert into Ped_prod values (741, '5236987410325', '458785');
insert into Ped_prod values (954, '7412365894563', '265894');
insert into Ped_prod values (456, '1574896325874', '987561');
insert into Ped_prod values (325, '7896541230158', '789654');
insert into Ped_prod values (987, '7896541230158', '190768');

alter table Pedido add num_nf numeric(4) references nf;
alter table nf add num_pedido numeric(6) references Pedido;

update Pedido set num_nf = '5698' where num_pedido = '569874';
update Pedido set num_nf = '4589' where num_pedido = '458785';
update Pedido set num_nf = '7598' where num_pedido = '265894';
update Pedido set num_nf = '1589' where num_pedido = '987561';
update Pedido set num_nf = '7412' where num_pedido = '789654';
update Pedido set num_nf = '6584' where num_pedido = '190768';

update nf set num_pedido = '569874' where num_nf = '5698';
update nf set num_pedido = '458785' where num_nf = '4589';
update nf set num_pedido = '265894' where num_nf = '7598';
update nf set num_pedido = '987561' where num_nf = '1589';
update nf set num_pedido = '789654' where num_nf = '7412';
update nf set num_pedido = '190768' where num_nf = '6584';

-------------------------------------------------------------------------------------------------------------------

--Relatório de atendentes que tenham nomes que comecem com as letras "P" e ganhem mais de 2000;
--Faça um acréscimo de 10% nos salários destes funcionários e mostre os nomes em maiúsculo.
select cpf_func, upper(nome_aten) as Nome, salr_func, salr_func * 1.1 as "Acrescimo 10%" from Atendente where nome_aten like 'P%' and salr_func > 2000;

--Relatório de produtos alimentícios e que tenham preço de venda menor que 5 (ordenar pelo preço em ordem decrescente).
select * from Produto where categoria in ('Grãos','Pães','Doces') and preco_venda < 5 order by preco_venda DESC;

--Relatório de quantos clientes tem o cpf que termine com 8 ou 0.
select count(cod_clie) as "Qtd Clientes" from Cliente where cpf like '%8' or cpf like '%0';

--Relatório para calcular a média dos salarios de valeria e de roberto. 
--Exibir como Média Salarial.
select avg(salr_func) as "Media Salarial" from Atendente where nome_aten = 'Valeria' or nome_aten = 'Roberto';

--Relatório que mostra a data, o numero e os valores das notas fiscais emitidas a partir de 2014
--e que tenham valor entre 50 e 100 reais. Arredonde os valores.
select num_nf, round(total_nf, 1) as "Valor total", data_emi from nf where data_emi > '2014' and total_nf between 50 and 100;


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Relatórios com joins :)
--Relatório dos fornecedores(nome) que vendem arroz.
select f.nome_forn as Fornecedor, p.nome_prod as 'Nome Produto' from Forn_prod as fp inner join Fornecedor as f on fp.cnpj_forn = f.cnpj_forn inner join Produto as p on fp.cod_bar = p.cod_bar where p.nome_prod = 'Arroz'

--Relatório de quantos pedidos cada atendente (nome) atendeu
select count(num_pedido) as 'Numero Pedidos', a.nome_aten from Pedido as p inner join Atendente as a on p.cpf_func = a.cpf_func group by a.nome_aten

--Relatório de todos os pedidos feitos por clientes com cpf que terminam com 0 ou 8.
select c.cod_clie, c.cpf_clie, num_pedido as "Núm. Pedidos" from Cliente as c left join Pedido as p on p.cod_clie = c.cod_clie where c.cpf_clie like '%8' or c.cpf like '%0';

--Relatório de produtos que foram vendidos mais de uma vez: seus nomes e seus pedidos
select pp.num_pedido, p.nome_prod from Ped_prod as pp right join Produto as p on p.cod_bar = pp.cod_bar where (select count(pr.nome_prod) from Produto as pr) > 1;

--Relatório de pedidos que foram pagos no pix
select fp.nome_pag, p.num_pedido from Pedido as p full join Form_pag as fp on p.cod_pag = fp.cod_pag where p.cod_pag = 5;

--Relatório do funcionário que tem o maior salário e seus pedidos
select a.nome_aten, p.num_pedido, a.salr_func from Pedido as p inner join Atendente as a on a.cpf_func = p.cpf_func where a.salr_func = (select max(a.salr_func) from Atendente as a);

--Relatório dos pedidos que contém cândida
select pp.num_pedido, pr.nome_prod from Ped_prod as pp left join Produto as pr on pr.cod_bar = pp.cod_bar where pr.nome_prod = 'Candida';

--Relatório das formas de pagamento usadas para pagar pedidos que contém sabonete
select pp.num_pedido, pr.nome_prod, fp.nome_pag from Ped_prod as pp right join Produto as pr on pr.cod_bar = pp.cod_bar inner join Pedido as p on p.num_pedido = pp.num_pedido left join Form_pag as fp on fp.cod_pag = p.cod_pag where pr.nome_prod = 'Sabonete';

--Relatório das formas de pagamento usadas para pagar pedidos que contém sabonete
select p.cod_clie, p.num_pedido, fp.nome_pag from Cliente as c inner join Pedido as p on p.cod_clie = c.cod_clie inner join Form_pag as fp on fp.cod_pag = p.cod_pag where p.cod_clie = 1;

--Relatório das formas de pagamento usadas para pagar pedidos que contém sabonete
select pp.num_pedido, pr.nome_prod, pr.categoria, fp.nome_pag from Ped_prod as pp right join Produto as pr on pr.cod_bar = pp.cod_bar inner join Pedido as p on p.num_pedido = pp.num_pedido left join Form_pag as fp on fp.cod_pag = p.cod_pag where pr.categoria = 'Grãos';