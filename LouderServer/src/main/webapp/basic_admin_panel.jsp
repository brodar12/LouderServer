<%@ page import = "java.util.ArrayList,java.util.Map,com.Databases.DownloadStatus,com.Model.PcHardware,com.DisplayContent.HardwarePagination,com.Databases.HardwareInfoCrud" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <link rel="stylesheet" href="styles/style.css">
    <title>Admin Panel</title>
</head>
<body>

    <%
    HardwarePagination.pagination_init();
    %>

   <%!
   DownloadStatus status_down= new DownloadStatus();
    HardwareInfoCrud db_operation= new HardwareInfoCrud();
    ArrayList<PcHardware> items;
    PcHardware items_hardware;
    int count_pag=0;
    String modal_access,modal_access_file,mac_address,filePath;
    %>

<div class="nav_bar" >
    <a class="active" href="#home">Admin Panel</a>
       <a class="logoutstyle" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=modalpathfile">Set File Path</a>
       <div class="logout_component">
       <a class="logoutstyle" href="#logout" >Logout</a>
       </div>
</div>


<div class="container">

<%
if(db_operation.get_data_is_empty()==0){
%>

<div class="row">
  <div class="emptycol" style="height:180px; width: 1024px;"></div>
</div>

<div class="row">
  <div class="col"></div>
    <div class="col" style="height: 60px; width: 400px;">
      <center>
        <div class="alert alert-danger" role="alert" style="height: 120px;width: 350px;size: 36px;font-size: 28px;">
          For this page data is not exists!!!
        </div>
      </center>
    </div>
  <div class="col"></div>
</div>


<%
}
else{
%>


<div class="row">
  <div class="emptycol" style="height:65px; width: 1024px;"></div>
</div>

<div class="row">
    <div class="emptycol" style="height:50px; width: 1024px;">
      <h2>Statistics</h2>
       <button class="btn btn-success" onclick="location.href='http://localhost:8080/LouderServer/serv/StatusServlet?status=sendforallfile&macaddress=null';" style="margin-left: 79%;margin-top: -6%;"  >Send All File</button>
       <button class="btn btn-danger" onclick="location.href='http://localhost:8080/LouderServer/serv/StatusServlet?status=clearall&macaddress=null';" style="margin-left: 91%;margin-top: -11%;"  >Clear All</button>
    </div>
</div>


<div class="row">

        <div class="tableinfo" style="width: 1024px;">

          <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Date</th>
                  <th scope="col">IP</th>
                  <th scope="col">Download status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>


              <%

              if(HardwarePagination.get_curent_page()!=1){
              items=db_operation.get_interval_items_hardware_network((HardwarePagination.get_curent_page()*20)-20);
               count_pag=(HardwarePagination.get_curent_page()*20)-20;
              }else{
               items=db_operation.get_interval_items_hardware_network(HardwarePagination.get_curent_page());
                count_pag=HardwarePagination.get_curent_page();
              }


              for(int i=0;i<items.size();++i){
              %>
              <tbody>
                <tr>
                  <th scope="row"><%= count_pag %></th>
                  <td><%= items.get(i).getDate_time()%></td>
                  <td>
                    <a href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=modal&macaddress=<%=items.get(i).getMacaddress()%>" ><%= items.get(i).getIp()%></a>
                  </td>
                  <td>
                        <%
                            if(status_down.get_download_status(items.get(i).getMacaddress())==true){
                        %>
                             <h4 style="color:red;">Downloaded</h4>
                        <%
                        }
                        else {
                        %>
                              <h4 style="color:green;">Waiting</h4>
                        <%
                        }
                        %>
                  </td>
                  <td>
                     <!-- href="LinkURL" target="_blank">
                         <div class="form-check">
                           <input type="checkbox" class="form-check-input" name="status" value="<%=items.get(i).getMacaddress()%>" >
                           <label class="form-check-label" for="exampleCheck1">Install file</label>
                         </div>-->
                         <%
                         if(status_down.get_download_status(items.get(i).getMacaddress())==false){
                         %>
                         <button type="button" class="btn btn-primary" style="width:115px; margin-left: -20px;"  onclick="location.href='http://localhost:8080/LouderServer/serv/StatusServlet?status=decline&macaddress=<%=items.get(i).getMacaddress()%>';" >Install file</button>
                          <%
                          }else{
                          %>
                         <button type="button" class="btn btn-danger" style="width:140px; margin-left: -20px;"   >File is installed</button>
                           <%
                           }
                           %>
                  </td>
                </tr>
              </tbody>
              <%
              ++count_pag;
              }
              %>

        </table>

        </div>
</div>



<div class="row">
<div class="col"></div>
<div class="col">
        <div class="paginationinfo" style="height: 60px; width: 400px;">
                 <nav aria-label="Page navigation example">
                   <ul class="pagination">
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=downloadstat&action=prev&curent_page=empty">Previous</a></li>
                     <% for(int i=HardwarePagination.get_previous_page();i<=HardwarePagination.get_next_page();++i){
                         if(i==HardwarePagination.get_curent_page()){
                     %>
                    <li class="page-item"><a class="page-link" style="background-color:#4caf5078;" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=downloadstat&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                    <% }else{
                    %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=downloadstat&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                     <%}
                     }
                     %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=downloadstat&action=next&curent_page=empty">Next</a></li>
                   </ul>
                 </nav>
               </div>
  </div>
  <div class="col"></div>
</div>

<%
}
%>

