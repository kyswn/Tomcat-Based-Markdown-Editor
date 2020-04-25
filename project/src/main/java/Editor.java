import java.io.IOException;
import java.sql.* ;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.RequestDispatcher;


import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Servlet implementation class for Servlet: ConfigurationTest
 *
 */
public class Editor extends HttpServlet {
    /**
     * The Servlet constructor
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public Editor() {}

    public void init() throws ServletException
    {
        try{Class.forName("com.mysql.jdbc.Driver");}
        catch (ClassNotFoundException e){
            System.out.println("cannot load sql driver");
        }
        System.out.println("starting the server");
    }
    
    public void destroy()
    {
        System.out.println("closing the server");
    }

    /**
     * Handles HTTP GET requests
     * 
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        System.out.println("inside doget");
        String action = request.getParameter("action");
        if(action == null){
            System.out.println("there is no action");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } 
        switch(action){
            case "open":
            open_Handler(request,response);
            break;
            case "save":
            System.out.println("save should use post");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            break;
            case "delete":
            System.out.println("delete should use post");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            break;
            case "preview":
            preview_Handler(request,response);
            break;
            case "list":
            list_Handler(request,response);
            //System.out.println(response.getStatus());
            break;
            default:
                System.out.println("Unrecognized action");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            
        }



        //request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
    
    /**
     * Handles HTTP POST requests
     * 
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        System.out.println("inside dopost");
        String action = request.getParameter("action");
        if(action == null){
            System.out.println("there is no action");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } 
        switch(action){
            case "open":
            open_Handler(request,response);
            break;
            case "save":
            save_Handler(request,response);
            break;
            case "delete":
            delete_Handler(request,response);
            break;
            case "preview":
            preview_Handler(request,response);
            break;
            case "list":
            list_Handler(request,response);
            break;
            default:
                System.out.println("Unrecognized action");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            
        }
    }

    public void  open_Handler(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //required parameters: username and postid
        //function: return the edit page for the post with the given postid by the user
        //If postid <= 0,
        //if title and body parameters have been passed, use the passed parameter values as the initial title and body values and return with status code 200 (OK)
        //otherwise, set missing title and/or body to empty string and return with status code 200 (OK)
        //If postid > 0,
        //if title and body parameters have been passed, use the passed parameter values as the initial title and body values and return with status code 200 (OK)
        //otherwise
        //if (username, postid) row exists in the database, retrieve the title and body from the database and return with status code 200 (OK).
        //otherwise, return with status code 404 (Not found).
        System.out.println("inside open_Handler");
        String username = request.getParameter("username");
        if(username==null) {
            System.out.println("there is no username");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String post_id_string = request.getParameter("postid");
        if(post_id_string == null) {
            System.out.println("there is no postid");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        //check if postid is integer
        int postid;
        if (post_id_string.matches("-?\\d+")){
            postid = Integer.parseInt(post_id_string);
        }
        else {
            System.out.println("postid is not integer");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        if(postid<=0){
            if(title!=null && body!=null){
                request.setAttribute("title",title);
                request.setAttribute("body",body);

            }
            else{
                if(title==null) request.setAttribute("title",""); else request.setAttribute("title",title);
                if(body==null) request.setAttribute("body",""); else request.setAttribute("body",body);
            }
            RequestDispatcher rD =  request.getRequestDispatcher("/edit.jsp");
            if(rD==null){
                System.out.println("cannot find edit.jsp");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            try{
                rD.forward(request,response);
            }
            catch (Exception e) {
                System.out.println("cannot forward the request and response to edit.jsp");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        

        }
        else{
            //postid>0
            if(title!=null && body!=null){
                request.setAttribute("title",title);
                request.setAttribute("body",body);
                RequestDispatcher rD =  request.getRequestDispatcher("/edit.jsp");
                if(rD==null){
                    System.out.println("cannot find edit.jsp");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                try{
                    rD.forward(request,response);
                }
                catch (Exception e) {
                    System.out.println("cannot forward the request and response to edit.jsp");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return ;
                }
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            else {
                //if (username, postid) row exists in the database, retrieve the title and body 
                //from the database and return with status code 200 (OK).
                //otherwise, return with status code 404 (Not found).
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    System.out.println("going into db");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS144", "cs144", "");
                    ps = conn.prepareStatement(
                        "SELECT * FROM Posts WHERE username = ? AND postid = ?"
                    );
                    ps.setString(1,username);
                    ps.setInt(2,postid);
                    rs = ps.executeQuery();
                    if(!rs.next()){
                        //no result
                        System.out.println("username, postid touple doesn't exit in db");
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return;

                    }
                    else{
                        //result
                        title = rs.getString("title");
                        body = rs.getString("body");
                        request.setAttribute("title",title);
                        request.setAttribute("body",body);
                        request.getRequestDispatcher("/edit.jsp").forward(request,response);
                        response.setStatus(HttpServletResponse.SC_OK);
                        return;                        
                    }

                } catch (Exception ex) {                    
                    System.err.println("SQLException: " + ex.getMessage());
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }finally {
                    System.out.println("int the finally block closing connections");
                    try { rs.close(); } catch (Exception e) {System.out.println("error when closing resultset rs"); }
                    try { ps.close(); } catch (Exception e) {System.out.println("error when closing prepareStatement ps"); }
                    try { conn.close(); } catch (Exception e) {System.out.println("error when closing connection conn");} 
                    
                }



            }



        }



    }
    public void save_Handler(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //required parameters: username, postid, title, and body
        //function: save the post into the database and go to the list page for the user
        //if postid <= 0
        //assign a new postid, and save the content as a new post
        //if postid > 0
        //if (username, postid) row exists in the database, update the row with new title, body, and modification date.
        //if (username, postid) row does not exist, do not make any change to the database

        System.out.println("inside save_Handler");
        
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String username = request.getParameter("username");
        String post_id_string = request.getParameter("postid");
        if(title==null||body==null||username==null||post_id_string==null) {
            System.out.println("lacks parameter ");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //check if postid is integer
        int postid;
        if (post_id_string.matches("-?\\d+")){
            postid = Integer.parseInt(post_id_string);
        }
        else {
            System.out.println("postid is not integer");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if(postid<=0){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                System.out.println("going into db");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS144", "cs144", "");
                ps = conn.prepareStatement(
                    "SELECT MAX(postid) AS previousId FROM Posts WHERE username = ? GROUP BY username"
                );
                ps.setString(1,username);
                rs = ps.executeQuery();
                //TODO DO I need to first close it                                    
                ps = conn.prepareStatement(
                    "INSERT INTO Posts VALUES(?, ?, ?,?,?,?)"); 
                ps.setString(1,username);
                ps.setString(3,title);
                ps.setString(4,body);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ps.setTimestamp(5,timestamp);
                ps.setTimestamp(6,timestamp);             
                if(!rs.next()){
                    //no result, set postid to 1 and save 
                    int newPostId = 1;
                    ps.setInt(2,newPostId);
                }
                else{
                    //result, save the content with id+1
                    int newPostId = rs.getInt("previousId")+1;
                    ps.setInt(2,newPostId);                 
                }
                ps.executeUpdate();
                response.setStatus(HttpServletResponse.SC_OK);


            }
            catch(Exception e){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                System.out.println("there is some problem when making a new record");
            }
            finally{
                System.out.println("int the finally block closing connections");
                try { rs.close(); } catch (Exception e) {System.out.println("error when closing resultset rs"); }
                try { ps.close(); } catch (Exception e) {System.out.println("error when closing prepareStatement ps"); }
                try { conn.close(); } catch (Exception e) {System.out.println("error when closing connection conn");} 
            }

        }
        else{
            //postid>0
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                System.out.println("going into db");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS144", "cs144", "");
                ps = conn.prepareStatement(
                    "SELECT * FROM Posts WHERE username = ? AND postid = ?"
                );
                ps.setString(1,username);
                ps.setInt(2,postid);
                rs = ps.executeQuery();
                if(!rs.next()){
                    //no result, no changes
                    System.out.println("username, postid touple doesn't exit in db");
                    response.setStatus(HttpServletResponse.SC_OK);
                    
                }
                else{
                    //result, update that record
                    ps = conn.prepareStatement(
                        "UPDATE Posts SET title = ?, body = ?, modified = ? WHERE username = ? AND postid = ?"
                        );
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    ps.setString(1,title);
                    ps.setString(2,body);
                    ps.setTimestamp(3,timestamp);
                    ps.setString(4,username);
                    ps.setInt(5,postid);
                    ps.executeUpdate();

                    response.setStatus(HttpServletResponse.SC_OK);
                                    
                }


            }
            catch(Exception e){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                System.out.println("there is some problem when making a new record");
            }
            finally{
                System.out.println("int the finally block closing connections");
                try { rs.close(); } catch (Exception e) {System.out.println("error when closing resultset rs"); }
                try { ps.close(); } catch (Exception e) {System.out.println("error when closing prepareStatement ps"); }
                try { conn.close(); } catch (Exception e) {System.out.println("error when closing connection conn");} 
                list_Handler(request,response);
            }
        }
    }
    public void delete_Handler(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //required parameters: username and postid
        //function: delete the corresponding post and go to the list page
        System.out.println("inside preview_Handler");        
        String username = request.getParameter("username");
        String post_id_string = request.getParameter("postid");
        if(username==null||post_id_string==null) {
            System.out.println("lacks parameter ");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //check if postid is integer
        int postid;
        if (post_id_string.matches("-?\\d+")){
            postid = Integer.parseInt(post_id_string);
        }
        else {
            System.out.println("postid is not integer");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            System.out.println("going into db");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS144", "cs144", "");
            ps = conn.prepareStatement(
                "DELETE FROM Posts WHERE username = ? AND postid = ?"
            );
            ps.setString(1,username);
            ps.setInt(2,postid);
            ps.executeUpdate();
        }
         catch(Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("there is some problem when deleting a new record");
        }
        finally{
            System.out.println("int the finally block closing connections");
            try { rs.close(); } catch (Exception e) {System.out.println("error when closing resultset rs"); }
            try { ps.close(); } catch (Exception e) {System.out.println("error when closing prepareStatement ps"); }
            try { conn.close(); } catch (Exception e) {System.out.println("error when closing connection conn");} 
            response.setStatus(HttpServletResponse.SC_OK);
            list_Handler(request,response);
        }
    }



    
    public void preview_Handler(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //required parameters: username, postid, title, and body
        //function: return the preview page with the html rendering of the given title and body    
        System.out.println("inside preview_Handler");
        
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String username = request.getParameter("username");
        String post_id_string = request.getParameter("postid");
        if(title==null||body==null||username==null||post_id_string==null) {
            System.out.println("lacks parameter ");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //check if postid is integer
        int postid;
        if (post_id_string.matches("-?\\d+")){
            postid = Integer.parseInt(post_id_string);
        }
        else {
            System.out.println("postid is not integer");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("title",title);
        request.setAttribute("body",body);
        RequestDispatcher rD =  request.getRequestDispatcher("/preview.jsp");
        if(rD==null){
            System.out.println("cannot find preview.jsp");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try{
            rD.forward(request,response);
        }
        catch (Exception e) {
            System.out.println("cannot forward the request and response to preview.jsp");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return;
    }
    public void  list_Handler(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //required parameters: username
        //function: return the list page for the user

        System.out.println("in the list_Handler");
        String username = request.getParameter("username");
        
        if(username ==  null){
            System.out.println("list_Handler has to have parameter usernmae ");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("username",username);
        RequestDispatcher rD =  request.getRequestDispatcher("/list.jsp");
        if(rD==null){
            System.out.println("cannot find list.jsp");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try{
            rD.forward(request,response);
        }
        catch (Exception e) {
            System.out.println("cannot forward the request and response to list.jsp");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //TODO actually get the list

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            System.out.println("going into db");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS144", "cs144", "");
            ps = conn.prepareStatement(
                "SELECT postid, title, modified, created FROM Posts WHERE username = ? SORT BY postid"
            );
            ps.setString(1,username);
            rs= ps.executeQuery();
            ArrayList<Integer> postids = new ArrayList();
            ArrayList<String> titles = new ArrayList();
            ArrayList<Timestamp> modifieds = new ArrayList();
            ArrayList<Timestamp> createds =  new ArrayList();
            while(rs.next()){
                postids.add(rs.getInt("postid"));
                titles.add(rs.getString("title"));
                modifieds.add(rs.getTimestamp("modified"));
                createds.add(rs.getTimestamp("created"));
            }
            request.setAttribute("postids",postids);
            request.setAttribute("titles",titles);
            request.setAttribute("modifieds",modifieds);
            request.setAttribute("createds",createds);



        }
         catch(Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("there is some problem when extracting the list of records");
        }
        finally{
            System.out.println("int the finally block closing connections");
            try { rs.close(); } catch (Exception e) {System.out.println("error when closing resultset rs"); }
            try { ps.close(); } catch (Exception e) {System.out.println("error when closing prepareStatement ps"); }
            try { conn.close(); } catch (Exception e) {System.out.println("error when closing connection conn");} 
            response.setStatus(HttpServletResponse.SC_OK);
        }




        response.setStatus(HttpServletResponse.SC_OK);
        return;

    }
        




}



