package com.test.upLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UpLoad extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path=request.getServletContext().getRealPath("/upload");
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(new File(path));
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		try {
			List<FileItem> items=upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
//					String filedName=fileItem.getFieldName();
//					System.out.println(filedName);
//					String name=fileItem.getName();
//					System.out.println(name);
				}else {
//					String filedName=fileItem.getFieldName();
//					System.out.println(filedName);
					String name=fileItem.getName();
//					String realname=name.substring(name.indexOf("\\")+1);
					System.out.println(name);
					FileOutputStream outputStream=new FileOutputStream("e:/upload/"+name);
					IOUtils.copy(fileItem.getInputStream(), outputStream);
					fileItem.delete();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
