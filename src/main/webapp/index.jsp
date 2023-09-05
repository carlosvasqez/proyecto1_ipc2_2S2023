<%-- 
    Document   : index
    Created on : 1 sept 2023, 20:02:11
    Author     : usuario
--%>

<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>login</title>
        <jsp:directive.include file="includes/bootstrap_css.jsp" />
    </head>
    <body>
        <main class="container">
            <form action="${pageContext.request.contextPath}/NewServlet2" method="POST" enctype="multipart/form-data">
                <div class="bg-body-tertiary p-5 rounded">
                    <h1>Carga de Archivo Inicial json</h1>
                    <p class="lead">Cargue el archivo json con los datos.</p>
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Default file input example</label>
                        <input class="form-control" type="file" id="formFile" name="archivoJson" accept=".json"/>
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary">Subir Archivo 📁</button>
                </div>
            </form>
        </main>
        <jsp:directive.include file="includes/bootstrap_js.jsp" />
    </body>
</html>
