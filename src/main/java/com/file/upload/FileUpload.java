package com.file.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class FileUpload extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> multipleFiles=upload.parseRequest(request);
			for(FileItem item:multipleFiles) {
				item.write(new File("E:\\eclipse workspace\\file-upload-jsp/"+ item.getName()));
			}
			System.out.println("file uploaded sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
