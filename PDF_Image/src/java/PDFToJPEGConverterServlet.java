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
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@MultipartConfig
public class PDFToJPEGConverterServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final String OUTPUT_DIRECTORY = "output";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve file from the request
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

        // Save the uploaded PDF file to the uploads directory
        String fileName = filePart.getSubmittedFileName();
        File pdfFile = new File(uploadsDir, fileName);
        filePart.write(pdfFile.getAbsolutePath());

        // Create a timestamp for unique output filenames
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            // Convert each page of the PDF to JPEG and save it in the output directory with unique filenames
            for (int page = 0; page < pageCount; page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String outputFileName = "DarshanConvertor_" + (page + 1) + "_" + timestamp + ".jpg";
                File outputFile = new File(outputDir, outputFileName);
                ImageIO.write(image, "JPEG", outputFile);
            }

            // Set success message and list of output files
            request.setAttribute("message", "PDF converted successfully.");
            request.setAttribute("outputFiles", outputDir.listFiles());
        } catch (IOException e) {
            request.setAttribute("message", "Error converting PDF to JPEG: " + e.getMessage());
        }

        // Forward the request back to the JSP page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
