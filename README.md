#  PW2 Sistemas de Vendas

## Atividade 1

Com JPA você aprendeu que podemos gerar nossas tabelas no banco de dados usando anotações. Deste modo, crie um novo projeto e crie entidades conforme diagrama de classe a seguir.

Você deve:

* Definir os **relacionamento das associações entre as classes** usando as anotações JPA. :white_check_mark:

conforme instruções do material em [https://fagno.github.io/pwebii-spring-ifto/jpa/#_mapeamento_com_associa%C3%A7%C3%B5es](https://fagno.github.io/pwebii-spring-ifto/jpa/#_mapeamento_com_associações).

**Você deve inserir os dados do(s) produto(s) e venda(s) diretamente no banco.**

Não é necessário criar a interface web completa do projeto, você deve apenas: 

* Criar **list.html** de **Venda** e apresentar os seguintes dados (**ID,** **DATA** E **TOTAL**) da(s) venda(s). :white_check_mark:
* Crie o Repository e Controller de **Venda** para apresentar os dados solicitados. :white_check_mark:

## Atividade 2

Nesta tarefa, você deve complementar o projeto da [Mapeamento com associações usando JPA](https://moodlepalmas.ifto.edu.br/moodle/mod/assign/view.php?id=63417) e criar o carrinho de compras.

**NOTAS:**

* Alterar a associação entre **ItemVenda** e **Venda** para navegabilidade bidirecional; :white_check_mark:

* Criar **FORM de cadastro** e apresentar **lista de produtos**; :white_check_mark:

* Criar **FORM de venda**, na qual, deve permitir selecionar o itens que deseja comprar, informar quantidade e finalizar venda; :white_check_mark:

* Por fim, criar **view** para apresentar lista de vendas (já feito na tarefa anterior). :white_check_mark:

## Atividade 3

Nesta tarefa, você deve complementar o projeto Carrinho de Compras, conforme a seguir.

* incluir o BootStrap (https://getbootstrap.com/) no seu projeto, dependência a seguir.

* incluir as views header e footer (disponível anexo a tarefa) usando fragments do Spring.

Ao incluir os arquivos header.html e footer.html (modelo a ser utilizado), faça o insert nas outras páginas HTML. Para incluir o cabeçalho e rodapé basta inserir a linha de código a seguir na sua página. NOTA: O código disponível no header e footer não precisar ser repetido nas suas views.

Para header (NOTA: Incluir NavBar no Header):

```html
<div th:insert="fragments/header :: modelheader"></div>
````

Para footer:

```html
<div th:insert="fragments/footer :: modelfooter"></div>
````

*  criar view home.html para seu projeto e utilizar a classe ConfiguracaoSpringMvc.java anexo a atividade para carregar a mesma.