package util.uploader;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.constants.Constants;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class Uploader implements Constants {
    public File uploadFile(HttpServletRequest request) throws FileUploadException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_NAME);
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = servletFileUpload.parseRequest(request);
        File file = null;

        for (FileItem item : items) {
            if (!item.isFormField()) {
                file = new File(rb.getString(POSTER_DIR).trim() + item.getName());
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println(file.getAbsoluteFile());
                }
                try {
                    item.write(file);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
        return  file;
    }
}
