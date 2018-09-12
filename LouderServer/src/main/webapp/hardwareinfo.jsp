<%@ page import = "java.util.ArrayList,java.util.Map,com.Databases.HardwareInfoCrud,com.Model.PcHardware,com.DisplayContent.HardwarePagination" %>
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
      HardwareInfoCrud db_operation= new HardwareInfoCrud();
      ArrayList<PcHardware> items;
      int count_pag=0;
     %>


    <div class="nav_bar" >
      <a class="active" href="#home">Admin Panel</a>
        <a href="http://localhost:8080/LouderServer/walletstatistics.jsp">wallet</a>
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
    <div class="emptycol" style="height:80px; width: 1024px;">
      <h2>Statistics</h2>
    </div>
</div>


<div class="row">

        <div class="tableinfo" style="width: 1024px;">

          <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">CPU</th>
                  <th scope="col">GPU</th>
                  <th scope="col">TotalPhysicalMemory</th>
                  <th scope="col">OStype</th>
                </tr>
              </thead>




              <%

              if(HardwarePagination.get_curent_page()!=1){
              items=db_operation.get_interval_items((HardwarePagination.get_curent_page()*20)-20);
               count_pag=(HardwarePagination.get_curent_page()*20)-20;
              }else{
               items=db_operation.get_interval_items(HardwarePagination.get_curent_page());
                count_pag=HardwarePagination.get_curent_page();
              }


              for(int i=0;i<items.size();++i){
              %>
              <tbody>
                <tr>
                  <th scope="row"><%= count_pag %></th>
                  <td><%= items.get(i).getCpu_type() %></td>
                  <td><%= items.get(i).getGpu_type() %></td>
                  <td><%= items.get(i).getPhysicalmemory() %></td>
                  <td><%= items.get(i).getOstype() %></td>
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
  <div class="emptycol" style="height:45px; width: 1024px;"></div>
</div>


    <div class="row">
    <div class="col"></div>
    <div class="col">
            <div class="col">
              <div class="paginationinfo" style="height: 60px; width: 400px;">
                    <nav aria-label="Page navigation example">
                      <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=hardware&action=prev&curent_page=empty">Previous</a></li>
                        <% for(int i=HardwarePagination.get_previous_page();i<=HardwarePagination.get_next_page();++i){
                            if(i==HardwarePagination.get_curent_page()){
                        %>
                       <li class="page-item"><a class="page-link" style="background-color:#4caf5078;" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=hardware&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                       <% }else{
                       %>
                        <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=hardware&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                        <%}
                        }
                        %>
                        <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=hardware&action=next&curent_page=empty">Next</a></li>
                      </ul>
                    </nav>
                  </div>
              </div>
      </div>
      <div class="col"></div>
      </div>

<%
}
%>

</div>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="bootstrap/js/bootstrap.min.js" ></script>
  </body>
</html>
