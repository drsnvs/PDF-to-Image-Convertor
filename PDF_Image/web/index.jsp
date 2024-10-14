<%-- 
    Document   : index
    Created on : 14 Oct, 2024, 11:49:22 PM
    Author     : Darshan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PDF to JPEG Converter</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .converted-image {
            max-width: 100%;
            height: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-top: 20px;
        }
        .file-location {
            font-style: italic;
            color: #6c757d;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card shadow-sm">
            <div class="card-header">
                <h3 class="card-title text-center">PDF to JPEG Converter</h3>
            </div>
            <div class="card-body">
                <form action="PDFToJPEGConverterServlet" method="post" enctype="multipart/form-data" class="mb-4">
                    <div class="mb-3">
                        <label for="pdfFile" class="form-label">Upload a PDF File</label>
                        <input type="file" class="form-control" name="pdfFile" id="pdfFile" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Convert to JPEG</button>
                </form>

                <!-- Display Conversion Message -->
                <c:if test="${not empty message}">
                    <div class="alert alert-info" role="alert">
                        ${message}
                    </div>
                </c:if>

                <!-- Display Converted Images and File Location -->
                <c:if test="${not empty outputFiles}">
                    <div class="row">
                        <c:forEach var="fileName" items="${outputFiles}">
                            <div class="col-md-4 text-center">
                                <img src="file://${user.home}/Downloads/${fileName}" class="converted-image" alt="Converted Image">
                                <p class="file-location">Stored at: ${user.home}/Downloads/${fileName}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>