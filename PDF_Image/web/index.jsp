<%-- 
    Document   : index
    Created on : 14 Oct, 2024, 11:49:22 PM
    Author     : Darshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>PDF to JPEG Converter</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        .message {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h2>Convert PDF to JPEG</h2>

    <form action="PDFToJPEGConverterServlet" method="post" enctype="multipart/form-data">
        <label for="pdfFile">Select PDF to upload:</label>
        <input type="file" name="pdfFile" id="pdfFile" accept="application/pdf" required><br><br>
        <button type="submit">Upload and Convert</button>
    </form>

    <div>
        <c:if test="${not empty message}">
            <p class="${not empty outputFiles ? 'message' : 'error'}">${message}</p>
        </c:if>

        <c:if test="${not empty outputFiles}">
            <h3>Converted JPEG Files:</h3>
            <ul>
                <c:forEach var="file" items="${outputFiles}">
                    <li><a href="${pageContext.request.contextPath}/output/${file.name}" download>${file.name}</a></li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</body>
</html>