</div>


<div class="modal fade" id="FilePath" tabindex="-1" role="dialog" aria-labelledby="FilePathLabel" aria-hidden="true" style="padding-top: 170px;" >
  <div class="modal-dialog" role="document" >
    <div class="modal-content" style="width: auto;height:auto;">
      <div class="modal-header" style="background-color:#212529;color: #fff;">
        <h3 class="modal-title" id="FilePathLabel">File path</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color: #fff;">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <center>

        <div class="container">
         <div class="row">
             <div class="col">
                <H4>Mounted file:<%=status_down.get_file_name()%></H4>
             </div>
         </div>
        <div class="row">
          <div class="col">
                   <form method="POST" action="http://localhost:8080/LouderServer/serv/StatusServlet?macaddres=&ipaddres='">
                     <div class="row">
                       <div class="col">
                         <input type="text" name="filePath" style="width:100%; margin-top:20px;" class="form-control"
                         <%
                         if(status_down.get_file_path()!=null){
                         %>
                         value="<%=status_down.get_file_path()%>"
                         <%
                         }
                         else{
                         %>
                           placeholder="Path to file"
                           <%
                           }
                           %>
                         >
                       </div>
                     </div>
                      <div class="row">
                        <div class="col">
                        <button type="submit" style="width:150px; margin-top: 25px;" class="btn btn-primary">Set path</button>
                        </div>
                      </div>
                   </form>
          </div>
        </div>
      </div>

  </center>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="ModalHardware" tabindex="-1" role="dialog" aria-labelledby="ModalHardwareLabel" aria-hidden="true" style="padding-top: 170px;" >
  <div class="modal-dialog" role="document" >
    <div class="modal-content" style="width: auto;height:auto;">
      <div class="modal-header" style="background-color:#212529;color: #fff;">
        <h3 class="modal-title" id="ModalHardwareLabel">Hardware info</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color: #fff;">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <center>
        <%
          items_hardware=new PcHardware();
          mac_address=request.getParameter("mac");
          items_hardware=db_operation.get_all_items_hardware_inet(mac_address);
        %>

        <div class="container">
        <div class="row">
          <div class="col">
            <div class="inlines"><h3>IP:</h3></div>
            <div class="inlines"><p><%=items_hardware.getIp()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>MAC:</h3></div>
              <div class="inlines"><p><%=items_hardware.getMacaddress()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>CPU:</h3></div>
              <div class="inlines"><p><%=items_hardware.getCpu_type()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>GPU:</h3></div>
              <div class="inlines"><p><%=items_hardware.getGpu_type()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>RAM:</h3></div>
              <div class="inlines"><p><%=items_hardware.getPhysicalmemory()%></p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>OStype:</h3></div>
              <div class="inlines"><p><%=items_hardware.getOstype()%></p></div>
          </div>
        </div>

      </div>

  </center>
      </div>
    </div>
  </div>
</div>



<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="bootstrap/js/bootstrap.min.js" ></script>

<%
modal_access=request.getParameter("modalaces");
if(modal_access!=null && modal_access.equals("true") ){
%>

<script type="text/javascript" >
$('#ModalHardware').appendTo("body").modal('show');
</script>

<%
}
%>


<%
modal_access_file=request.getParameter("modalaces1");
if(modal_access_file!=null && modal_access_file.equals("true") ){
%>

<script type="text/javascript" >
$('#FilePath').appendTo("body").modal('show');
</script>

<%
}
%>

  </body>
</html>
