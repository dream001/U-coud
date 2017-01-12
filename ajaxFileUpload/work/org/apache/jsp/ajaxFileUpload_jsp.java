package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajaxFileUpload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Ajax File Uploader Plugin For Jquery</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"ajaxfileupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction ajaxFileUpload() {\r\n");
      out.write("\t\t$.ajaxFileUpload({\r\n");
      out.write("\t\t\turl : 'upload',\r\n");
      out.write("\t\t\tsecureuri : false,\r\n");
      out.write("\t\t\tfileElementId : 'fileToUpload',\r\n");
      out.write("\t\t\tdataType : 'json',\r\n");
      out.write("\t\t\tdata : {username : $(\"#username\").val()},\r\n");
      out.write("\t\t\tsuccess : function(data, status) {\r\n");
      out.write("\t\t\t\t$('#viewImg').attr('src',data.picUrl);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\terror : function(data, status, e) {\r\n");
      out.write("\t\t\t\talert('上传出错');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>选择图片后,点击按钮上传</h1>\r\n");
      out.write("\t<input id=\"fileToUpload\" type=\"file\" size=\"45\" name=\"fileToUpload\"\r\n");
      out.write("\t\tclass=\"input\">\r\n");
      out.write("\t<button class=\"button\" onclick=\"ajaxFileUpload()\">上传</button>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<img id=\"viewImg\">\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
