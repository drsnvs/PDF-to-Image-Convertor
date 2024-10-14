//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.rendering.ImageType;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@MultipartConfig
//public class PDFToJPEGConverterServlet extends HttpServlet {
//    // Set directories for uploads and outputs
//    private static final String UPLOAD_DIRECTORY = "uploads";
//    private static final String OUTPUT_DIRECTORY = "output";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Retrieve file from the request
//        Part filePart = request.getPart("pdfFile");
//        if (filePart == null || filePart.getSize() == 0) {
//            request.setAttribute("message", "Please upload a valid PDF file.");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            return;
//        }
//
//        // Create directories for uploads and outputs if they don't exist
//        String uploadsPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//        File uploadsDir = new File(uploadsPath);
//        if (!uploadsDir.exists()) uploadsDir.mkdirs();
//
//        // Save the uploaded PDF file to the uploads directory
//        String fileName = filePart.getSubmittedFileName();
//        File pdfFile = new File(uploadsDir, fileName);
//        filePart.write(pdfFile.getAbsolutePath());
//
//        // Create a timestamp for unique output filenames
//        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//        // Get the path to the user's Downloads folder
//        String userHome = System.getProperty("user.home");
//        File downloadsDir = Paths.get(userHome, "Downloads").toFile(); // Locate the Downloads folder
//
//        try (PDDocument document = Loader.loadPDF(pdfFile)) {
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//            int pageCount = document.getNumberOfPages();
//
//            // Convert each page of the PDF to JPEG and save it in the Downloads directory
//            for (int page = 0; page < pageCount; page++) {
//                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//                String outputFileName = "DarshanConvertor_" + (page + 1) + "_" + timestamp + ".jpg";
//                File outputFile = new File(downloadsDir, outputFileName);  // Save to Downloads folder
//                ImageIO.write(image, "JPEG", outputFile);
//            }
//
//            // Set success message and list of output files
//            request.setAttribute("message", "PDF converted successfully.");
//            request.setAttribute("outputFiles", downloadsDir.listFiles());  // List the files in the Downloads folder
//        } catch (IOException e) {
//            request.setAttribute("message", "Error converting PDF to JPEG: " + e.getMessage());
//        }
//
//        // Forward the request back to the JSP page
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
//}
//
//
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.rendering.ImageType;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@MultipartConfig
//public class PDFToJPEGConverterServlet extends HttpServlet {
//    private static final String UPLOAD_DIRECTORY = "uploads";
//    private static final String OUTPUT_DIRECTORY = "output";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Part filePart = request.getPart("pdfFile");
//        if (filePart == null || filePart.getSize() == 0) {
//            request.setAttribute("message", "Please upload a valid PDF file.");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            return;
//        }
//
//        // Create directories for uploads and outputs if they don't exist
//        String uploadsPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//        String outputPath = getServletContext().getRealPath("") + File.separator + OUTPUT_DIRECTORY;
//        File uploadsDir = new File(uploadsPath);
//        File outputDir = new File(outputPath);
//        if (!uploadsDir.exists()) uploadsDir.mkdirs();
//        if (!outputDir.exists()) outputDir.mkdirs();
//
//        // Clear previous output files
//        for (File file : outputDir.listFiles()) {
//            file.delete();
//        }
//
//        // Save the uploaded PDF file to the uploads directory
//        String fileName = filePart.getSubmittedFileName();
//        File pdfFile = new File(uploadsDir, fileName);
//        filePart.write(pdfFile.getAbsolutePath());
//
//        // Create a timestamp for unique output filenames
//        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//        try (PDDocument document = Loader.loadPDF(pdfFile)) {
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//            int pageCount = document.getNumberOfPages();
//
//            // Convert each page of the PDF to JPEG and save it in the output directory
//            for (int page = 0; page < pageCount; page++) {
//                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//                String outputFileName = "DarshanConvertor_" + (page + 1) + "_" + timestamp + ".jpg";
//                File outputFile = new File(outputDir, outputFileName); // Save to web-accessible output directory
//                ImageIO.write(image, "JPEG", outputFile);
//            }
//
//            // Set success message and list of output files
//            request.setAttribute("message", "PDF converted successfully.");
//            request.setAttribute("outputFiles", outputDir.listFiles()); // List the files in the output directory
//        } catch (IOException e) {
//            request.setAttribute("message", "Error converting PDF to JPEG: " + e.getMessage());
//        }
//
//        // Forward the request back to the JSP page
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
//}


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class PDFToJPEGConverterServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads"; // Folder for uploaded PDFs
    private static final String OUTPUT_DIRECTORY = "output"; // Folder for output images

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("pdfFile");
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("message", "Please upload a valid PDF file.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // Create directories for uploads and outputs if they don't exist
        String uploadsPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        String outputPath = getServletContext().getRealPath("") + File.separator + OUTPUT_DIRECTORY;
        File uploadsDir = new File(uploadsPath);
        File outputDir = new File(outputPath);
        if (!uploadsDir.exists()) uploadsDir.mkdirs();
        if (!outputDir.exists()) outputDir.mkdirs();
//        if (!uploadsDir.exists()) uploadsDir.mkdirs();

        // Clear previous output files
        for (File file : outputDir.listFiles()) {
            file.delete();
        }
        
        // Save the uploaded PDF file to the uploads directory
        String fileName = filePart.getSubmittedFileName();
        File pdfFile = new File(uploadsDir, fileName);
        filePart.write(pdfFile.getAbsolutePath());

        List<String> outputFileNames = new ArrayList<>(); // Store output filenames
        // Create a timestamp for unique output filenames
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        
        String userHome = System.getProperty("user.home");
        File downloadsDir = Paths.get(userHome, "Downloads").toFile();
        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            // Convert each page of the PDF to JPEG and save it in the output directory
            for (int page = 0; page < pageCount; page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String outputFileName = "DarshanConvertor_" + (page + 1) + "_" + timestamp + ".jpg";
                File outputFile = new File(downloadsDir, outputFileName); // Save to web-accessible output directory
                ImageIO.write(image, "JPEG", outputFile);
                outputFileNames.add(outputFileName); // Add filename to the list
            }

            // Set success message and list of output files
            request.setAttribute("message", "PDF converted successfully.");
            request.setAttribute("outputFiles",outputFileNames); // List the files in the output directory
        } catch (IOException e) {
            request.setAttribute("message", "Error converting PDF to JPEG: " + e.getMessage());
        }

        // Forward the request back to the JSP page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
