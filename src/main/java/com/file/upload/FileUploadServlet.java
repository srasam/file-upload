package com.file.upload;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
class FileUploadServlet extends HttpServlet {
	
	private final String upload_directory = "C:/uploads";
	   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
               
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(upload_directory + File.separator + name));
                    }
                }
            
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            request.setAttribute("message",
                                 "Servlet only handles file upload request");
        }
     
        request.getRequestDispatcher("/message.jsp").forward(request, response);
      
    }
 // implementation -2
//    private static final long serialVersionUID = 1L;
//     
//    // location to store file uploaded
//    private static final String UPLOAD_DIRECTORY = "upload";
// 
//    // upload settings
//    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
// 
//    /**
//     * Upon receiving file upload submission, parses the request to read
//     * upload data and saves the file on disk.
//     */
//    protected void doPost(HttpServletRequest request,
//            HttpServletResponse response) throws ServletException, IOException {
//        // checks if the request actually contains upload file
//        if (!ServletFileUpload.isMultipartContent(request)) {
//            // if not, we stop here
//            PrintWriter out = response.getWriter();
//            out.println("Error: upload a file, Form must have enctype=multipart/form-data.");
//            out.flush();
//            return;
//        }
// 
//        // configures upload settings
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        // sets memory threshold - beyond which files are stored in disk
//        factory.setSizeThreshold(MEMORY_THRESHOLD);
//        // sets temporary location to store files
//        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
// 
//        ServletFileUpload upload = new ServletFileUpload(factory);
//         
//        // sets maximum size of upload file
//        upload.setFileSizeMax(MAX_FILE_SIZE);
//         
//        // sets maximum size of request (include file + form data)
//        upload.setSizeMax(MAX_REQUEST_SIZE);
// 
//        // constructs the directory path to store upload file
//        // this path is relative to application's directory
//        String uploadPath = getServletContext().getRealPath("")
//                + File.separator + UPLOAD_DIRECTORY;
//        
//        System.out.println("uploadPath:" +uploadPath);
//         
//        // creates the directory if it does not exist
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
// 
//        try {
//            // parses the request's content to extract file data
//            @SuppressWarnings("unchecked")
//            List<FileItem> formItems = upload.parseRequest(request);
// 
//            if (formItems != null && formItems.size() > 0) {
//                // iterates over form's fields
//                for (FileItem item : formItems) {
//                    // processes only fields that are not form fields
//                    if (!item.isFormField()) {
//                        String fileName = new File(item.getName()).getName();
//                        String filePath = uploadPath + File.separator + fileName;
//                        File storeFile = new File(filePath);
// 
//                        // saves the file on disk
//                        item.write(storeFile);
//                        request.setAttribute("message",
//                            "uploaded file successfully!");
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            request.setAttribute("message",
//                    "There was an error: " + ex.getMessage());
//        }
//        // redirects client to message page
//        getServletContext().getRequestDispatcher("/message.jsp").forward(
//                request, response);
//    }
	
   
}