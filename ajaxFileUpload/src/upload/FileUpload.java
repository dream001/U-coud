package upload;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUpload {
	
	 private static String uploadPath = null;
	 private static String tempPath = null; 
	 private static File uploadFile = null;
	 private static File tempPathFile = null;
	 private static int sizeThreshold = 1024;
	 private static int sizeMax = 4194304;
	
	 static{
		   sizeMax  = Integer.parseInt(FileConst.getValue("sizeMax"));
		   sizeThreshold = Integer.parseInt(FileConst.getValue("sizeThreshold"));
		   uploadPath = FileConst.getValue("uploadPath");
		   uploadFile = new File(uploadPath);
	       if (!uploadFile.exists()) {
	           uploadFile.mkdirs();
	       }
	       tempPath = FileConst.getValue("tempPath");
	       tempPathFile = new File(tempPath);
	       if (!tempPathFile.exists()) {
	           tempPathFile.mkdirs();
	       }
	 }
	/**
	 * 
	 * @param request
	 * @return true 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> upload(HttpServletRequest request){
		
		boolean flag = true;

		Map<String,Object> map = new HashMap<String,Object>();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart){
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(sizeThreshold); 
				factory.setRepository(tempPathFile);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(sizeMax);
				List<FileItem> items = upload.parseRequest(request);
				
				if(!checkFileType(items)) return null;
				Iterator<FileItem> itr = items.iterator();
			
				while (itr.hasNext()){
					 FileItem item = (FileItem) itr.next();
					 if (!item.isFormField()){
							 String name = item.getName();
							 if(name!=null){
								 File fullFile=new File(item.getName());
								 String filename = fullFile.getName();
								 String saveName = new Date().getTime() + filename.substring(filename.lastIndexOf("."));
								 String picUrl = getPriPath(uploadPath) + saveName;

								 File savedFile=new File(uploadPath,saveName);
								 item.write(savedFile);
								 
								 map.put("itemSize", item.getSize());
								 map.put("filename", filename);
								 map.put("picUrl", picUrl);								 
							 }
					 }
				}
			} catch (FileUploadException e) {
				flag = false;
				e.printStackTrace();
			}catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		}else{
			flag = false;
			System.out.println("the enctype must be multipart/form-data");
		}
		if(flag){
			return map;
		}
		return null;
	}
	
	private static String getPriPath(String uploadPath){
		
		StringBuffer sb = new StringBuffer();
		
		String[] tar = uploadPath.split("/");
		
		sb.append("/");
		sb.append(tar[tar.length-1]);
		sb.append("/");
		
		return sb.toString();
	}
	
	/**
	 *
	 * @param filePath
	 */
	public static void deleteFile(String [] filePath){
		if(filePath!=null && filePath.length>0){
			for(String path:filePath){
				String realPath = uploadPath+path;
				File delfile = new File(realPath);
				if(delfile.exists()){
					delfile.delete();
				}
			}
			
		}
	}
	
	/**
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath){
		if(filePath!=null && !"".equals(filePath)){
			String [] path = new String []{filePath};
			deleteFile(path);
		}
	}
	
	/**
	 *
	 * @param items
	 * @return
	 */
	private static Boolean checkFileType(List<FileItem> items){
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()){
			 FileItem item = (FileItem) itr.next();
			 if (!item.isFormField()){
					 String name = item.getName();
					 if(name!=null){
						 File fullFile=new File(item.getName());
						 boolean isType = ReadUploadFileType.readUploadFileType(fullFile);
						 if(!isType) return false;
						 break;
					 }
			 }
		}
		
		return true;
	}
}
