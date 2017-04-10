/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cse.cse564.CRUD;

/**
 *
 * @author acheruvu
 */
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ClientApp {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD_server/webresources/";
    
    public ClientApp() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("/");
    }
    
    /**
     * This method is used to create students
     */
    
    public ClientResponse createStudent(String studentid, String studentname)
    {
        Form params=new Form();
        params.add("StudentID",studentid);
        params.add("StudentName",studentname);
        return webResource.path("gradebook/student").post(ClientResponse.class, params);
        
    }
    /**
     * This method is used to create gradeItem
     * @param gid, percentage
     */
    
    public ClientResponse createGradeItem(String gradeid, String per)
    {
        Form params=new Form();
        params.add("GradeID", gradeid);
        params.add("Percentage",per);
        return webResource.path("gradebook/gradeitem").post(ClientResponse.class, params);
      
    }
    
    /**
     * 
     * This method is used to get student details based on given id
     * @param stuentid
     * return response
     */
    
    public ClientResponse getStudentDetails(String studentid)
    {
        return webResource.path("gradebook/student").path(studentid).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    
    /**
     * 
     * This method is used to get grade details based on given id
     * @param stuentid
     * return response
     */
    
     public ClientResponse getGradeDetails(String gradeid)
    {
        return webResource.path("gradebook/gradeitem").path(gradeid).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    
    
    /**
     * This method gives the details of gradebook
     * @param args 
     */
    
    public ClientResponse getGradebookdetails()
    {
         return webResource.path("gradebook").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    
    /**
     * This method helps in updating gradeitem by giving gradeid, feedback, grade ,studentid for a given student
     * @param sid,gid,grade,percentage
     */
    
    public ClientResponse updateGradeforParticularStudent( String sid, String gid, String grade, String feedback)
    {
         Form params=new Form();
         params.add("StudentID",sid);
         params.add("GradeID", gid);
         params.add("Grade", grade);
         params.add("Feedback",feedback);
     
         return webResource.path("gradebook/student").put(ClientResponse.class, params);
    }
    
    
    /**
     * This method is to delete student based on given sid
     * @param sid
     */
    
    public ClientResponse deleteStudent(String sid)
    {
        System.out.println("Deleting student");
        Form params=new Form();
        params.add("StudentID", sid);
        return webResource.path("gradebook/student").path(sid).delete(ClientResponse.class);

        
    }
    
    /**
     * This method is to delete gradeitem on given gid
     * @param gid
     */
    
    public ClientResponse deleteGradeItemforallStudents(String gid)
    {
        Form params=new Form();
        params.add("GradeID", gid);
        return webResource.path("gradebook/gradeitem").path(gid).delete(ClientResponse.class);  
    }
    
    /**
     * This method is used to delete grade for particular student
     * @param sid,gid
     */
    
    public ClientResponse deleteGradeforParticularStudent(String sid,String gid)
    {
        System.out.println("In client App -delete grade for particular student");
        Form params=new Form();
        params.add("StudentID",sid);
        params.add("GradeID",gid);
        return webResource.path("gradebook").path(sid).path(gid).delete(ClientResponse.class);
        
    }
 /**   public static void main(String[] args)
    {
        ClientApp obj=new ClientApp();
       ClientResponse r= obj.createStudent("1", "aa");
       System.out.println(r.toString());
       
       r= obj.createStudent("2", "bb");
       System.out.println(r.toString());
       r= obj.createStudent("3", "cc");
       System.out.println(r.toString());
       r= obj.createStudent("4", "dd");
       System.out.println(r.toString());
       
       r=obj.createGradeItem("quiz", "1");
       System.out.println(r.toString());
       
       r=obj.createGradeItem("midterm", "30");
       System.out.println(r.toString());
       
       r=obj.getStudentDetails("1");
       System.out.println(r.toString());
       
       
       r=obj.getGradebookdetails();
       System.out.println(r.toString());
       
       r=obj.updateGradeforParticularStudent("1", "midterm", "A", "good job");
       System.out.println(r.toString());
       r=obj.getGradebookdetails();
       System.out.println(r.toString());
       
       r=obj.deleteStudent("1");
       System.out.println(r.toString());
       r=obj.getGradebookdetails();
       System.out.println(r.toString());
       
       
       
       
       r=obj.deleteGradeforParticularStudent("3", "quiz");
       System.out.println(r.toString());
       r=obj.getGradebookdetails();
       System.out.println(r.toString());
       
        r=obj.deleteGradeforParticularStudent("4", "midterm");
       System.out.println(r.toString());
       r=obj.getGradebookdetails();
       System.out.println(r.toString());
       
       
       
       
    }**/
    
}
