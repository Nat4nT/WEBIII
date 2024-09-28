
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifpr.foz.ifprstore.models.Author" %>
<%@ page import="br.edu.ifpr.foz.ifprstore.models.Book" %>
<%@ page import="br.edu.ifpr.foz.ifprstore.models.BookStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    Book book = (Book) request.getAttribute("book");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administração de Livros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">

</head>
<body class="bg-light">

<!-- Menu superior -->
<nav class="navbar navbar-expand-lg shadow-sm navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/IFPRStore_war_exploded/"> <img src="images/logo.png" alt="">Book Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/IFPRStore_war_exploded/">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Autores</a>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <!-- Barra lateral -->
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">
                            <span data-feather="home"></span>
                            Dashboard
                        </a>
                    </li>

                </ul>
            </div>
        </nav>

        <!-- Conteúdo principal -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Edição de Livros</h1>
            </div>

            <form action="<%= request.getContextPath() %>/books/update" method="post">

                <div class="mb-3">
                    <label for="id" class="form-label">Id: </label>
                    <input type="text" class="form-control" id="id" name="field_id" value="<%= book.getId() %>" readonly>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">Nome: </label>
                    <input type="text" class="form-control" id="name" name="field_name" value="<%= book.getName() %>">
                </div>

                <div class="mb-3">
                    <label for="date" class="form-label">Data Lançamento: </label>
                    <input type="date" class="form-control" id="date" name="field_date" value="<%= book.getDate() %>">
                </div>

                <div class="mb-3">
                    <label for="field_author" class="form-label">Autor: </label>
                    <select class="form-select" name="field_author" id="field_author">
                        <option>Selecione um Autor...</option>
                        <% for (Author author: authors) {%>

                        <% String selected = ((book.getAuthor().getId()) == author.getId()) ? "selected" : "no"; %>
                        <option <%= selected %> value="<%= author.getId()%>"><%=author.getName() %></option>
                        <% } %>

                    </select>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label">Status: </label>
                    <select class="form-select" name="status" id="status">
                        <option selected>Selecione um departamento...</option>


                        <% for (BookStatus status : BookStatus.values()) {%>

                        <% String selected2 = ((book.getStatus().name()).equals( status.name())) ? "selected" : ""; %>
                        <option <%= selected2 %> value="<%= status.name()%>"><%=status.getStatus() %></option>
                        <% } %>

                    </select>
                </div>
                <div class="col-12">
                    <button class="btn btn-primary btn-sm px-5" type="submit">atualizar</button>
                </div>

            </form>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>