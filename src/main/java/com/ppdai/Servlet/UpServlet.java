package com.ppdai.Servlet;

import com.ppdai.Dao.ImageDao;
import com.ppdai.POJO.Image;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UpServlet extends HttpServlet {
    private static final long serialVersionUID = 9040092588964420552L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        boolean flag = ServletFileUpload.isMultipartContent(request);
        ImageDao dao = new ImageDao();
        if (flag) {
            DiskFileItemFactory factory = new DiskFileItemFactory(10000, new File("/"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem fileItem : items) {
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();
                        long time = System.currentTimeMillis();
                        fileName = time + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                        System.out.println("重命名: " + fileName);
                        String sName = "upload\\" + fileName;
                        String path = this.getServletContext().getRealPath("upload");
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        try {
                            fileItem.write(new File(dir, fileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        fileItem.delete(); // 删除临时文件

                        Image image=new Image();
                        image.setUsernameurl(sName);
                        dao.images(image);
//                        dao.images(sName);
                        out.println("提交成功！等待管理员审核");
                        response.setHeader("refresh", "2;url=index.jsp");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

        out.flush();
        out.close();
    }
}
