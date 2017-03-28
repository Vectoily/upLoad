package com.test.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Encoder;

public class DownLoad extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName=request.getParameter("fileName");
		fileName=new String(fileName.getBytes("iso8859-1"),"utf-8");
		File file=new File("e:/upload/"+fileName);
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName);
		if (file.exists()) {
			FileInputStream fis=new FileInputStream(file);
			OutputStream os=response.getOutputStream();
			int len=0;
			byte[] by=new byte[1024 * 100];
			while((len=fis.read(by))!=-1){
				os.write(by, 0, len);
				os.flush();
			}
			os.close();
			fis.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
