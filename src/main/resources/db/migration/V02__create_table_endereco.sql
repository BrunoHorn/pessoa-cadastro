create table  endereco (
id bigserial primary key,
logradouro varchar(150),
cep varchar(8),
numero varchar(10),
cidade varchar(150),
principal boolean,
pessoa_id bigint,

	constraint fk_pessoa_id foreign key (pessoa_id) references pessoa (id)
)